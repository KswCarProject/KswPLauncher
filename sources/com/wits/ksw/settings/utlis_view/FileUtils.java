package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtils {
    private static String factoryPath = "/mnt/vendor/persist/mylogo.zip";

    public static boolean copyFile(String newPath) {
        int bytesum = 0;
        boolean isFile = false;
        try {
            if (new File(newPath).exists()) {
                InputStream inStream = new FileInputStream(newPath);
                File file = new File(factoryPath);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                FileOutputStream fs = new FileOutputStream(factoryPath);
                byte[] buffer = new byte[1444];
                while (true) {
                    int read = inStream.read(buffer);
                    int byteread = read;
                    if (read == -1) {
                        break;
                    }
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
                fs.close();
                inStream.close();
            }
            isFile = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isFile;
    }

    public static void copyFolder(String resource, String target) throws Exception {
        File resourceFile = new File(resource);
        if (resourceFile.exists()) {
            File targetFile = new File(target);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            } else {
                targetFile.delete();
                targetFile.mkdirs();
            }
            for (File file : resourceFile.listFiles()) {
                File file1 = new File(targetFile.getAbsolutePath() + File.separator + resourceFile.getName());
                if (file.isFile()) {
                    Log.d("logoUpdate", "文件" + file.getName());
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    copyFile(file, new File(file1.getAbsolutePath() + File.separator + file.getName()));
                }
                if (file.isDirectory()) {
                    copyFolder(file.getAbsolutePath(), file1.getAbsolutePath());
                }
            }
            return;
        }
        throw new Exception("源目标路径：[" + resource + "] 不存在...");
    }

    public static void copyZip(String soure, String target) {
        try {
            copyFile(new File(soure), new File(target));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File resource, File target) throws Exception {
        long start = System.currentTimeMillis();
        FileInputStream inputStream = new FileInputStream(resource);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        FileOutputStream outputStream = new FileOutputStream(target);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        byte[] bytes = new byte[2048];
        while (true) {
            int read = inputStream.read(bytes);
            int len = read;
            if (read != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            } else {
                bufferedOutputStream.flush();
                bufferedInputStream.close();
                bufferedOutputStream.close();
                inputStream.close();
                outputStream.close();
                long end = System.currentTimeMillis();
                Log.d("logoUpdate", "耗时：" + ((end - start) / 1000) + " s");
                return;
            }
        }
    }

    public static void upZipFile(File zipFile, String folderPath) throws IOException {
        ZipFile zfile = new ZipFile(zipFile);
        Enumeration zList = zfile.entries();
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                Log.d("unzip", "ze.getName() = " + ze.getName());
                String dirstr = new String((folderPath + ze.getName()).getBytes("8859_1"), "GB2312");
                Log.d("unzip", "str = " + dirstr);
                new File(dirstr).mkdir();
            } else {
                Log.d("unzip", "ze.getName() = " + ze.getName());
                OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
                InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
                while (true) {
                    int read = is.read(buf, 0, 1024);
                    int readLen = read;
                    if (read == -1) {
                        break;
                    }
                    os.write(buf, 0, readLen);
                }
                is.close();
                os.close();
            }
        }
        zfile.close();
    }

    private static File getRealFileName(String baseDir, String absFileName) {
        String[] dirs = absFileName.split("/");
        File ret = new File(baseDir);
        if (dirs.length <= 1) {
            return ret;
        }
        for (int i = 0; i < dirs.length - 1; i++) {
            String substr = dirs[i];
            try {
                substr = new String(substr.getBytes("8859_1"), "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ret = new File(ret, substr);
        }
        Log.d("unzip", "1ret = " + ret);
        if (!ret.exists()) {
            ret.mkdirs();
        }
        String substr2 = dirs[dirs.length - 1];
        try {
            substr2 = new String(substr2.getBytes("8859_1"), "GB2312");
            Log.d("", "substr = " + substr2);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        File ret2 = new File(ret, substr2);
        Log.d("unzip", "2ret = " + ret2);
        return ret2;
    }

    public static List<String> getExtSdUsbPathList() {
        List<String> paths = new ArrayList<>();
        for (File fl : new File("/storage").listFiles()) {
            if (fl.getAbsolutePath().lastIndexOf("self") < 0 && fl.getAbsolutePath().lastIndexOf("emulated") < 0) {
                paths.add(fl.getAbsolutePath());
            }
        }
        return paths;
    }

    @RequiresApi(api = 24)
    public static void changeSystemLanguage(LocaleList locale) {
        if (locale != null) {
            try {
                Class classActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
                Object objIActivityManager = classActivityManagerNative.getDeclaredMethod("getDefault", new Class[0]).invoke(classActivityManagerNative, new Object[0]);
                Class classIActivityManager = Class.forName("android.app.IActivityManager");
                Configuration config = (Configuration) classIActivityManager.getDeclaredMethod("getConfiguration", new Class[0]).invoke(objIActivityManager, new Object[0]);
                config.setLocales(locale);
                classIActivityManager.getDeclaredMethod("updatePersistentConfiguration", new Class[]{Configuration.class}).invoke(objIActivityManager, new Object[]{config});
            } catch (Exception e) {
            }
        }
    }

    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals("mounted")) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String file : children) {
                if (!deleteDir(new File(dir, file))) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void savaData(String key, boolean isCheck) {
        if (isCheck) {
            try {
                PowerManagerApp.setSettingsInt(key, 1);
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            PowerManagerApp.setSettingsInt(key, 0);
        }
    }

    public static void savaIntData(String key, int value) {
        try {
            PowerManagerApp.setSettingsInt(key, value);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void savaStringData(String key, String value) {
        try {
            PowerManagerApp.setSettingsString(key, value);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
