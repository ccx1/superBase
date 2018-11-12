package com.example.administrator.superbase.utils.encode;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class DSAUtils
{
  public static final String ALGORITHM = "DSA";
  private static final int KS1024 = 1024;
  private static final byte[] SEED = { 84, 104, 105, 115, 39, 115, 32, 115, 101, 101, 100, 32, 102, 111, 114, 32, 103, 101, 110, 101, 114, 97, 116, 105, 110, 103, 32, 51, 54, 48, 79, 83, 47, 68, 83, 65, 32, 107, 101, 121, 32, 112, 97, 105, 114, 32, 111, 110, 32, 50, 48, 49, 54, 47, 48, 56, 47, 49, 49 };
  
  public static KeyPair generate()
    throws NoSuchAlgorithmException
  {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
    SecureRandom random = new SecureRandom();
    random.setSeed(SEED);
    keyPairGenerator.initialize(1024, random);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    return keyPair;
  }
  
  public static String getKeyString(byte[] keyEncoded)
    throws UnsupportedEncodingException
  {
    byte[] keyBase64 = Base64.encode(keyEncoded, 0);
    return new String(keyBase64, "UTF-8");
  }
  
  public static byte[] getKeyEncoded(String keyBase64)
    throws UnsupportedEncodingException
  {
    byte[] keyEncoded = Base64.decode(keyBase64.getBytes("UTF-8"), 0);
    return keyEncoded;
  }
  
  public static PrivateKey getPrivateKey(byte[] privateKeyEncoded)
    throws InvalidKeySpecException, NoSuchAlgorithmException
  {
    PKCS8EncodedKeySpec pKCS8 = new PKCS8EncodedKeySpec(privateKeyEncoded);
    KeyFactory keyFactory = KeyFactory.getInstance("DSA");
    return keyFactory.generatePrivate(pKCS8);
  }
  
  public static PublicKey getPublicKey(byte[] publicKeyEncoded)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    X509EncodedKeySpec x509 = new X509EncodedKeySpec(publicKeyEncoded);
    KeyFactory keyFactory = KeyFactory.getInstance("DSA");
    return keyFactory.generatePublic(x509);
  }
  
  public static byte[] sign(byte[] plain, PrivateKey privateKey)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
  {
    Signature signer = Signature.getInstance("DSA");
    signer.initSign(privateKey);
    signer.update(plain);
    return signer.sign();
  }
  
  public static byte[] sign(byte[] plain, byte[] privateKeyEncoded)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException
  {
    return sign(plain, getPrivateKey(privateKeyEncoded));
  }
  
  public static byte[] sign(byte[] plain, String privateKeyBase64)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, UnsupportedEncodingException
  {
    return sign(plain, getPrivateKey(getKeyEncoded(privateKeyBase64)));
  }
  
  public static boolean verify(byte[] plain, byte[] sign, PublicKey publicKey)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException
  {
    Signature signer = Signature.getInstance("DSA");
    signer.initVerify(publicKey);
    signer.update(plain);
    return signer.verify(sign);
  }
  
  public static boolean verify(byte[] plain, byte[] sign, byte[] publicKeyEncoded)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException
  {
    return verify(plain, sign, getPublicKey(publicKeyEncoded));
  }
  
  public static boolean verify(byte[] plain, byte[] sign, String publicKeyBase64)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, UnsupportedEncodingException
  {
    return verify(plain, sign, getPublicKey(getKeyEncoded(publicKeyBase64)));
  }
}

