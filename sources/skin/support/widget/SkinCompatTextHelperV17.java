package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatTextHelperV17 extends SkinCompatTextHelper {
    private int mDrawableEndResId;
    private int mDrawableStartResId;

    public SkinCompatTextHelperV17(TextView view) {
        super(view);
        this.mDrawableStartResId = 0;
        this.mDrawableEndResId = 0;
    }

    @Override // skin.support.widget.SkinCompatTextHelper
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        Context context = this.mView.getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, C1899R.styleable.SkinCompatTextHelper, defStyleAttr, 0);
        if (a.hasValue(C1899R.styleable.SkinCompatTextHelper_android_drawableStart)) {
            int resourceId = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_drawableStart, 0);
            this.mDrawableStartResId = resourceId;
            this.mDrawableStartResId = SkinCompatHelper.checkResourceId(resourceId);
        }
        if (a.hasValue(C1899R.styleable.SkinCompatTextHelper_android_drawableEnd)) {
            int resourceId2 = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_drawableEnd, 0);
            this.mDrawableEndResId = resourceId2;
            this.mDrawableEndResId = SkinCompatHelper.checkResourceId(resourceId2);
        }
        a.recycle();
        super.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override // skin.support.widget.SkinCompatTextHelper
    public void onSetCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        this.mDrawableStartResId = start;
        this.mDrawableTopResId = top;
        this.mDrawableEndResId = end;
        this.mDrawableBottomResId = bottom;
        applyCompoundDrawablesRelativeResource();
    }

    @Override // skin.support.widget.SkinCompatTextHelper
    protected void applyCompoundDrawablesRelativeResource() {
        Drawable drawableLeft = null;
        Drawable drawableStart = null;
        this.mDrawableLeftResId = checkResourceId(this.mDrawableLeftResId);
        if (this.mDrawableLeftResId != 0) {
            drawableLeft = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableLeftResId);
        }
        this.mDrawableTopResId = checkResourceId(this.mDrawableTopResId);
        Drawable drawableTop = this.mDrawableTopResId != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableTopResId) : null;
        this.mDrawableRightResId = checkResourceId(this.mDrawableRightResId);
        Drawable drawableRight = this.mDrawableRightResId != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableRightResId) : null;
        this.mDrawableBottomResId = checkResourceId(this.mDrawableBottomResId);
        Drawable drawableBottom = this.mDrawableBottomResId != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableBottomResId) : null;
        if (this.mDrawableStartResId != 0) {
            drawableStart = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableStartResId);
        }
        if (drawableStart == null) {
            drawableStart = drawableLeft;
        }
        Drawable drawableEnd = this.mDrawableEndResId != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableEndResId) : null;
        if (drawableEnd == null) {
            drawableEnd = drawableRight;
        }
        if (this.mDrawableLeftResId != 0 || this.mDrawableTopResId != 0 || this.mDrawableRightResId != 0 || this.mDrawableBottomResId != 0 || this.mDrawableStartResId != 0 || this.mDrawableEndResId != 0) {
            this.mView.setCompoundDrawablesWithIntrinsicBounds(drawableStart, drawableTop, drawableEnd, drawableBottom);
        }
    }
}
