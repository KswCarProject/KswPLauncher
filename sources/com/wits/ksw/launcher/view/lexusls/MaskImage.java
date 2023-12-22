package com.wits.ksw.launcher.view.lexusls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.wits.ksw.C0899R;

/* loaded from: classes14.dex */
public class MaskImage extends AppCompatImageView {
    public static String TAG = MaskImage.class.getSimpleName() + " liuhao";
    private AttributeSet attrs;
    private int mImageSource;
    private int mMaskSource;
    private Bitmap mask;

    public MaskImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        mask();
    }

    public void mask() {
        Log.d(TAG, "attr " + this.attrs);
        TypedArray a = getContext().obtainStyledAttributes(this.attrs, C0899R.styleable.MaskImage, 0, 0);
        this.mImageSource = a.getResourceId(0, 0);
        this.mMaskSource = a.getResourceId(1, 0);
        Log.d(TAG, " imageSource " + this.mImageSource + " mMaskSource " + this.mMaskSource);
        Bitmap original = BitmapFactory.decodeResource(getResources(), this.mImageSource);
        Log.d(TAG, "pic size:" + original.getWidth() + "-" + original.getHeight());
        this.mask = BitmapFactory.decodeResource(getResources(), this.mMaskSource);
        Log.d(TAG, "mask size:" + this.mask.getWidth() + "-" + this.mask.getHeight());
        Bitmap result = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, 0.0f, 0.0f, (Paint) null);
        Matrix mMatrix = new Matrix();
        mCanvas.drawBitmap(this.mask, mMatrix, paint);
        paint.setXfermode(null);
        setImageBitmap(result);
        setScaleType(ImageView.ScaleType.CENTER);
        a.recycle();
    }

    public void mask(Bitmap bitmap) {
        if (bitmap != null) {
            Log.d(TAG, "pic size:" + bitmap.getWidth() + "-" + bitmap.getHeight());
            Log.d(TAG, "mask size:" + this.mask.getWidth() + "-" + this.mask.getHeight());
            Bitmap result = Bitmap.createBitmap(this.mask.getWidth(), this.mask.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas mCanvas = new Canvas(result);
            Paint paint = new Paint(1);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
            Paint paintG = new Paint(1);
            mCanvas.drawBitmap(bitmap, 0.0f, 0.0f, paintG);
            Matrix mMatrix = new Matrix();
            mCanvas.drawBitmap(this.mask, mMatrix, paint);
            paint.setXfermode(null);
            setImageBitmap(result);
            setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    public Bitmap bitMapScale(Bitmap bitmap, float withScale, float highScale) {
        Matrix matrix = new Matrix();
        matrix.postScale(withScale, highScale);
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public void setBitmap(Bitmap bitmap) {
        Bitmap bmp = cropBitmap(bitmap);
        Log.d(TAG, "W = " + bmp.getWidth() + " h =" + bmp.getHeight());
        mask(bmp);
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {
        return Bitmap.createBitmap(bitmap, 0, 0, 1280, 480, (Matrix) null, false);
    }
}
