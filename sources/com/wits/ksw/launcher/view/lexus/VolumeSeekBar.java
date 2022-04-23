package com.wits.ksw.launcher.view.lexus;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class VolumeSeekBar extends AppCompatSeekBar {
    private boolean touch;

    public VolumeSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.touch = false;
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setTouch(boolean touch2) {
        this.touch = touch2;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.touch) {
            return super.onTouchEvent(event);
        }
        return false;
    }
}
