package com.wits.ksw.launcher.als_id7.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.p004v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes17.dex */
public class MusicSeekBar extends AppCompatSeekBar {
    private boolean touch;

    public MusicSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.touch = false;
    }

    @Override // android.support.p004v7.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.touch) {
            return super.onTouchEvent(event);
        }
        return false;
    }
}
