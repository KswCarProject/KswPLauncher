package com.wits.ksw.settings.utlis_view;

import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* loaded from: classes10.dex */
public class UsbUtil {
    private static final String USB_CHANGE_PATH = "/sys/kernel/mhl_8334/wits_mhl_mode";
    private static final int USB_HOST_MODE = 1;
    private static final int USB_SLAVE_MODE = 0;
    private static int mCurrentUsbMode;
    private static Thread usbIoThread;
    private static Runnable usbMobeRunnable = new Runnable() { // from class: com.wits.ksw.settings.utlis_view.UsbUtil.1
        @Override // java.lang.Runnable
        public void run() {
            File awakeTimeFile = new File(UsbUtil.USB_CHANGE_PATH);
            try {
                FileWriter fr = new FileWriter(awakeTimeFile);
                fr.write(UsbUtil.mCurrentUsbMode + "");
                fr.close();
            } catch (IOException e) {
                Log.e("UsbModeUtil", "updateUsbMode failed. cause:", e);
            }
        }
    };

    public static void updateUsbMode(boolean isHost) {
        mCurrentUsbMode = isHost ? 1 : 0;
        Thread thread = usbIoThread;
        if (thread != null) {
            thread.interrupt();
        }
        Thread thread2 = new Thread(usbMobeRunnable);
        usbIoThread = thread2;
        thread2.start();
    }
}
