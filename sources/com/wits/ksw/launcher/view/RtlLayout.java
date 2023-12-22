package com.wits.ksw.launcher.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import com.wits.ksw.C0899R;
import java.util.Locale;

/* loaded from: classes16.dex */
public class RtlLayout extends ConstraintLayout {
    private static final String TAG = "RtlId5Layout";

    public RtlLayout(Context context) {
        super(context);
    }

    public RtlLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RtlLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.view.View
    public void setBackground(Drawable background) {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        Log.i(TAG, "init: language=" + language);
        if (language.contains("ar")) {
            background = getResources().getDrawable(C0899R.mipmap.id5_bk_rtl);
        }
        super.setBackground(background);
    }
}
