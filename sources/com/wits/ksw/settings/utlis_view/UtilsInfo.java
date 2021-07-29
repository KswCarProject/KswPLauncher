package com.wits.ksw.settings.utlis_view;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.ContactsContract;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wits.ksw.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;

public class UtilsInfo {
    private ContactsContract.CommonDataKinds.Phone mPhone;

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
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long size = stat.getBlockCountLong() * stat.getBlockSizeLong();
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
        return SystemProperties.get("gsm.version.baseband");
    }

    public static String getIMEI() {
        return SystemProperties.get("persist.wits.imei");
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

    public static String getSettingsVer(Context context) {
        String pingtai;
        Context context2 = context;
        if (!TextUtils.isEmpty(getBaseband_Ver())) {
            String substring = getBaseband_Ver().substring(0, 4);
            int index_NA = getBaseband_Ver().indexOf("NA");
            int index_platform_M506 = getBaseband_Ver().indexOf("M506");
            int index_platform_M501 = getBaseband_Ver().indexOf("M501");
            int index_platform_8953 = getBaseband_Ver().indexOf("8953");
            int index_platform_8937 = getBaseband_Ver().indexOf("8937");
            int index_platform_8917 = getBaseband_Ver().indexOf("8917");
            int index_platform_511 = getBaseband_Ver().indexOf("M511");
            int index_platform_600 = getBaseband_Ver().indexOf("M600");
            int index_platform_450 = getBaseband_Ver().indexOf("SDM450");
            if (index_platform_M506 > -1) {
                pingtai = "M506";
            } else if (index_platform_8917 > -1) {
                pingtai = "M506";
            } else if (index_platform_450 > -1) {
                pingtai = "450";
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
            } else {
                pingtai = "8953";
            }
            String string = context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai});
            if (index_NA <= -1) {
                if (index_platform_M501 > -1) {
                    return context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai + "EA-1"});
                }
                return context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai + "EA"});
            } else if (index_platform_M501 > -1) {
                int i = index_NA;
                return context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai + "NA"});
            } else {
                return context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai + "NA"});
            }
        } else {
            return context2.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE});
        }
    }

    public static String getSettingsVersion(Context context) {
        String pingtai;
        if (!TextUtils.isEmpty(getBaseband_Ver())) {
            String substring = getBaseband_Ver().substring(0, 4);
            int index_NA = getBaseband_Ver().indexOf("NA");
            int index_platform_M506 = getBaseband_Ver().indexOf("M506");
            int index_platform_M501 = getBaseband_Ver().indexOf("M501");
            int index_platform_8953 = getBaseband_Ver().indexOf("8953");
            int index_platform_8937 = getBaseband_Ver().indexOf("8937");
            int index_platform_8917 = getBaseband_Ver().indexOf("8917");
            int index_platform_511 = getBaseband_Ver().indexOf("M511");
            int index_platform_600 = getBaseband_Ver().indexOf("M600");
            int index_platform_450 = getBaseband_Ver().indexOf("SDM450");
            if (index_platform_M506 > -1) {
                pingtai = "M506";
            } else if (index_platform_8917 > -1) {
                pingtai = "M506";
            } else if (index_platform_450 > -1) {
                pingtai = "450";
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
            } else {
                pingtai = "8953";
            }
            String string = context.getString(R.string.audi_set_sys_info_system_ver, new Object[]{Build.VERSION.RELEASE + "-" + pingtai});
            if (index_NA > -1) {
                if (index_platform_M501 > -1) {
                    return Build.VERSION.RELEASE + "-" + pingtai + "NA";
                }
                return Build.VERSION.RELEASE + "-" + pingtai + "NA";
            } else if (index_platform_M501 > -1) {
                return Build.VERSION.RELEASE + "-" + pingtai + "EA-1";
            } else {
                return Build.VERSION.RELEASE + "-" + pingtai + "EA";
            }
        } else {
            Context context2 = context;
            return Build.VERSION.RELEASE;
        }
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(1);
        int month = calendar.get(2) + 1;
        int day = calendar.get(5);
        if (year >= 2019) {
            return year + "-" + month + "-" + day;
        }
        return "null";
    }

    public static void showQRCode(Context context) {
        File file = new File("/mnt/vendor/persist/OEM/qrCode.jpg");
        DialogViews dialogViews = new DialogViews(context);
        if (file.exists()) {
            dialogViews.qrCode(BitmapFactory.decodeFile(file.getPath()));
        } else if (generateQRCode(context, true)) {
            dialogViews.qrCode(BitmapFactory.decodeFile(file.getPath()));
        }
    }

    public static boolean generateQRCode(Context context, boolean show) {
        if (!"null".equals(getDate())) {
            saveBitmap("qrCode.jpg", createQRCodeBitmap(getIMEI() + "," + getDate(), 300, 300, "", "", "5", ViewCompat.MEASURED_STATE_MASK, -1), context);
            return true;
        } else if (!show) {
            return false;
        } else {
            ToastUtils.showToastShort(context, context.getString(R.string.date_error));
            return false;
        }
    }

    public static Bitmap createQRCodeBitmap(String content, int width, int height, String character_set, String error_correction_level, String margin, int color_black, int color_white) {
        int i = width;
        int i2 = height;
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        if (i < 0) {
            String str = character_set;
            String str2 = error_correction_level;
            String str3 = margin;
        } else if (i2 < 0) {
            String str4 = character_set;
            String str5 = error_correction_level;
            String str6 = margin;
        } else {
            try {
                Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                if (!TextUtils.isEmpty(character_set)) {
                    try {
                        hints.put(EncodeHintType.CHARACTER_SET, character_set);
                    } catch (WriterException e) {
                        e = e;
                        String str7 = error_correction_level;
                        String str8 = margin;
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    String str9 = character_set;
                }
                if (!TextUtils.isEmpty(error_correction_level)) {
                    try {
                        hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
                    } catch (WriterException e2) {
                        e = e2;
                        String str82 = margin;
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    String str10 = error_correction_level;
                }
                if (!TextUtils.isEmpty(margin)) {
                    try {
                        hints.put(EncodeHintType.MARGIN, margin);
                    } catch (WriterException e3) {
                        e = e3;
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    String str11 = margin;
                }
                BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                int[] pixels = new int[(i * i2)];
                for (int y = 0; y < i2; y++) {
                    for (int x = 0; x < i; x++) {
                        if (bitMatrix.get(x, y)) {
                            pixels[(y * i) + x] = color_black;
                        } else {
                            pixels[(y * i) + x] = color_white;
                        }
                    }
                }
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                Bitmap bitmap = createBitmap;
                createBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                return bitmap;
            } catch (WriterException e4) {
                e = e4;
                String str12 = character_set;
                String str72 = error_correction_level;
                String str822 = margin;
                e.printStackTrace();
                return null;
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
