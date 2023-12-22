package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

/* loaded from: classes13.dex */
public class DragView extends View {
    private static final int DRAG_SCALE = 60;
    private boolean isNeedDragBg;
    private float mAnimationScale;
    private Bitmap mBitmap;
    private WindowManager.LayoutParams mLayoutParams;
    private int mRegistrationX;
    private int mRegistrationY;
    private float mScale;
    private WindowManager mWindowManager;

    public DragView(Context context, Bitmap bitmap, int registrationX, int registrationY, int left, int top, int width, int height) {
        super(context);
        this.mAnimationScale = 0.9f;
        this.isNeedDragBg = true;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        Matrix scale = new Matrix();
        float scaleFactor = width;
        float scaleFactor2 = (60.0f + scaleFactor) / scaleFactor;
        this.mScale = scaleFactor2;
        scale.setScale(scaleFactor2, scaleFactor2);
        this.mBitmap = Bitmap.createBitmap(bitmap, left, top, width, height, scale, true);
        this.mRegistrationX = registrationX + 30;
        this.mRegistrationY = registrationY + 30;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.isNeedDragBg) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(0);
            canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), p);
        }
        float scale = this.mAnimationScale;
        if (scale < 0.999f) {
            float height = this.mBitmap.getHeight();
            float width = this.mBitmap.getWidth();
            float offset1 = (width - (width * scale)) / 2.0f;
            float offset2 = (height - (height * scale)) / 2.0f;
            canvas.translate(offset1, offset2);
            canvas.scale(scale, scale);
        }
        Paint p2 = new Paint();
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, p2);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mBitmap.recycle();
    }

    public void setScale(float scale) {
        if (scale > 1.0f) {
            this.mAnimationScale = 1.0f;
        } else {
            this.mAnimationScale = scale;
        }
        invalidate();
    }

    public void show(IBinder windowToken, int touchX, int touchY) {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(-2, -2, touchX - this.mRegistrationX, touchY - this.mRegistrationY, 1002, 768, -3);
        lp.gravity = 51;
        lp.token = windowToken;
        lp.setTitle("DragView");
        this.mLayoutParams = lp;
        this.mWindowManager.addView(this, lp);
    }

    void move(int touchX, int touchY) {
        WindowManager.LayoutParams lp = this.mLayoutParams;
        lp.x = touchX - this.mRegistrationX;
        lp.y = touchY - this.mRegistrationY;
        this.mWindowManager.updateViewLayout(this, lp);
    }

    void remove() {
        this.mWindowManager.removeView(this);
    }
}
