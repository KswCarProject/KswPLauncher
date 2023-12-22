package com.wits.ksw.launcher.id7_new.view;

import android.content.Context;
import android.support.p001v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

/* loaded from: classes17.dex */
public class MySeepViewPage extends ViewPager {
    private float preX;

    public MySeepViewPage(Context context) {
        super(context);
        this.preX = 0.0f;
    }

    public MySeepViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.preX = 0.0f;
    }

    @Override // android.support.p001v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean res = super.onInterceptTouchEvent(event);
        if (event.getAction() == 0) {
            this.preX = event.getX();
        } else if (Math.abs(event.getX() - this.preX) > 0.0f) {
            return true;
        } else {
            this.preX = event.getX();
        }
        return res;
    }

    @Override // android.support.p001v4.view.ViewPager
    public boolean executeKeyEvent(KeyEvent event) {
        return false;
    }
}
