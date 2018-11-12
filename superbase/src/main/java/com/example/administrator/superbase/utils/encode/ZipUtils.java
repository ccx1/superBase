package com.example.administrator.superbase.utils.encode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtils {
    private static final String TAG = "ZipUtils";

    public static byte[] gz(byte[] plain)
            throws Exception {
        ByteArrayInputStream  input    = null;
        ByteArrayOutputStream output   = null;
        GZIPOutputStream      gzOutput = null;
        try {
            input = new ByteArrayInputStream(plain);
            output = new ByteArrayOutputStream();
            gzOutput = new GZIPOutputStream(output);
            gzOutput.write(plain);
            gzOutput.close();
            return output.toByteArray();
        } catch (IOException e) {
            throw new Exception("compress(gzip) failed(IOException)", e);
        } catch (NullPointerException e) {
            throw new Exception("compress(gzip) failed(NullPointerException)", e);
        } finally {
            try {
                if (null != gzOutput) {
                    gzOutput.close();
                }
            } catch (Exception e) {
                LOG.e("ZipUtils", "close gzip output stream failed(Exception): " + e
                        .getMessage());
            }
            try {
                if (null != input) {
                    input.close();
                }
            } catch (Exception e) {
                LOG.e("ZipUtils", "close byte array input stream failed(Exception): " + e
                        .getMessage());
            }
            try {
                if (null != output) {
                    output.close();
                }
            } catch (Exception e) {
                LOG.e("ZipUtils", "close byte array output stream failed(Exception): " + e
                        .getMessage());
            }
        }
    }

    public static byte[] ungz(byte[] cipher)
            throws Exception {
        try {
            return ungz0(cipher);
        } catch (Exception e) {
            return doWhileUNGZFailed(e, cipher);
        }
    }

    private static byte[] doWhileUNGZFailed(Exception e, byte[] cipher)
            throws Exception {
        try {
            if (((e instanceof IOException)) && (TextUtils.contains(e.getMessage(), "unknown format")) &&
                    (null != cipher) && (cipher.length > 0)) {
                LOG.i("ZipUtils", "UNGZ failed, maybe NON-GZ plain: " + new String(cipher, "UTF-8"));
                return cipher;
            }
        } catch (Exception localException) {
        }
        throw e;
    }

    private static byte[] ungz0(byte[] cipher)
            throws Exception {
        ByteArrayInputStream  input   = null;
        ByteArrayOutputStream output  = null;
        GZIPInputStream       gzInput = null;
        int                   length  = 0;
        byte[]                buffer  = new byte[1024];
        try {
            output = new ByteArrayOutputStream();
            input = new ByteArrayInputStream(cipher);
            gzInput = new GZIPInputStream(input);
            while ((length = gzInput.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            return output.toByteArray();
        } finally {
            try {
                if (null != gzInput) {
                    gzInput.close();
                }
            } catch (Exception e) {
                LOG.e("ZipUtils", "close gzip input stream failed(Exception): " + e
                        .getMessage());
            }
            try {
                if (null != input) {
                    input.close();
                }
            } catch (Exception e) {
                LOG.e("ZipUtils", "close byte array input stream failed(Exception): " + e
                        .getMessage());
            }
            try {
                if (null != output) {
                    output.close();
                }
            } catch (Exception e) {
                LOG.e("ZipUtils", "close byte array output stream failed(Exception): " + e
                        .getMessage());
            }
        }
    }
}
