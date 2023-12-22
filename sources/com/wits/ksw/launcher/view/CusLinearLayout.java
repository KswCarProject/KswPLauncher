package com.wits.ksw.launcher.view;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.utils.KswUtils;

/* loaded from: classes16.dex */
public class CusLinearLayout extends LinearLayout implements View.OnFocusChangeListener {
    private static final String TAG = "CusLinearLayout";
    private int itemRightPostionX;
    int leftX;

    public CusLinearLayout(Context context) {
        this(context, null);
    }

    public CusLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.leftX = 60;
        setOnFocusChangeListener(this);
        setDefaultFocusHighlightEnabled(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        Log.i(TAG, "dispatchKeyEvent: keyCode=" + keyCode);
        if (event.getAction() == 0) {
            if (keyCode == 19 || keyCode == 20) {
                return true;
            }
            if (keyCode == 22) {
            }
        } else if (event.getAction() == 1) {
            if (keyCode == 19) {
                sendKeyDownUpSync(22);
                Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_UP---->>>KEYCODE_DPAD_RIGHT");
                return true;
            } else if (keyCode == 20) {
                sendKeyDownUpSync(21);
                Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_DOWN---->>>KEYCODE_DPAD_LEFT");
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() { // from class: com.wits.ksw.launcher.view.CusLinearLayout.1
            @Override // java.lang.Runnable
            public void run() {
                new Thread(new Runnable() { // from class: com.wits.ksw.launcher.view.CusLinearLayout.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Instrumentation instrumentation = new Instrumentation();
                        instrumentation.sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }

    public void scaleSmail(View view) {
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(view.getContext(), C0899R.anim.scale_out);
        view.startAnimation(scaleAnimation);
    }

    public void scaleBig(View view) {
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(view.getContext(), C0899R.anim.scale_in);
        view.startAnimation(scaleAnimation);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean hasFocus) {
        Log.i(TAG, "onFocusChange: ");
        if (hasFocus) {
            scaleBig(view);
            translationX(view);
            return;
        }
        scaleSmail(view);
    }

    public void translationX(View view) {
        int i = this.leftX;
        itemRightPostionX(view);
        int itemLocationOnScreenX = getItemLocationOnScreenX(view);
        if (itemLocationOnScreenX <= 0) {
            itemLocationOnScreenX = this.leftX;
        }
        int itemPostionX = itemPostionX(itemLocationOnScreenX);
        Log.i(TAG, "translationX: itemPostionX=" + itemPostionX);
        if (itemPostionX >= this.itemRightPostionX) {
            Log.i(TAG, "translationX: \u6700\u53f3\u7aef");
            itemPostionX = this.itemRightPostionX;
        } else if (itemLocationOnScreenX <= this.leftX) {
            itemPostionX = itemPostionX(itemLocationOnScreenX);
        }
        int moveIconPostionX = itemPostionX - 400;
        Log.i(TAG, "translationX: moveIconPostionX=" + moveIconPostionX);
        try {
            MainActivity.mainActivity.bcMainActivity.CustomBcItemBgImageView.translationX(moveIconPostionX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int itemPostionX(int itemLocationOnScreenX) {
        return itemLocationOnScreenX + 116;
    }

    private void itemRightPostionX(View view) {
        int scrrenWidth = KswUtils.screenWidth(view.getContext());
        Log.i(TAG, "itemRightPostionX: scrrenWidth=" + scrrenWidth);
        this.itemRightPostionX = (scrrenWidth - 116) - this.leftX;
        Log.i(TAG, "itemRightPostionX: itemRightPostionX=" + this.itemRightPostionX);
    }

    private int getItemLocationOnScreenX(View view) {
        int[] postionX = new int[2];
        view.getLocationOnScreen(postionX);
        int x = postionX[0];
        Log.i(TAG, "getItemLocationOnScreenX: " + x);
        return x;
    }
}
