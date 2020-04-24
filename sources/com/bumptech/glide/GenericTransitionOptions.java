package com.bumptech.glide;

import android.support.annotation.NonNull;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

public final class GenericTransitionOptions<TranscodeType> extends TransitionOptions<GenericTransitionOptions<TranscodeType>, TranscodeType> {
    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> withNoTransition() {
        return (GenericTransitionOptions) new GenericTransitionOptions().dontTransition();
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(int viewAnimationId) {
        return (GenericTransitionOptions) new GenericTransitionOptions().transition(viewAnimationId);
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(@NonNull ViewPropertyTransition.Animator animator) {
        return (GenericTransitionOptions) new GenericTransitionOptions().transition(animator);
    }

    @NonNull
    public static <TranscodeType> GenericTransitionOptions<TranscodeType> with(@NonNull TransitionFactory<? super TranscodeType> transitionFactory) {
        return (GenericTransitionOptions) new GenericTransitionOptions().transition(transitionFactory);
    }
}
