package com.wits.ksw.launcher.view.ntg5;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class NTG5TextView extends AppCompatTextView implements View.OnFocusChangeListener {
    public NTG5TextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NTG5TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("NTG5TextView", "create");
        setOnFocusChangeListener(this);
    }

    public void onFocusChange(View v, boolean hasFocus) {
        Log.d("NTG5TextView", "onFocusChange: " + hasFocus);
        if (hasFocus) {
            setTextSize(50.0f);
        } else {
            setTextSize(26.0f);
        }
    }
}
