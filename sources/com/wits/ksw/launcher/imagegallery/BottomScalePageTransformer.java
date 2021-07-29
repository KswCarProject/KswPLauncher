package com.wits.ksw.launcher.imagegallery;

import android.os.Build;
import android.view.View;

public class BottomScalePageTransformer extends BasePageTransformer {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;

    public void transformPage(View page, float position) {
        if (position < -1.0f) {
            position = -1.0f;
        } else if (position > 1.0f) {
            position = 1.0f;
        }
        float scaleValue = ((position < 0.0f ? position + 1.0f : 1.0f - position) * 0.20000005f) + 1.0f;
        page.setPivotX((((float) page.getWidth()) * page.getScaleX()) / 2.0f);
        page.setPivotY((float) page.getHeight());
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
        if (Build.VERSION.SDK_INT < 19) {
            page.getParent().requestLayout();
        }
    }
}
