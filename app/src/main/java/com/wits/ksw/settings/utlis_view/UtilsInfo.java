package com.wits.ksw.settings.utlis_view;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UtilsInfo {
    public static String getRAMSize(Context context) {
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(info);
        int ramSize = (int) (info.totalMem / 1073741824);
        if (info.totalMem % 1073741824 != 0) {
            ramSize++;
        }
        return ramSize + "GB";
    }

    public static String getROMSize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockCountLong = statFs.getBlockCountLong();
        long availROMSize = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        return (availROMSize / 1073741824) + "." + ((availROMSize % 1073741824) / 10000000) + "GB/" + getTotalMemSizeInfo();
    }

    private static String getTotalMemSizeInfo() {
        int i;
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long size = stat.getBlockCountLong() * stat.getBlockSizeLong();
        int i2 = 0;
        long[] size_mapping_table = {2 * 1073741824, 4 * 1073741824, 8 * 1073741824, 16 * 1073741824, 32 * 1073741824, 64 * 1073741824, 128 * 1073741824, 256 * 1073741824, 512 * 1073741824};
        String[] size_mapping_table_str = {"2GB", "4GB", "8GB", "16GB", "32GB", "64GB", "128GB", "256GB", "512GB"};
        while (true) {
            i = i2;
            if (i < size_mapping_table.length && size > size_mapping_table[i]) {
                i2 = i + 1;
            }
        }
        if (i == size_mapping_table.length) {
            i--;
        }
        return size_mapping_table_str[i];
    }

    public static String getBaseband_Ver() {
        return SystemProperties.get("gsm.version.baseband");
    }

    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
        if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "";
        }
        return tm.getDeviceId();
    }

    public static String getVersion(int index) {
        String[] version = {"null", "null", "null", "null"};
        try {
            BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/version"), 8192);
            version[0] = localBufferedReader.readLine().split("\\s+")[2];
            localBufferedReader.close();
        } catch (IOException e) {
        }
        version[1] = Build.VERSION.RELEASE;
        version[2] = Build.MODEL;
        version[3] = Build.DISPLAY;
        return version[index];
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
