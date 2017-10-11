package com.example.administrator.viewutilslist.utilslist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.Display;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 图片处理
 */
public class ImageUtils {

    public static String bitmaptoString(Bitmap bitmap) {

        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    public static Bitmap stringtoBitmap(String string) {

        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * @TITLE 中文图片链接处理方法
     * @DATE 2016年8月3日14:09:16
     * @Version 1.0
     * @Author xxx
     */
    public static String utf8Togb2312(String str) {
        System.out.println("-==" + str);

        String data = "";
        try {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c + "".getBytes().length > 1 && c != ':' && c != '/') {
                    data = data + java.net.URLEncoder.encode(c + "", "utf-8");
                } else {
                    data = data + c;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            System.out.println(data);
        }
        data = data.replace("+", "%20");
        return data;
    }


    //二次采样
    public static Bitmap calculateBitmap(Context context, Activity activity, Uri uri) {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //1.测量原图的大小：不真的加载图片，只加载图片的边缘
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;//设置只加载边缘
        BitmapFactory.decodeStream(inputStream,
                new Rect(-1, -1, -1, -1), option);

        //获取原图的大小：原图的大小保存在了option里面
        int width = option.outWidth;
        int height = option.outHeight;

        Log.e("print", "---->>" + width + ":" + height);


        //2.根据原图大小计算采样率
        int sampleSize = calculateSampleSize(activity, width, height);
        Log.e("print", "sampleSize---->>" + sampleSize);

        //3.重新用采样率加载图片
        option.inSampleSize = sampleSize;
        option.inJustDecodeBounds = false;//真正的加载图片


        //第一次采样的时候，流到了末尾，二次采样的时候需要重新打开流
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //重新按照采样率真正加载图片
        bitmap = BitmapFactory.decodeStream(inputStream,
                new Rect(-1, -1, -1, -1), option);

        return bitmap;
    }

    /**
     * 按照屏幕的分辨率来计算采样率
     *
     * @param activity
     * @param width
     * @param height
     * @return
     */
    public static int calculateSampleSize(Activity activity, int width, int height) {


        //获得屏幕大小
        Display display = activity.getWindowManager().getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);//屏幕的大小就放在了point对象中

        int screenWidth = point.x;
        int screenHeight = point.y;

        Log.e("print", "screensize---->>" + screenWidth + ":" + screenHeight);

        int xSampleSize = width / screenWidth;
        int ySampleSize = height / screenHeight;


        return xSampleSize > ySampleSize ? xSampleSize : ySampleSize;
    }






}
