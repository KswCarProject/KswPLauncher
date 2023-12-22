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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.BitmapUtil;
import com.wits.ksw.launcher.utils.ScreenUtil;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes16.dex */
public class LinearGradientProgressNew extends View implements SkinCompatSupportable {
    private static final String TAG = "LinearGradientProgressNew";
    private Bitmap gradientBitmap;
    private Drawable gradientDrawable;
    private int mHeight;
    private boolean mOffset;
    private float mOffsetY;
    private int mOrientation;
    private int mScale;
    private int mScaleHeight;
    private float mTiltAngle;
    private float mTiltWidth;
    private int mWidth;
    private int maxScale;
    private Paint paint;
    private TypedArray typedArray;

    public LinearGradientProgressNew(Context context) {
        super(context);
        this.maxScale = 100;
        this.mTiltWidth = 0.0f;
        this.mScale = 0;
        this.mOffset = false;
        this.gradientBitmap = null;
        this.mOffsetY = 0.0f;
        this.mScaleHeight = 0;
        this.mTiltAngle = 0.0f;
        Log.i(TAG, "init context");
        initView();
    }

    public LinearGradientProgressNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.maxScale = 100;
        this.mTiltWidth = 0.0f;
        this.mScale = 0;
        this.mOffset = false;
        this.gradientBitmap = null;
        this.mOffsetY = 0.0f;
        this.mScaleHeight = 0;
        this.mTiltAngle = 0.0f;
        Log.i(TAG, "init context attrs");
        initConfig(context, attrs);
        initView();
    }

    public LinearGradientProgressNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.maxScale = 100;
        this.mTiltWidth = 0.0f;
        this.mScale = 0;
        this.mOffset = false;
        this.gradientBitmap = null;
        this.mOffsetY = 0.0f;
        this.mScaleHeight = 0;
        this.mTiltAngle = 0.0f;
        Log.i(TAG, "init context attrs defStyleAttr");
        initConfig(context, attrs);
        initView();
    }

    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, C0899R.styleable.Gradient_Progress);
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
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setDither(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged w " + w + " h " + h);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calcScaleHeight(this.mScale);
        calcTiltAngle(this.mScale);
        Log.e(TAG, "onDraw: mScaleHeight " + this.mScaleHeight + " mTiltAngle " + this.mTiltAngle);
        int i = this.mScaleHeight;
        if (i > 0) {
            this.gradientBitmap = BitmapUtil.getScaleAndRotateBitmap(this.gradientDrawable, this.mWidth, i, this.mTiltAngle);
        }
        if (this.gradientBitmap != null) {
            this.mOffsetY = (int) (Math.tan((Math.abs(this.mTiltAngle) * 3.141592653589793d) / 180.0d) * this.mTiltWidth);
            Log.e(TAG, "onDraw: gradientBitmap.getHeight(): " + this.gradientBitmap.getHeight() + " mOffsetY " + this.mOffsetY);
            canvas.drawBitmap(this.gradientBitmap, 0.0f, (this.mHeight - this.mScaleHeight) - this.mOffsetY, (Paint) null);
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
        if (this.mOrientation == 1) {
            if (scale > 1000) {
                int i = this.mHeight;
                value = Math.min(((scale - 500) * i) / (this.maxScale - 500), i);
            } else {
                value = Math.min((ScreenUtil.dip2px(26.0f) * scale) / 1000, ScreenUtil.dip2px(26.0f));
            }
        } else {
            int i2 = this.mHeight;
            value = Math.min((i2 * scale) / this.maxScale, i2);
        }
        this.mScaleHeight = calcOffsetHeight(this.mScale) + value;
    }

    private int calcOffsetHeight(int scale) {
        if (!this.mOffset || scale <= 0) {
            return 0;
        }
        if (this.mOrientation == 0) {
            if (scale > 50) {
                if (scale <= 50 || scale >= 90) {
                    if (scale < 90 || scale >= 110) {
                        if (scale < 110 || scale >= 130) {
                            if (scale < 130 || scale >= 180) {
                                if (scale < 180 || scale >= 200) {
                                    if (scale >= 200 && scale < 250) {
                                        int offset = ScreenUtil.dip2px(1.3f);
                                        return offset;
                                    } else if (scale >= 250 && scale < 300) {
                                        int offset2 = ScreenUtil.dip2px(2.0f);
                                        return offset2;
                                    } else if (scale < 300 || scale >= this.maxScale) {
                                        return 0;
                                    } else {
                                        int offset3 = ScreenUtil.dip2px(1.3f);
                                        return offset3;
                                    }
                                }
                                int offset4 = ScreenUtil.dip2px(2.0f);
                                return offset4;
                            }
                            int offset5 = ScreenUtil.dip2px(2.7f);
                            return offset5;
                        }
                        int offset6 = ScreenUtil.dip2px(3.3f);
                        return offset6;
                    }
                    int offset7 = ScreenUtil.dip2px(4.0f);
                    return offset7;
                }
                int offset8 = ScreenUtil.dip2px(4.7f);
                return offset8;
            }
            int offset9 = ScreenUtil.dip2px(5.3f);
            return offset9;
        } else if (scale > 2000 && scale <= 3000) {
            int offset10 = ScreenUtil.dip2px(2.0f);
            return offset10;
        } else if (scale <= 3000 || scale > 4000) {
            if (scale <= 4000 || scale > 4500) {
                if (scale <= 4500 || scale > 5000) {
                    if (scale <= 5000 || scale > 5500) {
                        if (scale > 5500 && scale <= 6500) {
                            int offset11 = ScreenUtil.dip2px(0.0f);
                            return offset11;
                        } else if (scale <= 6500 || scale > 7000) {
                            return 0;
                        } else {
                            int offset12 = -ScreenUtil.dip2px(3.0f);
                            return offset12;
                        }
                    }
                    int offset13 = ScreenUtil.dip2px(4.0f);
                    return offset13;
                }
                int offset14 = ScreenUtil.dip2px(2.0f);
                return offset14;
            }
            int offset15 = ScreenUtil.dip2px(5.0f);
            return offset15;
        } else {
            int offset16 = ScreenUtil.dip2px(4.0f);
            return offset16;
        }
    }

    private void calcTiltAngle(int scale) {
        this.mTiltAngle = 0.0f;
        if (scale <= 0) {
            return;
        }
        int i = this.mOrientation;
        if (i == 0) {
            if (scale > 50) {
                if (scale <= 50 || scale >= 90) {
                    if (scale < 90 || scale >= 110) {
                        if (scale < 110 || scale >= 130) {
                            if (scale < 130 || scale >= 180) {
                                if (scale < 180 || scale >= 200) {
                                    if (scale >= 200 && scale < 230) {
                                        this.mTiltAngle = -2.5f;
                                        return;
                                    } else if (scale >= 230 && scale < this.maxScale) {
                                        this.mTiltAngle = -2.0f;
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                                this.mTiltAngle = -3.5f;
                                return;
                            }
                            this.mTiltAngle = -4.0f;
                            return;
                        }
                        this.mTiltAngle = -5.0f;
                        return;
                    }
                    this.mTiltAngle = -6.0f;
                    return;
                }
                this.mTiltAngle = -7.0f;
                return;
            }
            this.mTiltAngle = -9.0f;
        } else if (i == 1) {
            if (scale > 1000) {
                if (scale <= 1000 || scale > 2000) {
                    if (scale <= 2000 || scale > 3000) {
                        if (scale <= 3000 || scale > 4000) {
                            if (scale <= 4000 || scale > 4500) {
                                if (scale > 4500 && scale <= 5000) {
                                    this.mTiltAngle = 2.5f;
                                    return;
                                } else if (scale <= 5000 || scale > 5500) {
                                    if (scale > 5500 && scale <= 6500) {
                                        this.mTiltAngle = 2.0f;
                                        return;
                                    } else if (scale > 6500 && scale <= 7000) {
                                        this.mTiltAngle = 1.5f;
                                        return;
                                    } else {
                                        return;
                                    }
                                } else {
                                    this.mTiltAngle = 2.5f;
                                    return;
                                }
                            }
                            this.mTiltAngle = 4.0f;
                            return;
                        }
                        this.mTiltAngle = 5.5f;
                        return;
                    }
                    this.mTiltAngle = 6.5f;
                    return;
                }
                this.mTiltAngle = 8.5f;
                return;
            }
            this.mTiltAngle = 7.0f;
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        Log.e(TAG, "applySkin");
        if (SkinCompatResources.getInstance() != null && this.typedArray != null) {
            this.gradientDrawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(2, 2));
        }
        invalidate();
    }
}
