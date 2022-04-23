package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.util.TypedValue;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinMaterialBottomNavigationView extends BottomNavigationView implements SkinCompatSupportable {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private int mDefaultTintResId;
    private int mIconTintResId;
    private int mTextColorResId;

    public SkinMaterialBottomNavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinMaterialBottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTextColorResId = 0;
        this.mIconTintResId = 0;
        this.mDefaultTintResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationView, defStyleAttr, R.style.Widget_Design_BottomNavigationView);
        if (a.hasValue(R.styleable.BottomNavigationView_itemIconTint)) {
            this.mIconTintResId = a.getResourceId(R.styleable.BottomNavigationView_itemIconTint, 0);
        } else {
            this.mDefaultTintResId = resolveColorPrimary();
        }
        if (a.hasValue(R.styleable.BottomNavigationView_itemTextColor)) {
            this.mTextColorResId = a.getResourceId(R.styleable.BottomNavigationView_itemTextColor, 0);
        } else {
            this.mDefaultTintResId = resolveColorPrimary();
        }
        a.recycle();
        applyItemIconTintResource();
        applyItemTextColorResource();
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
            setItemTextColor(createDefaultColorStateList(16842808));
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

    private int resolveColorPrimary() {
        TypedValue value = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, value, true)) {
            return 0;
        }
        return value.resourceId;
    }

    public void applySkin() {
        applyItemIconTintResource();
        applyItemTextColorResource();
    }
}
