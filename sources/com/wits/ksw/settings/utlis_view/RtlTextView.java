package com.wits.ksw.settings.utlis_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

@SuppressLint({"AppCompatCustomView"})
public class RtlTextView extends TextView {
    private static final String TAG = "RtlTextView";

    public RtlTextView(Context context) {
        super(context);
    }

    public RtlTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RtlTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RtlTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (getResources().getConfiguration().locale.getLanguage().contains("ar")) {
            Log.i(TAG, "RtlTextView: ");
            setGravity(21);
            return;
        }
        setGravity(16);
    }
}
