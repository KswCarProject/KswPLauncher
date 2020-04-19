package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

public final class DrawableTransitionOptions extends TransitionOptions<DrawableTransitionOptions, Drawable> {
    @NonNull
    public static DrawableTransitionOptions withCrossFade() {
        return new DrawableTransitionOptions().crossFade();
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade(int duration) {
        return new DrawableTransitionOptions().crossFade(duration);
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new DrawableTransitionOptions().crossFade(drawableCrossFadeFactory);
    }

    @NonNull
    public static DrawableTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return new DrawableTransitionOptions().crossFade(builder);
    }

    @NonNull
    public static DrawableTransitionOptions with(@NonNull TransitionFactory<Drawable> transitionFactory) {
        return (DrawableTransitionOptions) new DrawableTransitionOptions().transition(transitionFactory);
    }

    @NonNull
    public DrawableTransitionOptions crossFade() {
        return crossFade(new DrawableCrossFadeFactory.Builder());
    }

    @NonNull
    public DrawableTransitionOptions crossFade(int duration) {
        return crossFade(new DrawableCrossFadeFactory.Builder(duration));
    }

    @NonNull
    public DrawableTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return (DrawableTransitionOptions) transition(drawableCrossFadeFactory);
    }

    @NonNull
    public DrawableTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return crossFade(builder.build());
    }
}
