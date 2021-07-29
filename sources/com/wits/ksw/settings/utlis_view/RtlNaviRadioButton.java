package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

public class RtlNaviRadioButton extends RadioButton {
    private static final String TAG = "RtlNaviRadioButton";
    Drawable buttonDrawable;

    public RtlNaviRadioButton(Context context) {
        super(context);
        init();
    }

    public RtlNaviRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RtlNaviRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        String language = getResources().getConfiguration().locale.getLanguage();
        Log.i(TAG, "init: language=" + language);
        if (!language.contains("ar")) {
            Drawable buttonDrawable2 = getButtonDrawable();
            this.buttonDrawable = buttonDrawable2;
            setButtonDrawable(buttonDrawable2);
            this.buttonDrawable = null;
        }
        super.setCompoundDrawables(left, top, this.buttonDrawable, bottom);
    }

    /* access modifiers changed from: protected */
    public void init() {
        Drawable buttonDrawable2 = getButtonDrawable();
        this.buttonDrawable = buttonDrawable2;
        if (buttonDrawable2 == null) {
            Log.d(TAG, "getButtonDrawable null");
        } else if (getResources().getConfiguration().locale.getLanguage().contains("ar")) {
            Log.i(TAG, "RtlRadioButton: ");
            setButtonDrawable((Drawable) null);
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.buttonDrawable, (Drawable) null);
            setGravity(21);
            setCompoundDrawablePadding(10);
        } else {
            setButtonDrawable(this.buttonDrawable);
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            setCompoundDrawablePadding(10);
        }
    }
}
