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
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.utils.KswUtils;

/* loaded from: classes16.dex */
public class CustomBcImageView extends ImageView implements View.OnFocusChangeListener {
    private static final String TAG = "KswApplication";
    private int itemHalfWidth;
    private int itemRightPostionX;
    int leftX;
    private int moveItemViewHalfWidth;

    public CustomBcImageView(Context context) {
        this(context, null);
    }

    public CustomBcImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.leftX = 0;
        this.itemHalfWidth = 0;
        Log.i("KswApplication", "CustomBcImageView: " + getWidth());
        setOnFocusChangeListener(this);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.itemHalfWidth = getWidth() / 2;
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == 0) {
            if (keyCode == 19 || keyCode == 20) {
                return true;
            }
        } else if (event.getAction() == 1) {
            if (keyCode == 20) {
                sendKeyDownUpSync(22);
                Log.i("KswApplication", "dispatchKeyEvent: KEYCODE_DPAD_UP---->>>KEYCODE_DPAD_RIGHT");
                return true;
            } else if (keyCode == 19) {
                sendKeyDownUpSync(21);
                Log.i("KswApplication", "dispatchKeyEvent: KEYCODE_DPAD_DOWN---->>>KEYCODE_DPAD_LEFT");
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() { // from class: com.wits.ksw.launcher.view.CustomBcImageView.1
            @Override // java.lang.Runnable
            public void run() {
                new Thread(new Runnable() { // from class: com.wits.ksw.launcher.view.CustomBcImageView.1.1
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
        Log.i("KswApplication", "onFocusChange: ");
        if (hasFocus) {
            scaleBig(view);
            int X = getItemLocationOnScreenX(view);
            int Y = getItemLocationOnScreenY(view);
            Log.i("KswApplication", "onFocusChange: view[" + X + "," + Y + "]");
            translationX(view);
            return;
        }
        scaleSmail(view);
    }

    public void translationX(View view) {
        int i = this.leftX;
        calculateRightPostionX(view);
        int itemLocationOnScreenX = getItemLocationOnScreenX(view);
        Log.i("KswApplication", "translationX: itemLocationOnScreenX=" + itemLocationOnScreenX);
        if (itemLocationOnScreenX <= this.leftX) {
            Log.i("KswApplication", "translationX: \u6700\u5de6\u7aef");
            itemLocationOnScreenX = this.leftX;
        }
        int itemCenterPointX = itemCenterPoint(itemLocationOnScreenX);
        Log.i("KswApplication", "translationX: itemCenterPoint=" + itemCenterPointX);
        if (itemCenterPointX >= this.itemRightPostionX) {
            Log.i("KswApplication", "translationX: \u6700\u53f3\u7aef");
            itemCenterPointX = this.itemRightPostionX;
        } else if (itemLocationOnScreenX <= this.leftX) {
            Log.i("KswApplication", "translationX: \u6700\u5de6\u7aef");
            itemCenterPointX = itemCenterPoint(itemLocationOnScreenX);
        }
        try {
            int width = MainActivity.mainActivity.bcMainActivity.CustomBcItemBgImageView.getWidth() / 2;
            this.moveItemViewHalfWidth = width;
            int moveIconPostionX = itemCenterPointX - width;
            MainActivity.mainActivity.bcMainActivity.CustomBcItemBgImageView.translationX(moveIconPostionX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int itemCenterPoint(int itemLocationOnScreenX) {
        return this.itemHalfWidth + itemLocationOnScreenX;
    }

    private void calculateRightPostionX(View view) {
        int scrrenWidth = KswUtils.screenWidth(view.getContext());
        this.itemRightPostionX = scrrenWidth - this.itemHalfWidth;
        Log.i("KswApplication", "calculateRightPostionX: calculateRightPostionX=" + this.itemRightPostionX);
    }

    private int getItemLocationOnScreenX(View view) {
        int[] postion = new int[2];
        view.getLocationOnScreen(postion);
        int x = postion[0];
        return x;
    }

    private int getItemLocationOnScreenY(View view) {
        int[] postion = new int[2];
        view.getLocationOnScreen(postion);
        int y = postion[1];
        Log.i("KswApplication", "getItemLocationOnScreenY: " + y);
        return y;
    }
}
