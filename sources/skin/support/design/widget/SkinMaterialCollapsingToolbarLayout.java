package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinMaterialCollapsingToolbarLayout extends CollapsingToolbarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mContentScrimResId;
    private int mStatusBarScrimResId;

    public SkinMaterialCollapsingToolbarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.view.View, skin.support.design.widget.SkinMaterialCollapsingToolbarLayout] */
    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContentScrimResId = 0;
        this.mStatusBarScrimResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CollapsingToolbarLayout, defStyleAttr, R.style.Widget_Design_CollapsingToolbar);
        this.mContentScrimResId = a.getResourceId(R.styleable.CollapsingToolbarLayout_contentScrim, 0);
        this.mStatusBarScrimResId = a.getResourceId(R.styleable.CollapsingToolbarLayout_statusBarScrim, 0);
        a.recycle();
        applyContentScrimResource();
        applyStatusBarScrimResource();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, 0);
    }

    private void applyStatusBarScrimResource() {
        Drawable drawable;
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mStatusBarScrimResId);
        this.mStatusBarScrimResId = checkResourceId;
        if (checkResourceId != 0 && (drawable = SkinCompatResources.getDrawableCompat(getContext(), this.mStatusBarScrimResId)) != null) {
            setStatusBarScrim(drawable);
        }
    }

    private void applyContentScrimResource() {
        Drawable drawable;
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mContentScrimResId);
        this.mContentScrimResId = checkResourceId;
        if (checkResourceId != 0 && (drawable = SkinCompatResources.getDrawableCompat(getContext(), this.mContentScrimResId)) != null) {
            setContentScrim(drawable);
        }
    }

    public void applySkin() {
        applyContentScrimResource();
        applyStatusBarScrimResource();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
    }
}
