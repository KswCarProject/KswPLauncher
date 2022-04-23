package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class SkinCompatProgressBar extends ProgressBar implements SkinCompatSupportable {
    private SkinCompatProgressBarHelper mSkinCompatProgressBarHelper;

    public SkinCompatProgressBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 16842871);
    }

    public SkinCompatProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
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
