package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import java.util.Locale;

/* loaded from: classes10.dex */
public class RtlTextView extends TextView {
    private static final String TAG = "RtlTextView";

    public RtlTextView(Context context) {
        super(context);
    }

    public RtlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RtlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RtlTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.contains("ar")) {
            Log.i(TAG, "RtlTextView: ");
            setGravity(21);
            return;
        }
        setGravity(16);
    }
}
