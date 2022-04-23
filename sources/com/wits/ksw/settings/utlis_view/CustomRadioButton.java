package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import skin.support.widget.SkinCompatRadioButton;

public class CustomRadioButton extends SkinCompatRadioButton {
    private static final String TAG = "RtlRadioButton";
    protected Drawable buttonDrawable = null;

    public CustomRadioButton(Context context) {
        super(context);
        init();
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        Drawable buttonDrawable2 = getButtonDrawable();
        this.buttonDrawable = buttonDrawable2;
        if (buttonDrawable2 != null) {
            String language = getResources().getConfiguration().locale.getLanguage();
            Log.i(TAG, "RtlRadioButton: " + language);
            if (language.contains("ar")) {
                setButtonDrawable((Drawable) null);
                setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.buttonDrawable, (Drawable) null);
                setGravity(21);
                return;
            }
            setButtonDrawable(this.buttonDrawable);
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }
}
