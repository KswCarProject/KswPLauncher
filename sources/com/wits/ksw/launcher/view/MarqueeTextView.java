package com.wits.ksw.launcher.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MarqueeTextView extends AppCompatTextView {
    public String TAG;

    public MarqueeTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.TAG = "MarqueeTextView";
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TAG = "MarqueeTextView";
    }

    /* access modifiers changed from: package-private */
    public void init() {
        Log.d(this.TAG, "init");
        setSingleLine(true);
        setSelected(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void setEllipsize(TextUtils.TruncateAt where) {
        super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    public boolean isFocused() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    }

    public void setText(CharSequence text, TextView.BufferType type) {
        super.setText(text, type);
        Log.d(this.TAG, "text:" + text);
    }
}
