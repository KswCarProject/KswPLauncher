package com.wits.ksw.launcher.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

@SuppressLint({"AppCompatCustomView"})
public class CustomBcItemBgImageView extends ImageView {
    private static final String TAG = CustomBcItemBgImageView.class.getName();

    public CustomBcItemBgImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    @SuppressLint({"NewApi"})
    public CustomBcItemBgImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setDefaultFocusHighlightEnabled(false);
    }

    public void translationX(int x) {
        try {
            ObjectAnimator.ofFloat(this, "translationX", new float[]{(float) x}).setDuration(200).start();
            String str = TAG;
            Log.i(str, "<<<<<<<<<<<<<<< translationX: x=" + x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
