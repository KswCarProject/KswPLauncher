package skin.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatEditText;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinMaterialTextInputLayout extends TextInputLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mCounterTextColorResId;
    private int mDefaultTextColorResId;
    private int mErrorTextColorResId;
    private int mFocusedTextColorResId;
    private int mPasswordToggleResId;

    public SkinMaterialTextInputLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinMaterialTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [skin.support.design.widget.SkinMaterialTextInputLayout, android.view.View] */
    public SkinMaterialTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPasswordToggleResId = 0;
        this.mCounterTextColorResId = 0;
        this.mErrorTextColorResId = 0;
        this.mFocusedTextColorResId = 0;
        this.mDefaultTextColorResId = 0;
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout, defStyleAttr, R.style.Widget_Design_TextInputLayout);
        if (a.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            int resourceId = a.getResourceId(R.styleable.TextInputLayout_android_textColorHint, 0);
            this.mFocusedTextColorResId = resourceId;
            this.mDefaultTextColorResId = resourceId;
            applyFocusedTextColorResource();
        }
        loadErrorTextColorResFromAttributes(a.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0));
        loadCounterTextColorResFromAttributes(a.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0));
        this.mPasswordToggleResId = a.getResourceId(R.styleable.TextInputLayout_passwordToggleDrawable, 0);
        a.recycle();
    }

    private void loadCounterTextColorResFromAttributes(int resId) {
        if (resId != 0) {
            TypedArray counterTA = getContext().obtainStyledAttributes(resId, skin.support.R.styleable.SkinTextAppearance);
            if (counterTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                this.mCounterTextColorResId = counterTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            counterTA.recycle();
        }
        applyCounterTextColorResource();
    }

    public void setCounterEnabled(boolean enabled) {
        SkinMaterialTextInputLayout.super.setCounterEnabled(enabled);
        if (enabled) {
            applyCounterTextColorResource();
        }
    }

    private void applyCounterTextColorResource() {
        TextView counterView;
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mCounterTextColorResId);
        this.mCounterTextColorResId = checkResourceId;
        if (checkResourceId != 0 && (counterView = getCounterView()) != null) {
            counterView.setTextColor(SkinCompatResources.getColor(getContext(), this.mCounterTextColorResId));
            updateEditTextBackground();
        }
    }

    private TextView getCounterView() {
        try {
            Field counterView = TextInputLayout.class.getDeclaredField("mCounterView");
            counterView.setAccessible(true);
            return (TextView) counterView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setErrorTextAppearance(int resId) {
        SkinMaterialTextInputLayout.super.setErrorTextAppearance(resId);
        loadErrorTextColorResFromAttributes(resId);
    }

    private void loadErrorTextColorResFromAttributes(int resId) {
        if (resId != 0) {
            TypedArray errorTA = getContext().obtainStyledAttributes(resId, skin.support.R.styleable.SkinTextAppearance);
            if (errorTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                this.mErrorTextColorResId = errorTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, 0);
            }
            errorTA.recycle();
        }
        applyErrorTextColorResource();
    }

    public void setErrorEnabled(boolean enabled) {
        SkinMaterialTextInputLayout.super.setErrorEnabled(enabled);
        if (enabled) {
            applyErrorTextColorResource();
        }
    }

    private void applyErrorTextColorResource() {
        TextView errorView;
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mErrorTextColorResId);
        this.mErrorTextColorResId = checkResourceId;
        if (checkResourceId != 0 && checkResourceId != R.color.design_error && (errorView = getErrorView()) != null) {
            errorView.setTextColor(SkinCompatResources.getColor(getContext(), this.mErrorTextColorResId));
            updateEditTextBackground();
        }
    }

    private TextView getErrorView() {
        try {
            Field errorView = TextInputLayout.class.getDeclaredField("mErrorView");
            errorView.setAccessible(true);
            return (TextView) errorView.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateEditTextBackground() {
        try {
            Method updateEditTextBackground = TextInputLayout.class.getDeclaredMethod("updateEditTextBackground", new Class[0]);
            updateEditTextBackground.setAccessible(true);
            updateEditTextBackground.invoke(this, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefaultTextColor(ColorStateList colors) {
        try {
            Field defaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
            defaultTextColor.setAccessible(true);
            defaultTextColor.set(this, colors);
            updateLabelState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyFocusedTextColorResource() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mFocusedTextColorResId);
        this.mFocusedTextColorResId = checkResourceId;
        if (checkResourceId != 0 && checkResourceId != R.color.abc_hint_foreground_material_light) {
            setFocusedTextColor(SkinCompatResources.getColorStateList(getContext(), this.mFocusedTextColorResId));
        } else if (getEditText() != null) {
            int textColorResId = 0;
            if (getEditText() instanceof SkinCompatEditText) {
                textColorResId = ((SkinCompatEditText) getEditText()).getTextColorResId();
            } else if (getEditText() instanceof SkinMaterialTextInputEditText) {
                textColorResId = ((SkinMaterialTextInputEditText) getEditText()).getTextColorResId();
            }
            int textColorResId2 = SkinCompatHelper.checkResourceId(textColorResId);
            if (textColorResId2 != 0) {
                setFocusedTextColor(SkinCompatResources.getColorStateList(getContext(), textColorResId2));
            }
        }
    }

    private void setFocusedTextColor(ColorStateList colors) {
        try {
            Field focusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
            focusedTextColor.setAccessible(true);
            focusedTextColor.set(this, colors);
            updateLabelState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateLabelState() {
        Class<TextInputLayout> cls = TextInputLayout.class;
        try {
            Method updateLabelState = cls.getDeclaredMethod("updateLabelState", new Class[]{Boolean.TYPE});
            updateLabelState.setAccessible(true);
            updateLabelState.invoke(this, new Object[]{false});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void applySkin() {
        applyErrorTextColorResource();
        applyCounterTextColorResource();
        applyFocusedTextColorResource();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
    }
}
