package com.example.administrator.viewutilslist.utils.encode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 12:21
 */

public class MapUtil {
    public MapUtil() {
    }

    public static String objToStr(Object object, String... excludedKeys) {
        return mapToStr(objToMap(object, excludedKeys), excludedKeys);
    }

    public static String mapToStr(Map<String, String> params, String... excludedKeys) {
        if(params == null) {
            return null;
        } else {
            String[] var2 = excludedKeys;
            int var3 = excludedKeys.length;

            int i;
            String key;
            for(i = 0; i < var3; ++i) {
                key = var2[i];
                if(params.containsKey(key)) {
                    params.remove(key);
                }
            }

            StringBuffer content = new StringBuffer();
            List<String> keys = new ArrayList(params.keySet());
            Collections.sort(keys);

            for(i = 0; i < keys.size(); ++i) {
                key = (String)keys.get(i);
                String value = (String)params.get(key);
                content.append((i == 0?"":"&") + key + "=" + value);
            }

            return content.toString();
        }
    }

    public static Map<String, String> objToMap(Object object, String... excludedKeys) {
        TreeMap map = new TreeMap();

        int i;
        String excludedKey;
        try {
            Class<?> c = Class.forName(object.getClass().getName());
            Method[] m = c.getMethods();

            for(i = 0; i < m.length; ++i) {
                excludedKey = m[i].getName();
                if(excludedKey.startsWith("get")) {
                    Object value = m[i].invoke(object, new Object[0]);
                    if(value != null && (value instanceof String || value instanceof Boolean || value instanceof Long || value instanceof Integer)) {
                        String key = excludedKey.substring(3);
                        key = key.substring(0, 1).toLowerCase() + key.substring(1);
                        map.put(key, String.valueOf(value));
                    }
                }
            }
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }

        String[] var10 = excludedKeys;
        int var11 = excludedKeys.length;

        for(i = 0; i < var11; ++i) {
            excludedKey = var10[i];
            if(map.containsKey(excludedKey)) {
                map.remove(excludedKey);
            }
        }

        return map;
    }
}
