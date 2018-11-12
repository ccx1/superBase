package com.example.administrator.superbase.utils.encode;

import java.security.MessageDigest;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 12:12
 */

public class AESUtil {
    private static final byte[] AES_IV = initIv();

    public AESUtil() {
    }

    public static String encrypt(String originalText, String encryptionKey, String charset) {
        try {
            byte[]        md          = HexUtil.decodeHex(encryptionKey.toCharArray());
            SecretKeySpec key         = new SecretKeySpec(md, "AES");
            Cipher        cipher      = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[]        byteContent = null;
            if (charset == null && "".equals(charset)) {
                byteContent = originalText.getBytes();
            } else {
                byteContent = originalText.getBytes(charset);
            }

            IvParameterSpec iv = new IvParameterSpec(AES_IV);
            cipher.init(1, key, iv);
            byte[] result = cipher.doFinal(byteContent);
            return HexUtil.encodeHexString(result);
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    public static String decrypt(String ciphertext, String decryptionKey, String charset) {
        try {
            byte[]          md          = HexUtil.decodeHex(decryptionKey.toCharArray());
            SecretKeySpec   key         = new SecretKeySpec(md, "AES");
            Cipher          cipher      = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[]          decoderBtye = HexUtil.decodeHex(ciphertext.toCharArray());
            IvParameterSpec iv          = new IvParameterSpec(AES_IV);
            cipher.init(2, key, iv);
            byte[] result = cipher.doFinal(decoderBtye);
            return new String(result, charset);
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    private static byte[] initIv() {
        int    blockSize = 16;
        byte[] iv        = new byte[blockSize];

        for (int i = 0; i < blockSize; ++i) {
            iv[i] = 0;
        }

        return iv;
    }

    private static final byte[] IV = {48, 49, 48, 50, 48, 51, 48, 52, 48, 53, 48, 54, 48, 55, 48, 56};

    public static byte[] encrypt(byte[] plain, String key)
            throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[]        kb = md.digest(key.getBytes("UTF-8"));

        IvParameterSpec iv   = new IvParameterSpec(IV);
        SecretKeySpec   spec = new SecretKeySpec(kb, "AES");

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(1, spec, iv);

        return cipher.doFinal(plain);
    }

    public static byte[] decrypt(byte[] cipherText, String key)
            throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[]        kb = md.digest(key.getBytes("UTF-8"));

        IvParameterSpec iv   = new IvParameterSpec(IV);
        SecretKeySpec   spec = new SecretKeySpec(kb, "AES");

        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(2, spec, iv);

        return cipher.doFinal(cipherText);
    }

    public static String byte2Hex(byte[] b) {
        StringBuffer hexs = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            hexs.append(Integer.toHexString(b[i] >>> 4 & 0xF));
            hexs.append(Integer.toHexString(b[i] & 0xF));
        }
        return hexs.toString().toLowerCase(Locale.getDefault());
    }

    public static byte[] hex2Byte(String hexs) {
        char[] c   = hexs.toLowerCase(Locale.getDefault()).toCharArray();
        int    len = c.length / 2;
        byte[] b   = new byte[len];
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            b[i] = ((byte) (hex2Int(c[pos]) << 4 | hex2Int(c[(pos + 1)])));
        }
        return b;
    }

    private static int hex2Int(char c) {
        if (('0' <= c) && (c <= '9')) {
            return c - '0';
        }
        if (('A' <= c) && (c <= 'F')) {
            return c - 'A' + 10;
        }
        if (('a' <= c) && (c <= 'f')) {
            return c - 'a' + 10;
        }
        return 0;
    }
}
