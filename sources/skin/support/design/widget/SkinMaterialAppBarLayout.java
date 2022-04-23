package skin.support.design.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;

public class SkinMaterialAppBarLayout extends AppBarLayout implements SkinCompatSupportable {
    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinMaterialAppBarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [skin.support.design.widget.SkinMaterialAppBarLayout, android.view.View] */
    public SkinMaterialAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, 0);
    }

    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
    }
}
