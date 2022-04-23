package com.wits.ksw.launcher.view;

import android.content.Context;
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

    public BcViewPager(Context context) {
        this(context, (AttributeSet) null);
    }

    public BcViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true, new ViewPager.PageTransformer() {
            public void transformPage(View view, float postion) {
                Log.i(BcViewPager.TAG, "transformPage: postion=" + postion);
            }
        });
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int postion, float v, int i1) {
                Log.i(BcViewPager.TAG, "onPageScrolled: " + postion);
            }

            public void onPageSelected(int postion) {
                Log.i(BcViewPager.TAG, "onPageSelected: ----------------" + postion);
            }

            public void onPageScrollStateChanged(int postion) {
                Log.i(BcViewPager.TAG, "onPageScrollStateChanged: " + postion);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onTouchEvent: ");
        return super.onTouchEvent(ev);
    }
}
