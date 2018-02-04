package com.example.administrator.superbase.utils.encode;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 12:22
 */

public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final int KEY_SIZE = 2048;
    public static final String PUBLIC_KEY = "PUBLIC_KEY";
    public static final String PRIVATE_KEY = "PRIVATE_KEY";

    public RSAUtil() {
    }

    public static String sign(String sourceStr, String privateKeyStr, String charset) {
        try {
            byte[] sourceData = null;
            if(charset != null && !"".equals(charset)) {
                sourceData = sourceStr.getBytes(charset);
            } else {
                sourceData = sourceStr.getBytes();
            }

            byte[] privateKey = HexUtil.decodeHex(privateKeyStr.toCharArray());
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(priKey);
            signature.update(sourceData);
            byte[] result = signature.sign();
            return HexUtil.encodeHexString(result);
        } catch (Exception var10) {
            throw new RuntimeException(var10);
        }
    }

    public static boolean verify(String sourceStr, String sign, String publicKey, String charset) {
        try {
            byte[] sourceData = null;
            if(charset != null && !"".equals(charset)) {
                sourceData = sourceStr.getBytes(charset);
            } else {
                sourceData = sourceStr.getBytes();
            }

            byte[] publicKeyBytes = HexUtil.decodeHex(publicKey.toCharArray());
            byte[] signBytes = HexUtil.decodeHex(sign.toCharArray());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(pubKey);
            signature.update(sourceData);
            return signature.verify(signBytes);
        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }
    }

    public static Map<String, String> generateKeyPair() {
        HashMap keyMap = new HashMap();

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            keyMap.put("PUBLIC_KEY", HexUtil.encodeHexString(keyPair.getPublic().getEncoded()));
            keyMap.put("PRIVATE_KEY", HexUtil.encodeHexString(keyPair.getPrivate().getEncoded()));
            return keyMap;
        } catch (Throwable var3) {
            return keyMap;
        }
    }
}
