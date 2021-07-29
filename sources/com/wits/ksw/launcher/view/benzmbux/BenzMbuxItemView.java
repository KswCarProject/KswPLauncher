package com.wits.ksw.launcher.view.benzmbux;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class BenzMbuxItemView extends ImageView implements View.OnFocusChangeListener {
    private static final String TAG = ("KSWLauncher." + BenzMbuxItemView.class.getSimpleName());

    public BenzMbuxItemView(Context context) {
        this(context, (AttributeSet) null);
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
        new Handler().post(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        new Instrumentation().sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }

    public void onFocusChange(View view, boolean hasFocus) {
        Log.i(TAG, "onFocusChange: ");
    }
}
