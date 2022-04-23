package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatTextHelperV17 extends SkinCompatTextHelper {
    private int mDrawableEndResId = 0;
    private int mDrawableStartResId = 0;

    public SkinCompatTextHelperV17(TextView view) {
        super(view);
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatTextHelper, defStyleAttr, 0);
        if (a.hasValue(R.styleable.SkinCompatTextHelper_android_drawableStart)) {
            int resourceId = a.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableStart, 0);
            this.mDrawableStartResId = resourceId;
            this.mDrawableStartResId = SkinCompatHelper.checkResourceId(resourceId);
        }
        if (a.hasValue(R.styleable.SkinCompatTextHelper_android_drawableEnd)) {
            int resourceId2 = a.getResourceId(R.styleable.SkinCompatTextHelper_android_drawableEnd, 0);
            this.mDrawableEndResId = resourceId2;
            this.mDrawableEndResId = SkinCompatHelper.checkResourceId(resourceId2);
        }
        a.recycle();
        super.loadFromAttributes(attrs, defStyleAttr);
    }

    public void onSetCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        this.mDrawableStartResId = start;
        this.mDrawableTopResId = top;
        this.mDrawableEndResId = end;
        this.mDrawableBottomResId = bottom;
        applyCompoundDrawablesRelativeResource();
    }

    /* access modifiers changed from: protected */
    public void applyCompoundDrawablesRelativeResource() {
        Drawable drawableLeft = null;
        Drawable drawableTop = null;
        Drawable drawableRight = null;
        Drawable drawableBottom = null;
        Drawable drawableStart = null;
        Drawable drawableEnd = null;
        this.mDrawableLeftResId = checkResourceId(this.mDrawableLeftResId);
        if (this.mDrawableLeftResId != 0) {
            drawableLeft = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableLeftResId);
        }
        this.mDrawableTopResId = checkResourceId(this.mDrawableTopResId);
        if (this.mDrawableTopResId != 0) {
            drawableTop = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableTopResId);
        }
        this.mDrawableRightResId = checkResourceId(this.mDrawableRightResId);
        if (this.mDrawableRightResId != 0) {
            drawableRight = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableRightResId);
        }
        this.mDrawableBottomResId = checkResourceId(this.mDrawableBottomResId);
        if (this.mDrawableBottomResId != 0) {
            drawableBottom = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableBottomResId);
        }
        if (this.mDrawableStartResId != 0) {
            drawableStart = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableStartResId);
        }
        if (drawableStart == null) {
            drawableStart = drawableLeft;
        }
        if (this.mDrawableEndResId != 0) {
            drawableEnd = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableEndResId);
        }
        if (drawableEnd == null) {
            drawableEnd = drawableRight;
        }
        if (this.mDrawableLeftResId != 0 || this.mDrawableTopResId != 0 || this.mDrawableRightResId != 0 || this.mDrawableBottomResId != 0 || this.mDrawableStartResId != 0 || this.mDrawableEndResId != 0) {
            this.mView.setCompoundDrawablesWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom);
        }
    }
}
