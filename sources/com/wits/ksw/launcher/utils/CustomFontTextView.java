package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes11.dex */
public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        this(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        AssetManager asm = context.getAssets();
        Typeface typeface = Typeface.createFromAsset(asm, "fonts/Roboto-Light.ttf");
        setTypeface(typeface);
    }
}
