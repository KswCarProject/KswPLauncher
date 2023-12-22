package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import java.util.Locale;
import skin.support.widget.SkinCompatRadioButton;

/* loaded from: classes10.dex */
public class CustomSkinNaviRadioButton extends SkinCompatRadioButton {
    private static final String TAG = "RtlNaviRadioButton";
    Drawable buttonDrawable;

    public CustomSkinNaviRadioButton(Context context) {
        super(context);
        init();
    }

    public CustomSkinNaviRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSkinNaviRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        Log.i(TAG, "init: language=" + language);
        if (!language.contains("ar")) {
            Drawable buttonDrawable = getButtonDrawable();
            this.buttonDrawable = buttonDrawable;
            setButtonDrawable(buttonDrawable);
            this.buttonDrawable = null;
        }
        super.setCompoundDrawables(left, top, this.buttonDrawable, bottom);
    }

    protected void init() {
        Drawable buttonDrawable = getButtonDrawable();
        this.buttonDrawable = buttonDrawable;
        if (buttonDrawable == null) {
            Log.d(TAG, "getButtonDrawable null");
            return;
        }
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.contains("ar")) {
            Log.i(TAG, "RtlRadioButton: ");
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
