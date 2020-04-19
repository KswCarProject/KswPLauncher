package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.BitmapTransitionFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

public final class BitmapTransitionOptions extends TransitionOptions<BitmapTransitionOptions, Bitmap> {
    @NonNull
    public static BitmapTransitionOptions withCrossFade() {
        return new BitmapTransitionOptions().crossFade();
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade(int duration) {
        return new BitmapTransitionOptions().crossFade(duration);
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new BitmapTransitionOptions().crossFade(drawableCrossFadeFactory);
    }

    @NonNull
    public static BitmapTransitionOptions withCrossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return new BitmapTransitionOptions().crossFade(builder);
    }

    @NonNull
    public static BitmapTransitionOptions withWrapped(@NonNull TransitionFactory<Drawable> drawableCrossFadeFactory) {
        return new BitmapTransitionOptions().transitionUsing(drawableCrossFadeFactory);
    }

    @NonNull
    public static BitmapTransitionOptions with(@NonNull TransitionFactory<Bitmap> transitionFactory) {
        return (BitmapTransitionOptions) new BitmapTransitionOptions().transition(transitionFactory);
    }

    @NonNull
    public BitmapTransitionOptions crossFade() {
        return crossFade(new DrawableCrossFadeFactory.Builder());
    }

    @NonNull
    public BitmapTransitionOptions crossFade(int duration) {
        return crossFade(new DrawableCrossFadeFactory.Builder(duration));
    }

    @NonNull
    public BitmapTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return transitionUsing(drawableCrossFadeFactory);
    }

    @NonNull
    public BitmapTransitionOptions transitionUsing(@NonNull TransitionFactory<Drawable> drawableCrossFadeFactory) {
        return (BitmapTransitionOptions) transition(new BitmapTransitionFactory(drawableCrossFadeFactory));
    }

    @NonNull
    public BitmapTransitionOptions crossFade(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return transitionUsing(builder.build());
    }
}
