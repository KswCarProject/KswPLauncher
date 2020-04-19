package com.wits.ksw.launcher.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint({"AppCompatCustomView"})
@BindingMethods({@BindingMethod(attribute = "setImageLevel", method = "setImageLevel", type = ImageView.class)})
public class ID7SpeedImageView extends ImageView {
    public ID7SpeedImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ID7SpeedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageLevel(int level) {
        super.setImageLevel(level);
    }
}
