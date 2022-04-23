package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class BitmapTransitionFactory extends BitmapContainerTransitionFactory<Bitmap> {
    public BitmapTransitionFactory(TransitionFactory<Drawable> realFactory) {
        super(realFactory);
    }

    /* access modifiers changed from: protected */
    public Bitmap getBitmap(Bitmap current) {
        return current;
    }
}
