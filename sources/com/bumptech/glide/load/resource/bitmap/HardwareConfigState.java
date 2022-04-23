package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import java.io.File;

final class HardwareConfigState {
    private static final File FD_SIZE_LIST = new File("/proc/self/fd");
    private static final int MAXIMUM_FDS_FOR_HARDWARE_CONFIGS = 700;
    private static final int MINIMUM_DECODES_BETWEEN_FD_CHECKS = 50;
    private static final int MIN_HARDWARE_DIMENSION = 128;
    private static volatile HardwareConfigState instance;
    private volatile int decodesSinceLastFdCheck;
    private volatile boolean isHardwareConfigAllowed = true;

    static HardwareConfigState getInstance() {
        if (instance == null) {
            synchronized (HardwareConfigState.class) {
                if (instance == null) {
                    instance = new HardwareConfigState();
                }
            }
        }
        return instance;
    }

    private HardwareConfigState() {
    }

    /* access modifiers changed from: package-private */
    public boolean setHardwareConfigIfAllowed(int targetWidth, int targetHeight, BitmapFactory.Options optionsWithScaling, DecodeFormat decodeFormat, boolean isHardwareConfigAllowed2, boolean isExifOrientationRequired) {
        if (!isHardwareConfigAllowed2 || Build.VERSION.SDK_INT < 26 || isExifOrientationRequired) {
            return false;
        }
        boolean result = targetWidth >= 128 && targetHeight >= 128 && isFdSizeBelowHardwareLimit();
        if (result) {
            optionsWithScaling.inPreferredConfig = Bitmap.Config.HARDWARE;
            optionsWithScaling.inMutable = false;
        }
        return result;
    }

    private synchronized boolean isFdSizeBelowHardwareLimit() {
        boolean z = true;
        int i = this.decodesSinceLastFdCheck + 1;
        this.decodesSinceLastFdCheck = i;
        if (i >= 50) {
            this.decodesSinceLastFdCheck = 0;
            int currentFds = FD_SIZE_LIST.list().length;
            if (currentFds >= 700) {
                z = false;
            }
            this.isHardwareConfigAllowed = z;
            if (!this.isHardwareConfigAllowed && Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + currentFds + ", limit " + 700);
            }
        }
        return this.isHardwareConfigAllowed;
    }
}
