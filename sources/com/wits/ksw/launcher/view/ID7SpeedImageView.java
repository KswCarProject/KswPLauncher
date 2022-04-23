package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ID7SpeedImageView extends ImageView {
    public ID7SpeedImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ID7SpeedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageLevel(int level) {
        super.setImageLevel(level);
    }
}
