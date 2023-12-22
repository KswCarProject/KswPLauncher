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
import android.support.p001v4.internal.view.SupportMenu;
import android.support.p001v4.view.InputDeviceCompat;
import android.support.p001v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.ibm.icu.text.BreakIterator;
import com.wits.ksw.C0899R;

/* loaded from: classes16.dex */
public class ColorArcProgressBar extends View {
    private final int DEGREE_PROGRESS_DISTANCE;
    private Paint allArcPaint;
    private int aniSpeed;
    private int bgArcColor;
    private float bgArcWidth;
    private RectF bgRect;
    private float centerX;
    private float centerY;
    private int[] colors;
    private Paint curSpeedPaint;
    private float curSpeedSize;
    private float currentAngle;
    private Paint degreePaint;
    private int diameter;
    private int hintColor;
    private Paint hintPaint;
    private float hintSize;
    private String hintString;
    private boolean isAutoTextSize;
    private boolean isNeedContent;
    private boolean isNeedDial;
    private boolean isNeedTitle;
    private boolean isNeedUnit;
    private boolean isShowCurrentSpeed;

    /* renamed from: k */
    private float f193k;
    private float lastAngle;
    private OnSeekArcChangeListener listener;
    private float longDegree;
    private int longDegreeColor;
    private PaintFlagsDrawFilter mDrawFilter;
    private int mHeight;
    private float mTouchInvalidateRadius;
    private int mWidth;
    private float maxValues;
    private float progress;
    private ValueAnimator progressAnimator;
    private Paint progressPaint;
    private float progressWidth;
    private Matrix rotateMatrix;
    private boolean seekEnable;
    private float shortDegree;
    private int shortDegreeColor;
    private float startAngle;
    private float sweepAngle;
    private SweepGradient sweepGradient;
    private float textSize;
    private String titleString;
    private Paint vTextPaint;

    /* loaded from: classes16.dex */
    public interface OnSeekArcChangeListener {
        void onProgressChanged(ColorArcProgressBar seekArc, int progress, boolean fromUser);

        void onStartTrackingTouch(ColorArcProgressBar seekArc);

        void onStopTrackingTouch(ColorArcProgressBar seekArc);
    }

    public ColorArcProgressBar(Context context) {
        super(context, null);
        this.DEGREE_PROGRESS_DISTANCE = dipToPx(8.0f);
        this.diameter = BreakIterator.WORD_IDEO_LIMIT;
        this.colors = new int[]{-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
        this.startAngle = 135.0f;
        this.sweepAngle = 270.0f;
        this.currentAngle = 0.0f;
        this.maxValues = 60.0f;
        this.progress = 0.0f;
        this.bgArcWidth = dipToPx(10.0f);
        this.progressWidth = dipToPx(10.0f);
        this.textSize = dipToPx(60.0f);
        this.hintSize = dipToPx(15.0f);
        this.curSpeedSize = dipToPx(13.0f);
        this.aniSpeed = 200;
        this.longDegree = dipToPx(13.0f);
        this.shortDegree = dipToPx(5.0f);
        this.longDegreeColor = -15658735;
        this.shortDegreeColor = -15658735;
        this.hintColor = -10000537;
        this.bgArcColor = -15658735;
        this.isShowCurrentSpeed = true;
        this.isAutoTextSize = true;
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        this.DEGREE_PROGRESS_DISTANCE = dipToPx(8.0f);
        this.diameter = BreakIterator.WORD_IDEO_LIMIT;
        this.colors = new int[]{-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
        this.startAngle = 135.0f;
        this.sweepAngle = 270.0f;
        this.currentAngle = 0.0f;
        this.maxValues = 60.0f;
        this.progress = 0.0f;
        this.bgArcWidth = dipToPx(10.0f);
        this.progressWidth = dipToPx(10.0f);
        this.textSize = dipToPx(60.0f);
        this.hintSize = dipToPx(15.0f);
        this.curSpeedSize = dipToPx(13.0f);
        this.aniSpeed = 200;
        this.longDegree = dipToPx(13.0f);
        this.shortDegree = dipToPx(5.0f);
        this.longDegreeColor = -15658735;
        this.shortDegreeColor = -15658735;
        this.hintColor = -10000537;
        this.bgArcColor = -15658735;
        this.isShowCurrentSpeed = true;
        this.isAutoTextSize = true;
        initConfig(context, attrs);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.DEGREE_PROGRESS_DISTANCE = dipToPx(8.0f);
        this.diameter = BreakIterator.WORD_IDEO_LIMIT;
        this.colors = new int[]{-16711936, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK};
        this.startAngle = 135.0f;
        this.sweepAngle = 270.0f;
        this.currentAngle = 0.0f;
        this.maxValues = 60.0f;
        this.progress = 0.0f;
        this.bgArcWidth = dipToPx(10.0f);
        this.progressWidth = dipToPx(10.0f);
        this.textSize = dipToPx(60.0f);
        this.hintSize = dipToPx(15.0f);
        this.curSpeedSize = dipToPx(13.0f);
        this.aniSpeed = 200;
        this.longDegree = dipToPx(13.0f);
        this.shortDegree = dipToPx(5.0f);
        this.longDegreeColor = -15658735;
        this.shortDegreeColor = -15658735;
        this.hintColor = -10000537;
        this.bgArcColor = -15658735;
        this.isShowCurrentSpeed = true;
        this.isAutoTextSize = true;
        initConfig(context, attrs);
        initView();
    }

    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0899R.styleable.ColorArcProgressBar);
        int color1 = a.getColor(4, -16711936);
        int color2 = a.getColor(5, color1);
        int color3 = a.getColor(6, color1);
        this.bgArcColor = a.getColor(0, -15658735);
        this.longDegreeColor = a.getColor(3, -15658735);
        this.shortDegreeColor = a.getColor(3, -15658735);
        this.hintColor = a.getColor(8, -10000537);
        this.colors = new int[]{color1, color2, color3, color3};
        this.sweepAngle = a.getInteger(17, 270);
        this.bgArcWidth = a.getDimension(1, dipToPx(10.0f));
        this.progressWidth = a.getDimension(7, dipToPx(10.0f));
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

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        Log.v("ColorArcProgressBar", "onSizeChanged: mWidth:" + this.mWidth + " mHeight:" + this.mHeight);
        this.diameter = (int) (Math.min(this.mWidth, this.mHeight) - (((this.longDegree + this.DEGREE_PROGRESS_DISTANCE) + (this.progressWidth / 2.0f)) * 2.0f));
        Log.v("ColorArcProgressBar", "onSizeChanged: diameter:" + this.diameter);
        RectF rectF = new RectF();
        this.bgRect = rectF;
        rectF.top = this.longDegree + this.DEGREE_PROGRESS_DISTANCE + (this.progressWidth / 2.0f);
        this.bgRect.left = this.longDegree + this.DEGREE_PROGRESS_DISTANCE + (this.progressWidth / 2.0f);
        this.bgRect.right = this.diameter + this.longDegree + (this.progressWidth / 2.0f) + this.DEGREE_PROGRESS_DISTANCE;
        this.bgRect.bottom = this.diameter + this.longDegree + (this.progressWidth / 2.0f) + this.DEGREE_PROGRESS_DISTANCE;
        Log.v("ColorArcProgressBar", "initView: " + this.diameter);
        float f = this.longDegree;
        int i = this.DEGREE_PROGRESS_DISTANCE;
        float f2 = this.progressWidth;
        int i2 = this.diameter;
        this.centerX = ((((i + f) + (f2 / 2.0f)) * 2.0f) + i2) / 2.0f;
        this.centerY = ((((f + i) + (f2 / 2.0f)) * 2.0f) + i2) / 2.0f;
        this.sweepGradient = new SweepGradient(this.centerX, this.centerY, this.colors, (float[]) null);
        this.mTouchInvalidateRadius = 50.0f;
        if (this.isAutoTextSize) {
            int i3 = this.diameter;
            float f3 = (float) (i3 * 0.3d);
            this.textSize = f3;
            this.hintSize = (float) (i3 * 0.1d);
            this.curSpeedSize = (float) (i3 * 0.1d);
            this.vTextPaint.setTextSize(f3);
            this.hintPaint.setTextSize(this.hintSize);
            this.curSpeedPaint.setTextSize(this.curSpeedSize);
        }
    }

    private void initView() {
        Paint paint = new Paint();
        this.degreePaint = paint;
        paint.setColor(this.longDegreeColor);
        Paint paint2 = new Paint();
        this.allArcPaint = paint2;
        paint2.setAntiAlias(true);
        this.allArcPaint.setStyle(Paint.Style.STROKE);
        this.allArcPaint.setStrokeWidth(this.bgArcWidth);
        this.allArcPaint.setColor(this.bgArcColor);
        this.allArcPaint.setStrokeCap(Paint.Cap.SQUARE);
        Paint paint3 = new Paint();
        this.progressPaint = paint3;
        paint3.setAntiAlias(true);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.progressPaint.setStrokeWidth(this.progressWidth);
        this.progressPaint.setColor(-16776961);
        Paint paint4 = new Paint();
        this.vTextPaint = paint4;
        paint4.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.vTextPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint5 = new Paint();
        this.hintPaint = paint5;
        paint5.setColor(this.hintColor);
        this.hintPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint6 = new Paint();
        this.curSpeedPaint = paint6;
        paint6.setColor(this.hintColor);
        this.curSpeedPaint.setTextAlign(Paint.Align.CENTER);
        this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
        this.rotateMatrix = new Matrix();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.mDrawFilter);
        if (this.isNeedDial) {
            for (int i = 0; i < 40; i++) {
                if (i > 15 && i < 25) {
                    canvas.rotate(9.0f, this.centerX, this.centerY);
                } else {
                    if (i % 5 == 0) {
                        this.degreePaint.setStrokeWidth(dipToPx(2.0f));
                        this.degreePaint.setColor(this.longDegreeColor);
                        float f = this.centerX;
                        float f2 = this.centerY;
                        int i2 = this.diameter;
                        float f3 = this.progressWidth;
                        int i3 = this.DEGREE_PROGRESS_DISTANCE;
                        canvas.drawLine(f, ((f2 - (i2 / 2)) - (f3 / 2.0f)) - i3, f, (((f2 - (i2 / 2)) - (f3 / 2.0f)) - i3) - this.longDegree, this.degreePaint);
                    } else {
                        this.degreePaint.setStrokeWidth(dipToPx(1.4f));
                        this.degreePaint.setColor(this.shortDegreeColor);
                        float f4 = this.centerX;
                        float f5 = this.centerY;
                        int i4 = this.diameter;
                        float f6 = this.progressWidth;
                        int i5 = this.DEGREE_PROGRESS_DISTANCE;
                        float f7 = this.longDegree;
                        float f8 = this.shortDegree;
                        canvas.drawLine(f4, (((f5 - (i4 / 2)) - (f6 / 2.0f)) - i5) - ((f7 - f8) / 2.0f), f4, ((((f5 - (i4 / 2)) - (f6 / 2.0f)) - i5) - ((f7 - f8) / 2.0f)) - f8, this.degreePaint);
                    }
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
            canvas.drawText(String.format("%.0f", Float.valueOf(this.progress)), this.centerX, this.centerY + (this.textSize / 4.0f), this.vTextPaint);
        }
        if (this.isNeedUnit) {
            canvas.drawText(this.hintString, this.centerX, this.centerY + this.textSize, this.hintPaint);
        }
        if (this.isNeedTitle) {
            canvas.drawText(this.titleString, this.centerX, this.centerY - this.textSize, this.curSpeedPaint);
        }
        invalidate();
    }

    public void setMaxValues(float maxValues) {
        this.maxValues = maxValues;
        this.f193k = this.sweepAngle / maxValues;
    }

    public void setProgress(float progress) {
        if (progress > this.maxValues) {
            progress = this.maxValues;
        }
        if (progress < 0.0f) {
            progress = 0.0f;
        }
        this.progress = progress;
        float f = this.currentAngle;
        this.lastAngle = f;
        setAnimation(f, this.f193k * progress, this.aniSpeed);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.seekEnable) {
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
        return false;
    }

    private void onStartTrackingTouch() {
        OnSeekArcChangeListener onSeekArcChangeListener = this.listener;
        if (onSeekArcChangeListener != null) {
            onSeekArcChangeListener.onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        OnSeekArcChangeListener onSeekArcChangeListener = this.listener;
        if (onSeekArcChangeListener != null) {
            onSeekArcChangeListener.onStopTrackingTouch(this);
        }
    }

    private void updateOnTouch(MotionEvent event) {
        boolean validateTouch = validateTouch(event.getX(), event.getY());
        if (!validateTouch) {
            return;
        }
        setPressed(true);
        double mTouchAngle = getTouchDegrees(event.getX(), event.getY());
        int progress = angleToProgress(mTouchAngle);
        Log.v("ColorArcProgressBar", "updateOnTouch: " + progress);
        onProgressRefresh(progress, true);
    }

    private boolean validateTouch(float xPos, float yPos) {
        float x = xPos - this.centerX;
        float y = yPos - this.centerY;
        float touchRadius = (float) Math.sqrt((x * x) + (y * y));
        double angle = Math.toDegrees((Math.atan2(y, x) + 1.5707963267948966d) - Math.toRadians(225.0d));
        Log.v("ColorArcProgressBar", " touchRadius: " + touchRadius + " angle=" + angle);
        if (angle < 0.0d) {
            angle += 360.0d;
        }
        if (touchRadius <= this.mTouchInvalidateRadius || angle < 0.0d || angle > this.sweepAngle) {
            return false;
        }
        return true;
    }

    private double getTouchDegrees(float xPos, float yPos) {
        float x = xPos - this.centerX;
        float y = yPos - this.centerY;
        double angle = Math.toDegrees((Math.atan2(y, x) + 1.5707963267948966d) - Math.toRadians(225.0d));
        if (angle < 0.0d) {
            angle += 360.0d;
        }
        Log.v("ColorArcProgressBar", "getTouchDegrees: " + angle);
        return angle;
    }

    private int angleToProgress(double angle) {
        int progress = (int) Math.round(valuePerDegree() * angle);
        int progress2 = progress < 0 ? 0 : progress;
        float f = this.maxValues;
        return ((float) progress2) > f ? (int) f : progress2;
    }

    private float valuePerDegree() {
        return this.maxValues / this.sweepAngle;
    }

    private void onProgressRefresh(int progress, boolean fromUser) {
        updateProgress(progress, fromUser);
    }

    private void updateProgress(int progress, boolean fromUser) {
        this.progress = progress;
        OnSeekArcChangeListener onSeekArcChangeListener = this.listener;
        if (onSeekArcChangeListener != null) {
            onSeekArcChangeListener.onProgressChanged(this, progress, fromUser);
        }
        float f = (progress / this.maxValues) * this.sweepAngle;
        this.currentAngle = f;
        this.lastAngle = f;
        invalidate();
    }

    public void setArcWidth(int bgArcWidth) {
        this.bgArcWidth = bgArcWidth;
    }

    public void setProgressWidth(int progressWidth) {
        this.progressWidth = progressWidth;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setHintSize(int hintSize) {
        this.hintSize = hintSize;
    }

    public void setUnit(String hintString) {
        this.hintString = hintString;
        invalidate();
    }

    public void setDiameter(int diameter) {
        this.diameter = dipToPx(diameter);
    }

    private void setTitle(String title) {
        this.titleString = title;
    }

    private void setIsNeedTitle(boolean isNeedTitle) {
        this.isNeedTitle = isNeedTitle;
    }

    private void setIsNeedUnit(boolean isNeedUnit) {
        this.isNeedUnit = isNeedUnit;
    }

    private void setIsNeedDial(boolean isNeedDial) {
        this.isNeedDial = isNeedDial;
    }

    private void setAnimation(float last, float current, int length) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(last, current);
        this.progressAnimator = ofFloat;
        ofFloat.setDuration(length);
        this.progressAnimator.setTarget(Float.valueOf(this.currentAngle));
        this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.launcher.view.ColorArcProgressBar.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                ColorArcProgressBar.this.currentAngle = ((Float) animation.getAnimatedValue()).floatValue();
                ColorArcProgressBar colorArcProgressBar = ColorArcProgressBar.this;
                colorArcProgressBar.progress = colorArcProgressBar.currentAngle / ColorArcProgressBar.this.f193k;
            }
        });
        this.progressAnimator.start();
    }

    public void setSeekEnable(boolean seekEnable) {
        this.seekEnable = seekEnable;
    }

    public void setOnSeekArcChangeListener(OnSeekArcChangeListener listener) {
        this.listener = listener;
    }

    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) ((dip * density) + ((dip >= 0.0f ? 1 : -1) * 0.5f));
    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public int getPressed() {
        return (int) this.progress;
    }
}
