package com.wits.ksw.launcher.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.BitmapUtil;
import com.wits.ksw.launcher.utils.ScreenUtil;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

public class LinearGradientProgressNew extends View implements SkinCompatSupportable {
    private static final String TAG = "LinearGradientProgressNew";
    private Bitmap gradientBitmap = null;
    private Drawable gradientDrawable;
    private int mHeight;
    private boolean mOffset = false;
    private float mOffsetY = 0.0f;
    private int mOrientation;
    private int mScale = 0;
    private int mScaleHeight = 0;
    private float mTiltAngle = 0.0f;
    private float mTiltWidth = 0.0f;
    private int mWidth;
    private int maxScale = 100;
    private Paint paint;
    private TypedArray typedArray;

    public LinearGradientProgressNew(Context context) {
        super(context);
        Log.i(TAG, "init context");
        initView();
    }

    public LinearGradientProgressNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "init context attrs");
        initConfig(context, attrs);
        initView();
    }

    public LinearGradientProgressNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "init context attrs defStyleAttr");
        initConfig(context, attrs);
        initView();
    }

    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Gradient_Progress);
        this.typedArray = obtainStyledAttributes;
        this.maxScale = obtainStyledAttributes.getInteger(0, 100);
        this.mTiltWidth = this.typedArray.getDimension(5, 0.0f);
        this.mScale = this.typedArray.getInteger(4, 0);
        this.mOffset = this.typedArray.getBoolean(3, false);
        this.gradientDrawable = this.typedArray.getDrawable(2);
        if (SkinCompatResources.getInstance() != null) {
            this.gradientDrawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(2, 2));
        }
        this.mOrientation = this.typedArray.getInt(1, 0);
    }

    private void initView() {
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        this.paint.setDither(true);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged w " + w + " h " + h);
        this.mWidth = w;
        this.mHeight = h;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calcScaleHeight(this.mScale);
        calcTiltAngle(this.mScale);
        Log.e(TAG, "onDraw: mScaleHeight " + this.mScaleHeight + " mTiltAngle " + this.mTiltAngle);
        int i = this.mScaleHeight;
        if (i > 0) {
            this.gradientBitmap = BitmapUtil.getScaleAndRotateBitmap(this.gradientDrawable, this.mWidth, i, this.mTiltAngle);
        }
        if (this.gradientBitmap != null) {
            this.mOffsetY = (float) ((int) (Math.tan((((double) Math.abs(this.mTiltAngle)) * 3.141592653589793d) / 180.0d) * ((double) this.mTiltWidth)));
            Log.e(TAG, "onDraw: gradientBitmap.getHeight(): " + this.gradientBitmap.getHeight() + " mOffsetY " + this.mOffsetY);
            canvas.drawBitmap(this.gradientBitmap, 0.0f, ((float) (this.mHeight - this.mScaleHeight)) - this.mOffsetY, (Paint) null);
        }
    }

    public void setScae(int scale) {
        Log.e(TAG, "setScae scale == " + scale);
        this.mScale = scale;
        if (scale < 0) {
            this.mScale = 0;
        }
        int i = this.mScale;
        int i2 = this.maxScale;
        if (i > i2) {
            this.mScale = i2;
        }
        invalidate();
    }

    private void calcScaleHeight(int scale) {
        int value;
        if (scale <= 0) {
            this.mScaleHeight = 0;
            return;
        }
        if (this.mOrientation != 1) {
            int i = this.mHeight;
            value = Math.min((i * scale) / this.maxScale, i);
        } else if (scale > 1000) {
            int i2 = this.mHeight;
            value = Math.min(((scale - 500) * i2) / (this.maxScale - 500), i2);
        } else {
            value = Math.min((ScreenUtil.dip2px(26.0f) * scale) / 1000, ScreenUtil.dip2px(26.0f));
        }
        this.mScaleHeight = calcOffsetHeight(this.mScale) + value;
    }

    private int calcOffsetHeight(int scale) {
        if (!this.mOffset || scale <= 0) {
            return 0;
        }
        if (this.mOrientation == 0) {
            if (scale <= 50) {
                return ScreenUtil.dip2px(5.3f);
            }
            if (scale > 50 && scale < 90) {
                return ScreenUtil.dip2px(4.7f);
            }
            if (scale >= 90 && scale < 110) {
                return ScreenUtil.dip2px(4.0f);
            }
            if (scale >= 110 && scale < 130) {
                return ScreenUtil.dip2px(3.3f);
            }
            if (scale >= 130 && scale < 180) {
                return ScreenUtil.dip2px(2.7f);
            }
            if (scale >= 180 && scale < 200) {
                return ScreenUtil.dip2px(2.0f);
            }
            if (scale >= 200 && scale < 250) {
                return ScreenUtil.dip2px(1.3f);
            }
            if (scale >= 250 && scale < 300) {
                return ScreenUtil.dip2px(2.0f);
            }
            if (scale < 300 || scale >= this.maxScale) {
                return 0;
            }
            return ScreenUtil.dip2px(1.3f);
        } else if (scale > 2000 && scale <= 3000) {
            return ScreenUtil.dip2px(2.0f);
        } else {
            if (scale > 3000 && scale <= 4000) {
                return ScreenUtil.dip2px(4.0f);
            }
            if (scale > 4000 && scale <= 4500) {
                return ScreenUtil.dip2px(5.0f);
            }
            if (scale > 4500 && scale <= 5000) {
                return ScreenUtil.dip2px(2.0f);
            }
            if (scale > 5000 && scale <= 5500) {
                return ScreenUtil.dip2px(4.0f);
            }
            if (scale > 5500 && scale <= 6500) {
                return ScreenUtil.dip2px(0.0f);
            }
            if (scale <= 6500 || scale > 7000) {
                return 0;
            }
            return -ScreenUtil.dip2px(3.0f);
        }
    }

    private void calcTiltAngle(int scale) {
        this.mTiltAngle = 0.0f;
        if (scale > 0) {
            int i = this.mOrientation;
            if (i == 0) {
                if (scale <= 50) {
                    this.mTiltAngle = -9.0f;
                } else if (scale > 50 && scale < 90) {
                    this.mTiltAngle = -7.0f;
                } else if (scale >= 90 && scale < 110) {
                    this.mTiltAngle = -6.0f;
                } else if (scale >= 110 && scale < 130) {
                    this.mTiltAngle = -5.0f;
                } else if (scale >= 130 && scale < 180) {
                    this.mTiltAngle = -4.0f;
                } else if (scale >= 180 && scale < 200) {
                    this.mTiltAngle = -3.5f;
                } else if (scale >= 200 && scale < 230) {
                    this.mTiltAngle = -2.5f;
                } else if (scale >= 230 && scale < this.maxScale) {
                    this.mTiltAngle = -2.0f;
                }
            } else if (i != 1) {
            } else {
                if (scale <= 1000) {
                    this.mTiltAngle = 7.0f;
                } else if (scale > 1000 && scale <= 2000) {
                    this.mTiltAngle = 8.5f;
                } else if (scale > 2000 && scale <= 3000) {
                    this.mTiltAngle = 6.5f;
                } else if (scale > 3000 && scale <= 4000) {
                    this.mTiltAngle = 5.5f;
                } else if (scale > 4000 && scale <= 4500) {
                    this.mTiltAngle = 4.0f;
                } else if (scale > 4500 && scale <= 5000) {
                    this.mTiltAngle = 2.5f;
                } else if (scale > 5000 && scale <= 5500) {
                    this.mTiltAngle = 2.5f;
                } else if (scale > 5500 && scale <= 6500) {
                    this.mTiltAngle = 2.0f;
                } else if (scale > 6500 && scale <= 7000) {
                    this.mTiltAngle = 1.5f;
                }
            }
        }
    }

    public void applySkin() {
        Log.e(TAG, "applySkin");
        if (!(SkinCompatResources.getInstance() == null || this.typedArray == null)) {
            this.gradientDrawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(2, 2));
        }
        invalidate();
    }
}
