package com.bumptech.glide.load.engine.executor;

import android.os.Build;
import android.os.StrictMode;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class RuntimeCompat {
    private static final String CPU_LOCATION = "/sys/devices/system/cpu/";
    private static final String CPU_NAME_REGEX = "cpu[0-9]+";
    private static final String TAG = "GlideRuntimeCompat";

    private RuntimeCompat() {
    }

    static int availableProcessors() {
        int cpus = Runtime.getRuntime().availableProcessors();
        if (Build.VERSION.SDK_INT < 17) {
            return Math.max(getCoreCountPre17(), cpus);
        }
        return cpus;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int getCoreCountPre17() {
        File[] cpus = null;
        StrictMode.ThreadPolicy originalPolicy = StrictMode.allowThreadDiskReads();
        try {
            File cpuInfo = new File(CPU_LOCATION);
            final Pattern cpuNamePattern = Pattern.compile(CPU_NAME_REGEX);
            cpus = cpuInfo.listFiles(new FilenameFilter() { // from class: com.bumptech.glide.load.engine.executor.RuntimeCompat.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String s) {
                    return cpuNamePattern.matcher(s).matches();
                }
            });
        } finally {
            try {
                StrictMode.setThreadPolicy(originalPolicy);
                return Math.max(1, cpus == null ? cpus.length : 0);
            } catch (Throwable th) {
            }
        }
        StrictMode.setThreadPolicy(originalPolicy);
        return Math.max(1, cpus == null ? cpus.length : 0);
    }
}
