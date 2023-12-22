package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatImageHelper extends SkinCompatHelper {
    private static final String TAG = SkinCompatImageHelper.class.getSimpleName();
    private final ImageView mView;
    private int mSrcResId = 0;
    private int mSrcCompatResId = 0;

    public SkinCompatImageHelper(ImageView imageView) {
        this.mView = imageView;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = null;
        try {
            a = this.mView.getContext().obtainStyledAttributes(attrs, C1899R.styleable.SkinCompatImageView, defStyleAttr, 0);
            this.mSrcResId = a.getResourceId(C1899R.styleable.SkinCompatImageView_android_src, 0);
            this.mSrcCompatResId = a.getResourceId(C1899R.styleable.SkinCompatImageView_srcCompat, 0);
            applySkin();
        } finally {
            if (a != null) {
                a.recycle();
            }
        }
    }

    public void setImageResource(int resId) {
        this.mSrcResId = resId;
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        Drawable drawable;
        int checkResourceId = checkResourceId(this.mSrcCompatResId);
        this.mSrcCompatResId = checkResourceId;
        if (checkResourceId != 0) {
            Drawable drawable2 = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mSrcCompatResId);
            if (drawable2 != null) {
                this.mView.setImageDrawable(drawable2);
                return;
            }
            return;
        }
        int checkResourceId2 = checkResourceId(this.mSrcResId);
        this.mSrcResId = checkResourceId2;
        if (checkResourceId2 != 0 && (drawable = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mSrcResId)) != null) {
            this.mView.setImageDrawable(drawable);
        }
    }
}
