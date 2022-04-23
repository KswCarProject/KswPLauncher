package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatBackgroundHelper extends SkinCompatHelper {
    private int mBackgroundResId = 0;
    private final View mView;

    public SkinCompatBackgroundHelper(View view) {
        this.mView = view;
    }

    /* JADX INFO: finally extract failed */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinBackgroundHelper, defStyleAttr, 0);
        try {
            if (a.hasValue(R.styleable.SkinBackgroundHelper_android_background)) {
                this.mBackgroundResId = a.getResourceId(R.styleable.SkinBackgroundHelper_android_background, 0);
            }
            a.recycle();
            applySkin();
        } catch (Throwable th) {
            a.recycle();
            throw th;
        }
    }

    public void onSetBackgroundResource(int resId) {
        this.mBackgroundResId = resId;
        applySkin();
    }

    public void applySkin() {
        Drawable drawable;
        int checkResourceId = checkResourceId(this.mBackgroundResId);
        this.mBackgroundResId = checkResourceId;
        if (checkResourceId != 0 && (drawable = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mBackgroundResId)) != null) {
            int paddingLeft = this.mView.getPaddingLeft();
            int paddingTop = this.mView.getPaddingTop();
            int paddingRight = this.mView.getPaddingRight();
            int paddingBottom = this.mView.getPaddingBottom();
            ViewCompat.setBackground(this.mView, drawable);
            this.mView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }
}
