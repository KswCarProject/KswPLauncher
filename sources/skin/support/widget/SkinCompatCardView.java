package skin.support.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import skin.support.cardview.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatCardView extends CardView implements SkinCompatSupportable {
    private static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    private int mBackgroundColorResId;
    private int mThemeColorBackgroundResId;

    public SkinCompatCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinCompatCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mThemeColorBackgroundResId = 0;
        this.mBackgroundColorResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardView, defStyleAttr, R.style.CardView);
        if (a.hasValue(R.styleable.CardView_cardBackgroundColor)) {
            this.mBackgroundColorResId = a.getResourceId(R.styleable.CardView_cardBackgroundColor, 0);
        } else {
            TypedArray aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            this.mThemeColorBackgroundResId = aa.getResourceId(0, 0);
            aa.recycle();
        }
        a.recycle();
        applyBackgroundColorResource();
    }

    private void applyBackgroundColorResource() {
        int i;
        this.mBackgroundColorResId = SkinCompatHelper.checkResourceId(this.mBackgroundColorResId);
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mThemeColorBackgroundResId);
        this.mThemeColorBackgroundResId = checkResourceId;
        if (this.mBackgroundColorResId != 0) {
            setCardBackgroundColor(SkinCompatResources.getColorStateList(getContext(), this.mBackgroundColorResId));
        } else if (checkResourceId != 0) {
            float[] hsv = new float[3];
            Color.colorToHSV(SkinCompatResources.getColor(getContext(), this.mThemeColorBackgroundResId), hsv);
            if (hsv[2] > 0.5f) {
                i = getResources().getColor(R.color.cardview_light_background);
            } else {
                i = getResources().getColor(R.color.cardview_dark_background);
            }
            setCardBackgroundColor(ColorStateList.valueOf(i));
        }
    }

    public void applySkin() {
        applyBackgroundColorResource();
    }
}
