package com.wits.ksw.launcher.view;

import android.content.Context;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes16.dex */
public class CapitalizeTextView extends AppCompatTextView {
    public CapitalizeTextView(Context context) {
        super(context);
    }

    public CapitalizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CapitalizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        StringBuilder builder = new StringBuilder(text);
        try {
            builder.setCharAt(0, Character.toUpperCase(builder.charAt(0)));
            for (int i = 1; i < text.length(); i++) {
                builder.setCharAt(i, Character.toLowerCase(builder.charAt(i)));
            }
            super.setText(builder.toString(), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
