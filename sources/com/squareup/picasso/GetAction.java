package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;

class GetAction extends Action<Void> {
    GetAction(Picasso picasso, Request data, int memoryPolicy, int networkPolicy, Object tag, String key) {
        super(picasso, null, data, memoryPolicy, networkPolicy, 0, (Drawable) null, key, tag, false);
    }

    /* access modifiers changed from: package-private */
    public void complete(Bitmap result, Picasso.LoadedFrom from) {
    }

    public void error() {
    }
}
