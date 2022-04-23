package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class RtlLinearLayout extends LinearLayout {
    private static final String TAG = "RtlLinearLayout";

    public RtlLinearLayout(Context context) {
        super(context);
        init();
    }

    public RtlLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RtlLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RtlLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (getResources().getConfiguration().locale.getLanguage().contains("ar")) {
            Log.i(TAG, "RtlRadioButton: ");
            setGravity(21);
            return;
        }
        setGravity(16);
    }
}
