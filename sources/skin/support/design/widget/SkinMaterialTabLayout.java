package skin.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.C1911R;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes.dex */
public class SkinMaterialTabLayout extends TabLayout implements SkinCompatSupportable {
    private int mTabIndicatorColorResId;
    private int mTabSelectedTextColorResId;
    private int mTabTextColorsResId;

    public SkinMaterialTabLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTabIndicatorColorResId = 0;
        this.mTabTextColorsResId = 0;
        this.mTabSelectedTextColorResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, C1911R.styleable.TabLayout, defStyleAttr, 0);
        this.mTabIndicatorColorResId = a.getResourceId(C1911R.styleable.TabLayout_tabIndicatorColor, 0);
        int tabTextAppearance = a.getResourceId(C1911R.styleable.TabLayout_tabTextAppearance, C1911R.style.TextAppearance_Design_Tab);
        TypedArray ta = context.obtainStyledAttributes(tabTextAppearance, C1911R.styleable.SkinTextAppearance);
        try {
            this.mTabTextColorsResId = ta.getResourceId(C1911R.styleable.SkinTextAppearance_android_textColor, 0);
            ta.recycle();
            if (a.hasValue(C1911R.styleable.TabLayout_tabTextColor)) {
                this.mTabTextColorsResId = a.getResourceId(C1911R.styleable.TabLayout_tabTextColor, 0);
            }
            if (a.hasValue(C1911R.styleable.TabLayout_tabSelectedTextColor)) {
                this.mTabSelectedTextColorResId = a.getResourceId(C1911R.styleable.TabLayout_tabSelectedTextColor, 0);
            }
            a.recycle();
            applySkin();
        } catch (Throwable th) {
            ta.recycle();
            throw th;
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
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
