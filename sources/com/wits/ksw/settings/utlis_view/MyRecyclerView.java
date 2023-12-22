package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.util.AttributeSet;

/* loaded from: classes10.dex */
public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.support.p004v7.widget.RecyclerView, android.view.View, android.support.p001v4.view.ScrollingView
    public int computeVerticalScrollExtent() {
        return 5;
    }

    @Override // android.support.p004v7.widget.RecyclerView, android.view.View, android.support.p001v4.view.ScrollingView
    public int computeVerticalScrollOffset() {
        int sRange = super.computeVerticalScrollRange();
        int sExtent = super.computeVerticalScrollExtent();
        int range = sRange - sExtent;
        if (range == 0) {
            return 0;
        }
        return (int) (((super.computeVerticalScrollOffset() * sRange) * 1.0f) / range);
    }
}
