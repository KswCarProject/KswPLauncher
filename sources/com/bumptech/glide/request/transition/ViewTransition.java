package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import com.bumptech.glide.request.transition.Transition;

public class ViewTransition<R> implements Transition<R> {
    private final ViewTransitionAnimationFactory viewTransitionAnimationFactory;

    interface ViewTransitionAnimationFactory {
        Animation build(Context context);
    }

    ViewTransition(ViewTransitionAnimationFactory viewTransitionAnimationFactory2) {
        this.viewTransitionAnimationFactory = viewTransitionAnimationFactory2;
    }

    public boolean transition(R r, Transition.ViewAdapter adapter) {
        View view = adapter.getView();
        if (view == null) {
            return false;
        }
        view.clearAnimation();
        view.startAnimation(this.viewTransitionAnimationFactory.build(view.getContext()));
        return false;
    }
}
