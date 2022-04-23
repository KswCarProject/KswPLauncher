package skin.support.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import skin.support.R;

public class SkinCompatSeekBar extends AppCompatSeekBar implements SkinCompatSupportable {
    private SkinCompatSeekBarHelper mSkinCompatSeekBarHelper;

    public SkinCompatSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.seekBarStyle);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SkinCompatSeekBarHelper skinCompatSeekBarHelper = new SkinCompatSeekBarHelper(this);
        this.mSkinCompatSeekBarHelper = skinCompatSeekBarHelper;
        skinCompatSeekBarHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    public void applySkin() {
        SkinCompatSeekBarHelper skinCompatSeekBarHelper = this.mSkinCompatSeekBarHelper;
        if (skinCompatSeekBarHelper != null) {
            skinCompatSeekBarHelper.applySkin();
        }
    }
}
