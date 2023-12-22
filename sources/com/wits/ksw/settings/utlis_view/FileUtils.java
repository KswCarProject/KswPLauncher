package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import android.widget.Toast;
import com.google.zxing.common.StringUtils;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes10.dex */
public class FileUtils {
    public static String logoFilePath = " ";
    public static String upZipPath = " ";
    private static String factoryPath = " ";
    private static String persistPath = "/mnt/vendor/persist/";
    public static String folderPath = Environment.getExternalStorageDirectory().getPath();

    static {
        getFilePath();
    }

    static void getFilePath() {
        if (Build.VERSION.RELEASE.equals("12")) {
            logoFilePath = folderPath + "/mylogo12";
            upZipPath = persistPath + "mylogo12.zip";
            factoryPath = "/mnt/vendor/persist/mylogo12.zip";
        } else if (Build.VERSION.RELEASE.equals("13")) {
            logoFilePath = folderPath + "/mylogo13";
            upZipPath = persistPath + "mylogo13.zip";
            factoryPath = "/mnt/vendor/persist/mylogo13.zip";
        } else {
            logoFilePath = folderPath + "/mylogo";
            upZipPath = persistPath + "mylogo.zip";
            factoryPath = "/mnt/vendor/persist/mylogo.zip";
        }
    }

    public static String getLogoFilePath() {
        if (logoFilePath == null) {
            getFilePath();
        }
        return logoFilePath;
    }

    public static String getFactoryPath() {
        if (factoryPath == null) {
            getFilePath();
        }
        return factoryPath;
    }

    public static String getUpZipPath() {
        if (upZipPath == null) {
            getFilePath();
        }
        return upZipPath;
    }

    public static boolean copyFile(String newPath, Context context) {
        int bytesum = 0;
        try {
            File oldfile = new File(newPath);
            if (oldfile.exists()) {
                File file = new File(getFactoryPath());
                if (file.exists()) {
                    file.delete();
                }
                long fileLength = oldfile.length();
                long availableBlocks = getAvailableBlocks(persistPath);
                Log.d("FileUtils", "copyFile fileLength=" + fileLength + " availableBlocks=" + availableBlocks);
                if (availableBlocks - fileLength < 3145728) {
                    Toast.makeText(context, "\u7a7a\u95f4\u4e0d\u8db3,\u5bfc\u5165\u5931\u8d25,\u8bf7\u91cd\u8bd5", 0).show();
                    return false;
                }
                file.createNewFile();
                InputStream inStream = new FileInputStream(newPath);
                FileOutputStream fs = new FileOutputStream(getFactoryPath());
                byte[] buffer = new byte[1444];
                while (true) {
                    int byteread = inStream.read(buffer);
                    if (byteread == -1) {
                        break;
                    }
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
                fs.close();
                inStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static long getAvailableBlocks(String path) {
        StatFs sf = new StatFs(path);
        long totalBytes = sf.getTotalBytes();
        long freeBytes = sf.getFreeBytes();
        Log.d("FileUtils", "getAvailableBlocks totalBytes=" + totalBytes + " freeBytes=" + freeBytes);
        return freeBytes;
    }

    public static void copyFolder(String resource, String target) throws Exception {
        File resourceFile = new File(resource);
        if (!resourceFile.exists()) {
            throw new Exception("\u6e90\u76ee\u6807\u8def\u5f84\uff1a[" + resource + "] \u4e0d\u5b58\u5728...");
        }
        File targetFile = new File(target);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            targetFile.delete();
            targetFile.mkdirs();
        }
        File[] resourceFiles = resourceFile.listFiles();
        for (File file : resourceFiles) {
            File file1 = new File(targetFile.getAbsolutePath() + File.separator + resourceFile.getName());
            if (file.isFile()) {
                Log.d("logoUpdate", "\u6587\u4ef6" + file.getName());
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                File targetFile1 = new File(file1.getAbsolutePath() + File.separator + file.getName());
                copyFile(file, targetFile1);
            }
            if (file.isDirectory()) {
                String dir1 = file.getAbsolutePath();
                String dir2 = file1.getAbsolutePath();
                copyFolder(dir1, dir2);
            }
        }
    }

    public static void copyZip(String soure, String target) {
        File se = new File(soure);
        File tg = new File(target);
        try {
            copyFile(se, tg);
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
            int len = inputStream.read(bytes);
            if (len != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            } else {
                bufferedOutputStream.flush();
                bufferedInputStream.close();
                bufferedOutputStream.close();
                inputStream.close();
                outputStream.close();
                long end = System.currentTimeMillis();
                Log.d("logoUpdate", "\u8017\u65f6\uff1a" + ((end - start) / 1000) + " s");
                return;
            }
        }
    }

    public static void upZipFile(File zipFile, String folderPath2) throws IOException, IllegalArgumentException {
        ZipFile zfile = new ZipFile(zipFile);
        Enumeration zList = zfile.entries();
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ZipEntry ze = zList.nextElement();
            if (ze.isDirectory()) {
                Log.d("unzip", "ze.getName() = " + ze.getName());
                String dirstr = new String((folderPath2 + ze.getName()).getBytes("8859_1"), StringUtils.GB2312);
                Log.d("unzip", "str = " + dirstr);
                File f = new File(dirstr);
                f.mkdir();
            } else {
                Log.d("unzip", "ze.getName() = " + ze.getName());
                OutputStream os = new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath2, ze.getName())));
                InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
                while (true) {
                    int readLen = is.read(buf, 0, 1024);
                    if (readLen == -1) {
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
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                String substr = dirs[i];
                try {
                    substr = new String(substr.getBytes("8859_1"), StringUtils.GB2312);
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
                substr2 = new String(substr2.getBytes("8859_1"), StringUtils.GB2312);
                Log.d("", "substr = " + substr2);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            File ret2 = new File(ret, substr2);
            Log.d("unzip", "2ret = " + ret2);
            return ret2;
        }
        return ret;
    }

    public static List<String> getExtSdUsbPathList() {
        List<String> paths = new ArrayList<>();
        File file = new File("/storage");
        File[] files = file.listFiles();
        for (File fl : files) {
            Log.i("FileUtil ", "path =" + fl.getAbsolutePath());
            if (fl.getAbsolutePath().lastIndexOf("self") < 0 && fl.getAbsolutePath().lastIndexOf("emulated") < 0) {
                paths.add(fl.getAbsolutePath());
                Log.i("FileUtil ", "path =" + fl.getAbsolutePath());
            }
        }
        return paths;
    }

    public static List<String> getSDPath(Context context) {
        List<String> paths = new ArrayList<>();
        StorageManager mStorageManager = (StorageManager) context.getSystemService("storage");
        List<StorageVolume> volumes = mStorageManager.getStorageVolumes();
        try {
            Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getPath = storageVolumeClazz.getMethod("getPath", new Class[0]);
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable", new Class[0]);
            for (int i = 0; i < volumes.size(); i++) {
                StorageVolume storageVolume = volumes.get(i);
                String storagePath = (String) getPath.invoke(storageVolume, new Object[0]);
                boolean isRemovableResult = ((Boolean) isRemovable.invoke(storageVolume, new Object[0])).booleanValue();
                String description = storageVolume.getDescription(context);
                if (storagePath.lastIndexOf("self") < 0 && storagePath.lastIndexOf("emulated") < 0 && !"/dev/null".equals(storagePath)) {
                    paths.add(storagePath);
                    Log.i("FileUtil ", "path =" + storagePath);
                }
                Log.d("FileUtil", " i=" + i + " ,storagePath=" + storagePath + " ,isRemovableResult=" + isRemovableResult + " ,description=" + description);
            }
        } catch (Exception e) {
            Log.d("jason", " e:" + e);
        }
        return paths;
    }

    public static void changeSystemLanguage(LocaleList locale) {
        if (locale != null) {
            try {
                Class classActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
                Method getDefault = classActivityManagerNative.getDeclaredMethod("getDefault", new Class[0]);
                Object objIActivityManager = getDefault.invoke(classActivityManagerNative, new Object[0]);
                Class classIActivityManager = Class.forName("android.app.IActivityManager");
                Method getConfiguration = classIActivityManager.getDeclaredMethod("getConfiguration", new Class[0]);
                Configuration config = (Configuration) getConfiguration.invoke(objIActivityManager, new Object[0]);
                config.setLocales(locale);
                Class[] clzParams = {Configuration.class};
                Method updateConfiguration = classIActivityManager.getDeclaredMethod("updatePersistentConfiguration", clzParams);
                updateConfiguration.invoke(objIActivityManager, config);
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
            for (String str : children) {
                boolean success = deleteDir(new File(dir, str));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void savaData(String key, boolean isCheck) {
        try {
            if (isCheck) {
                PowerManagerApp.setSettingsInt(key, 1);
            } else {
                PowerManagerApp.setSettingsInt(key, 0);
            }
        } catch (Exception e) {
            e.getStackTrace();
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
