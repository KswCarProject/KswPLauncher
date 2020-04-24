package com.wits.ksw.launcher.view.ntg5;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NTG5ImageView extends AppCompatImageView implements View.OnFocusChangeListener {
    public static final String TAG = "NTG5ImageView";
    private TextView mTextView;

    public NTG5ImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TextView getTextView() {
        return this.mTextView;
    }

    public void bindTextView(TextView textView) {
        this.mTextView = textView;
    }

    public NTG5ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                NTG5ImageView.this.onFocusChange(view, z);
            }
        });
    }

    public void onFocusChange(View v, boolean hasFocus) {
        Log.d(TAG, "onFocusChange: " + hasFocus);
        if (hasFocus) {
            textViewOnFocus();
        } else {
            textViewUnFocus();
        }
    }

    private void textViewOnFocus() {
        if (this.mTextView != null) {
            this.mTextView.setTextSize(30.0f);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mTextView.getLayoutParams();
            lp.setMargins(0, 0, 0, 144);
            this.mTextView.setLayoutParams(lp);
        }
    }

    private void textViewUnFocus() {
        if (this.mTextView != null) {
            this.mTextView.setTextSize(24.0f);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mTextView.getLayoutParams();
            lp.setMargins(0, 0, 0, 155);
            this.mTextView.setLayoutParams(lp);
        }
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
}
