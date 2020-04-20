package com.wits.ksw.launcher.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.wits.ksw.R;

public class ColorArcProgressBar extends View {
    private final int DEGREE_PROGRESS_DISTANCE = dipToPx(8.0f);
    private Paint allArcPaint;
    private int aniSpeed = 200;
    private int bgArcColor = -15658735;
    private float bgArcWidth = ((float) dipToPx(10.0f));
    private RectF bgRect;
    private float centerX;
    private float centerY;
    private int[] colors = {-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
    private Paint curSpeedPaint;
    private float curSpeedSize = ((float) dipToPx(13.0f));
    /* access modifiers changed from: private */
    public float currentAngle = 0.0f;
    private Paint degreePaint;
    private int diameter = 500;
    private int hintColor = -10000537;
    private Paint hintPaint;
    private float hintSize = ((float) dipToPx(15.0f));
    private String hintString;
    private boolean isAutoTextSize = true;
    private boolean isNeedContent;
    private boolean isNeedDial;
    private boolean isNeedTitle;
    private boolean isNeedUnit;
    private boolean isShowCurrentSpeed = true;
    /* access modifiers changed from: private */
    public float k;
    private float lastAngle;
    private OnSeekArcChangeListener listener;
    private float longDegree = ((float) dipToPx(13.0f));
    private int longDegreeColor = -15658735;
    private PaintFlagsDrawFilter mDrawFilter;
    private int mHeight;
    private float mTouchInvalidateRadius;
    private int mWidth;
    private float maxValues = 60.0f;
    /* access modifiers changed from: private */
    public float progress = 0.0f;
    private ValueAnimator progressAnimator;
    private Paint progressPaint;
    private float progressWidth = ((float) dipToPx(10.0f));
    private Matrix rotateMatrix;
    private boolean seekEnable;
    private float shortDegree = ((float) dipToPx(5.0f));
    private int shortDegreeColor = -15658735;
    private float startAngle = 135.0f;
    private float sweepAngle = 270.0f;
    private SweepGradient sweepGradient;
    private float textSize = ((float) dipToPx(60.0f));
    private String titleString;
    private Paint vTextPaint;

    public interface OnSeekArcChangeListener {
        void onProgressChanged(ColorArcProgressBar colorArcProgressBar, int i, boolean z);

        void onStartTrackingTouch(ColorArcProgressBar colorArcProgressBar);

        void onStopTrackingTouch(ColorArcProgressBar colorArcProgressBar);
    }

    public ColorArcProgressBar(Context context) {
        super(context, (AttributeSet) null);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initConfig(context, attrs);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig(context, attrs);
        initView();
    }

    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorArcProgressBar);
        int color1 = a.getColor(4, -16711936);
        int color2 = a.getColor(5, color1);
        int color3 = a.getColor(6, color1);
        this.bgArcColor = a.getColor(0, -15658735);
        this.longDegreeColor = a.getColor(3, -15658735);
        this.shortDegreeColor = a.getColor(3, -15658735);
        this.hintColor = a.getColor(8, -10000537);
        this.colors = new int[]{color1, color2, color3, color3};
        this.sweepAngle = (float) a.getInteger(17, 270);
        this.bgArcWidth = a.getDimension(1, (float) dipToPx(10.0f));
        this.progressWidth = a.getDimension(7, (float) dipToPx(10.0f));
        this.seekEnable = a.getBoolean(13, false);
        this.isNeedTitle = a.getBoolean(11, false);
        this.isNeedContent = a.getBoolean(9, false);
        this.isNeedUnit = a.getBoolean(12, false);
        this.isNeedDial = a.getBoolean(10, false);
        this.hintString = a.getString(16);
        this.titleString = a.getString(15);
        this.progress = a.getFloat(2, 0.0f);
        this.maxValues = a.getFloat(14, 60.0f);
        setProgress(this.progress);
        setMaxValues(this.maxValues);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        Log.v("ColorArcProgressBar", "onSizeChanged: mWidth:" + this.mWidth + " mHeight:" + this.mHeight);
        this.diameter = (int) (((float) Math.min(this.mWidth, this.mHeight)) - (((this.longDegree + ((float) this.DEGREE_PROGRESS_DISTANCE)) + (this.progressWidth / 2.0f)) * 2.0f));
        StringBuilder sb = new StringBuilder();
        sb.append("onSizeChanged: diameter:");
        sb.append(this.diameter);
        Log.v("ColorArcProgressBar", sb.toString());
        this.bgRect = new RectF();
        this.bgRect.top = this.longDegree + ((float) this.DEGREE_PROGRESS_DISTANCE) + (this.progressWidth / 2.0f);
        this.bgRect.left = this.longDegree + ((float) this.DEGREE_PROGRESS_DISTANCE) + (this.progressWidth / 2.0f);
        this.bgRect.right = ((float) this.diameter) + this.longDegree + (this.progressWidth / 2.0f) + ((float) this.DEGREE_PROGRESS_DISTANCE);
        this.bgRect.bottom = ((float) this.diameter) + this.longDegree + (this.progressWidth / 2.0f) + ((float) this.DEGREE_PROGRESS_DISTANCE);
        Log.v("ColorArcProgressBar", "initView: " + this.diameter);
        this.centerX = ((((this.longDegree + ((float) this.DEGREE_PROGRESS_DISTANCE)) + (this.progressWidth / 2.0f)) * 2.0f) + ((float) this.diameter)) / 2.0f;
        this.centerY = ((((this.longDegree + ((float) this.DEGREE_PROGRESS_DISTANCE)) + (this.progressWidth / 2.0f)) * 2.0f) + ((float) this.diameter)) / 2.0f;
        this.sweepGradient = new SweepGradient(this.centerX, this.centerY, this.colors, (float[]) null);
        this.mTouchInvalidateRadius = 50.0f;
        if (this.isAutoTextSize) {
            this.textSize = (float) (((double) this.diameter) * 0.3d);
            this.hintSize = (float) (((double) this.diameter) * 0.1d);
            this.curSpeedSize = (float) (((double) this.diameter) * 0.1d);
            this.vTextPaint.setTextSize(this.textSize);
            this.hintPaint.setTextSize(this.hintSize);
            this.curSpeedPaint.setTextSize(this.curSpeedSize);
        }
    }

    private void initView() {
        this.degreePaint = new Paint();
        this.degreePaint.setColor(this.longDegreeColor);
        this.allArcPaint = new Paint();
        this.allArcPaint.setAntiAlias(true);
        this.allArcPaint.setStyle(Paint.Style.STROKE);
        this.allArcPaint.setStrokeWidth(this.bgArcWidth);
        this.allArcPaint.setColor(this.bgArcColor);
        this.allArcPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.progressPaint = new Paint();
        this.progressPaint.setAntiAlias(true);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.progressPaint.setStrokeWidth(this.progressWidth);
        this.progressPaint.setColor(-16776961);
        this.vTextPaint = new Paint();
        this.vTextPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.vTextPaint.setTextAlign(Paint.Align.CENTER);
        this.hintPaint = new Paint();
        this.hintPaint.setColor(this.hintColor);
        this.hintPaint.setTextAlign(Paint.Align.CENTER);
        this.curSpeedPaint = new Paint();
        this.curSpeedPaint.setColor(this.hintColor);
        this.curSpeedPaint.setTextAlign(Paint.Align.CENTER);
        this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
        this.rotateMatrix = new Matrix();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.mDrawFilter);
        if (this.isNeedDial) {
            for (int i = 0; i < 40; i++) {
                if (i <= 15 || i >= 25) {
                    if (i % 5 == 0) {
                        this.degreePaint.setStrokeWidth((float) dipToPx(2.0f));
                        this.degreePaint.setColor(this.longDegreeColor);
                        canvas.drawLine(this.centerX, ((this.centerY - ((float) (this.diameter / 2))) - (this.progressWidth / 2.0f)) - ((float) this.DEGREE_PROGRESS_DISTANCE), this.centerX, (((this.centerY - ((float) (this.diameter / 2))) - (this.progressWidth / 2.0f)) - ((float) this.DEGREE_PROGRESS_DISTANCE)) - this.longDegree, this.degreePaint);
                    } else {
                        this.degreePaint.setStrokeWidth((float) dipToPx(1.4f));
                        this.degreePaint.setColor(this.shortDegreeColor);
                        canvas.drawLine(this.centerX, (((this.centerY - ((float) (this.diameter / 2))) - (this.progressWidth / 2.0f)) - ((float) this.DEGREE_PROGRESS_DISTANCE)) - ((this.longDegree - this.shortDegree) / 2.0f), this.centerX, ((((this.centerY - ((float) (this.diameter / 2))) - (this.progressWidth / 2.0f)) - ((float) this.DEGREE_PROGRESS_DISTANCE)) - ((this.longDegree - this.shortDegree) / 2.0f)) - this.shortDegree, this.degreePaint);
                    }
                    canvas.rotate(9.0f, this.centerX, this.centerY);
                } else {
                    canvas.rotate(9.0f, this.centerX, this.centerY);
                }
            }
        }
        canvas.drawArc(this.bgRect, this.startAngle, this.sweepAngle, false, this.allArcPaint);
        this.rotateMatrix.setRotate(130.0f, this.centerX, this.centerY);
        this.sweepGradient.setLocalMatrix(this.rotateMatrix);
        this.progressPaint.setShader(this.sweepGradient);
        canvas.drawArc(this.bgRect, this.startAngle, this.currentAngle, false, this.progressPaint);
        if (this.isNeedContent) {
            canvas.drawText(String.format("%.0f", new Object[]{Float.valueOf(this.progress)}), this.centerX, this.centerY + (this.textSize / 4.0f), this.vTextPaint);
        }
        if (this.isNeedUnit) {
            canvas.drawText(this.hintString, this.centerX, this.centerY + this.textSize, this.hintPaint);
        }
        if (this.isNeedTitle) {
            canvas.drawText(this.titleString, this.centerX, this.centerY - this.textSize, this.curSpeedPaint);
        }
        invalidate();
    }

    public void setMaxValues(float maxValues2) {
        this.maxValues = maxValues2;
        this.k = this.sweepAngle / maxValues2;
    }

    public void setProgress(float progress2) {
        if (progress2 > this.maxValues) {
            progress2 = this.maxValues;
        }
        if (progress2 < 0.0f) {
            progress2 = 0.0f;
        }
        this.progress = progress2;
        this.lastAngle = this.currentAngle;
        setAnimation(this.lastAngle, this.k * progress2, this.aniSpeed);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!this.seekEnable) {
            return false;
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case 0:
                onStartTrackingTouch();
                updateOnTouch(event);
                break;
            case 1:
            case 3:
                onStopTrackingTouch();
                setPressed(false);
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case 2:
                updateOnTouch(event);
                break;
        }
        return true;
    }

    private void onStartTrackingTouch() {
        if (this.listener != null) {
            this.listener.onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        if (this.listener != null) {
            this.listener.onStopTrackingTouch(this);
        }
    }

    private void updateOnTouch(MotionEvent event) {
        if (validateTouch(event.getX(), event.getY())) {
            setPressed(true);
            int progress2 = angleToProgress(getTouchDegrees(event.getX(), event.getY()));
            Log.v("ColorArcProgressBar", "updateOnTouch: " + progress2);
            onProgressRefresh(progress2, true);
        }
    }

    private boolean validateTouch(float xPos, float yPos) {
        float x = xPos - this.centerX;
        float y = yPos - this.centerY;
        float touchRadius = (float) Math.sqrt((double) ((x * x) + (y * y)));
        double angle = Math.toDegrees((Math.atan2((double) y, (double) x) + 1.5707963267948966d) - Math.toRadians(225.0d));
        Log.v("ColorArcProgressBar", " touchRadius: " + touchRadius + " angle=" + angle);
        if (angle < 0.0d) {
            angle += 360.0d;
        }
        if (touchRadius <= this.mTouchInvalidateRadius || angle < 0.0d || angle > ((double) this.sweepAngle)) {
            return false;
        }
        return true;
    }

    private double getTouchDegrees(float xPos, float yPos) {
        double angle = Math.toDegrees((Math.atan2((double) (yPos - this.centerY), (double) (xPos - this.centerX)) + 1.5707963267948966d) - Math.toRadians(225.0d));
        if (angle < 0.0d) {
            angle += 360.0d;
        }
        Log.v("ColorArcProgressBar", "getTouchDegrees: " + angle);
        return angle;
    }

    private int angleToProgress(double angle) {
        int progress2 = (int) Math.round(((double) valuePerDegree()) * angle);
        int progress3 = progress2 < 0 ? 0 : progress2;
        return ((float) progress3) > this.maxValues ? (int) this.maxValues : progress3;
    }

    private float valuePerDegree() {
        return this.maxValues / this.sweepAngle;
    }

    private void onProgressRefresh(int progress2, boolean fromUser) {
        updateProgress(progress2, fromUser);
    }

    private void updateProgress(int progress2, boolean fromUser) {
        this.progress = (float) progress2;
        if (this.listener != null) {
            this.listener.onProgressChanged(this, progress2, fromUser);
        }
        this.currentAngle = (((float) progress2) / this.maxValues) * this.sweepAngle;
        this.lastAngle = this.currentAngle;
        invalidate();
    }

    public void setArcWidth(int bgArcWidth2) {
        this.bgArcWidth = (float) bgArcWidth2;
    }

    public void setProgressWidth(int progressWidth2) {
        this.progressWidth = (float) progressWidth2;
    }

    public void setTextSize(int textSize2) {
        this.textSize = (float) textSize2;
    }

    public void setHintSize(int hintSize2) {
        this.hintSize = (float) hintSize2;
    }

    public void setUnit(String hintString2) {
        this.hintString = hintString2;
        invalidate();
    }

    public void setDiameter(int diameter2) {
        this.diameter = dipToPx((float) diameter2);
    }

    private void setTitle(String title) {
        this.titleString = title;
    }

    private void setIsNeedTitle(boolean isNeedTitle2) {
        this.isNeedTitle = isNeedTitle2;
    }

    private void setIsNeedUnit(boolean isNeedUnit2) {
        this.isNeedUnit = isNeedUnit2;
    }

    private void setIsNeedDial(boolean isNeedDial2) {
        this.isNeedDial = isNeedDial2;
    }

    private void setAnimation(float last, float current, int length) {
        this.progressAnimator = ValueAnimator.ofFloat(new float[]{last, current});
        this.progressAnimator.setDuration((long) length);
        this.progressAnimator.setTarget(Float.valueOf(this.currentAngle));
        this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                float unused = ColorArcProgressBar.this.currentAngle = ((Float) animation.getAnimatedValue()).floatValue();
                float unused2 = ColorArcProgressBar.this.progress = ColorArcProgressBar.this.currentAngle / ColorArcProgressBar.this.k;
            }
        });
        this.progressAnimator.start();
    }

    public void setSeekEnable(boolean seekEnable2) {
        this.seekEnable = seekEnable2;
    }

    public void setOnSeekArcChangeListener(OnSeekArcChangeListener listener2) {
        this.listener = listener2;
    }

    private int dipToPx(float dip) {
        return (int) ((dip * getContext().getResources().getDisplayMetrics().density) + (((float) (dip >= 0.0f ? 1 : -1)) * 0.5f));
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getPressed() {
        return (int) this.progress;
    }
}
