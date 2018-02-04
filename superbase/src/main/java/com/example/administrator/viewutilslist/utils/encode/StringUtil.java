package com.example.administrator.viewutilslist.utils.encode;

import com.example.administrator.viewutilslist.utils.CharSequenceUtils;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 12:23
 */

public class StringUtil {
    private StringUtil() {
    }

    public static boolean isEmpty(String value) {
        int strLen;
        if(value != null && (strLen = value.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNumeric(Object obj) {
        if(obj == null) {
            return false;
        } else {
            char[] chars = obj.toString().toCharArray();
            int length = chars.length;
            if(length < 1) {
                return false;
            } else {
                int i = 0;
                if(length > 1 && chars[0] == 45) {
                    i = 1;
                }

                while(i < length) {
                    if(!Character.isDigit(chars[i])) {
                        return false;
                    }

                    ++i;
                }

                return true;
            }
        }
    }

    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if(values != null && values.length != 0) {
            String[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                result &= !isEmpty(value);
            }
        } else {
            result = false;
        }

        return result;
    }

    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if(!isEmpty(unicode)) {
            for(int i = 0; i < unicode.length(); ++i) {
                out.append(unicode.charAt(i));
            }
        }

        return out.toString();
    }

    public static String stripNonValidXMLCharacters(String input) {
        if(input != null && !"".equals(input)) {
            StringBuilder out = new StringBuilder();

            for(int i = 0; i < input.length(); ++i) {
                char current = input.charAt(i);
                if(current == 9 || current == 10 || current == 13 || current >= 32 && current <= '\ud7ff' || current >= '\ue000' && current <= '�' || current >= 65536 && current <= 1114111) {
                    out.append(current);
                }
            }

            return out.toString();
        } else {
            return "";
        }
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if(cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean startsWith(CharSequence str, CharSequence prefix) {
        return startsWith(str, prefix, false);
    }
    private static boolean startsWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
        return str != null && prefix != null?(prefix.length() > str.length()?false: CharSequenceUtils.regionMatches(str, ignoreCase, 0, prefix, 0, prefix.length())):str == null && prefix == null;
    }
}
