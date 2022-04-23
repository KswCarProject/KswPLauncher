package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf"));
    }
}
