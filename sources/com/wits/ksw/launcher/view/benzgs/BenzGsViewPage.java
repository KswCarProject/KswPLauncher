package com.wits.ksw.launcher.view.benzgs;

import android.content.Context;
import android.support.p001v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

/* loaded from: classes5.dex */
public class BenzGsViewPage extends ViewPager {
    private static final String TAG = "KswApplication." + BenzGsViewPage.class.getSimpleName();
    private Context mContext;
    private BenzGsViewMoel viewMoel;

    public BenzGsViewPage(Context context) {
        this(context, null);
    }

    public BenzGsViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.wits.ksw.launcher.view.benzgs.BenzGsViewPage.1
            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                BenzGsViewPage.this.viewMoel.setCurrentItem(null, i);
                if (i == 0) {
                    BenzGsViewPage.this.viewMoel.setIndex(4);
                    BenzConfig.saveIndex(BenzGsViewPage.this.mContext, 4);
                } else if (i == 1) {
                    BenzGsViewPage.this.viewMoel.setIndex(5);
                    BenzConfig.saveIndex(BenzGsViewPage.this.mContext, 5);
                }
            }

            @Override // android.support.p001v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void setViewMoel(BenzGsViewMoel viewMoel) {
        this.viewMoel = viewMoel;
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == 1) {
            String str = TAG;
            Log.i(str, "dispatchKeyEvent: ");
            if (event.getKeyCode() == 19) {
                int index = BenzConfig.getIndex(this.mContext);
                int index2 = index - 1;
                Log.i(str, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index2);
                if (index2 < 0) {
                    index2 = 0;
                }
                BenzConfig.saveIndex(this.mContext, index2);
                if (index2 < 5 && getCurrentItem() != 0) {
                    setCurrentItem(0);
                }
            } else if (event.getKeyCode() == 20) {
                int index3 = BenzConfig.getIndex(this.mContext);
                int index4 = index3 + 1;
                Log.i(str, "dispatchKeyEvent: " + event.getAction() + "\t " + event.getKeyCode() + "\t" + index4);
                if (index4 > 9) {
                    index4 = 9;
                }
                BenzConfig.saveIndex(this.mContext, index4);
                if (index4 >= 5 && getCurrentItem() != 1) {
                    setCurrentItem(1);
                }
            } else if (event.getKeyCode() == 66) {
                int index5 = BenzConfig.getIndex(this.mContext);
                this.viewMoel.onClick(index5);
                return true;
            }
            return super.dispatchKeyEvent(event);
        }
        return true;
    }
}
