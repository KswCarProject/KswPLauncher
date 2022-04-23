package skin.support.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.AttributeSet;
import skin.support.R;

public class SkinCompatRatingBar extends AppCompatRatingBar implements SkinCompatSupportable {
    private SkinCompatProgressBarHelper mSkinCompatProgressBarHelper;

    public SkinCompatRatingBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.ratingBarStyle);
    }

    public SkinCompatRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SkinCompatProgressBarHelper skinCompatProgressBarHelper = new SkinCompatProgressBarHelper(this);
        this.mSkinCompatProgressBarHelper = skinCompatProgressBarHelper;
        skinCompatProgressBarHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    public void applySkin() {
        SkinCompatProgressBarHelper skinCompatProgressBarHelper = this.mSkinCompatProgressBarHelper;
        if (skinCompatProgressBarHelper != null) {
            skinCompatProgressBarHelper.applySkin();
        }
    }
}
