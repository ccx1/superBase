package com.example.administrator.superbase.utils.encode;

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
            byte[] md = HexUtil.decodeHex(encryptionKey.toCharArray());
            SecretKeySpec key = new SecretKeySpec(md, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] byteContent = null;
            if(charset == null && "".equals(charset)) {
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
            byte[] md = HexUtil.decodeHex(decryptionKey.toCharArray());
            SecretKeySpec key = new SecretKeySpec(md, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] decoderBtye = HexUtil.decodeHex(ciphertext.toCharArray());
            IvParameterSpec iv = new IvParameterSpec(AES_IV);
            cipher.init(2, key, iv);
            byte[] result = cipher.doFinal(decoderBtye);
            return new String(result, charset);
        } catch (Exception var9) {
            throw new RuntimeException(var9);
        }
    }

    private static byte[] initIv() {
        int blockSize = 16;
        byte[] iv = new byte[blockSize];

        for(int i = 0; i < blockSize; ++i) {
            iv[i] = 0;
        }

        return iv;
    }
}
