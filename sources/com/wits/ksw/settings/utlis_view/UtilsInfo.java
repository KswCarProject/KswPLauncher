package com.wits.ksw.settings.utlis_view;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.ContactsContract;
import android.support.p001v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wits.ksw.C0899R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;

/* loaded from: classes10.dex */
public class UtilsInfo {
    private ContactsContract.CommonDataKinds.Phone mPhone;

    public static String getRAMSize(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(info);
        int ramSize = (int) (info.totalMem / 1073741824);
        if (info.totalMem % 1073741824 != 0) {
            ramSize++;
        }
        if (Build.VERSION.RELEASE.equals("10")) {
            if (getMemoryValue() == null) {
                return ramSize + "GB";
            }
            return getMemoryValue() + "GB";
        }
        return ramSize + "GB";
    }

    public static String getMemoryValue() {
        if (new File("/mnt/vendor/persist/OEM/memoryvalue_501a").exists()) {
            Properties properties = new Properties();
            try {
                FileInputStream fileInputStream = new FileInputStream("/mnt/vendor/persist/OEM/memoryvalue_501a");
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str = properties.getProperty("memoryvalue");
            return str;
        }
        return null;
    }

    public static String getROMSize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        statFs.getBlockCountLong();
        long availableCounts = statFs.getAvailableBlocksLong();
        long size = statFs.getBlockSizeLong();
        long availROMSize = availableCounts * size;
        return (availROMSize / 1073741824) + "." + ((availROMSize % 1073741824) / 10000000) + "GB/" + getTotalMemSizeInfo();
    }

    private static String getTotalMemSizeInfo() {
        File userDataDir = Environment.getDataDirectory();
        StatFs stat = new StatFs(userDataDir.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long size = totalBlocks * blockSize;
        long[] size_mapping_table = {2 * 1073741824, 4 * 1073741824, 8 * 1073741824, 16 * 1073741824, 32 * 1073741824, 64 * 1073741824, 128 * 1073741824, 256 * 1073741824, 512 * 1073741824};
        String[] size_mapping_table_str = {"2GB", "4GB", "8GB", "16GB", "32GB", "64GB", "128GB", "256GB", "512GB"};
        int i = 0;
        while (i < size_mapping_table.length && size > size_mapping_table[i]) {
            i++;
        }
        if (i == size_mapping_table.length) {
            i--;
        }
        return size_mapping_table_str[i];
    }

    public static String getBaseband_Ver() {
        String Version = SystemProperties.get("gsm.version.baseband");
        return Version;
    }

    public static String getIMEI() {
        String imei = SystemProperties.get("persist.wits.imei");
        return imei;
    }

    public static String getVersion(int index) {
        String[] version = {"null", "null", "null", "null"};
        try {
            FileReader localFileReader = new FileReader("/proc/version");
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            String str2 = localBufferedReader.readLine();
            String[] arrayOfString = str2.split("\\s+");
            version[0] = arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        version[1] = Build.VERSION.RELEASE;
        version[2] = Build.MODEL;
        version[3] = "Witstek-" + Build.DISPLAY;
        return version[index];
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    public static String getSettingsVer(Context context) {
        String version;
        String pingtai;
        String str = Build.VERSION.RELEASE;
        String realVer = changeVersion();
        if (!TextUtils.isEmpty(getBaseband_Ver())) {
            getBaseband_Ver().substring(0, 4);
            int index_NA = getBaseband_Ver().indexOf("NA");
            int index_platform_M506 = getBaseband_Ver().indexOf("M506");
            int index_platform_M501 = getBaseband_Ver().indexOf("M501");
            int index_platform_8953 = getBaseband_Ver().indexOf("8953");
            int index_platform_8937 = getBaseband_Ver().indexOf("8937");
            int index_platform_8917 = getBaseband_Ver().indexOf("8917");
            int index_platform_511 = getBaseband_Ver().indexOf("M511");
            int index_platform_600 = getBaseband_Ver().indexOf("M600");
            int index_platform_450 = getBaseband_Ver().indexOf("SDM450");
            int index_platform_700 = getBaseband_Ver().indexOf("M700");
            if (index_platform_M506 > -1) {
                pingtai = "M506";
            } else if (index_platform_8917 > -1) {
                pingtai = "M506";
            } else if (index_platform_450 > -1) {
                pingtai = "501A-";
            } else if (index_platform_M501 > -1) {
                pingtai = "8953";
            } else if (index_platform_8953 > -1) {
                pingtai = "8953";
            } else if (index_platform_8937 > -1) {
                pingtai = "8937";
            } else if (index_platform_511 > -1) {
                pingtai = "662";
            } else if (index_platform_600 > -1) {
                pingtai = "662";
            } else if (index_platform_700 > -1) {
                pingtai = "680";
            } else {
                pingtai = "8953";
            }
            String str2 = realVer + "-" + pingtai;
            if (index_NA > -1) {
                if (index_platform_M501 > -1) {
                    version = realVer + "-" + pingtai + "NA-1";
                } else {
                    version = realVer + "-" + pingtai + "NA";
                }
            } else if (index_platform_M501 > -1) {
                version = realVer + "-" + pingtai + "EA-1";
            } else {
                version = realVer + "-" + pingtai + "EA";
            }
        } else {
            version = realVer;
        }
        String settingsVer = context.getString(C0899R.string.audi_set_sys_info_system_ver, "Witstek-" + version);
        return settingsVer;
    }

    public static String getSettingsVersion(Context context) {
        String pingtai;
        String realVer = changeVersion();
        if (!TextUtils.isEmpty(getBaseband_Ver())) {
            getBaseband_Ver().substring(0, 4);
            int index_NA = getBaseband_Ver().indexOf("NA");
            int index_platform_M506 = getBaseband_Ver().indexOf("M506");
            int index_platform_M501 = getBaseband_Ver().indexOf("M501");
            int index_platform_8953 = getBaseband_Ver().indexOf("8953");
            int index_platform_8937 = getBaseband_Ver().indexOf("8937");
            int index_platform_8917 = getBaseband_Ver().indexOf("8917");
            int index_platform_511 = getBaseband_Ver().indexOf("M511");
            int index_platform_600 = getBaseband_Ver().indexOf("M600");
            int index_platform_450 = getBaseband_Ver().indexOf("SDM450");
            int index_platform_700 = getBaseband_Ver().indexOf("M700");
            if (index_platform_M506 > -1) {
                pingtai = "M506";
            } else if (index_platform_8917 > -1) {
                pingtai = "M506";
            } else if (index_platform_450 > -1) {
                pingtai = "501A-";
            } else if (index_platform_M501 > -1) {
                pingtai = "8953";
            } else if (index_platform_8953 > -1) {
                pingtai = "8953";
            } else if (index_platform_8937 > -1) {
                pingtai = "8937";
            } else if (index_platform_511 > -1) {
                pingtai = "662";
            } else if (index_platform_600 > -1) {
                pingtai = "662";
            } else if (index_platform_700 > -1) {
                pingtai = "680";
            } else {
                pingtai = "8953";
            }
            context.getString(C0899R.string.audi_set_sys_info_system_ver, realVer + "-" + pingtai);
            if (index_NA > -1) {
                if (index_platform_M501 > -1) {
                    String settingsVer = realVer + "-" + pingtai + "NA-1";
                    return settingsVer;
                }
                String settingsVer2 = realVer + "-" + pingtai + "NA";
                return settingsVer2;
            } else if (index_platform_M501 > -1) {
                String settingsVer3 = realVer + "-" + pingtai + "EA-1";
                return settingsVer3;
            } else {
                String settingsVer4 = realVer + "-" + pingtai + "EA";
                return settingsVer4;
            }
        }
        String settingsVer5 = changeVersion();
        return settingsVer5;
    }

    public static String changeVersion() {
        String targetVer = SystemProperties.get("ksw_android11");
        if (targetVer == null || targetVer.isEmpty()) {
            return Build.VERSION.RELEASE;
        }
        if (Integer.parseInt(targetVer) > Integer.parseInt(Build.VERSION.RELEASE)) {
            return targetVer;
        }
        return Build.VERSION.RELEASE;
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(1);
        int month = calendar.get(2) + 1;
        int day = calendar.get(5);
        if (year < 2019) {
            return "null";
        }
        String date = year + "-" + month + "-" + day;
        return date;
    }

    public static void showQRCode(Context context) {
        File file = new File("/mnt/vendor/persist/OEM/qrCode.jpg");
        DialogViews dialogViews = new DialogViews(context);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            dialogViews.qrCode(bitmap);
        } else if (generateQRCode(context, true)) {
            Bitmap bitmap2 = BitmapFactory.decodeFile(file.getPath());
            dialogViews.qrCode(bitmap2);
        }
    }

    public static boolean generateQRCode(Context context, boolean show) {
        if ("null".equals(getDate())) {
            if (show) {
                ToastUtils.showToastShort(context, context.getString(C0899R.string.date_error));
                return false;
            }
            return false;
        }
        String info = getIMEI() + "," + getDate();
        Bitmap qrCode = createQRCodeBitmap(info, 300, 300, "", "", "5", ViewCompat.MEASURED_STATE_MASK, -1);
        saveBitmap("qrCode.jpg", qrCode, context);
        return true;
    }

    public static Bitmap createQRCodeBitmap(String content, int width, int height, String character_set, String error_correction_level, String margin, int color_black, int color_white) {
        if (!TextUtils.isEmpty(content) && width >= 0 && height >= 0) {
            try {
                Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                if (!TextUtils.isEmpty(character_set)) {
                    try {
                        hints.put(EncodeHintType.CHARACTER_SET, character_set);
                    } catch (WriterException e) {
                        e = e;
                        e.printStackTrace();
                        return null;
                    }
                }
                if (!TextUtils.isEmpty(error_correction_level)) {
                    try {
                        hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
                    } catch (WriterException e2) {
                        e = e2;
                        e.printStackTrace();
                        return null;
                    }
                }
                if (!TextUtils.isEmpty(margin)) {
                    try {
                        hints.put(EncodeHintType.MARGIN, margin);
                    } catch (WriterException e3) {
                        e = e3;
                        e.printStackTrace();
                        return null;
                    }
                }
                BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                int[] pixels = new int[width * height];
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (bitMatrix.get(x, y)) {
                            pixels[(y * width) + x] = color_black;
                        } else {
                            pixels[(y * width) + x] = color_white;
                        }
                    }
                }
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                return bitmap;
            } catch (WriterException e4) {
                e = e4;
            }
        }
        return null;
    }

    public static void saveBitmap(String name, Bitmap bitmap, Context context) {
        File saveFile = new File("/mnt/vendor/persist/OEM", name);
        try {
            FileOutputStream saveImgOut = new FileOutputStream(saveFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, saveImgOut);
            saveImgOut.flush();
            saveImgOut.close();
            Log.d("saveBitmap", saveFile.getPath());
        } catch (IOException e) {
        }
    }
}
