package com.example.administrator.viewutilslist.utils.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 12:36
 */

public class SHA1Util {
    private SHA1Util() {
    }

    public static byte[] sha1(byte[] var0) {
        try {
            MessageDigest var1 = MessageDigest.getInstance("SHA-1");
            return var1.digest(var0);
        } catch (NoSuchAlgorithmException var3) {
            throw new RuntimeException(var3);
        }
    }
}
