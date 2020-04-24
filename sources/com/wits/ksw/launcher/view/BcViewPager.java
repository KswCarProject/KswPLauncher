package com.wits.ksw.launcher.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BcViewPager extends ViewPager {
    public static final float MAX_SCALE = 1.0f;
    public static final float MIN_SCALE = 0.9f;
    /* access modifiers changed from: private */
    public static final String TAG = BcViewPager.class.getName();

    public BcViewPager(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public BcViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true, new ViewPager.PageTransformer() {
            public void transformPage(@NonNull View view, float postion) {
                String access$000 = BcViewPager.TAG;
                Log.i(access$000, "transformPage: postion=" + postion);
            }
        });
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int postion, float v, int i1) {
                String access$000 = BcViewPager.TAG;
                Log.i(access$000, "onPageScrolled: " + postion);
            }

            public void onPageSelected(int postion) {
                String access$000 = BcViewPager.TAG;
                Log.i(access$000, "onPageSelected: ----------------" + postion);
            }

            public void onPageScrollStateChanged(int postion) {
                String access$000 = BcViewPager.TAG;
                Log.i(access$000, "onPageScrollStateChanged: " + postion);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent: ");
        return super.onTouchEvent(ev);
    }
}
