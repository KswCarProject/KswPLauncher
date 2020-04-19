package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.Preconditions;

public abstract class DrawableResource<T extends Drawable> implements Resource<T>, Initializable {
    protected final T drawable;

    public DrawableResource(T drawable2) {
        this.drawable = (Drawable) Preconditions.checkNotNull(drawable2);
    }

    @NonNull
    public final T get() {
        Drawable.ConstantState state = this.drawable.getConstantState();
        if (state == null) {
            return this.drawable;
        }
        return state.newDrawable();
    }

    public void initialize() {
        if (this.drawable instanceof BitmapDrawable) {
            ((BitmapDrawable) this.drawable).getBitmap().prepareToDraw();
        } else if (this.drawable instanceof GifDrawable) {
            ((GifDrawable) this.drawable).getFirstFrame().prepareToDraw();
        }
    }
}
