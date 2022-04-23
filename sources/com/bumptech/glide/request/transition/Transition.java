package com.bumptech.glide.request.transition;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface Transition<R> {

    public interface ViewAdapter {
        Drawable getCurrentDrawable();

        View getView();

        void setDrawable(Drawable drawable);
    }

    boolean transition(R r, ViewAdapter viewAdapter);
}
