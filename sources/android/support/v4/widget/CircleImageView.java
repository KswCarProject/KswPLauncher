package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.widget.ImageView;

class CircleImageView extends ImageView {
    private static final int FILL_SHADOW_COLOR = 1023410176;
    private static final int KEY_SHADOW_COLOR = 503316480;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 1.75f;
    private Animation.AnimationListener mListener;
    int mShadowRadius;

    CircleImageView(Context context, int color) {
        super(context);
        ShapeDrawable circle;
        float density = getContext().getResources().getDisplayMetrics().density;
        int shadowYOffset = (int) (Y_OFFSET * density);
        int shadowXOffset = (int) (0.0f * density);
        this.mShadowRadius = (int) (SHADOW_RADIUS * density);
        if (elevationSupported()) {
            circle = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, 4.0f * density);
        } else {
            ShapeDrawable circle2 = new ShapeDrawable(new OvalShadow(this.mShadowRadius));
            setLayerType(1, circle2.getPaint());
            circle2.getPaint().setShadowLayer((float) this.mShadowRadius, (float) shadowXOffset, (float) shadowYOffset, KEY_SHADOW_COLOR);
            int padding = this.mShadowRadius;
            setPadding(padding, padding, padding, padding);
            circle = circle2;
        }
        circle.getPaint().setColor(color);
        ViewCompat.setBackground(this, circle);
    }

    private boolean elevationSupported() {
        return Build.VERSION.SDK_INT >= 21;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!elevationSupported()) {
            setMeasuredDimension(getMeasuredWidth() + (this.mShadowRadius * 2), getMeasuredHeight() + (this.mShadowRadius * 2));
        }
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        this.mListener = listener;
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        if (this.mListener != null) {
            this.mListener.onAnimationStart(getAnimation());
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (this.mListener != null) {
            this.mListener.onAnimationEnd(getAnimation());
        }
    }

    public void setBackgroundColorRes(int colorRes) {
        setBackgroundColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public void setBackgroundColor(int color) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(color);
        }
    }

    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint = new Paint();

        OvalShadow(int shadowRadius) {
            CircleImageView.this.mShadowRadius = shadowRadius;
            updateRadialGradient((int) rect().width());
        }

        /* access modifiers changed from: protected */
        public void onResize(float width, float height) {
            super.onResize(width, height);
            updateRadialGradient((int) width);
        }

        public void draw(Canvas canvas, Paint paint) {
            int viewWidth = CircleImageView.this.getWidth();
            int viewHeight = CircleImageView.this.getHeight();
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) (viewWidth / 2), this.mShadowPaint);
            canvas.drawCircle((float) (viewWidth / 2), (float) (viewHeight / 2), (float) ((viewWidth / 2) - CircleImageView.this.mShadowRadius), paint);
        }

        private void updateRadialGradient(int diameter) {
            this.mRadialGradient = new RadialGradient((float) (diameter / 2), (float) (diameter / 2), (float) CircleImageView.this.mShadowRadius, new int[]{CircleImageView.FILL_SHADOW_COLOR, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.mShadowPaint.setShader(this.mRadialGradient);
        }
    }
}
