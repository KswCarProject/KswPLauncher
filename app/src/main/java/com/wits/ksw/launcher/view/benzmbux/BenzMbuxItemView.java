package com.wits.ksw.launcher.view.benzmbux;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

@SuppressLint({"AppCompatCustomView"})
public class BenzMbuxItemView extends ImageView implements View.OnFocusChangeListener {
    private static final String TAG = ("KSWLauncher." + BenzMbuxItemView.class.getSimpleName());

    public BenzMbuxItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BenzMbuxItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= 26) {
            setDefaultFocusHighlightEnabled(false);
        }
        setFocusable(true);
        setOnFocusChangeListener(this);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == 0) {
            if (keyCode == 19 || keyCode == 20) {
                return true;
            }
        } else if (event.getAction() == 1) {
            if (keyCode == 20) {
                sendKeyDownUpSync(22);
                Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_UP---->>>KEYCODE_DPAD_RIGHT");
                return true;
            } else if (keyCode == 19) {
                sendKeyDownUpSync(21);
                Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_DOWN---->>>KEYCODE_DPAD_LEFT");
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
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
