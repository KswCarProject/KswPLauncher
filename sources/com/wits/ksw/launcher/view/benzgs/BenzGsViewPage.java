package com.wits.ksw.launcher.view.benzgs;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

public class BenzGsViewPage extends ViewPager {
    private static final String TAG = ("KSWLauncher." + BenzGsViewPage.class.getSimpleName());
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public BenzGsViewMoel viewMoel;

    public BenzGsViewPage(Context context) {
        this(context, (AttributeSet) null);
    }

    public BenzGsViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {
            }

            public void onPageSelected(int i) {
                BenzGsViewPage.this.viewMoel.setCurrentItem((View) null, i);
                if (i == 0) {
                    BenzGsViewPage.this.viewMoel.setIndex(4);
                    BenzConfig.saveIndex(BenzGsViewPage.this.mContext, 4);
                } else if (i == 1) {
                    BenzGsViewPage.this.viewMoel.setIndex(5);
                    BenzConfig.saveIndex(BenzGsViewPage.this.mContext, 5);
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void setViewMoel(BenzGsViewMoel viewMoel2) {
        this.viewMoel = viewMoel2;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() != 1) {
            return true;
        }
        String str = TAG;
        Log.i(str, "dispatchKeyEvent: ");
        if (event.getKeyCode() == 19) {
            int index = BenzConfig.getIndex(this.mContext) - 1;
            Log.i(str, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index);
            if (index < 0) {
                index = 0;
            }
            BenzConfig.saveIndex(this.mContext, index);
            if (index < 5 && getCurrentItem() != 0) {
                setCurrentItem(0);
            }
        } else if (event.getKeyCode() == 20) {
            int index2 = BenzConfig.getIndex(this.mContext) + 1;
            Log.i(str, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index2);
            if (index2 > 9) {
                index2 = 9;
            }
            BenzConfig.saveIndex(this.mContext, index2);
            if (index2 >= 5 && getCurrentItem() != 1) {
                setCurrentItem(1);
            }
        } else if (event.getKeyCode() == 66) {
            this.viewMoel.onClick(BenzConfig.getIndex(this.mContext));
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
