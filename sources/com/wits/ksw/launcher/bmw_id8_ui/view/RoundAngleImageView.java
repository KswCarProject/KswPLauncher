package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes15.dex */
public class RoundAngleImageView extends ImageView {
    private int roundHeight;
    private int roundWidth;

    public RoundAngleImageView(Context context) {
        super(context);
        this.roundWidth = 7;
        this.roundHeight = 7;
        init(context, null);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.roundWidth = 7;
        this.roundHeight = 7;
        init(context, attrs);
    }

    public RoundAngleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.roundWidth = 7;
        this.roundHeight = 7;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        float density = context.getResources().getDisplayMetrics().density;
        this.roundWidth = (int) (this.roundWidth * density);
        this.roundHeight = (int) (this.roundHeight * density);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        if (bitmap.isRecycled()) {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            canvas2 = new Canvas(bitmap);
        }
        super.draw(canvas2);
        drawRoundAngle(canvas2);
        Paint paint = new Paint();
        paint.setXfermode(null);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        bitmap.recycle();
    }

    public void setRoundWidth(int roundWidth, int roundHeight) {
        this.roundWidth = roundWidth;
        this.roundHeight = roundHeight;
    }

    private void drawRoundAngle(Canvas canvas) {
        Paint maskPaint = new Paint();
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Path maskPath = new Path();
        maskPath.addRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), this.roundWidth, this.roundHeight, Path.Direction.CW);
        maskPath.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(maskPath, maskPaint);
    }
}
