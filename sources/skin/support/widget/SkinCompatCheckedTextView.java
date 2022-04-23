package skin.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatCheckedTextView extends AppCompatCheckedTextView implements SkinCompatSupportable {
    private static final int[] TINT_ATTRS = {16843016};
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mCheckMarkResId;
    private SkinCompatTextHelper mTextHelper;

    public SkinCompatCheckedTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatCheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.checkedTextViewStyle);
    }

    public SkinCompatCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCheckMarkResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
        SkinCompatTextHelper create = SkinCompatTextHelper.create(this);
        this.mTextHelper = create;
        create.loadFromAttributes(attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, TINT_ATTRS, defStyleAttr, 0);
        this.mCheckMarkResId = a.getResourceId(0, 0);
        a.recycle();
        applyCheckMark();
    }

    public void setCheckMarkDrawable(int resId) {
        this.mCheckMarkResId = resId;
        applyCheckMark();
    }

    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(resId);
        }
    }

    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetTextAppearance(context, resId);
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }
    }

    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.applySkin();
        }
        applyCheckMark();
    }

    private void applyCheckMark() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mCheckMarkResId);
        this.mCheckMarkResId = checkResourceId;
        if (checkResourceId != 0) {
            setCheckMarkDrawable(SkinCompatResources.getDrawableCompat(getContext(), this.mCheckMarkResId));
        }
    }
}
