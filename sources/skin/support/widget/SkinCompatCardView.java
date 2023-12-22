package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.p004v7.widget.CardView;
import android.util.AttributeSet;
import skin.support.cardview.C1903R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatCardView extends CardView implements SkinCompatSupportable {
    private static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    private int mBackgroundColorResId;
    private int mThemeColorBackgroundResId;

    public SkinCompatCardView(Context context) {
        this(context, null);
    }

    public SkinCompatCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mThemeColorBackgroundResId = 0;
        this.mBackgroundColorResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, C1903R.styleable.CardView, defStyleAttr, C1903R.style.CardView);
        if (a.hasValue(C1903R.styleable.CardView_cardBackgroundColor)) {
            this.mBackgroundColorResId = a.getResourceId(C1903R.styleable.CardView_cardBackgroundColor, 0);
        } else {
            TypedArray aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            this.mThemeColorBackgroundResId = aa.getResourceId(0, 0);
            aa.recycle();
        }
        a.recycle();
        applyBackgroundColorResource();
    }

    private void applyBackgroundColorResource() {
        int color;
        this.mBackgroundColorResId = SkinCompatHelper.checkResourceId(this.mBackgroundColorResId);
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mThemeColorBackgroundResId);
        this.mThemeColorBackgroundResId = checkResourceId;
        if (this.mBackgroundColorResId != 0) {
            ColorStateList backgroundColor = SkinCompatResources.getColorStateList(getContext(), this.mBackgroundColorResId);
            setCardBackgroundColor(backgroundColor);
        } else if (checkResourceId != 0) {
            int themeColorBackground = SkinCompatResources.getColor(getContext(), this.mThemeColorBackgroundResId);
            float[] hsv = new float[3];
            Color.colorToHSV(themeColorBackground, hsv);
            if (hsv[2] > 0.5f) {
                color = getResources().getColor(C1903R.color.cardview_light_background);
            } else {
                color = getResources().getColor(C1903R.color.cardview_dark_background);
            }
            ColorStateList backgroundColor2 = ColorStateList.valueOf(color);
            setCardBackgroundColor(backgroundColor2);
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyBackgroundColorResource();
    }
}
