package com.wits.ksw.launcher.als_id7_ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CustomRadiusImageView extends AppCompatImageView {
    private float height;
    private Matrix matrix;
    private Paint paint;
    private float radius;
    private float width;

    public CustomRadiusImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomRadiusImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRadiusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setAntiAlias(true);
        this.matrix = new Matrix();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = (float) getMeasuredWidth();
        float measuredHeight = (float) getMeasuredHeight();
        this.height = measuredHeight;
        this.radius = Math.min(this.width, measuredHeight) / 2.0f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
        } else if (drawable instanceof BitmapDrawable) {
            this.paint.setShader(initBitmapShader((BitmapDrawable) drawable));
            canvas.drawCircle(this.width / 2.0f, this.height / 2.0f, this.radius, this.paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = Math.max(this.width / ((float) bitmap.getWidth()), this.height / ((float) bitmap.getHeight()));
        this.matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(this.matrix);
        return bitmapShader;
    }
}
