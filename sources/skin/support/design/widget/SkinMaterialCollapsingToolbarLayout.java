package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.C1911R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes.dex */
public class SkinMaterialCollapsingToolbarLayout extends CollapsingToolbarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mContentScrimResId;
    private int mStatusBarScrimResId;

    public SkinMaterialCollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SkinMaterialCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContentScrimResId = 0;
        this.mStatusBarScrimResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, C1911R.styleable.CollapsingToolbarLayout, defStyleAttr, C1911R.style.Widget_Design_CollapsingToolbar);
        this.mContentScrimResId = a.getResourceId(C1911R.styleable.CollapsingToolbarLayout_contentScrim, 0);
        this.mStatusBarScrimResId = a.getResourceId(C1911R.styleable.CollapsingToolbarLayout_statusBarScrim, 0);
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

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyContentScrimResource();
        applyStatusBarScrimResource();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
    }
}
