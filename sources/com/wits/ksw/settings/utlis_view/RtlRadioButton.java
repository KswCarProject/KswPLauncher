package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;
import java.util.Locale;

/* loaded from: classes10.dex */
public class RtlRadioButton extends RadioButton {
    private static final String TAG = "RtlRadioButton";
    protected Drawable buttonDrawable;

    public RtlRadioButton(Context context) {
        super(context);
        this.buttonDrawable = null;
        init();
    }

    public RtlRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.buttonDrawable = null;
        init();
    }

    public RtlRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.buttonDrawable = null;
        init();
    }

    public RtlRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.buttonDrawable = null;
        init();
    }

    protected void init() {
        Drawable buttonDrawable = getButtonDrawable();
        this.buttonDrawable = buttonDrawable;
        if (buttonDrawable == null) {
            return;
        }
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        Log.i(TAG, "RtlRadioButton: " + language);
        if (language.contains("ar")) {
            setButtonDrawable((Drawable) null);
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.buttonDrawable, (Drawable) null);
            setGravity(21);
            setCompoundDrawablePadding(10);
            return;
        }
        setButtonDrawable(this.buttonDrawable);
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        setCompoundDrawablePadding(10);
    }
}
