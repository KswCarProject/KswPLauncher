package com.wits.ksw.launcher.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.utils.BitmapUtil;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes16.dex */
public class LinearGradientProgress extends View implements SkinCompatSupportable {
    private static final String TAG = LinearGradientProgress.class.getSimpleName();
    private int[] colorArray;
    private final int colorArrayLength;
    private float[] colorPosition;
    private LinearGradient linearGradient;
    private boolean linearVisible;
    private int mHeight;
    private int mOrientation;
    private int mScale;
    private int mWidth;
    private Bitmap maskBitmap;
    private Drawable maskDrawable;
    private boolean maskVisible;
    private int maxScale;
    private Paint paint;
    private Bitmap rulerBitmap;
    private Drawable rulerDrawable;
    private boolean rulerVisible;
    private Bitmap scaleBitmap;
    private Drawable scaleDrawable;
    private boolean scaleVisible;
    private TypedArray typedArray;

    public LinearGradientProgress(Context context) {
        super(context);
        this.colorArrayLength = 5;
        this.maxScale = 100;
        this.mScale = 0;
        this.rulerBitmap = null;
        this.maskBitmap = null;
        this.scaleBitmap = null;
        Log.i(TAG, "init context");
        initView();
    }

    public LinearGradientProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.colorArrayLength = 5;
        this.maxScale = 100;
        this.mScale = 0;
        this.rulerBitmap = null;
        this.maskBitmap = null;
        this.scaleBitmap = null;
        Log.i(TAG, "init context attrs");
        initConfig(context, attrs);
        initView();
    }

    public LinearGradientProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.colorArrayLength = 5;
        this.maxScale = 100;
        this.mScale = 0;
        this.rulerBitmap = null;
        this.maskBitmap = null;
        this.scaleBitmap = null;
        Log.i(TAG, "init context attrs defStyleAttr");
        initConfig(context, attrs);
        initView();
    }

    private void initConfig(Context context, AttributeSet attrs) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, C0899R.styleable.Linear_Gradient_Progress);
        this.typedArray = obtainStyledAttributes;
        this.linearVisible = obtainStyledAttributes.getBoolean(0, true);
        this.rulerVisible = this.typedArray.getBoolean(6, false);
        this.maskVisible = this.typedArray.getBoolean(2, false);
        this.scaleVisible = this.typedArray.getBoolean(9, false);
        this.maxScale = this.typedArray.getInteger(3, 100);
        this.mScale = this.typedArray.getInteger(7, 0);
        this.mOrientation = this.typedArray.getInt(4, 0);
        this.rulerDrawable = this.typedArray.getDrawable(5);
        this.maskDrawable = this.typedArray.getDrawable(1);
        this.scaleDrawable = this.typedArray.getDrawable(8);
        if (SkinCompatResources.getInstance() != null) {
            this.rulerDrawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(5, 5));
            this.maskDrawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(1, 1));
            this.scaleDrawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(8, 8));
        }
    }

    private void initView() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setDither(true);
        if (TextUtils.equals(ID8LauncherConstants.ID8_SKIN_SPORT, ID8LauncherConstants.loadCurrentSkin())) {
            this.colorArray = getResources().getIntArray(C0899R.array.bmw_id8_dashboard_linear_red);
        } else if (TextUtils.equals(ID8LauncherConstants.ID8_SKIN_EFFICIENT, ID8LauncherConstants.loadCurrentSkin())) {
            this.colorArray = getResources().getIntArray(C0899R.array.bmw_id8_dashboard_linear_blue);
        } else {
            this.colorArray = getResources().getIntArray(C0899R.array.bmw_id8_dashboard_linear_yellow);
        }
        this.colorPosition = new float[]{0.0f, 0.21f, 0.47f, 0.71f, 0.78f, 0.94f};
        this.rulerBitmap = BitmapUtil.drawableToBitmap(this.rulerDrawable);
        this.maskBitmap = BitmapUtil.drawableToBitmap(this.maskDrawable);
        this.scaleBitmap = BitmapUtil.drawableToBitmap(this.scaleDrawable);
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
        Rect mSrcRect;
        Rect mDestRect;
        Bitmap bitmap;
        Bitmap bitmap2;
        super.onDraw(canvas);
        Log.i(TAG, "onDraw");
        if (this.linearVisible && this.paint != null) {
            if (this.mOrientation == 0) {
                int i = this.mHeight;
                LinearGradient linearGradient = new LinearGradient(0.0f, i, 0.0f, i - calcScale(this.mScale), this.colorArray, this.colorPosition, Shader.TileMode.CLAMP);
                this.linearGradient = linearGradient;
                this.paint.setShader(linearGradient);
                canvas.drawRect(0.0f, this.mHeight - calcScale(this.mScale), this.mWidth, this.mHeight, this.paint);
            } else {
                LinearGradient linearGradient2 = new LinearGradient(0.0f, 0.0f, calcScale(this.mScale), this.mHeight, this.colorArray, this.colorPosition, Shader.TileMode.CLAMP);
                this.linearGradient = linearGradient2;
                this.paint.setShader(linearGradient2);
                canvas.drawRect(0.0f, 0.0f, calcScale(this.mScale), this.mHeight, this.paint);
            }
        }
        if (this.rulerVisible && (bitmap2 = this.rulerBitmap) != null) {
            if (this.mOrientation != 0) {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, bitmap2.getWidth(), this.mHeight, true);
                this.rulerBitmap = createScaledBitmap;
                canvas.drawBitmap(createScaledBitmap, calcScale(this.mScale), 0.0f, (Paint) null);
            } else {
                Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(bitmap2, this.mWidth, bitmap2.getHeight(), true);
                this.rulerBitmap = createScaledBitmap2;
                canvas.drawBitmap(createScaledBitmap2, 0.0f, this.mHeight - calcScale(this.mScale), (Paint) null);
            }
        }
        if (this.maskVisible && (bitmap = this.maskBitmap) != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        if (this.scaleVisible && this.scaleBitmap != null) {
            if (this.mOrientation == 0) {
                mSrcRect = new Rect(0, this.mHeight - calcScale(this.mScale), this.mWidth, this.mHeight);
                mDestRect = new Rect(0, this.mHeight - calcScale(this.mScale), this.mWidth, this.mHeight);
            } else {
                mSrcRect = new Rect(0, 0, calcScale(this.mScale), this.mHeight);
                mDestRect = new Rect(0, 0, calcScale(this.mScale), this.mHeight);
            }
            canvas.drawBitmap(this.scaleBitmap, mSrcRect, mDestRect, (Paint) null);
        }
    }

    public void setColorArray(int[] color) {
        if (color == null || color.length != 5) {
            return;
        }
        this.colorArray = color;
        invalidate();
    }

    public void setPositionArray(float[] position) {
        if (position == null || position.length != 5) {
            return;
        }
        this.colorPosition = position;
        invalidate();
    }

    public void setScale(int scale) {
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

    private int calcScale(int scale) {
        int value;
        if (this.mOrientation == 0) {
            int i = this.mHeight;
            value = Math.min((i * scale) / this.maxScale, i);
        } else {
            int i2 = this.mWidth;
            value = Math.min((i2 * scale) / this.maxScale, i2);
        }
        if (value < 0) {
            return 0;
        }
        return value;
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        Log.i(TAG, "applySkin");
        if (TextUtils.equals(ID8LauncherConstants.ID8_SKIN_SPORT, ID8LauncherConstants.loadCurrentSkin())) {
            this.colorArray = getResources().getIntArray(C0899R.array.bmw_id8_dashboard_linear_red);
        } else if (TextUtils.equals(ID8LauncherConstants.ID8_SKIN_EFFICIENT, ID8LauncherConstants.loadCurrentSkin())) {
            this.colorArray = getResources().getIntArray(C0899R.array.bmw_id8_dashboard_linear_blue);
        } else {
            this.colorArray = getResources().getIntArray(C0899R.array.bmw_id8_dashboard_linear_yellow);
        }
        if (SkinCompatResources.getInstance() != null && this.typedArray != null) {
            Drawable drawable = SkinCompatResources.getInstance().getDrawable(this.typedArray.getResourceId(1, 1));
            this.maskDrawable = drawable;
            this.maskBitmap = BitmapUtil.drawableToBitmap(drawable);
        }
        invalidate();
    }
}