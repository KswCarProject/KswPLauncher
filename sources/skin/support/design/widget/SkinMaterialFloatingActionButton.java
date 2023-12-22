package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.C1911R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatImageHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes.dex */
public class SkinMaterialFloatingActionButton extends FloatingActionButton implements SkinCompatSupportable {
    private int mBackgroundTintResId;
    private SkinCompatImageHelper mImageHelper;
    private int mRippleColorResId;

    public SkinMaterialFloatingActionButton(Context context) {
        this(context, null);
    }

    public SkinMaterialFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SkinMaterialFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mRippleColorResId = 0;
        this.mBackgroundTintResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, C1911R.styleable.FloatingActionButton, defStyleAttr, C1911R.style.Widget_Design_FloatingActionButton);
        this.mBackgroundTintResId = a.getResourceId(C1911R.styleable.FloatingActionButton_backgroundTint, 0);
        this.mRippleColorResId = a.getResourceId(C1911R.styleable.FloatingActionButton_rippleColor, 0);
        a.recycle();
        applyBackgroundTintResource();
        applyRippleColorResource();
        SkinCompatImageHelper skinCompatImageHelper = new SkinCompatImageHelper(this);
        this.mImageHelper = skinCompatImageHelper;
        skinCompatImageHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    private void applyBackgroundTintResource() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mBackgroundTintResId);
        this.mBackgroundTintResId = checkResourceId;
        if (checkResourceId != 0) {
            setBackgroundTintList(SkinCompatResources.getColorStateList(getContext(), this.mBackgroundTintResId));
        }
    }

    private void applyRippleColorResource() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mRippleColorResId);
        this.mRippleColorResId = checkResourceId;
        if (checkResourceId != 0) {
            setRippleColor(SkinCompatResources.getColor(getContext(), this.mRippleColorResId));
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyBackgroundTintResource();
        applyRippleColorResource();
        SkinCompatImageHelper skinCompatImageHelper = this.mImageHelper;
        if (skinCompatImageHelper != null) {
            skinCompatImageHelper.applySkin();
        }
    }
}
