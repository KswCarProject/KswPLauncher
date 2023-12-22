package com.wits.ksw.launcher.view.ntg5;

import android.content.Context;
import android.support.p004v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/* loaded from: classes3.dex */
public class NTG5TextView extends AppCompatTextView implements View.OnFocusChangeListener {
    public NTG5TextView(Context context) {
        this(context, null);
    }

    public NTG5TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("NTG5TextView", "create");
        setOnFocusChangeListener(this);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d("NTG5TextView", "onFocusChange: " + hasFocus);
        if (hasFocus) {
            setTextSize(50.0f);
        } else {
            setTextSize(26.0f);
        }
    }
}
