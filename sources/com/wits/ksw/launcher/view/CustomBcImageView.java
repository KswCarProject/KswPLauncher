package com.wits.ksw.launcher.view;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.KswUtils;

@SuppressLint({"AppCompatCustomView"})
public class CustomBcImageView extends ImageView implements View.OnFocusChangeListener {
    private static final String TAG = "KSWLauncher";
    private int itemHalfWidth;
    private int itemRightPostionX;
    int leftX;
    private int moveItemViewHalfWidth;

    public CustomBcImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomBcImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.leftX = 0;
        this.itemHalfWidth = 0;
        Log.i("KSWLauncher", "CustomBcImageView: " + getWidth());
        setOnFocusChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.itemHalfWidth = getWidth() / 2;
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
                Log.i("KSWLauncher", "dispatchKeyEvent: KEYCODE_DPAD_UP---->>>KEYCODE_DPAD_RIGHT");
                return true;
            } else if (keyCode == 19) {
                sendKeyDownUpSync(21);
                Log.i("KSWLauncher", "dispatchKeyEvent: KEYCODE_DPAD_DOWN---->>>KEYCODE_DPAD_LEFT");
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

    public void scaleSmail(View view) {
        view.startAnimation((ScaleAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.scale_out));
    }

    public void scaleBig(View view) {
        view.startAnimation((ScaleAnimation) AnimationUtils.loadAnimation(view.getContext(), R.anim.scale_in));
    }

    public void onFocusChange(View view, boolean hasFocus) {
        Log.i("KSWLauncher", "onFocusChange: ");
        if (hasFocus) {
            scaleBig(view);
            int X = getItemLocationOnScreenX(view);
            int Y = getItemLocationOnScreenY(view);
            Log.i("KSWLauncher", "onFocusChange: view[" + X + "," + Y + "]");
            translationX(view);
            return;
        }
        scaleSmail(view);
    }

    public void translationX(View view) {
        int i = this.leftX;
        calculateRightPostionX(view);
        int itemLocationOnScreenX = getItemLocationOnScreenX(view);
        Log.i("KSWLauncher", "translationX: itemLocationOnScreenX=" + itemLocationOnScreenX);
        if (itemLocationOnScreenX <= this.leftX) {
            Log.i("KSWLauncher", "translationX: 最左端");
            itemLocationOnScreenX = this.leftX;
        }
        int itemCenterPointX = itemCenterPoint(itemLocationOnScreenX);
        Log.i("KSWLauncher", "translationX: itemCenterPoint=" + itemCenterPointX);
        if (itemCenterPointX >= this.itemRightPostionX) {
            Log.i("KSWLauncher", "translationX: 最右端");
            itemCenterPointX = this.itemRightPostionX;
        } else if (itemLocationOnScreenX <= this.leftX) {
            Log.i("KSWLauncher", "translationX: 最左端");
            itemCenterPointX = itemCenterPoint(itemLocationOnScreenX);
        }
        try {
            this.moveItemViewHalfWidth = MainActivity.mainActivity.bcMainActivity.CustomBcItemBgImageView.getWidth() / 2;
            MainActivity.mainActivity.bcMainActivity.CustomBcItemBgImageView.translationX(itemCenterPointX - this.moveItemViewHalfWidth);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int itemCenterPoint(int itemLocationOnScreenX) {
        return this.itemHalfWidth + itemLocationOnScreenX;
    }

    private void calculateRightPostionX(View view) {
        this.itemRightPostionX = KswUtils.screenWidth(view.getContext()) - this.itemHalfWidth;
        Log.i("KSWLauncher", "calculateRightPostionX: calculateRightPostionX=" + this.itemRightPostionX);
    }

    private int getItemLocationOnScreenX(View view) {
        int[] postion = new int[2];
        view.getLocationOnScreen(postion);
        return postion[0];
    }

    private int getItemLocationOnScreenY(View view) {
        int[] postion = new int[2];
        view.getLocationOnScreen(postion);
        int y = postion[1];
        Log.i("KSWLauncher", "getItemLocationOnScreenY: " + y);
        return y;
    }
}
