package com.example.administrator.viewutilslist.utils.encode;

import java.io.UnsupportedEncodingException;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 12:39
 */

public class Base64 {
    private static final byte[] MAP = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    public Base64() {
    }

    public static byte[] decode(byte[] var0) {
        return decode(var0, var0.length);
    }

    public static byte[] decode(byte[] var0, int var1) {
        int var2 = var1 / 4 * 3;
        if(var2 == 0) {
            return new byte[0];
        } else {
            byte[] var3 = new byte[var2];
            int var4 = 0;

            while(true) {
                byte var5 = var0[var1 - 1];
                if(var5 != 10 && var5 != 13 && var5 != 32 && var5 != 9) {
                    if(var5 != 61) {
                        int var6 = 0;
                        int var7 = 0;
                        boolean var8 = false;
                        int var9 = 0;

                        for(int var10 = 0; var10 < var1; ++var10) {
                            var5 = var0[var10];
                            if(var5 != 10 && var5 != 13 && var5 != 32 && var5 != 9) {
                                int var11;
                                if(var5 >= 65 && var5 <= 90) {
                                    var11 = var5 - 65;
                                } else if(var5 >= 97 && var5 <= 122) {
                                    var11 = var5 - 71;
                                } else if(var5 >= 48 && var5 <= 57) {
                                    var11 = var5 + 4;
                                } else if(var5 == 43) {
                                    var11 = 62;
                                } else {
                                    if(var5 != 47) {
                                        return null;
                                    }

                                    var11 = 63;
                                }

                                var9 = var9 << 6 | (byte)var11;
                                if(var7 % 4 == 3) {
                                    var3[var6++] = (byte)((var9 & 16711680) >> 16);
                                    var3[var6++] = (byte)((var9 & '\uff00') >> 8);
                                    var3[var6++] = (byte)(var9 & 255);
                                }

                                ++var7;
                            }
                        }

                        if(var4 > 0) {
                            var9 <<= 6 * var4;
                            var3[var6++] = (byte)((var9 & 16711680) >> 16);
                            if(var4 == 1) {
                                var3[var6++] = (byte)((var9 & '\uff00') >> 8);
                            }
                        }

                        byte[] var12 = new byte[var6];
                        System.arraycopy(var3, 0, var12, 0, var6);
                        return var12;
                    }

                    ++var4;
                }

                --var1;
            }
        }
    }

    public static String encode(byte[] var0, String var1) throws UnsupportedEncodingException {
        int var2 = var0.length * 4 / 3;
        var2 += var2 / 76 + 3;
        byte[] var3 = new byte[var2];
        int var4 = 0;
        int var5 = 0;
        int var6 = var0.length - var0.length % 3;

        for(int var7 = 0; var7 < var6; var7 += 3) {
            var3[var4++] = MAP[(var0[var7] & 255) >> 2];
            var3[var4++] = MAP[(var0[var7] & 3) << 4 | (var0[var7 + 1] & 255) >> 4];
            var3[var4++] = MAP[(var0[var7 + 1] & 15) << 2 | (var0[var7 + 2] & 255) >> 6];
            var3[var4++] = MAP[var0[var7 + 2] & 63];
            if((var4 - var5) % 76 == 0 && var4 != 0) {
                var3[var4++] = 10;
                ++var5;
            }
        }

        switch(var0.length % 3) {
            case 1:
                var3[var4++] = MAP[(var0[var6] & 255) >> 2];
                var3[var4++] = MAP[(var0[var6] & 3) << 4];
                var3[var4++] = 61;
                var3[var4++] = 61;
                break;
            case 2:
                var3[var4++] = MAP[(var0[var6] & 255) >> 2];
                var3[var4++] = MAP[(var0[var6] & 3) << 4 | (var0[var6 + 1] & 255) >> 4];
                var3[var4++] = MAP[(var0[var6 + 1] & 15) << 2];
                var3[var4++] = 61;
        }

        return new String(var3, 0, var4, var1);
    }
}
