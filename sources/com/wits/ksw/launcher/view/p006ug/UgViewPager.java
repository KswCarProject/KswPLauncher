package com.wits.ksw.launcher.view.p006ug;

import android.content.Context;
import android.support.p001v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

/* renamed from: com.wits.ksw.launcher.view.ug.UgViewPager */
/* loaded from: classes9.dex */
public class UgViewPager extends ViewPager {
    private static final String TAG = "KswApplication." + UgViewPager.class.getSimpleName();
    boolean isLeftScroll;
    boolean isScrolling;
    private int lastValue;
    private boolean left;
    public UgPageChangeListener listener;
    private int mViewPagerIndex;
    private boolean right;

    /* renamed from: com.wits.ksw.launcher.view.ug.UgViewPager$UgPageChangeListener */
    /* loaded from: classes9.dex */
    public interface UgPageChangeListener {
        void onPageSelected(int i, boolean left, boolean right);
    }

    public UgViewPager(Context context) {
        this(context, null);
    }

    public UgViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mViewPagerIndex = -1;
        this.left = false;
        this.right = false;
        this.lastValue = -1;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.launcher.view.ug.UgViewPager.1
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (UgViewPager.this.isScrolling) {
                    if (UgViewPager.this.mViewPagerIndex == arg0) {
                        UgViewPager.this.right = false;
                        UgViewPager.this.left = true;
                    } else {
                        UgViewPager.this.right = true;
                        UgViewPager.this.left = false;
                    }
                }
                UgViewPager.this.lastValue = arg2;
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                Log.i(UgViewPager.TAG, "onPageSelected: " + i + " left:" + UgViewPager.this.left + " right:" + UgViewPager.this.right);
                if (UgViewPager.this.listener != null) {
                    UgViewPager.this.listener.onPageSelected(i, UgViewPager.this.left, UgViewPager.this.right);
                    UgViewPager ugViewPager = UgViewPager.this;
                    ugViewPager.right = ugViewPager.left = false;
                }
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int arg0) {
                Log.i(UgViewPager.TAG, "onPageScrollStateChanged: " + arg0);
                UgViewPager.this.isScrolling = arg0 == 1;
                if (UgViewPager.this.isScrolling) {
                    UgViewPager ugViewPager = UgViewPager.this;
                    ugViewPager.mViewPagerIndex = ugViewPager.getCurrentItem();
                }
            }
        });
    }

    public void setUgPageChangeListener(UgPageChangeListener listener) {
        this.listener = listener;
    }
}
