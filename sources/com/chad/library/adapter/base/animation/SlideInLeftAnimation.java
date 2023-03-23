package com.chad.library.adapter.base.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideInLeftAnimation implements BaseAnimation {
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "translationX", new float[]{(float) (-view.getRootView().getWidth()), 0.0f})};
    }
}
