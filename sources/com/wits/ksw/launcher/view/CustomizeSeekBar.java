package com.wits.ksw.launcher.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes16.dex */
public class CustomizeSeekBar extends FrameLayout implements SkinCompatSupportable {
    private static final String TAG = CustomizeSeekBar.class.getSimpleName();
    private Drawable background;
    private Drawable bgIndicator;
    private Drawable bgProgress;
    private LinearLayoutCompat linearLayoutCompat;
    private Context mContext;
    private int mProgress;

    /* renamed from: mx */
    private float f194mx;
    private MySeekBarChangeListener seekBarChangeListener;
    private float splitWidth;
    private TypedArray typedArray;

    /* loaded from: classes16.dex */
    public interface MySeekBarChangeListener {
        void onProgressChanged(CustomizeSeekBar seekBar);

        void onStopTrackingTouch(CustomizeSeekBar seekBar);
    }

    public CustomizeSeekBar(Context context) {
        super(context);
        this.mProgress = 0;
        this.mContext = context;
    }

    public CustomizeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mProgress = 0;
        this.mContext = context;
        setClickable(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, C0899R.styleable.CustomizeSeekBar);
        this.typedArray = obtainStyledAttributes;
        this.background = obtainStyledAttributes.getDrawable(0);
        this.bgProgress = this.typedArray.getDrawable(2);
        this.bgIndicator = this.typedArray.getDrawable(1);
        if (SkinCompatResources.getInstance() != null) {
            this.background = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(0, 0));
            this.bgProgress = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(2, 2));
            this.bgIndicator = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(1, 1));
        }
        Log.e(TAG, "background =" + this.background + " bgProgress=" + this.bgProgress + " bgIndicator=" + this.bgIndicator + " mProgress" + this.mProgress);
        int bgProgressWidth = this.bgProgress.getIntrinsicWidth();
        this.splitWidth = bgProgressWidth / 100.0f;
        this.mProgress = 0;
        initView();
    }

    public int getProgress() {
        return this.mProgress;
    }

    private void initView() {
        addBackground();
        addProgress(this.mProgress);
        invalidate();
    }

    private void addBackground() {
        removeAllViews();
        ImageView bgIv = new ImageView(this.mContext);
        ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(this.background.getIntrinsicWidth(), this.background.getIntrinsicHeight());
        bgIv.setLayoutParams(params);
        bgIv.setBackground(this.background);
        addView(bgIv);
    }

    public void addProgress(int progress) {
        ViewGroup.LayoutParams paramsLinerLayout;
        ViewGroup.LayoutParams paramsProgress;
        if (this.bgProgress == null || this.bgIndicator == null) {
            return;
        }
        LinearLayoutCompat linearLayoutCompat = this.linearLayoutCompat;
        if (linearLayoutCompat != null) {
            removeView(linearLayoutCompat);
        }
        this.mProgress = progress;
        int width = (int) (this.splitWidth * progress);
        if (width > this.bgProgress.getIntrinsicWidth()) {
            width = this.bgProgress.getIntrinsicWidth();
        }
        this.linearLayoutCompat = new LinearLayoutCompat(this.mContext);
        if (this.mProgress == 99) {
            paramsLinerLayout = new FrameLayout.LayoutParams(this.bgProgress.getIntrinsicWidth(), this.bgProgress.getIntrinsicHeight());
        } else {
            paramsLinerLayout = new FrameLayout.LayoutParams(this.bgIndicator.getIntrinsicWidth() + width, this.bgProgress.getIntrinsicHeight());
        }
        this.linearLayoutCompat.setLayoutParams(paramsLinerLayout);
        this.linearLayoutCompat.setOrientation(0);
        if (width != 0) {
            ImageView ivProgress = new ImageView(this.mContext);
            if (this.mProgress == 99) {
                paramsProgress = new FrameLayout.LayoutParams(this.bgProgress.getIntrinsicWidth(), this.bgProgress.getIntrinsicHeight());
            } else {
                paramsProgress = new FrameLayout.LayoutParams(width, this.bgProgress.getIntrinsicHeight());
            }
            ivProgress.setLayoutParams(paramsProgress);
            ivProgress.setBackground(this.bgProgress);
            ivProgress.setScaleType(ImageView.ScaleType.FIT_XY);
            this.linearLayoutCompat.addView(ivProgress);
        }
        int i = this.mProgress;
        if (i > 0 && i < 99) {
            ImageView ivIndicator = new ImageView(this.mContext);
            ViewGroup.LayoutParams paramsProgress2 = new FrameLayout.LayoutParams(this.bgIndicator.getIntrinsicWidth(), this.bgIndicator.getIntrinsicHeight());
            ivIndicator.setLayoutParams(paramsProgress2);
            ivIndicator.setBackground(this.bgIndicator);
            this.linearLayoutCompat.addView(ivIndicator);
        }
        addView(this.linearLayoutCompat);
        invalidate();
    }

    public static void setProgress(final CustomizeSeekBar seekBar, int progress) {
        Log.e(TAG, "setProgress progress" + progress);
        if (seekBar != null) {
            seekBar.addProgress(progress);
        }
    }

    public void setOnSeekBarChangeListener(MySeekBarChangeListener seekBarChangeListener) {
        this.seekBarChangeListener = seekBarChangeListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "--dispatchTouchEvent---->" + ev.getAction());
        if (this.seekBarChangeListener == null) {
            return super.dispatchTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case 1:
            case 2:
                calculateTouchEvent(ev);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void calculateTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        this.f194mx = x;
        int progress = (int) (x / this.splitWidth);
        Log.e(TAG, " calculateTouchEvent progress" + progress + " mx=" + this.f194mx + " splitWidth=" + this.splitWidth);
        addProgress(progress);
        if (ev.getAction() == 2) {
            this.seekBarChangeListener.onProgressChanged(this);
            return;
        }
        MySeekBarChangeListener mySeekBarChangeListener = this.seekBarChangeListener;
        if (mySeekBarChangeListener != null) {
            mySeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        Log.e(TAG, "applySkin");
        if (SkinCompatResources.getInstance() != null && this.typedArray != null) {
            this.background = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(0, 0));
            this.bgProgress = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(2, 2));
            this.bgIndicator = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(1, 1));
            initView();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "--onInterceptTouchEvent---->" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "--onTouchEvent---->" + event.getAction());
        return super.onTouchEvent(event);
    }
}
