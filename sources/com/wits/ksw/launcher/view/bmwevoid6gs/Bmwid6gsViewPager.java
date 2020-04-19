package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class Bmwid6gsViewPager extends ViewPager {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + Bmwid6gsViewPager.class.getSimpleName());
    boolean isScrolling;
    /* access modifiers changed from: private */
    public boolean left;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int mViewPagerIndex;
    /* access modifiers changed from: private */
    public boolean right;
    /* access modifiers changed from: private */
    public BmwId6gsViewMode viewMode;

    public Bmwid6gsViewPager(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public Bmwid6gsViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mViewPagerIndex = -1;
        this.left = false;
        this.right = false;
        this.mContext = context;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (!Bmwid6gsViewPager.this.isScrolling) {
                    return;
                }
                if (Bmwid6gsViewPager.this.mViewPagerIndex == arg0) {
                    boolean unused = Bmwid6gsViewPager.this.right = false;
                    boolean unused2 = Bmwid6gsViewPager.this.left = true;
                    return;
                }
                boolean unused3 = Bmwid6gsViewPager.this.right = true;
                boolean unused4 = Bmwid6gsViewPager.this.left = false;
            }

            public void onPageSelected(int i) {
                Bmwid6gsViewPager.this.viewMode.setPageIndex(i);
                switch (i) {
                    case 0:
                        if (!Bmwid6gsViewPager.this.right) {
                            Log.i(Bmwid6gsViewPager.TAG, "onPageSelected: left");
                            BmwId6GsConfig.saveIndex(Bmwid6gsViewPager.this.mContext, 0);
                            Bmwid6gsViewPager.this.viewMode.setIndex(0);
                            break;
                        } else {
                            Log.i(Bmwid6gsViewPager.TAG, "onPageSelected: right");
                            BmwId6GsConfig.saveIndex(Bmwid6gsViewPager.this.mContext, 3);
                            Bmwid6gsViewPager.this.viewMode.setIndex(3);
                            break;
                        }
                    case 1:
                        if (!Bmwid6gsViewPager.this.right) {
                            Log.i(Bmwid6gsViewPager.TAG, "onPageSelected: left");
                            BmwId6GsConfig.saveIndex(Bmwid6gsViewPager.this.mContext, 4);
                            Bmwid6gsViewPager.this.viewMode.setIndex(4);
                            break;
                        } else {
                            Log.i(Bmwid6gsViewPager.TAG, "onPageSelected: right");
                            BmwId6GsConfig.saveIndex(Bmwid6gsViewPager.this.mContext, 7);
                            Bmwid6gsViewPager.this.viewMode.setIndex(7);
                            break;
                        }
                    case 2:
                        BmwId6GsConfig.saveIndex(Bmwid6gsViewPager.this.mContext, 8);
                        Bmwid6gsViewPager.this.viewMode.setIndex(8);
                        break;
                }
                boolean unused = Bmwid6gsViewPager.this.right = Bmwid6gsViewPager.this.left = false;
            }

            public void onPageScrollStateChanged(int arg0) {
                Bmwid6gsViewPager bmwid6gsViewPager = Bmwid6gsViewPager.this;
                boolean z = true;
                if (arg0 != 1) {
                    z = false;
                }
                bmwid6gsViewPager.isScrolling = z;
                if (Bmwid6gsViewPager.this.isScrolling) {
                    int unused = Bmwid6gsViewPager.this.mViewPagerIndex = Bmwid6gsViewPager.this.getCurrentItem();
                }
            }
        });
    }

    public void setViewMode(BmwId6gsViewMode viewMode2) {
        this.viewMode = viewMode2;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 0 || event.getAction() != 1) {
            return true;
        }
        Log.i(TAG, "dispatchKeyEvent: ");
        if (event.getKeyCode() == 19) {
            int index = BmwId6GsConfig.getIndex(this.mContext) - 1;
            Log.i(TAG, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index);
            if (index < 0) {
                index = 0;
            }
            if (index < 8 && index >= 4 && getCurrentItem() != 1) {
                setCurrentItem(1);
            } else if (index < 4 && getCurrentItem() != 0) {
                setCurrentItem(0);
            }
            BmwId6GsConfig.saveIndex(this.mContext, index);
        } else if (event.getKeyCode() == 20) {
            int index2 = BmwId6GsConfig.getIndex(this.mContext) + 1;
            Log.i(TAG, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index2);
            if (index2 > 11) {
                index2 = 11;
            }
            if (index2 >= 4 && index2 <= 7 && getCurrentItem() != 1) {
                setCurrentItem(1);
            } else if (index2 >= 8 && getCurrentItem() != 2) {
                setCurrentItem(2);
            }
            BmwId6GsConfig.saveIndex(this.mContext, index2);
        } else if (event.getKeyCode() == 66) {
            this.viewMode.onClick((View) null, BmwId6GsConfig.getIndex(this.mContext));
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
