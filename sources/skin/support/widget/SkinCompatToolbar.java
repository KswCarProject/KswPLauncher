package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p004v7.widget.Toolbar;
import android.util.AttributeSet;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatToolbar extends Toolbar implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mNavigationIconResId;
    private int mSubtitleTextColorResId;
    private int mTitleTextColorResId;

    public SkinCompatToolbar(Context context) {
        this(context, null);
    }

    public SkinCompatToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, C1899R.attr.toolbarStyle);
    }

    public SkinCompatToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTitleTextColorResId = 0;
        this.mSubtitleTextColorResId = 0;
        this.mNavigationIconResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, C1899R.styleable.Toolbar, defStyleAttr, 0);
        this.mNavigationIconResId = a.getResourceId(C1899R.styleable.Toolbar_navigationIcon, 0);
        int titleAp = a.getResourceId(C1899R.styleable.Toolbar_titleTextAppearance, 0);
        int subtitleAp = a.getResourceId(C1899R.styleable.Toolbar_subtitleTextAppearance, 0);
        a.recycle();
        if (titleAp != 0) {
            TypedArray a2 = context.obtainStyledAttributes(titleAp, C1899R.styleable.SkinTextAppearance);
            this.mTitleTextColorResId = a2.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColor, 0);
            a2.recycle();
        }
        if (subtitleAp != 0) {
            TypedArray a3 = context.obtainStyledAttributes(subtitleAp, C1899R.styleable.SkinTextAppearance);
            this.mSubtitleTextColorResId = a3.getResourceId(C1899R.styleable.SkinTextAppearance_android_textColor, 0);
            a3.recycle();
        }
        TypedArray a4 = context.obtainStyledAttributes(attrs, C1899R.styleable.Toolbar, defStyleAttr, 0);
        if (a4.hasValue(C1899R.styleable.Toolbar_titleTextColor)) {
            this.mTitleTextColorResId = a4.getResourceId(C1899R.styleable.Toolbar_titleTextColor, 0);
        }
        if (a4.hasValue(C1899R.styleable.Toolbar_subtitleTextColor)) {
            this.mSubtitleTextColorResId = a4.getResourceId(C1899R.styleable.Toolbar_subtitleTextColor, 0);
        }
        a4.recycle();
        applyTitleTextColor();
        applySubtitleTextColor();
        applyNavigationIcon();
    }

    private void applyTitleTextColor() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mTitleTextColorResId);
        this.mTitleTextColorResId = checkResourceId;
        if (checkResourceId != 0) {
            setTitleTextColor(SkinCompatResources.getColor(getContext(), this.mTitleTextColorResId));
        }
    }

    private void applySubtitleTextColor() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mSubtitleTextColorResId);
        this.mSubtitleTextColorResId = checkResourceId;
        if (checkResourceId != 0) {
            setSubtitleTextColor(SkinCompatResources.getColor(getContext(), this.mSubtitleTextColorResId));
        }
    }

    private void applyNavigationIcon() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mNavigationIconResId);
        this.mNavigationIconResId = checkResourceId;
        if (checkResourceId != 0) {
            setNavigationIcon(SkinCompatResources.getDrawableCompat(getContext(), this.mNavigationIconResId));
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(resId);
        }
    }

    @Override // android.support.p004v7.widget.Toolbar
    public void setNavigationIcon(int resId) {
        super.setNavigationIcon(resId);
        this.mNavigationIconResId = resId;
        applyNavigationIcon();
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        applyTitleTextColor();
        applySubtitleTextColor();
        applyNavigationIcon();
    }
}
