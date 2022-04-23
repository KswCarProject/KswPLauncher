package skin.support.widget;

import android.content.res.TypedArray;
import android.support.v4.widget.CompoundButtonCompat;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatCompoundButtonHelper extends SkinCompatHelper {
    private int mButtonResourceId = 0;
    private int mButtonTintResId = 0;
    private final CompoundButton mView;

    public SkinCompatCompoundButtonHelper(CompoundButton view) {
        this.mView = view;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, R.styleable.CompoundButton, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.CompoundButton_android_button)) {
                this.mButtonResourceId = a.getResourceId(R.styleable.CompoundButton_android_button, 0);
            }
            if (a.hasValue(R.styleable.CompoundButton_buttonTint)) {
                this.mButtonTintResId = a.getResourceId(R.styleable.CompoundButton_buttonTint, 0);
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
