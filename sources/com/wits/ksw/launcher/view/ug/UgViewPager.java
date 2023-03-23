package com.wits.ksw.launcher.view.ug;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

public class UgViewPager extends ViewPager {
    /* access modifiers changed from: private */
    public static final String TAG = ("KswApplication." + UgViewPager.class.getSimpleName());
    boolean isLeftScroll;
    boolean isScrolling;
    /* access modifiers changed from: private */
    public int lastValue;
    /* access modifiers changed from: private */
    public boolean left;
    public UgPageChangeListener listener;
    /* access modifiers changed from: private */
    public int mViewPagerIndex;
    /* access modifiers changed from: private */
    public boolean right;

    public interface UgPageChangeListener {
        void onPageSelected(int i, boolean z, boolean z2);
    }

    public UgViewPager(Context context) {
        this(context, (AttributeSet) null);
    }

    public UgViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mViewPagerIndex = -1;
        this.left = false;
        this.right = false;
        this.lastValue = -1;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (UgViewPager.this.isScrolling) {
                    if (UgViewPager.this.mViewPagerIndex == arg0) {
                        boolean unused = UgViewPager.this.right = false;
                        boolean unused2 = UgViewPager.this.left = true;
                    } else {
                        boolean unused3 = UgViewPager.this.right = true;
                        boolean unused4 = UgViewPager.this.left = false;
                    }
                }
                int unused5 = UgViewPager.this.lastValue = arg2;
            }

            public void onPageSelected(int i) {
                Log.i(UgViewPager.TAG, "onPageSelected: " + i + " left:" + UgViewPager.this.left + " right:" + UgViewPager.this.right);
                if (UgViewPager.this.listener != null) {
                    UgViewPager.this.listener.onPageSelected(i, UgViewPager.this.left, UgViewPager.this.right);
                    UgViewPager ugViewPager = UgViewPager.this;
                    boolean unused = ugViewPager.right = ugViewPager.left = false;
                }
            }

            public void onPageScrollStateChanged(int arg0) {
                Log.i(UgViewPager.TAG, "onPageScrollStateChanged: " + arg0);
                UgViewPager ugViewPager = UgViewPager.this;
                boolean z = true;
                if (arg0 != 1) {
                    z = false;
                }
                ugViewPager.isScrolling = z;
                if (UgViewPager.this.isScrolling) {
                    UgViewPager ugViewPager2 = UgViewPager.this;
                    int unused = ugViewPager2.mViewPagerIndex = ugViewPager2.getCurrentItem();
                }
            }
        });
    }

    public void setUgPageChangeListener(UgPageChangeListener listener2) {
        this.listener = listener2;
    }
}
