package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/* loaded from: classes15.dex */
public class ID8HorizontalScrollView extends HorizontalScrollView {
    private View.OnDragListener onDragListener;
    private ScrollViewListener scrollViewListener;

    /* loaded from: classes15.dex */
    public interface ScrollViewListener {
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }

    public ID8HorizontalScrollView(Context context) {
        super(context);
        this.scrollViewListener = null;
        this.onDragListener = null;
    }

    public ID8HorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.scrollViewListener = null;
        this.onDragListener = null;
    }

    public ID8HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.scrollViewListener = null;
        this.onDragListener = null;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override // android.view.View
    public void setOnDragListener(View.OnDragListener l) {
        this.onDragListener = l;
    }

    @Override // android.view.View
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        ScrollViewListener scrollViewListener = this.scrollViewListener;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(x, y, oldx, oldy);
        }
    }
}
