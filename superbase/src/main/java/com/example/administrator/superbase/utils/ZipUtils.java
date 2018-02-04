package com.example.administrator.superbase.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 15:11
 */

public class ZipUtils {
    private static final int BUFF_SIZE = 1048576;

    public ZipUtils() {
    }

    public static void zip(File var0, File var1) throws IOException {
        if(var0.exists()) {
            ZipOutputStream var2 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(var1), 1048576));
            zipFiles(var0, var2, "");
            var2.flush();
            var2.close();
        }
    }

    static void zipFiles(File var0, ZipOutputStream var1, String var2) throws IOException {
        if(var0.exists()) {
            if(var0.isDirectory()) {
                File[] var3 = var0.listFiles();
                if(var3 != null) {
                    File[] var4 = var3;
                    int var5 = var3.length;

                    for(int var6 = 0; var6 < var5; ++var6) {
                        File var7 = var4[var6];
                        zipFiles(var7, var1, var2 + "\\" + var7.getName());
                    }
                }
            } else {
                zipFile(var0, var1, var2);
            }

        }
    }

    static void zipFile(File var0, ZipOutputStream var1, String var2) throws IOException {
        byte[] var3 = new byte[1048576];
        BufferedInputStream var4 = new BufferedInputStream(new FileInputStream(var0), 1048576);
        String var5 = null;
        if("".equals(var2)) {
            var5 = var0.getName();
        } else {
            (new StringBuilder()).append(var2).append("\\").append(var0.getName()).toString();
        }

        var1.putNextEntry(new ZipEntry(var2));

        int var6;
        while((var6 = var4.read(var3)) != -1) {
            var1.write(var3, 0, var6);
        }

        var4.close();
        var1.flush();
        var1.closeEntry();
    }
}
