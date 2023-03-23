package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class ID8HorizontalScrollView extends HorizontalScrollView {
    private View.OnDragListener onDragListener = null;
    private ScrollViewListener scrollViewListener = null;

    public interface ScrollViewListener {
        void onScrollChanged(int i, int i2, int i3, int i4);
    }

    public ID8HorizontalScrollView(Context context) {
        super(context);
    }

    public ID8HorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ID8HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener2) {
        this.scrollViewListener = scrollViewListener2;
    }

    public void setOnDragListener(View.OnDragListener l) {
        this.onDragListener = l;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        ScrollViewListener scrollViewListener2 = this.scrollViewListener;
        if (scrollViewListener2 != null) {
            scrollViewListener2.onScrollChanged(x, y, oldx, oldy);
        }
    }
}
