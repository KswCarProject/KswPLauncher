package skin.support.widget;

import android.content.res.TypedArray;
import android.support.p001v4.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatCompoundButtonHelper extends SkinCompatHelper {
    private int mButtonResourceId = 0;
    private int mButtonTintResId = 0;
    private final CompoundButton mView;

    public SkinCompatCompoundButtonHelper(CompoundButton view) {
        this.mView = view;
    }

    void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, C1899R.styleable.CompoundButton, defStyleAttr, 0);
        try {
            if (a.hasValue(C1899R.styleable.CompoundButton_android_button)) {
                this.mButtonResourceId = a.getResourceId(C1899R.styleable.CompoundButton_android_button, 0);
            }
            if (a.hasValue(C1899R.styleable.CompoundButton_buttonTint)) {
                this.mButtonTintResId = a.getResourceId(C1899R.styleable.CompoundButton_buttonTint, 0);
            }
            a.recycle();
            applySkin();
        } catch (Throwable th) {
            a.recycle();
            throw th;
        }
    }

    public void setButtonDrawable(int resId) {
        this.mButtonResourceId = resId;
        applySkin();
    }

    @Override // skin.support.widget.SkinCompatHelper
    public void applySkin() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mButtonResourceId);
        this.mButtonResourceId = checkResourceId;
        if (checkResourceId != 0) {
            CompoundButton compoundButton = this.mView;
            compoundButton.setButtonDrawable(SkinCompatResources.getDrawableCompat(compoundButton.getContext(), this.mButtonResourceId));
        }
        int checkResourceId2 = SkinCompatHelper.checkResourceId(this.mButtonTintResId);
        this.mButtonTintResId = checkResourceId2;
        if (checkResourceId2 != 0) {
            CompoundButton compoundButton2 = this.mView;
            CompoundButtonCompat.setButtonTintList(compoundButton2, SkinCompatResources.getColorStateList(compoundButton2.getContext(), this.mButtonTintResId));
        }
    }
}
