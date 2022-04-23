package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinMaterialTabLayout extends TabLayout implements SkinCompatSupportable {
    private int mTabIndicatorColorResId;
    private int mTabSelectedTextColorResId;
    private int mTabTextColorsResId;

    public SkinMaterialTabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX INFO: finally extract failed */
    public SkinMaterialTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTabIndicatorColorResId = 0;
        this.mTabTextColorsResId = 0;
        this.mTabSelectedTextColorResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout, defStyleAttr, 0);
        this.mTabIndicatorColorResId = a.getResourceId(R.styleable.TabLayout_tabIndicatorColor, 0);
        TypedArray ta = context.obtainStyledAttributes(a.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab), R.styleable.SkinTextAppearance);
        try {
            this.mTabTextColorsResId = ta.getResourceId(R.styleable.SkinTextAppearance_android_textColor, 0);
            ta.recycle();
            if (a.hasValue(R.styleable.TabLayout_tabTextColor)) {
                this.mTabTextColorsResId = a.getResourceId(R.styleable.TabLayout_tabTextColor, 0);
            }
            if (a.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
                this.mTabSelectedTextColorResId = a.getResourceId(R.styleable.TabLayout_tabSelectedTextColor, 0);
            }
            a.recycle();
            applySkin();
        } catch (Throwable th) {
            ta.recycle();
            throw th;
        }
    }

    public void applySkin() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mTabIndicatorColorResId);
        this.mTabIndicatorColorResId = checkResourceId;
        if (checkResourceId != 0) {
            setSelectedTabIndicatorColor(SkinCompatResources.getColor(getContext(), this.mTabIndicatorColorResId));
        }
        int checkResourceId2 = SkinCompatHelper.checkResourceId(this.mTabTextColorsResId);
        this.mTabTextColorsResId = checkResourceId2;
        if (checkResourceId2 != 0) {
            setTabTextColors(SkinCompatResources.getColorStateList(getContext(), this.mTabTextColorsResId));
        }
        int checkResourceId3 = SkinCompatHelper.checkResourceId(this.mTabSelectedTextColorResId);
        this.mTabSelectedTextColorResId = checkResourceId3;
        if (checkResourceId3 != 0) {
            int selected = SkinCompatResources.getColor(getContext(), this.mTabSelectedTextColorResId);
            if (getTabTextColors() != null) {
                setTabTextColors(getTabTextColors().getDefaultColor(), selected);
            }
        }
    }
}
