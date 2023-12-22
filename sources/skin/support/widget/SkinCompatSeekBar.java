package skin.support.widget;

import android.content.Context;
import android.support.p004v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import skin.support.C1899R;

/* loaded from: classes.dex */
public class SkinCompatSeekBar extends AppCompatSeekBar implements SkinCompatSupportable {
    private SkinCompatSeekBarHelper mSkinCompatSeekBarHelper;

    public SkinCompatSeekBar(Context context) {
        this(context, null);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, C1899R.attr.seekBarStyle);
    }

    public SkinCompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SkinCompatSeekBarHelper skinCompatSeekBarHelper = new SkinCompatSeekBarHelper(this);
        this.mSkinCompatSeekBarHelper = skinCompatSeekBarHelper;
        skinCompatSeekBarHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatSeekBarHelper skinCompatSeekBarHelper = this.mSkinCompatSeekBarHelper;
        if (skinCompatSeekBarHelper != null) {
            skinCompatSeekBarHelper.applySkin();
        }
    }
}
