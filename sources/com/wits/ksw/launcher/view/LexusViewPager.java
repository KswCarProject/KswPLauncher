package com.wits.ksw.launcher.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class LexusViewPager extends ViewPager {
    private static final int LEFT_TO_RIGHT = 1;
    private static final int RIGHT_TO_LEFT = 2;
    private static final String TAG = LexusViewPager.class.getName();
    private static int direction = 0;
    private boolean canScroll;
    private boolean firstMove;
    private boolean isUp;
    private float lastX;

    public LexusViewPager(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public LexusViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.canScroll = true;
        this.isUp = true;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.isUp = false;
                this.lastX = event.getX();
                break;
            case 1:
                this.firstMove = false;
                this.isUp = true;
                break;
            case 2:
                if (event.getX() - this.lastX >= 0.0f) {
                    direction = 1;
                    break;
                } else {
                    direction = 2;
                    break;
                }
        }
        String str = TAG;
        Log.i(str, "dispatchTouchEvent//: isUp=" + this.isUp + " direction=" + direction + " getCurrentItem=" + getCurrentItem() + " action=" + event.getAction() + " lastX=" + this.lastX + " getX=" + event.getX());
        if (this.isUp) {
            this.canScroll = true;
            return super.dispatchTouchEvent(event);
        } else if (direction == 2 && getCurrentItem() == 1) {
            this.canScroll = false;
            return true;
        } else {
            this.canScroll = true;
            return super.dispatchTouchEvent(event);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        String str = TAG;
        Log.d(str, "onInterceptTouchEvent action=" + event.getAction());
        if (this.canScroll) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        String str = TAG;
        Log.d(str, "onTouchEvent action=" + event.getAction() + " direction=" + direction + " getCurrentItem=" + getCurrentItem());
        if (!this.canScroll) {
            return false;
        }
        if (event.getAction() != 1 || direction != 2 || getCurrentItem() != 1) {
            return super.onTouchEvent(event);
        }
        boolean b = super.onTouchEvent(event);
        setCurrentItem(1);
        invalidate();
        return b;
    }
}
