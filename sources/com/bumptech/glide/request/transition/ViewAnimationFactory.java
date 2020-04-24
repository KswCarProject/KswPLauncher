package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewTransition;

public class ViewAnimationFactory<R> implements TransitionFactory<R> {
    private Transition<R> transition;
    private final ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory;

    public ViewAnimationFactory(Animation animation) {
        this((ViewTransition.ViewTransitionAnimationFactory) new ConcreteViewTransitionAnimationFactory(animation));
    }

    public ViewAnimationFactory(int animationId) {
        this((ViewTransition.ViewTransitionAnimationFactory) new ResourceViewTransitionAnimationFactory(animationId));
    }

    ViewAnimationFactory(ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory2) {
        this.viewTransitionAnimationFactory = viewTransitionAnimationFactory2;
    }

    public Transition<R> build(DataSource dataSource, boolean isFirstResource) {
        if (dataSource == DataSource.MEMORY_CACHE || !isFirstResource) {
            return NoTransition.get();
        }
        if (this.transition == null) {
            this.transition = new ViewTransition(this.viewTransitionAnimationFactory);
        }
        return this.transition;
    }

    private static class ConcreteViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        private final Animation animation;

        ConcreteViewTransitionAnimationFactory(Animation animation2) {
            this.animation = animation2;
        }

        public Animation build(Context context) {
            return this.animation;
        }
    }

    private static class ResourceViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {
        private final int animationId;

        ResourceViewTransitionAnimationFactory(int animationId2) {
            this.animationId = animationId2;
        }

        public Animation build(Context context) {
            return AnimationUtils.loadAnimation(context, this.animationId);
        }
    }
}
