package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatTextHelper extends SkinCompatHelper {
    private static final String TAG = SkinCompatTextHelper.class.getSimpleName();
    final TextView mView;
    private int mTextColorResId = 0;
    private int mTextColorHintResId = 0;
    protected int mDrawableBottomResId = 0;
    protected int mDrawableLeftResId = 0;
    protected int mDrawableRightResId = 0;
    protected int mDrawableTopResId = 0;

    public static SkinCompatTextHelper create(TextView textView) {
        if (Build.VERSION.SDK_INT >= 17) {
            return new SkinCompatTextHelperV17(textView);
        }
        return new SkinCompatTextHelper(textView);
    }

    public SkinCompatTextHelper(TextView view) {
        this.mView = view;
    }

    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        Context context = this.mView.getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, C1899R.styleable.SkinCompatTextHelper, defStyleAttr, 0);
        int ap = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_textAppearance, 0);
        if (a.hasValue(C1899R.styleable.SkinCompatTextHelper_android_drawableLeft)) {
            this.mDrawableLeftResId = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_drawableLeft, 0);
        }
        if (a.hasValue(C1899R.styleable.SkinCompatTextHelper_android_drawableTop)) {
            this.mDrawableTopResId = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_drawableTop, 0);
        }
        if (a.hasValue(C1899R.styleable.SkinCompatTextHelper_android_drawableRight)) {
            this.mDrawableRightResId = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_drawableRight, 0);
        }
        if (a.hasValue(C1899R.styleable.SkinCompatTextHelper_android_drawableBottom)) {
            this.mDrawableBottomResId = a.getResourceId(C1899R.styleable.SkinCompatTextHelper_android_drawableBottom, 0);
        }
        a.recycle();
        if (ap != 0) {
            TypedArray a2 = context.obtainStyledAttributes(ap, C1899R.styleable.SkinTextAppearance);
            if (a2.hasValue(C1899R.styleable.SkinTextAppearance_android_textColor)) {
                this.mTextColorResId = a2.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            if (a2.hasValue(C1899R.styleable.SkinTextAppearance_android_textColorHint)) {
                this.mTextColorHintResId = a2.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColorHint, 0);
            }
            a2.recycle();
        }
        TypedArray a3 = context.obtainStyledAttributes(attrs, C1899R.styleable.SkinTextAppearance, defStyleAttr, 0);
        if (a3.hasValue(C1899R.styleable.SkinTextAppearance_android_textColor)) {
            this.mTextColorResId = a3.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColor, 0);
        }
        if (a3.hasValue(C1899R.styleable.SkinTextAppearance_android_textColorHint)) {
            this.mTextColorHintResId = a3.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColorHint, 0);
        }
        a3.recycle();
        applySkin();
    }

    public void onSetTextAppearance(Context context, int resId) {
        TypedArray a = context.obtainStyledAttributes(resId, C1899R.styleable.SkinTextAppearance);
        if (a.hasValue(C1899R.styleable.SkinTextAppearance_android_textColor)) {
            this.mTextColorResId = a.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColor, 0);
        }
        if (a.hasValue(C1899R.styleable.SkinTextAppearance_android_textColorHint)) {
            this.mTextColorHintResId = a.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColorHint, 0);
        }
        a.recycle();
        applyTextColorResource();
        applyTextColorHintResource();
    }

    private void applyTextColorHintResource() {
        int checkResourceId = checkResourceId(this.mTextColorHintResId);
        this.mTextColorHintResId = checkResourceId;
        if (checkResourceId != C1899R.color.abc_hint_foreground_material_light && this.mTextColorHintResId != 0) {
            try {
                ColorStateList color = SkinCompatResources.getColorStateList(this.mView.getContext(), this.mTextColorHintResId);
                this.mView.setHintTextColor(color);
            } catch (Exception e) {
            }
        }
    }

    private void applyTextColorResource() {
        int checkResourceId = checkResourceId(this.mTextColorResId);
        this.mTextColorResId = checkResourceId;
        if (checkResourceId != C1899R.color.abc_primary_text_disable_only_material_light && this.mTextColorResId != C1899R.color.abc_secondary_text_material_light && this.mTextColorResId != 0) {
            try {
                ColorStateList color = SkinCompatResources.getColorStateList(this.mView.getContext(), this.mTextColorResId);
                this.mView.setTextColor(color);
            } catch (Exception e) {
            }
        }
    }

    public void onSetCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        this.mDrawableLeftResId = start;
        this.mDrawableTopResId = top;
        this.mDrawableRightResId = end;
        this.mDrawableBottomResId = bottom;
        applyCompoundDrawablesRelativeResource();
    }

    public void onSetCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        this.mDrawableLeftResId = left;
        this.mDrawableTopResId = top;
        this.mDrawableRightResId = right;
        this.mDrawableBottomResId = bottom;
        applyCompoundDrawablesResource();
    }

    protected void applyCompoundDrawablesRelativeResource() {
        applyCompoundDrawablesResource();
    }

    protected void applyCompoundDrawablesResource() {
        Drawable drawableLeft = null;
        int checkResourceId = checkResourceId(this.mDrawableLeftResId);
        this.mDrawableLeftResId = checkResourceId;
        if (checkResourceId != 0) {
            drawableLeft = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableLeftResId);
        }
        int checkResourceId2 = checkResourceId(this.mDrawableTopResId);
        this.mDrawableTopResId = checkResourceId2;
        Drawable drawableTop = checkResourceId2 != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableTopResId) : null;
        int checkResourceId3 = checkResourceId(this.mDrawableRightResId);
        this.mDrawableRightResId = checkResourceId3;
        Drawable drawableRight = checkResourceId3 != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableRightResId) : null;
        int checkResourceId4 = checkResourceId(this.mDrawableBottomResId);
        this.mDrawableBottomResId = checkResourceId4;
        Drawable drawableBottom = checkResourceId4 != 0 ? SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mDrawableBottomResId) : null;
        if (this.mDrawableLeftResId != 0 || this.mDrawableTopResId != 0 || this.mDrawableRightResId != 0 || this.mDrawableBottomResId != 0) {
            this.mView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }
    }

    public int getTextColorResId() {
        return this.mTextColorResId;
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        applyCompoundDrawablesRelativeResource();
        applyTextColorResource();
        applyTextColorHintResource();
    }
}
