package com.wits.ksw.settings.utlis_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

@SuppressLint({"AppCompatCustomView"})
public class RtlRadioButton extends RadioButton {
    private static final String TAG = "RtlRadioButton";
    protected Drawable buttonDrawable = null;

    public RtlRadioButton(Context context) {
        super(context);
        init();
    }

    public RtlRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RtlRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RtlRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.buttonDrawable = getButtonDrawable();
        String language = getResources().getConfiguration().locale.getLanguage();
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
    }
}
