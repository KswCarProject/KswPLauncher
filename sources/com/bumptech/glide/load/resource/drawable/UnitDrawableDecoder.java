package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

public class UnitDrawableDecoder implements ResourceDecoder<Drawable, Drawable> {
    public boolean handles(Drawable source, Options options) {
        return true;
    }

    public Resource<Drawable> decode(Drawable source, int width, int height, Options options) {
        return NonOwnedDrawableResource.newInstance(source);
    }
}
