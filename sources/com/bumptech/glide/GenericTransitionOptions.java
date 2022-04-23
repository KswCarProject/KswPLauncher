package com.bumptech.glide;

import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

public final class GenericTransitionOptions<TranscodeType> extends TransitionOptions<GenericTransitionOptions<TranscodeType>, TranscodeType> {
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> withNoTransition() {
        return (GenericTransitionOptions) new GenericTransitionOptions().dontTransition();
    }

    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(int viewAnimationId) {
        return (GenericTransitionOptions) new GenericTransitionOptions().transition(viewAnimationId);
    }

    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(ViewPropertyTransition.Animator animator) {
        return (GenericTransitionOptions) new GenericTransitionOptions().transition(animator);
    }

    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(TransitionFactory<? super TranscodeType> transitionFactory) {
        return (GenericTransitionOptions) new GenericTransitionOptions().transition(transitionFactory);
    }
}
