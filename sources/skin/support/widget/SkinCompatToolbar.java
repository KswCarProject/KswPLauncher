package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatToolbar extends Toolbar implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mNavigationIconResId;
    private int mSubtitleTextColorResId;
    private int mTitleTextColorResId;

    public SkinCompatToolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.toolbarStyle);
    }

    public SkinCompatToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTitleTextColorResId = 0;
        this.mSubtitleTextColorResId = 0;
        this.mNavigationIconResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Toolbar, defStyleAttr, 0);
        this.mNavigationIconResId = a.getResourceId(R.styleable.Toolbar_navigationIcon, 0);
        int titleAp = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        int subtitleAp = a.getResourceId(R.styleable.Toolbar_subtitleTextAppearance, 0);
        a.recycle();
        if (titleAp != 0) {
            TypedArray a2 = context.obtainStyledAttributes(titleAp, R.styleable.SkinTextAppearance);
            this.mTitleTextColorResId = a2.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            a2.recycle();
        }
        if (subtitleAp != 0) {
            TypedArray a3 = context.obtainStyledAttributes(subtitleAp, R.styleable.SkinTextAppearance);
            this.mSubtitleTextColorResId = a3.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            a3.recycle();
        }
        TypedArray a4 = context.obtainStyledAttributes(attrs, R.styleable.Toolbar, defStyleAttr, 0);
        if (a4.hasValue(R.styleable.Toolbar_titleTextColor)) {
            this.mTitleTextColorResId = a4.getResourceId(R.styleable.Toolbar_titleTextColor, 0);
        }
        if (a4.hasValue(R.styleable.Toolbar_subtitleTextColor)) {
            this.mSubtitleTextColorResId = a4.getResourceId(R.styleable.Toolbar_subtitleTextColor, 0);
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

    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(resId);
        }
    }

    public void setNavigationIcon(int resId) {
        super.setNavigationIcon(resId);
        this.mNavigationIconResId = resId;
        applyNavigationIcon();
    }

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
