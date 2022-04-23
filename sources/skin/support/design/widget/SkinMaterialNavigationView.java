package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.util.TypedValue;
import skin.support.content.res.SkinCompatResources;
import skin.support.content.res.SkinCompatThemeUtils;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinMaterialNavigationView extends NavigationView implements SkinCompatSupportable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mDefaultTintResId;
    private int mIconTintResId;
    private int mItemBackgroundResId;
    private int mTextColorResId;

    public SkinMaterialNavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinMaterialNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [skin.support.design.widget.SkinMaterialNavigationView, android.view.View] */
    public SkinMaterialNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int textAppearance;
        this.mItemBackgroundResId = 0;
        this.mTextColorResId = 0;
        this.mDefaultTintResId = 0;
        this.mIconTintResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, 0);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavigationView, defStyleAttr, R.style.Widget_Design_NavigationView);
        if (a.hasValue(R.styleable.NavigationView_itemIconTint)) {
            this.mIconTintResId = a.getResourceId(R.styleable.NavigationView_itemIconTint, 0);
        } else {
            this.mDefaultTintResId = SkinCompatThemeUtils.getColorPrimaryResId(context);
        }
        if (a.hasValue(R.styleable.NavigationView_itemTextAppearance) && (textAppearance = a.getResourceId(R.styleable.NavigationView_itemTextAppearance, 0)) != 0) {
            TypedArray ap = context.obtainStyledAttributes(textAppearance, R.styleable.SkinTextAppearance);
            if (ap.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                this.mTextColorResId = ap.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            ap.recycle();
        }
        if (a.hasValue(R.styleable.NavigationView_itemTextColor)) {
            this.mTextColorResId = a.getResourceId(R.styleable.NavigationView_itemTextColor, 0);
        } else {
            this.mDefaultTintResId = SkinCompatThemeUtils.getColorPrimaryResId(context);
        }
        if (this.mTextColorResId == 0) {
            this.mTextColorResId = SkinCompatThemeUtils.getTextColorPrimaryResId(context);
        }
        this.mItemBackgroundResId = a.getResourceId(R.styleable.NavigationView_itemBackground, 0);
        a.recycle();
        applyItemIconTintResource();
        applyItemTextColorResource();
        applyItemBackgroundResource();
    }

    public void setItemBackgroundResource(int resId) {
        SkinMaterialNavigationView.super.setItemBackgroundResource(resId);
        this.mItemBackgroundResId = resId;
        applyItemBackgroundResource();
    }

    private void applyItemBackgroundResource() {
        Drawable drawable;
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mItemBackgroundResId);
        this.mItemBackgroundResId = checkResourceId;
        if (checkResourceId != 0 && (drawable = SkinCompatResources.getDrawableCompat(getContext(), this.mItemBackgroundResId)) != null) {
            setItemBackground(drawable);
        }
    }

    public void setItemTextAppearance(int resId) {
        SkinMaterialNavigationView.super.setItemTextAppearance(resId);
        if (resId != 0) {
            TypedArray a = getContext().obtainStyledAttributes(resId, R.styleable.SkinTextAppearance);
            if (a.hasValue(R.styleable.SkinTextAppearance_android_textColor)) {
                this.mTextColorResId = a.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            a.recycle();
            applyItemTextColorResource();
        }
    }

    private void applyItemTextColorResource() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mTextColorResId);
        this.mTextColorResId = checkResourceId;
        if (checkResourceId != 0) {
            setItemTextColor(SkinCompatResources.getColorStateList(getContext(), this.mTextColorResId));
            return;
        }
        int checkResourceId2 = SkinCompatHelper.checkResourceId(this.mDefaultTintResId);
        this.mDefaultTintResId = checkResourceId2;
        if (checkResourceId2 != 0) {
            setItemTextColor(createDefaultColorStateList(16842806));
        }
    }

    private void applyItemIconTintResource() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mIconTintResId);
        this.mIconTintResId = checkResourceId;
        if (checkResourceId != 0) {
            setItemIconTintList(SkinCompatResources.getColorStateList(getContext(), this.mIconTintResId));
            return;
        }
        int checkResourceId2 = SkinCompatHelper.checkResourceId(this.mDefaultTintResId);
        this.mDefaultTintResId = checkResourceId2;
        if (checkResourceId2 != 0) {
            setItemIconTintList(createDefaultColorStateList(16842808));
        }
    }

    private ColorStateList createDefaultColorStateList(int baseColorThemeAttr) {
        TypedValue value = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(baseColorThemeAttr, value, true)) {
            return null;
        }
        ColorStateList baseColor = SkinCompatResources.getColorStateList(getContext(), value.resourceId);
        int colorPrimary = SkinCompatResources.getColor(getContext(), this.mDefaultTintResId);
        int defaultColor = baseColor.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{baseColor.getColorForState(iArr, defaultColor), colorPrimary, defaultColor});
    }

    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        applyItemIconTintResource();
        applyItemTextColorResource();
        applyItemBackgroundResource();
    }
}
