package com.example.administrator.viewutilslist.utils.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.administrator.viewutilslist.BaseConstant;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by v_chicunxiang on 2018/2/2.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/2 15:21
 */

public class FileUtil {

    public static String[] fileTypes = new String[]{"apk", "avi", "bmp", "chm", "dll", "doc", "docx", "dos", "gif", "html", "jpeg", "jpg", "movie", "mp3", "dat", "mp4", "mpe", "mpeg", "mpg", "pdf", "png", "ppt", "pptx", "rar", "txt", "wav", "wma", "wmv", "xls", "xlsx", "xml", "zip"};

    public FileUtil() {
    }

    @Deprecated
    public static File[] loadFiles(File var0) {
        File[] var1 = var0.listFiles();
        if(var1 == null) {
            var1 = new File[0];
        }

        ArrayList var2 = new ArrayList();
        ArrayList var3 = new ArrayList();
        File[] var4 = var1;
        int var5 = var1.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            File var7 = var4[var6];
            if(var7.isDirectory()) {
                var2.add(var7);
            } else if(var7.isFile()) {
                var3.add(var7);
            }
        }

        FileUtil.MyComparator var8 = new FileUtil.MyComparator();
        Collections.sort(var2, var8);
        Collections.sort(var3, var8);
        File[] var9 = new File[var2.size() + var3.size()];
        System.arraycopy(var2.toArray(new File[var2.size()]), 0, var9, 0, var2.size());
        System.arraycopy(var3.toArray(new File[var3.size()]), 0, var9, var2.size(), var3.size());
        return var9;
    }

    public static String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }

    public static String getMIMEType(String var0) {
        String var1 = "";
        String var2 = var0.substring(var0.lastIndexOf(".") + 1, var0.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var2);
        return var1;
    }

    /**
     * 打开文件
     * @param var0
     * @param var1
     */
    @Deprecated
    @SuppressLint({"WrongConstant", "ShowToast"})
    public static void openFile(File var0, Activity var1) {
        Intent var2 = new Intent();
        var2.addFlags(268435456);
        var2.setAction("android.intent.action.VIEW");
        String var3 = getMIMEType(var0);
        var2.setDataAndType(Uri.fromFile(var0), var3);

        try {
            var1.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(var1, "Can't find proper app to open this file", 1).show();
        }

    }
    /**
     * 打开文件
     * @param var0
     * @param var1
     */
    @Deprecated
    @SuppressLint("WrongConstant")
    public static void openFile(Uri var0, String var1, Activity var2) {
        Intent var3 = new Intent();
        var3.addFlags(268435456);
        var3.setAction("android.intent.action.VIEW");
        var3.setDataAndType(var0, var1);

        try {
            var2.startActivity(var3);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(var2, "Can't find proper app to open this file", 1).show();
        }

    }
    @Deprecated
    public static synchronized void saveObjectToFile(Object var0, File var1) throws Exception {
        ObjectOutputStream var2 = new ObjectOutputStream(new FileOutputStream(var1));
        var2.writeObject(var0);
        var2.flush();
        var2.close();
    }
    @Deprecated
    public static synchronized Object readObjectFromFile(File var0) throws Exception {
        ObjectInputStream var1 = new ObjectInputStream(new FileInputStream(var0));
        return var1.readObject();
    }
    @Deprecated
    public static class MyComparator implements Comparator<File> {
        public MyComparator() {
        }

        public int compare(File var1, File var2) {
            return var1.getName().compareTo(var2.getName());
        }
    }

    /**
     * 获取可以使用的缓存目录(默认目录名/cache/)
     * @param context
     * @return
     */
    public static File getDiskCacheDir(Context context) {
        return getDiskCacheDir(context, BaseConstant.RESOURCE_DIRECTORY);
    }

    /**
     * 获取可以使用的缓存目录
     * @param context
     * @param uniqueName 目录名称
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        final String cachePath = checkSDCard() ? getExternalCacheDir(context).getPath() : getAppCacheDir(context);

        File cacheDirFile = new File(cachePath);
        if (!cacheDirFile.exists()) {
            cacheDirFile.mkdirs();
        }

        return cacheDirFile;
    }

    /**
     * 获取程序外部的缓存目录
     * @param context
     * @return
     */
    public static File getExternalCacheDir(Context context) {
        // 这个sd卡中文件路径下的内容会随着，程序卸载或者设置中清除缓存后一起清空
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    /**
     * 获取文件路径空间大小
     * @param path
     * @return
     */
    public static long getUsableSpace(File path) {
        try{
            final StatFs stats = new StatFs(path.getPath());
            return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 空间大小单位格式化
     * @param size
     * @return
     */
    public static String formatSize(long size) {
        // Formatter.formatFileSize()
        String suffix = null;
        float fSize=0;

        if (size >= 1024) {
            suffix = "KB";
            fSize=size / 1024;
            if (fSize >= 1024) {
                suffix = "MB";
                fSize /= 1024;
            }
            if (fSize >= 1024) {
                suffix = "GB";
                fSize /= 1024;
            }
        } else {
            fSize = size;
        }

        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        StringBuilder resultBuffer = new StringBuilder(df.format(fSize));
        if (suffix != null)
            resultBuffer.append(suffix);
        return resultBuffer.toString();
    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public static boolean checkSDCard() {
        final String status = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(status)) {
            return true;
        }
        return false;
    }

    /**
     * 获取安装在用户手机上的com.itlanbao.app下的files目录
     *
     * @return files path
     */
    public static String getAppFilesDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    /**
     * 获取安装在用户手机上的com.itlanbao.yyapp下的cache目录
     *
     * @return cache path
     */
    public static String getAppCacheDir(Context context) {
        return context.getCacheDir().getPath();
    }

    /**
     * 创建app主目录
     *
     * @return boolean
     */
    public static boolean checkFileDirectory(Context context) {
        final File resDir = FileUtil.getDiskCacheDir(context);
        if (!resDir.exists()) {
            return resDir.mkdirs();
        }
        return true;
    }

    /**
     * 创建缓存文件夹
     */
    public static void initCacheFile(Context context) {

        if (LogUtil.isDebug()) {
            LogUtil.v("initCacheFile");
        }

        final String cacheDir = FileUtil.getDiskCacheDir(context).getAbsolutePath();

        final String imageDirPath = cacheDir + BaseConstant.CACHE_IMAGE_DIR;
        final File imageFileDir = new File(imageDirPath);
        if (!imageFileDir.exists()) {
            boolean isOk = imageFileDir.mkdirs();
            if (LogUtil.isDebug()) {
                LogUtil.v(imageDirPath + " 文件夹创建isOk" + isOk);
            }
        }

        final String audioDirPath = cacheDir + BaseConstant.CACHE_AUDIO_DIR;
        final File audioFileDir = new File(audioDirPath);
        if (!audioFileDir.exists()) {
            boolean isOk = audioFileDir.mkdirs();
            if (LogUtil.isDebug()) {
                LogUtil.v(audioDirPath + " 文件夹创建isOk" + isOk);
            }
        }

        final String messageDirPath = cacheDir + BaseConstant.CACHE_MESSAGE_DIR;
        final File messageFileDir = new File(messageDirPath);
        if (!messageFileDir.exists()) {
            boolean isOk = messageFileDir.mkdirs();
            if (LogUtil.isDebug()) {
                LogUtil.v(imageDirPath + " 文件夹创建isOk" + isOk);
            }
        }
    }


    /**
     * 读取文件
     *
     * @param sFileName
     * @return
     */
    public static String readFile(String sFileName) {
        if (TextUtils.isEmpty(sFileName)) {
            return null;
        }

        final StringBuffer sDest = new StringBuffer();
        final File f = new File(sFileName);
        if (!f.exists()) {
            return null;
        }
        try {
            FileInputStream is = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            try {
                String data = null;
                while ((data = br.readLine()) != null) {
                    sDest.append(data);
                }
            } catch (IOException ioex) {
                if (LogUtil.isDebug()) {
                    LogUtil.e(ioex.getMessage());
                }
            } finally {
                is.close();
                is = null;
                br.close();
                br = null;
            }
        } catch (Exception ex) {
            if (LogUtil.isDebug()) {
                LogUtil.e(ex.getMessage());
            }
        } catch (OutOfMemoryError ex) {
            if (LogUtil.isDebug()) {
                ex.printStackTrace();
            }
        }
        return sDest.toString().trim();
    }

    /**
     * 从assets 文件夹中获取文件并读取数据
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getFromAssets(Context context, String fileName ) {
        String result = "";
        try {
            final InputStream in = context.getResources().getAssets()
                    .open(fileName);
            // 获取文件的字节数
            final int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            in.read(buffer);

            result = new String(buffer, "UTF-8");
            in.close();
            buffer = null;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            if (LogUtil.isDebug()) {
                LogUtil.e(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 保存文件
     *
     * @param content
     * @param fileName
     * @param isAppend
     * @return
     */
    public static boolean writeStringToFile(String content, String fileName, boolean isAppend) {
        return writeStringToFile(content, "", fileName, isAppend);
    }

    public static boolean writeStringToFile(String content,
                                            String directoryPath, String fileName, boolean isAppend) {
        if (!TextUtils.isEmpty(content)) {
            if (!TextUtils.isEmpty(directoryPath)) {// 是否需要创建新的目录
                final File threadListFile = new File(directoryPath);
                if (!threadListFile.exists()) {
                    threadListFile.mkdirs();
                }
            }
            boolean bFlag = false;
            final int iLen = content.length();
            final File file = new File(fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                final FileOutputStream fos = new FileOutputStream(file,
                        isAppend);
                byte[] buffer = new byte[iLen];
                try {
                    buffer = content.getBytes();
                    fos.write(buffer);
                    if (isAppend) {
                        fos.write("||".getBytes());
                    }
                    fos.flush();
                    bFlag = true;
                } catch (IOException ioex) {
                    if (LogUtil.isDebug()) {
                        LogUtil.e(ioex.getMessage());
                    }
                } finally {
                    fos.close();
                    buffer = null;
                }
            } catch (Exception ex) {
                if (LogUtil.isDebug()) {
                    LogUtil.e(ex.getMessage());
                }
            } catch (OutOfMemoryError o) {
                if (LogUtil.isDebug()) {
                    o.printStackTrace();
                }
            }
            return bFlag;
        }
        return false;
    }

    /**
     * 重命名
     *
     * @param filePath
     * @return
     */
    public static boolean rename(String filePath, String newFilePath) {
        if (LogUtil.isDebug()) {
            LogUtil.e("filePath " + filePath);
            LogUtil.e("newFilePath " + newFilePath);
        }

        if (!TextUtils.isEmpty(filePath)) {
            final File file = new File(filePath);
            final File newFile = new File(newFilePath);
            if (file.exists()) {
                return file.renameTo(newFile);
            }
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        if (LogUtil.isDebug()) {
            LogUtil.e("deleteFile path " + filePath);
        }

        if (!TextUtils.isEmpty(filePath)) {
            final File file = new File(filePath);
            if (LogUtil.isDebug()) {
                LogUtil.e("deleteFile path exists " + file.exists());
            }
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    /**
     * 删除文件夹下所有文件
     *
     * @return
     */
    public static void deleteDirectoryAllFile(String directoryPath) {
        final File file = new File(directoryPath);
        deleteDirectoryAllFile(file);
    }

    public static void deleteDirectoryAllFile(File file) {
        if (!file.exists()) {
            return;
        }

        boolean rslt = true;// 保存中间结果
        if (!(rslt = file.delete())) {// 先尝试直接删除
            // 若文件夹非空。枚举、递归删除里面内容
            final File subs[] = file.listFiles();
            final int size = subs.length - 1;
            for (int i = 0; i <= size; i++) {
                if (subs[i].isDirectory())
                    deleteDirectoryAllFile(subs[i]);// 递归删除子文件夹内容
                rslt = subs[i].delete();// 删除子文件夹本身
            }
            // rslt = file.delete();// 删除此文件夹本身
        }

        if (!rslt) {
            if (LogUtil.isDebug()) {
                LogUtil.d("无法删除:" + file.getName());
            }
            return;
        }
    }

    /**
     * 根据后缀名删除文件
     *
     * @param delPath
     *            path of file
     * @param delEndName
     *            end name of file
     * @return boolean the result
     */
    public static boolean deleteEndFile(String delPath, String delEndName) {
        // param is null
        if (delPath == null || delEndName == null) {
            return false;
        }
        try {
            // create file
            final File file = new File(delPath);
            if (file != null) {
                if (file.isDirectory()) {
                    // file list
                    String[] fileList = file.list();
                    File delFile = null;

                    // digui
                    final int size = fileList.length;
                    for (int i = 0; i < size; i++) {
                        // create new file
                        delFile = new File(delPath + "/" + fileList[i]);
                        if (delFile != null && delFile.isFile()) {// 删除该文件夹下所有文件以delEndName为后缀的文件（不包含子文件夹里的文件）
                            // if (delFile != null) {//
                            // 删除该文件夹下所有文件以delEndName为后缀的文件（包含子文件夹里的文件）
                            deleteEndFile(delFile.toString(), delEndName);
                        } else {
                            // nothing
                        }
                    }
                } else if (file.isFile()) {

                    // check the end name
                    if (file.toString().contains(".")
                            && file.toString()
                            .substring(
                                    (file.toString().lastIndexOf(".") + 1))
                            .equals(delEndName)) {
                        // file delete
                        file.delete();
                    }
                }
            }
        } catch (Exception ex) {
            if (LogUtil.isDebug()) {
                LogUtil.e(ex.getMessage());
            }
            return false;
        }
        return true;
    }

    /**
     * 删除文件夹内所有文件
     *
     * @param delpath
     *            delpath path of file
     * @return boolean the result
     */
    public static boolean deleteAllFile(String delpath) {
        try {
            // create file
            final File file = new File(delpath);

            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {

                final String[] filelist = file.list();
                final int size = filelist.length;
                for (int i = 0; i < size; i++) {

                    // create new file
                    final File delfile = new File(delpath + "/" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        // digui
                        deleteFile(delpath + "/" + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception ex) {
            if (LogUtil.isDebug()) {
                LogUtil.e(ex.getMessage());
            }
            return false;
        }
        return true;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath
     *            被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {

        if (TextUtils.isEmpty(sPath)) {
            return false;
        }

        boolean flag;
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        final File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        final File[] files = dirFile.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                // 删除子文件
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                } // 删除子目录
                else {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                }
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取后缀名
     *
     * @param path
     *            全路径
     * @return
     */
    public static String getFileExtName(String path) {
        String ext = "";
        if ((path != null) && (path.length() > 0)) {
            int dot = path.lastIndexOf('.');
            if ((dot > -1) && (dot < (path.length() - 1))) {
                ext = path.substring(dot + 1);
            }
        }
        return ext;
    }

    /**
     * 获取文件名
     *
     * @param path
     *            全路径
     * @return
     */
    public static String getFileName(String path) {
        if (!TextUtils.isEmpty(path)) {
            return path.substring(path.lastIndexOf(File.separator) + 1);
        }
        return "";
    }

    /**
     * 获取文件所在的文件路径
     *
     * @param path
     * @return
     */
    public static String getFilePath(String path) {
        return path.substring(0, path.lastIndexOf(File.separator) + 1);
    }

    /**
     * 复制文件
     *
     * @param srcPath : 源文件全路径
     * @param destPath : 目标文件全路径
     * @return
     */
    public static long copyFile(String srcPath, String destPath) {
        try {
            int position = destPath.lastIndexOf(File.separator);
            String dir = destPath.substring(0, position);
            String newFileName = destPath.substring(position+1);
            final File cacheDir = new File(dir);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            return copyFile(new File(srcPath), new File(dir), newFileName);
        } catch (Exception e) {
            return 0;
        }

    }

    /**
     * 复制文件(以超快的速度复制文件)
     *
     * @param srcFile
     *            源文件File
     * @param destDir
     *            目标目录File
     * @param newFileName
     *            新文件名
     * @return 实际复制的字节数，如果文件、目录不存在、文件为null或者发生IO异常，返回-1
     */
    @SuppressWarnings("resource")
    public static long copyFile(final File srcFile, final File destDir, String newFileName) {
        long copySizes = 0;
        if (!srcFile.exists()) {
            if (LogUtil.isDebug()) {
                LogUtil.d("源文件不存在");
            }
            copySizes = -1;
        } else if (!destDir.exists()) {
            if (LogUtil.isDebug()) {
                LogUtil.d("目标目录不存在");
            }
            copySizes = -1;
        } else if (newFileName == null) {
            if (LogUtil.isDebug()) {
                LogUtil.d("文件名为null");
            }
            copySizes = -1;
        } else {
            FileChannel fcin = null;
            FileChannel fcout = null;
            try {
                fcin = new FileInputStream(srcFile).getChannel();
                fcout = new FileOutputStream(new File(destDir, newFileName)).getChannel();
                long size = fcin.size();
                fcin.transferTo(0, fcin.size(), fcout);
                copySizes = size;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fcin != null) {
                        fcin.close();
                    }
                    if (fcout != null) {
                        fcout.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return copySizes;
    }


    /**
     * 判断asset下是否存在某个文件
     *
     * @param context
     * @param fileName
     *            如:aa.txt或image/aa.jpg
     * @return
     */
    public static boolean existInAsset(Context context, String fileName) {
        boolean exist = false;
        try {
            String[] u = context.getAssets().list(getFilePath(fileName));
            for (String item : u) {
                if (item.equals(getFileName(fileName))) {
                    exist = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exist;
    }

    /**
     * 获取目录文件个数
     *
     * @param f
     * @return
     */
    public static long getlist(File f) {
        long size = 0;
        try {
            File flist[] = f.listFiles();
            size = flist.length;
            for (int i = 0; i < flist.length; i++) {
                final File file = flist[i];
                if (file == null) {
                    continue;
                }
                if (file.isDirectory()) {
                    size = size + getlist(file);
                    size--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取文件夹下所有文件大小
     *
     * @param f
     * @return
     */
    public static long getFileSize(File f) {
        long size = 0;
        try {
            File flist[] = f.listFiles();
            for (int i = 0; i < flist.length; i++) {
                final File file = flist[i];
                if (file == null) {
                    continue;
                }
                if (file.isDirectory()) {
                    size = size + getFileSize(file);
                } else {
                    size = size + file.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(File file) {
        if (file == null) {
            return "0B";
        }

        final long blockSize = getFileSize(file);

        if (LogUtil.isDebug()) {
            LogUtil.d("getAutoFileOrFilesSize 文件大小：" + blockSize);
        }

        return FormetFileSize(blockSize);
    }

    /**
     * 转换文件大小
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS) {
        final DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 将字符串写入到文本文件中
     *
     * @param strcontent
     */
    public static void writeFileSdcard(String strcontent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        // 每次写入时，都换行写
        String strContent = "-------当前时间===" + str + "\r\n" + strcontent + "\r\n";
        try {
            String strFilePath = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() +".txt";

            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File.");
        }
    }


    /**
     * 获取asset目录下的文件,并将其转成file返回
     * @param context
     * @param assetName
     * @return
     * @throws IOException
     */
    public static File fileFromAsset(Context context, String assetName) throws IOException {
        File outFile = new File(context.getCacheDir(), assetName + "-pdfview.pdf");
        copy(context.getAssets().open(assetName), outFile);
        return outFile;
    }

    private static void copy(InputStream inputStream, File output) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(output);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }


    /**
     * 读取Pdf文件的内容
     * @param path :文件地址
     */
    private static String readPdfContent(String path){
        String content = "";
        try {
            PdfReader pr = new PdfReader(path);
            int page = pr.getNumberOfPages();

            for(int i = 1 ;i<page+1;i++){
                content += PdfTextExtractor.getTextFromPage(pr, i); //遍历页码,读取Pdf文件内容
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 读取pdf并且返回string
     * @param context
     * @param pdfName
     * @return
     */
    public static String readPdfContent2String(Context context,String pdfName){
        try {
            File file = FileUtil.fileFromAsset(context, pdfName);
            String s = readPdfContent(file.getAbsolutePath());
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}

