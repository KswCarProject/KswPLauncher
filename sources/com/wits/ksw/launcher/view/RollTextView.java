package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class RollTextView extends TextView {
    public RollTextView(Context context) {
        super(context);
    }

    public RollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isFocused() {
        return true;
    }
}
