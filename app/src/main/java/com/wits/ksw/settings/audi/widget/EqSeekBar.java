package com.wits.ksw.settings.audi.widget;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

@BindingMethods({@BindingMethod(attribute = "enabled", method = "setEnabled", type = EqSeekBar.class)})
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
