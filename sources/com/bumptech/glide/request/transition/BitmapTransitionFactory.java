package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public class BitmapTransitionFactory extends BitmapContainerTransitionFactory<Bitmap> {
    public BitmapTransitionFactory(@NonNull TransitionFactory<Drawable> realFactory) {
        super(realFactory);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Bitmap getBitmap(@NonNull Bitmap current) {
        return current;
    }
}
