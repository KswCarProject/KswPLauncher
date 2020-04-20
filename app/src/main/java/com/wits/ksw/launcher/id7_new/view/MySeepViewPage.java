package com.wits.ksw.launcher.id7_new.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MySeepViewPage extends ViewPager {
    private float preX = 0.0f;

    public MySeepViewPage(@NonNull Context context) {
        super(context);
    }

    public MySeepViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

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

    public boolean executeKeyEvent(@NonNull KeyEvent event) {
        return false;
    }
}
