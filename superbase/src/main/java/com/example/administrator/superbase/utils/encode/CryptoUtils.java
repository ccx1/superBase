package com.example.administrator.superbase.utils.encode;

import com.example.administrator.superbase.utils.common.LogUtil;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 15:15
 */

public class CryptoUtils {
    Cipher cipher   = null;
    Cipher decipher = null;
    public static final int ALGORIGHM_DES = 0;
    public static final int ALGORIGHM_AES = 1;
    byte[] keyBytes = new byte[]{74, 111, 104, 110, 115, 111, 110, 77, 97, 74, 105, 70, 97, 110, 103, 74, 101, 114, 118, 105, 115, 76, 105, 117, 76, 105, 117, 83, 104, 97, 111, 90};
    String key = "TongliforniaJohnson";
    static final String HEXES = "0123456789ABCDEF";

    public CryptoUtils() {
    }

    public void init(int var1) {
        if(var1 == 0) {
            this.initDES();
        } else {
            this.initAES();
        }

    }

    public void initDES() {
        try {
            MessageDigest var1 = MessageDigest.getInstance("md5");
            byte[] var2 = var1.digest(this.key.getBytes("utf-8"));
            this.keyBytes = Arrays.copyOf(var2, 24);
            int var3 = 0;

            for(int var4 = 16; var3 < 8; this.keyBytes[var4++] = this.keyBytes[var3++]) {
                ;
            }

            SecretKeySpec var6 = new SecretKeySpec(this.keyBytes, "DESede");
            IvParameterSpec var7 = new IvParameterSpec(new byte[8]);
            this.cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            this.cipher.init(1, var6, var7);
            this.decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            this.decipher.init(2, var6, var7);
            LogUtil.d("encrypt", "initital for DES");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void initAES() {
        try {
            this.cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec var1 = new SecretKeySpec(this.keyBytes, "AES");
            this.cipher.init(1, var1);
            this.decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            this.decipher.init(2, var1);
            LogUtil.d("encrypt", "initital for AES");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public String encryptBase64String(String var1) throws Exception {
        byte[] var2 = this.encrypt(var1);
        return new String(android.util.Base64.encode(var2, 0));
    }

    public String decryptBase64String(String var1) throws Exception {
        byte[] var2 = android.util.Base64.decode(var1, 0);
        byte[] var3 = this.decrypt(var2);
        return new String(var3, "UTF-8");
    }

    public byte[] encrypt(String var1) throws Exception {
        byte[] var2 = var1.getBytes("UTF-8");
        byte[] var3 = this.cipher.doFinal(var2);
        return var3;
    }

    public byte[] encrypt(byte[] var1) throws Exception {
        return this.cipher.doFinal(var1);
    }

    public byte[] decrypt(byte[] var1) throws Exception {
        return this.decipher.doFinal(var1);
    }

    public static String getHex(byte[] var0) {
        if(var0 == null) {
            return null;
        } else {
            StringBuilder var1 = new StringBuilder(2 * var0.length);
            byte[] var2 = var0;
            int var3 = var0.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                byte var5 = var2[var4];
                var1.append("0123456789ABCDEF".charAt((var5 & 240) >> 4)).append("0123456789ABCDEF".charAt(var5 & 15));
            }

            return var1.toString();
        }
    }

    public static byte[] fromHexString(String var0) {
        int var1 = var0.length();
        byte[] var2 = new byte[var1 / 2];

        for(int var3 = 0; var3 < var1; var3 += 2) {
            var2[var3 / 2] = (byte)((Character.digit(var0.charAt(var3), 16) << 4) + Character.digit(var0.charAt(var3 + 1), 16));
        }

        return var2;
    }
}
