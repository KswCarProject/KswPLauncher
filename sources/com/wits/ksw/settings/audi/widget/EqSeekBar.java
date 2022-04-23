package com.wits.ksw.settings.audi.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

public class EqSeekBar extends AppCompatSeekBar {
    public EqSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public EqSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
