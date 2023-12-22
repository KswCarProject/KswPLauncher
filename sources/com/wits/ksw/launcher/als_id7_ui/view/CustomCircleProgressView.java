package com.wits.ksw.launcher.als_id7_ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.wits.ksw.C0899R;

/* loaded from: classes12.dex */
public class CustomCircleProgressView extends View {
    protected static final int DEFAULT_CIRCLE_COLOR = 0;
    protected static final int DEFAULT_CIRCLE_FILL_COLOR = 0;
    protected static final int DEFAULT_CIRCLE_PROGRESS_COLOR = 0;
    protected static final float DEFAULT_CIRCLE_STROKE_WIDTH = 2.7f;
    protected static final float DEFAULT_CIRCLE_X_RADIUS = 15.0f;
    protected static final float DEFAULT_CIRCLE_Y_RADIUS = 15.0f;
    protected static final float DEFAULT_END_ANGLE = 270.0f;
    protected static final boolean DEFAULT_LOCK_ENABLED = false;
    protected static final boolean DEFAULT_MAINTAIN_EQUAL_CIRCLE = false;
    protected static final int DEFAULT_MAX = 100;
    protected static final boolean DEFAULT_MOVE_OUTSIDE_CIRCLE = false;
    protected static final int DEFAULT_POINTER_ALPHA = 135;
    protected static final int DEFAULT_POINTER_ALPHA_ONTOUCH = 100;
    protected static final int DEFAULT_POINTER_COLOR = 0;
    protected static final float DEFAULT_POINTER_HALO_BORDER_WIDTH = 1.8f;
    protected static final int DEFAULT_POINTER_HALO_COLOR = 0;
    protected static final int DEFAULT_POINTER_HALO_COLOR_ONTOUCH = 0;
    protected static final float DEFAULT_POINTER_HALO_WIDTH = 0.0f;
    protected static final float DEFAULT_POINTER_RADIUS = 0.0f;
    protected static final int DEFAULT_PROGRESS = 0;
    protected static final float DEFAULT_START_ANGLE = 270.0f;
    protected static final boolean DEFAULT_USE_CUSTOM_RADII = false;
    protected final float DPTOPX_SCALE;
    protected final float MIN_TOUCH_TARGET_DP;
    protected float ccwDistanceFromEnd;
    protected float ccwDistanceFromPointer;
    protected float ccwDistanceFromStart;
    protected float cwDistanceFromEnd;
    protected float cwDistanceFromPointer;
    protected float cwDistanceFromStart;
    protected boolean isTouchEnabled;
    protected float lastCWDistanceFromStart;
    protected boolean lockAtEnd;
    protected boolean lockAtStart;
    protected boolean lockEnabled;
    protected int mCircleColor;
    protected int mCircleFillColor;
    protected Paint mCircleFillPaint;
    protected float mCircleHeight;
    protected Paint mCirclePaint;
    protected Path mCirclePath;
    protected int mCircleProgressColor;
    protected Paint mCircleProgressGlowPaint;
    protected Paint mCircleProgressPaint;
    protected Path mCircleProgressPath;
    protected RectF mCircleRectF;
    protected float mCircleStrokeWidth;
    protected float mCircleWidth;
    protected float mCircleXRadius;
    protected float mCircleYRadius;
    protected boolean mCustomRadii;
    protected float mEndAngle;
    protected boolean mIsMovingCW;
    protected boolean mMaintainEqualCircle;
    protected int mMax;
    protected boolean mMoveOutsideCircle;
    protected OnCircularSeekBarChangeListener mOnCircularSeekBarChangeListener;
    protected int mPointerAlpha;
    protected int mPointerAlphaOnTouch;
    protected int mPointerColor;
    protected Paint mPointerHaloBorderPaint;
    protected float mPointerHaloBorderWidth;
    protected int mPointerHaloColor;
    protected int mPointerHaloColorOnTouch;
    protected Paint mPointerHaloPaint;
    protected float mPointerHaloWidth;
    protected Paint mPointerPaint;
    protected float mPointerPosition;
    protected float[] mPointerPositionXY;
    protected float mPointerRadius;
    protected int mProgress;
    protected float mProgressDegrees;
    protected float mStartAngle;
    Bitmap mThumbBitmap;
    private int mThumbWidth;
    protected float mTotalCircleDegrees;
    protected boolean mUserIsMovingPointer;

    /* loaded from: classes12.dex */
    public interface OnCircularSeekBarChangeListener {
        void onProgressChanged(CustomCircleProgressView circularSeekBar, int progress, boolean fromUser);

        void onStartTrackingTouch(CustomCircleProgressView seekBar);

        void onStopTrackingTouch(CustomCircleProgressView seekBar);
    }

    protected void initAttributes(TypedArray attrArray) {
        this.mCircleXRadius = attrArray.getDimension(4, this.DPTOPX_SCALE * 15.0f);
        this.mCircleYRadius = attrArray.getDimension(5, this.DPTOPX_SCALE * 15.0f);
        this.mPointerRadius = attrArray.getDimension(17, this.DPTOPX_SCALE * 0.0f);
        this.mPointerHaloWidth = attrArray.getDimension(16, this.DPTOPX_SCALE * 0.0f);
        this.mPointerHaloBorderWidth = attrArray.getDimension(13, this.DPTOPX_SCALE * DEFAULT_POINTER_HALO_BORDER_WIDTH);
        this.mCircleStrokeWidth = attrArray.getDimension(3, this.DPTOPX_SCALE * DEFAULT_CIRCLE_STROKE_WIDTH);
        this.mPointerColor = attrArray.getColor(12, 0);
        this.mPointerHaloColor = attrArray.getColor(14, 0);
        this.mPointerHaloColorOnTouch = attrArray.getColor(15, 0);
        this.mCircleColor = attrArray.getColor(0, 0);
        this.mCircleProgressColor = attrArray.getColor(2, 0);
        this.mCircleFillColor = attrArray.getColor(1, 0);
        this.mPointerAlpha = Color.alpha(this.mPointerHaloColor);
        int i = attrArray.getInt(11, 100);
        this.mPointerAlphaOnTouch = i;
        if (i > 255 || i < 0) {
            this.mPointerAlphaOnTouch = 100;
        }
        this.mMax = attrArray.getInt(9, 100);
        this.mProgress = attrArray.getInt(18, 0);
        this.mCustomRadii = attrArray.getBoolean(20, false);
        this.mMaintainEqualCircle = attrArray.getBoolean(8, false);
        this.mMoveOutsideCircle = attrArray.getBoolean(10, false);
        this.lockEnabled = attrArray.getBoolean(7, false);
        this.mStartAngle = ((attrArray.getFloat(19, 270.0f) % 360.0f) + 360.0f) % 360.0f;
        float f = ((attrArray.getFloat(6, 270.0f) % 360.0f) + 360.0f) % 360.0f;
        this.mEndAngle = f;
        if (this.mStartAngle == f) {
            this.mEndAngle = f - 0.1f;
        }
    }

    protected void initPaints() {
        Paint paint = new Paint();
        this.mCirclePaint = paint;
        paint.setAntiAlias(true);
        this.mCirclePaint.setDither(true);
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mCirclePaint.setStyle(Paint.Style.STROKE);
        this.mCirclePaint.setStrokeJoin(Paint.Join.ROUND);
        this.mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.mCircleFillPaint = paint2;
        paint2.setAntiAlias(true);
        this.mCircleFillPaint.setDither(true);
        this.mCircleFillPaint.setColor(this.mCircleFillColor);
        this.mCircleFillPaint.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.mCircleProgressPaint = paint3;
        paint3.setAntiAlias(true);
        this.mCircleProgressPaint.setDither(true);
        this.mCircleProgressPaint.setColor(this.mCircleProgressColor);
        this.mCircleProgressPaint.setStrokeWidth(this.mCircleStrokeWidth);
        this.mCircleProgressPaint.setStyle(Paint.Style.STROKE);
        this.mCircleProgressPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mCircleProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint4 = new Paint();
        this.mCircleProgressGlowPaint = paint4;
        paint4.set(this.mCircleProgressPaint);
        this.mCircleProgressGlowPaint.setMaskFilter(new BlurMaskFilter(this.DPTOPX_SCALE * 0.1f, BlurMaskFilter.Blur.INNER));
        Paint paint5 = new Paint();
        this.mPointerPaint = paint5;
        paint5.setAntiAlias(true);
        this.mPointerPaint.setDither(true);
        this.mPointerPaint.setStyle(Paint.Style.FILL);
        this.mPointerPaint.setColor(this.mPointerColor);
        this.mPointerPaint.setStrokeWidth(this.mPointerRadius);
        Paint paint6 = new Paint();
        this.mPointerHaloPaint = paint6;
        paint6.set(this.mPointerPaint);
        this.mPointerHaloPaint.setColor(this.mPointerHaloColor);
        this.mPointerHaloPaint.setAlpha(this.mPointerAlpha);
        this.mPointerHaloPaint.setStrokeWidth(this.mPointerRadius + this.mPointerHaloWidth);
        Paint paint7 = new Paint();
        this.mPointerHaloBorderPaint = paint7;
        paint7.set(this.mPointerPaint);
        this.mPointerHaloBorderPaint.setStrokeWidth(this.mPointerHaloBorderWidth);
        this.mPointerHaloBorderPaint.setStyle(Paint.Style.STROKE);
    }

    protected void calculateTotalDegrees() {
        float f = (360.0f - (this.mStartAngle - this.mEndAngle)) % 360.0f;
        this.mTotalCircleDegrees = f;
        if (f <= 0.0f) {
            this.mTotalCircleDegrees = 360.0f;
        }
    }

    protected void calculateProgressDegrees() {
        float f = this.mPointerPosition - this.mStartAngle;
        this.mProgressDegrees = f;
        if (f < 0.0f) {
            f += 360.0f;
        }
        this.mProgressDegrees = f;
    }

    protected void calculatePointerAngle() {
        float progressPercent = this.mProgress / this.mMax;
        float f = (this.mTotalCircleDegrees * progressPercent) + this.mStartAngle;
        this.mPointerPosition = f;
        this.mPointerPosition = f % 360.0f;
    }

    protected void calculatePointerXYPosition() {
        PathMeasure pm = new PathMeasure(this.mCircleProgressPath, false);
        boolean returnValue = pm.getPosTan(pm.getLength(), this.mPointerPositionXY, null);
        if (!returnValue) {
            new PathMeasure(this.mCirclePath, false).getPosTan(0.0f, this.mPointerPositionXY, null);
        }
    }

    protected void initPaths() {
        Path path = new Path();
        this.mCirclePath = path;
        path.addArc(this.mCircleRectF, this.mStartAngle, this.mTotalCircleDegrees);
        Path path2 = new Path();
        this.mCircleProgressPath = path2;
        path2.addArc(this.mCircleRectF, this.mStartAngle, this.mProgressDegrees);
    }

    protected void initRects() {
        RectF rectF = this.mCircleRectF;
        float f = this.mCircleWidth;
        float f2 = this.mCircleHeight;
        rectF.set(-f, -f2, f, f2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawPath(this.mCirclePath, this.mCirclePaint);
        canvas.drawPath(this.mCircleProgressPath, this.mCircleProgressGlowPaint);
        canvas.drawPath(this.mCircleProgressPath, this.mCircleProgressPaint);
        canvas.drawPath(this.mCirclePath, this.mCircleFillPaint);
        float[] fArr = this.mPointerPositionXY;
        canvas.drawCircle(fArr[0], fArr[1], this.mPointerRadius + this.mPointerHaloWidth, this.mPointerHaloPaint);
        float[] fArr2 = this.mPointerPositionXY;
        canvas.drawCircle(fArr2[0], fArr2[1], this.mPointerRadius, this.mPointerPaint);
        if (this.mUserIsMovingPointer) {
            float[] fArr3 = this.mPointerPositionXY;
            canvas.drawCircle(fArr3[0], fArr3[1], this.mPointerRadius + this.mPointerHaloWidth + (this.mPointerHaloBorderWidth / 2.0f), this.mPointerHaloBorderPaint);
        }
        Bitmap bitmap = this.mThumbBitmap;
        float[] fArr4 = this.mPointerPositionXY;
        float f = fArr4[0];
        int i = this.mThumbWidth;
        canvas.drawBitmap(bitmap, f - (i / 2), fArr4[1] - (i / 2), (Paint) null);
    }

    public void setThumb(Drawable thumb) {
        this.mThumbBitmap = drawableToBitmap(thumb);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public int getProgress() {
        int progress = Math.round((this.mMax * this.mProgressDegrees) / this.mTotalCircleDegrees);
        return progress;
    }

    public void setProgress(int progress) {
        if (this.mProgress != progress) {
            this.mProgress = progress;
            OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = this.mOnCircularSeekBarChangeListener;
            if (onCircularSeekBarChangeListener != null) {
                onCircularSeekBarChangeListener.onProgressChanged(this, progress, false);
            }
            recalculateAll();
            invalidate();
        }
    }

    protected void setProgressBasedOnAngle(float angle) {
        this.mPointerPosition = angle;
        calculateProgressDegrees();
        this.mProgress = Math.round((this.mMax * this.mProgressDegrees) / this.mTotalCircleDegrees);
    }

    public void recalculateAll() {
        calculateTotalDegrees();
        calculatePointerAngle();
        calculateProgressDegrees();
        initRects();
        initPaths();
        calculatePointerXYPosition();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        if (this.mMaintainEqualCircle) {
            int min = Math.min(width, height);
            setMeasuredDimension(min, min);
        } else {
            setMeasuredDimension(width, height);
        }
        float f = this.mCircleStrokeWidth;
        float f2 = this.mPointerRadius;
        float f3 = this.mPointerHaloBorderWidth;
        float f4 = (((height / 2.0f) - f) - f2) - (f3 * 1.5f);
        this.mCircleHeight = f4;
        float f5 = (((width / 2.0f) - f) - f2) - (f3 * 1.5f);
        this.mCircleWidth = f5;
        if (this.mCustomRadii) {
            float f6 = this.mCircleYRadius;
            if (((f6 - f) - f2) - f3 < f4) {
                this.mCircleHeight = ((f6 - f) - f2) - (f3 * 1.5f);
            }
            float f7 = this.mCircleXRadius;
            if (((f7 - f) - f2) - f3 < f5) {
                this.mCircleWidth = ((f7 - f) - f2) - (f3 * 1.5f);
            }
        }
        if (this.mMaintainEqualCircle) {
            float min2 = Math.min(this.mCircleHeight, this.mCircleWidth);
            this.mCircleHeight = min2;
            this.mCircleWidth = min2;
        }
        recalculateAll();
    }

    public boolean isLockEnabled() {
        return this.lockEnabled;
    }

    public void setLockEnabled(boolean lockEnabled) {
        this.lockEnabled = lockEnabled;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        float additionalRadius;
        boolean z;
        if (this.isTouchEnabled) {
            float x = event.getX() - (getWidth() / 2);
            float y = event.getY() - (getHeight() / 2);
            float distanceX = this.mCircleRectF.centerX() - x;
            float distanceY = this.mCircleRectF.centerY() - y;
            float touchEventRadius = (float) Math.sqrt(Math.pow(distanceX, 2.0d) + Math.pow(distanceY, 2.0d));
            float minimumTouchTarget = this.DPTOPX_SCALE * 48.0f;
            float additionalRadius2 = this.mCircleStrokeWidth;
            if (additionalRadius2 < minimumTouchTarget) {
                additionalRadius = minimumTouchTarget / 2.0f;
            } else {
                additionalRadius = additionalRadius2 / 2.0f;
            }
            float outerRadius = Math.max(this.mCircleHeight, this.mCircleWidth) + additionalRadius;
            float innerRadius = Math.min(this.mCircleHeight, this.mCircleWidth) - additionalRadius;
            if (this.mPointerRadius < minimumTouchTarget / 2.0f) {
                float additionalRadius3 = minimumTouchTarget / 2.0f;
            } else {
                float additionalRadius4 = this.mPointerRadius;
            }
            float touchAngle = (float) (((Math.atan2(y, x) / 3.141592653589793d) * 180.0d) % 360.0d);
            float touchAngle2 = touchAngle < 0.0f ? touchAngle + 360.0f : touchAngle;
            float f = touchAngle2 - this.mStartAngle;
            this.cwDistanceFromStart = f;
            if (f < 0.0f) {
                f += 360.0f;
            }
            this.cwDistanceFromStart = f;
            this.ccwDistanceFromStart = 360.0f - f;
            float f2 = touchAngle2 - this.mEndAngle;
            this.cwDistanceFromEnd = f2;
            if (f2 < 0.0f) {
                f2 += 360.0f;
            }
            this.cwDistanceFromEnd = f2;
            this.ccwDistanceFromEnd = 360.0f - f2;
            switch (event.getAction()) {
                case 0:
                    float pointerRadiusDegrees = (float) ((this.mPointerRadius * 180.0f) / (Math.max(this.mCircleHeight, this.mCircleWidth) * 3.141592653589793d));
                    float f3 = this.mPointerPosition;
                    float f4 = touchAngle2 - f3;
                    this.cwDistanceFromPointer = f4;
                    if (f4 < 0.0f) {
                        f4 += 360.0f;
                    }
                    this.cwDistanceFromPointer = f4;
                    float f5 = 360.0f - f4;
                    this.ccwDistanceFromPointer = f5;
                    if (touchEventRadius < innerRadius || touchEventRadius > outerRadius || (f4 > pointerRadiusDegrees && f5 > pointerRadiusDegrees)) {
                        if (this.cwDistanceFromStart > this.mTotalCircleDegrees) {
                            this.mUserIsMovingPointer = false;
                            return false;
                        } else if (touchEventRadius >= innerRadius && touchEventRadius <= outerRadius) {
                            setProgressBasedOnAngle(touchAngle2);
                            this.lastCWDistanceFromStart = this.cwDistanceFromStart;
                            this.mIsMovingCW = true;
                            this.mPointerHaloPaint.setAlpha(this.mPointerAlphaOnTouch);
                            this.mPointerHaloPaint.setColor(this.mPointerHaloColorOnTouch);
                            recalculateAll();
                            invalidate();
                            OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = this.mOnCircularSeekBarChangeListener;
                            if (onCircularSeekBarChangeListener == null) {
                                z = true;
                            } else {
                                onCircularSeekBarChangeListener.onStartTrackingTouch(this);
                                z = true;
                                this.mOnCircularSeekBarChangeListener.onProgressChanged(this, this.mProgress, true);
                            }
                            this.mUserIsMovingPointer = z;
                            this.lockAtEnd = false;
                            this.lockAtStart = false;
                            break;
                        } else {
                            this.mUserIsMovingPointer = false;
                            return false;
                        }
                    } else {
                        setProgressBasedOnAngle(f3);
                        this.lastCWDistanceFromStart = this.cwDistanceFromStart;
                        this.mIsMovingCW = true;
                        this.mPointerHaloPaint.setAlpha(this.mPointerAlphaOnTouch);
                        this.mPointerHaloPaint.setColor(this.mPointerHaloColorOnTouch);
                        recalculateAll();
                        invalidate();
                        OnCircularSeekBarChangeListener onCircularSeekBarChangeListener2 = this.mOnCircularSeekBarChangeListener;
                        if (onCircularSeekBarChangeListener2 != null) {
                            onCircularSeekBarChangeListener2.onStartTrackingTouch(this);
                        }
                        this.mUserIsMovingPointer = true;
                        this.lockAtEnd = false;
                        this.lockAtStart = false;
                        break;
                    }
                case 1:
                    this.mPointerHaloPaint.setAlpha(this.mPointerAlpha);
                    this.mPointerHaloPaint.setColor(this.mPointerHaloColor);
                    if (this.mUserIsMovingPointer) {
                        this.mUserIsMovingPointer = false;
                        invalidate();
                        OnCircularSeekBarChangeListener onCircularSeekBarChangeListener3 = this.mOnCircularSeekBarChangeListener;
                        if (onCircularSeekBarChangeListener3 == null) {
                            break;
                        } else {
                            onCircularSeekBarChangeListener3.onStopTrackingTouch(this);
                            break;
                        }
                    } else {
                        return false;
                    }
                case 2:
                    if (this.mUserIsMovingPointer) {
                        float f6 = this.lastCWDistanceFromStart;
                        float f7 = this.cwDistanceFromStart;
                        if (f6 < f7) {
                            if (f7 - f6 > 180.0f && !this.mIsMovingCW) {
                                this.lockAtStart = true;
                                this.lockAtEnd = false;
                            } else {
                                this.mIsMovingCW = true;
                            }
                        } else if (f6 - f7 > 180.0f && this.mIsMovingCW) {
                            this.lockAtEnd = true;
                            this.lockAtStart = false;
                        } else {
                            this.mIsMovingCW = false;
                        }
                        if (this.lockAtStart && this.mIsMovingCW) {
                            this.lockAtStart = false;
                        }
                        if (this.lockAtEnd && !this.mIsMovingCW) {
                            this.lockAtEnd = false;
                        }
                        if (this.lockAtStart && !this.mIsMovingCW && this.ccwDistanceFromStart > 90.0f) {
                            this.lockAtStart = false;
                        }
                        if (this.lockAtEnd && this.mIsMovingCW && this.cwDistanceFromEnd > 90.0f) {
                            this.lockAtEnd = false;
                        }
                        if (!this.lockAtEnd) {
                            float f8 = this.mTotalCircleDegrees;
                            if (f7 > f8 && this.mIsMovingCW && f6 < f8) {
                                this.lockAtEnd = true;
                            }
                        }
                        if (this.lockAtStart && this.lockEnabled) {
                            this.mProgress = 0;
                            recalculateAll();
                            invalidate();
                            OnCircularSeekBarChangeListener onCircularSeekBarChangeListener4 = this.mOnCircularSeekBarChangeListener;
                            if (onCircularSeekBarChangeListener4 != null) {
                                onCircularSeekBarChangeListener4.onProgressChanged(this, this.mProgress, true);
                            }
                        } else if (this.lockAtEnd && this.lockEnabled) {
                            this.mProgress = this.mMax;
                            recalculateAll();
                            invalidate();
                            OnCircularSeekBarChangeListener onCircularSeekBarChangeListener5 = this.mOnCircularSeekBarChangeListener;
                            if (onCircularSeekBarChangeListener5 != null) {
                                onCircularSeekBarChangeListener5.onProgressChanged(this, this.mProgress, true);
                            }
                        } else if (!this.mMoveOutsideCircle && touchEventRadius > outerRadius) {
                            break;
                        } else {
                            if (f7 <= this.mTotalCircleDegrees) {
                                setProgressBasedOnAngle(touchAngle2);
                            }
                            recalculateAll();
                            invalidate();
                            OnCircularSeekBarChangeListener onCircularSeekBarChangeListener6 = this.mOnCircularSeekBarChangeListener;
                            if (onCircularSeekBarChangeListener6 != null) {
                                onCircularSeekBarChangeListener6.onProgressChanged(this, this.mProgress, true);
                            }
                        }
                        this.lastCWDistanceFromStart = this.cwDistanceFromStart;
                        break;
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    this.mPointerHaloPaint.setAlpha(this.mPointerAlpha);
                    this.mPointerHaloPaint.setColor(this.mPointerHaloColor);
                    this.mUserIsMovingPointer = false;
                    invalidate();
                    break;
            }
            if (event.getAction() != 2 || getParent() == null) {
                return true;
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }
        return false;
    }

    protected void init(AttributeSet attrs, int defStyle) {
        TypedArray attrArray = getContext().obtainStyledAttributes(attrs, C0899R.styleable.CircularSeekBar, defStyle, 0);
        initAttributes(attrArray);
        attrArray.recycle();
        initPaints();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), C0899R.C0900drawable.als_sp_id7_main_btn_music_progress_bar_slider);
        this.mThumbBitmap = decodeResource;
        this.mThumbWidth = decodeResource.getWidth();
    }

    public CustomCircleProgressView(Context context) {
        super(context);
        this.DPTOPX_SCALE = getResources().getDisplayMetrics().density;
        this.MIN_TOUCH_TARGET_DP = 48.0f;
        this.mCircleRectF = new RectF();
        this.mPointerColor = 0;
        this.mPointerHaloColor = 0;
        this.mPointerHaloColorOnTouch = 0;
        this.mCircleColor = 0;
        this.mCircleFillColor = 0;
        this.mCircleProgressColor = 0;
        this.mPointerAlpha = 135;
        this.mPointerAlphaOnTouch = 100;
        this.lockEnabled = true;
        this.lockAtStart = true;
        this.lockAtEnd = false;
        this.mUserIsMovingPointer = false;
        this.mPointerPositionXY = new float[2];
        this.isTouchEnabled = true;
        init(null, 0);
    }

    public CustomCircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.DPTOPX_SCALE = getResources().getDisplayMetrics().density;
        this.MIN_TOUCH_TARGET_DP = 48.0f;
        this.mCircleRectF = new RectF();
        this.mPointerColor = 0;
        this.mPointerHaloColor = 0;
        this.mPointerHaloColorOnTouch = 0;
        this.mCircleColor = 0;
        this.mCircleFillColor = 0;
        this.mCircleProgressColor = 0;
        this.mPointerAlpha = 135;
        this.mPointerAlphaOnTouch = 100;
        this.lockEnabled = true;
        this.lockAtStart = true;
        this.lockAtEnd = false;
        this.mUserIsMovingPointer = false;
        this.mPointerPositionXY = new float[2];
        this.isTouchEnabled = true;
        init(attrs, 0);
    }

    public CustomCircleProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.DPTOPX_SCALE = getResources().getDisplayMetrics().density;
        this.MIN_TOUCH_TARGET_DP = 48.0f;
        this.mCircleRectF = new RectF();
        this.mPointerColor = 0;
        this.mPointerHaloColor = 0;
        this.mPointerHaloColorOnTouch = 0;
        this.mCircleColor = 0;
        this.mCircleFillColor = 0;
        this.mCircleProgressColor = 0;
        this.mPointerAlpha = 135;
        this.mPointerAlphaOnTouch = 100;
        this.lockEnabled = true;
        this.lockAtStart = true;
        this.lockAtEnd = false;
        this.mUserIsMovingPointer = false;
        this.mPointerPositionXY = new float[2];
        this.isTouchEnabled = true;
        init(attrs, defStyle);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Bundle state = new Bundle();
        state.putParcelable("PARENT", superState);
        state.putInt("MAX", this.mMax);
        state.putInt("PROGRESS", this.mProgress);
        state.putInt("mCircleColor", this.mCircleColor);
        state.putInt("mCircleProgressColor", this.mCircleProgressColor);
        state.putInt("mPointerColor", this.mPointerColor);
        state.putInt("mPointerHaloColor", this.mPointerHaloColor);
        state.putInt("mPointerHaloColorOnTouch", this.mPointerHaloColorOnTouch);
        state.putInt("mPointerAlpha", this.mPointerAlpha);
        state.putInt("mPointerAlphaOnTouch", this.mPointerAlphaOnTouch);
        state.putBoolean("lockEnabled", this.lockEnabled);
        state.putBoolean("isTouchEnabled", this.isTouchEnabled);
        return state;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;
        Parcelable superState = savedState.getParcelable("PARENT");
        super.onRestoreInstanceState(superState);
        this.mMax = savedState.getInt("MAX");
        this.mProgress = savedState.getInt("PROGRESS");
        this.mCircleColor = savedState.getInt("mCircleColor");
        this.mCircleProgressColor = savedState.getInt("mCircleProgressColor");
        this.mPointerColor = savedState.getInt("mPointerColor");
        this.mPointerHaloColor = savedState.getInt("mPointerHaloColor");
        this.mPointerHaloColorOnTouch = savedState.getInt("mPointerHaloColorOnTouch");
        this.mPointerAlpha = savedState.getInt("mPointerAlpha");
        this.mPointerAlphaOnTouch = savedState.getInt("mPointerAlphaOnTouch");
        this.lockEnabled = savedState.getBoolean("lockEnabled");
        this.isTouchEnabled = savedState.getBoolean("isTouchEnabled");
        initPaints();
        recalculateAll();
    }

    public void setOnSeekBarChangeListener(OnCircularSeekBarChangeListener l) {
        this.mOnCircularSeekBarChangeListener = l;
    }

    public void setCircleColor(int color) {
        this.mCircleColor = color;
        this.mCirclePaint.setColor(color);
        invalidate();
    }

    public int getCircleColor() {
        return this.mCircleColor;
    }

    public void setCircleProgressColor(int color) {
        this.mCircleProgressColor = color;
        this.mCircleProgressPaint.setColor(color);
        invalidate();
    }

    public int getCircleProgressColor() {
        return this.mCircleProgressColor;
    }

    public void setPointerColor(int color) {
        this.mPointerColor = color;
        this.mPointerPaint.setColor(color);
        invalidate();
    }

    public int getPointerColor() {
        return this.mPointerColor;
    }

    public void setPointerHaloColor(int color) {
        this.mPointerHaloColor = color;
        this.mPointerHaloPaint.setColor(color);
        invalidate();
    }

    public int getPointerHaloColor() {
        return this.mPointerHaloColor;
    }

    public void setPointerAlpha(int alpha) {
        if (alpha >= 0 && alpha <= 255) {
            this.mPointerAlpha = alpha;
            this.mPointerHaloPaint.setAlpha(alpha);
            invalidate();
        }
    }

    public int getPointerAlpha() {
        return this.mPointerAlpha;
    }

    public void setPointerAlphaOnTouch(int alpha) {
        if (alpha >= 0 && alpha <= 255) {
            this.mPointerAlphaOnTouch = alpha;
        }
    }

    public int getPointerAlphaOnTouch() {
        return this.mPointerAlphaOnTouch;
    }

    public void setCircleFillColor(int color) {
        this.mCircleFillColor = color;
        this.mCircleFillPaint.setColor(color);
        invalidate();
    }

    public int getCircleFillColor() {
        return this.mCircleFillColor;
    }

    public void setMax(int max) {
        if (max > 0) {
            if (max <= this.mProgress) {
                this.mProgress = 0;
                OnCircularSeekBarChangeListener onCircularSeekBarChangeListener = this.mOnCircularSeekBarChangeListener;
                if (onCircularSeekBarChangeListener != null) {
                    onCircularSeekBarChangeListener.onProgressChanged(this, 0, false);
                }
            }
            this.mMax = max;
            recalculateAll();
            invalidate();
        }
    }

    public synchronized int getMax() {
        return this.mMax;
    }

    public void setIsTouchEnabled(boolean isTouchEnabled) {
        this.isTouchEnabled = isTouchEnabled;
    }

    public boolean getIsTouchEnabled() {
        return this.isTouchEnabled;
    }
}
