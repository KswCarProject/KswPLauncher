package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import java.util.Locale;
import skin.support.widget.SkinCompatCheckBox;

/* loaded from: classes10.dex */
public class CustomSkinCheckBox extends SkinCompatCheckBox {
    private static final String TAG = "RtlCheckBox";

    public CustomSkinCheckBox(Context context) {
        super(context);
        init();
    }

    public CustomSkinCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSkinCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        Drawable drawable = getButtonDrawable();
        if (drawable == null) {
            return;
        }
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.contains("ar")) {
            Log.i(TAG, "RtlCheckBox: ");
            setButtonDrawable((Drawable) null);
            setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            setGravity(21);
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        setButtonDrawable(drawable);
        setGravity(16);
    }
}
