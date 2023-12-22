package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.content.Context;
import android.support.p001v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

/* loaded from: classes5.dex */
public class Bmwid6gsViewPager extends ViewPager {
    private static final String TAG = "KswApplication." + Bmwid6gsViewPager.class.getSimpleName();
    boolean isScrolling;
    private boolean left;
    private Context mContext;
    private int mViewPagerIndex;
    private boolean right;
    private BmwId6gsViewMode viewMode;

    public Bmwid6gsViewPager(Context context) {
        this(context, null);
    }

    public Bmwid6gsViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mViewPagerIndex = -1;
        this.left = false;
        this.right = false;
        this.mContext = context;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsViewPager.1
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                if (Bmwid6gsViewPager.this.isScrolling) {
                    if (Bmwid6gsViewPager.this.mViewPagerIndex == arg0) {
                        Bmwid6gsViewPager.this.right = false;
                        Bmwid6gsViewPager.this.left = true;
                        return;
                    }
                    Bmwid6gsViewPager.this.right = true;
                    Bmwid6gsViewPager.this.left = false;
                }
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
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
                Bmwid6gsViewPager bmwid6gsViewPager = Bmwid6gsViewPager.this;
                bmwid6gsViewPager.right = bmwid6gsViewPager.left = false;
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int arg0) {
                Bmwid6gsViewPager.this.isScrolling = arg0 == 1;
                if (Bmwid6gsViewPager.this.isScrolling) {
                    Bmwid6gsViewPager bmwid6gsViewPager = Bmwid6gsViewPager.this;
                    bmwid6gsViewPager.mViewPagerIndex = bmwid6gsViewPager.getCurrentItem();
                }
            }
        });
    }

    public void setViewMode(BmwId6gsViewMode viewMode) {
        this.viewMode = viewMode;
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() != 0 && event.getAction() == 1) {
            String str = TAG;
            Log.i(str, "dispatchKeyEvent: ");
            if (event.getKeyCode() == 19) {
                int index = BmwId6GsConfig.getIndex(this.mContext);
                int index2 = index - 1;
                Log.i(str, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index2);
                if (index2 < 0) {
                    index2 = 0;
                }
                if (index2 < 8 && index2 >= 4 && getCurrentItem() != 1) {
                    setCurrentItem(1);
                } else if (index2 < 4 && getCurrentItem() != 0) {
                    setCurrentItem(0);
                }
                BmwId6GsConfig.saveIndex(this.mContext, index2);
            } else if (event.getKeyCode() == 20) {
                int index3 = BmwId6GsConfig.getIndex(this.mContext);
                int index4 = index3 + 1;
                Log.i(str, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index4);
                if (index4 > 11) {
                    index4 = 11;
                }
                if (index4 >= 4 && index4 <= 7 && getCurrentItem() != 1) {
                    setCurrentItem(1);
                } else if (index4 >= 8 && getCurrentItem() != 2) {
                    setCurrentItem(2);
                }
                BmwId6GsConfig.saveIndex(this.mContext, index4);
            } else if (event.getKeyCode() == 66) {
                int index5 = BmwId6GsConfig.getIndex(this.mContext);
                this.viewMode.onClick(null, index5);
                return true;
            }
            return super.dispatchKeyEvent(event);
        }
        return true;
    }
}
