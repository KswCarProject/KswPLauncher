package com.wits.ksw.launcher.imagegallery;

import android.os.Build;
import android.view.View;

/* loaded from: classes11.dex */
public class BottomScalePageTransformer extends BasePageTransformer {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;

    @Override // android.support.p001v4.view.ViewPager.PageTransformer
    public void transformPage(View page, float position) {
        if (position < -1.0f) {
            position = -1.0f;
        } else if (position > 1.0f) {
            position = 1.0f;
        }
        float tempScale = position < 0.0f ? position + 1.0f : 1.0f - position;
        float scaleValue = (tempScale * 0.20000005f) + 1.0f;
        float pivotX = (page.getWidth() * page.getScaleX()) / 2.0f;
        float pivotY = page.getHeight();
        page.setPivotX(pivotX);
        page.setPivotY(pivotY);
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
        if (Build.VERSION.SDK_INT < 19) {
            page.getParent().requestLayout();
        }
    }
}
