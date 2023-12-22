package com.wits.ksw.launcher.view.benzmbux;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes9.dex */
public class BenzMbuxItemView extends ImageView implements View.OnFocusChangeListener {
    private static final String TAG = "KswApplication." + BenzMbuxItemView.class.getSimpleName();

    public BenzMbuxItemView(Context context) {
        this(context, null);
    }

    public BenzMbuxItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= 26) {
            setDefaultFocusHighlightEnabled(false);
        }
        setFocusable(true);
        setOnFocusChangeListener(this);
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() { // from class: com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView.1
            @Override // java.lang.Runnable
            public void run() {
                new Thread(new Runnable() { // from class: com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Instrumentation instrumentation = new Instrumentation();
                        instrumentation.sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean hasFocus) {
        Log.i(TAG, "onFocusChange: ");
    }
}
