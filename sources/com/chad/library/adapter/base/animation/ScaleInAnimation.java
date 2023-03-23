package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleInAnimation implements BaseAnimation {
    private static final float DEFAULT_SCALE_FROM = 0.5f;
    private final float mFrom;

    public ScaleInAnimation() {
        this(DEFAULT_SCALE_FROM);
    }

    public ScaleInAnimation(float from) {
        this.mFrom = from;
    }

    public Animator[] getAnimators(View view) {
        return new ObjectAnimator[]{ObjectAnimator.ofFloat(view, "scaleX", new float[]{this.mFrom, 1.0f}), ObjectAnimator.ofFloat(view, "scaleY", new float[]{this.mFrom, 1.0f})};
    }
}
