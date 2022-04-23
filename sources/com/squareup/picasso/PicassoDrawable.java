package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

final class PicassoDrawable extends BitmapDrawable {
    private static final Paint DEBUG_PAINT = new Paint();
    private static final float FADE_DURATION = 200.0f;
    int alpha = 255;
    boolean animating;
    private final boolean debugging;
    private final float density;
    private final Picasso.LoadedFrom loadedFrom;
    Drawable placeholder;
    long startTimeMillis;

    static void setBitmap(ImageView target, Context context, Bitmap bitmap, Picasso.LoadedFrom loadedFrom2, boolean noFade, boolean debugging2) {
        Drawable placeholder2 = target.getDrawable();
        if (placeholder2 instanceof AnimationDrawable) {
            ((AnimationDrawable) placeholder2).stop();
        }
        target.setImageDrawable(new PicassoDrawable(context, bitmap, placeholder2, loadedFrom2, noFade, debugging2));
    }

    static void setPlaceholder(ImageView target, Drawable placeholderDrawable) {
        target.setImageDrawable(placeholderDrawable);
        if (target.getDrawable() instanceof AnimationDrawable) {
            ((AnimationDrawable) target.getDrawable()).start();
        }
    }

    PicassoDrawable(Context context, Bitmap bitmap, Drawable placeholder2, Picasso.LoadedFrom loadedFrom2, boolean noFade, boolean debugging2) {
        super(context.getResources(), bitmap);
        this.debugging = debugging2;
        this.density = context.getResources().getDisplayMetrics().density;
        this.loadedFrom = loadedFrom2;
        if (loadedFrom2 != Picasso.LoadedFrom.MEMORY && !noFade) {
            this.placeholder = placeholder2;
            this.animating = true;
            this.startTimeMillis = SystemClock.uptimeMillis();
        }
    }

    public void draw(Canvas canvas) {
        if (!this.animating) {
            super.draw(canvas);
        } else {
            float normalized = ((float) (SystemClock.uptimeMillis() - this.startTimeMillis)) / FADE_DURATION;
            if (normalized >= 1.0f) {
                this.animating = false;
                this.placeholder = null;
                super.draw(canvas);
            } else {
                Drawable drawable = this.placeholder;
                if (drawable != null) {
                    drawable.draw(canvas);
                }
                super.setAlpha((int) (((float) this.alpha) * normalized));
                super.draw(canvas);
                super.setAlpha(this.alpha);
                if (Build.VERSION.SDK_INT <= 10) {
                    invalidateSelf();
                }
            }
        }
        if (this.debugging) {
            drawDebugIndicator(canvas);
        }
    }

    public void setAlpha(int alpha2) {
        this.alpha = alpha2;
        Drawable drawable = this.placeholder;
        if (drawable != null) {
            drawable.setAlpha(alpha2);
        }
        super.setAlpha(alpha2);
    }

    public void setColorFilter(ColorFilter cf) {
        Drawable drawable = this.placeholder;
        if (drawable != null) {
            drawable.setColorFilter(cf);
        }
        super.setColorFilter(cf);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        Drawable drawable = this.placeholder;
        if (drawable != null) {
            drawable.setBounds(bounds);
        }
        super.onBoundsChange(bounds);
    }

    private void drawDebugIndicator(Canvas canvas) {
        Paint paint = DEBUG_PAINT;
        paint.setColor(-1);
        canvas.drawPath(getTrianglePath(new Point(0, 0), (int) (this.density * 16.0f)), paint);
        paint.setColor(this.loadedFrom.debugColor);
        canvas.drawPath(getTrianglePath(new Point(0, 0), (int) (this.density * 15.0f)), paint);
    }

    private static Path getTrianglePath(Point p1, int width) {
        Point p2 = new Point(p1.x + width, p1.y);
        Point p3 = new Point(p1.x, p1.y + width);
        Path path = new Path();
        path.moveTo((float) p1.x, (float) p1.y);
        path.lineTo((float) p2.x, (float) p2.y);
        path.lineTo((float) p3.x, (float) p3.y);
        return path;
    }
}
