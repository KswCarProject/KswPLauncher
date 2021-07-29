package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

public class DragView extends View {
    private static final int DRAG_SCALE = 60;
    private boolean isNeedDragBg = true;
    private float mAnimationScale = 0.9f;
    private Bitmap mBitmap;
    private WindowManager.LayoutParams mLayoutParams;
    private int mRegistrationX;
    private int mRegistrationY;
    private float mScale;
    private WindowManager mWindowManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DragView(Context context, Bitmap bitmap, int registrationX, int registrationY, int left, int top, int width, int height) {
        super(context);
        Context context2 = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        Matrix scale = new Matrix();
        float scaleFactor = (float) width;
        float f = (60.0f + scaleFactor) / scaleFactor;
        this.mScale = f;
        float scaleFactor2 = f;
        scale.setScale(scaleFactor2, scaleFactor2);
        this.mBitmap = Bitmap.createBitmap(bitmap, left, top, width, height, scale, true);
        this.mRegistrationX = registrationX + 30;
        this.mRegistrationY = registrationY + 30;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mBitmap.getWidth(), this.mBitmap.getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.isNeedDragBg) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setColor(0);
            canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), p);
        }
        float scale = this.mAnimationScale;
        if (scale < 0.999f) {
            float height = (float) this.mBitmap.getHeight();
            float width = (float) this.mBitmap.getWidth();
            canvas.translate((width - (width * scale)) / 2.0f, (height - (height * scale)) / 2.0f);
            canvas.scale(scale, scale);
        }
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, new Paint());
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
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

    /* access modifiers changed from: package-private */
    public void move(int touchX, int touchY) {
        WindowManager.LayoutParams lp = this.mLayoutParams;
        lp.x = touchX - this.mRegistrationX;
        lp.y = touchY - this.mRegistrationY;
        this.mWindowManager.updateViewLayout(this, lp);
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        this.mWindowManager.removeView(this);
    }
}
