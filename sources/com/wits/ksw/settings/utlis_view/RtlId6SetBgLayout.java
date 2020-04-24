package com.wits.ksw.settings.utlis_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import com.wits.ksw.R;

public class RtlId6SetBgLayout extends RelativeLayout {
    private static final String TAG = "RtlId6SetBgLayout";

    public RtlId6SetBgLayout(Context context) {
        super(context);
    }

    public RtlId6SetBgLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RtlId6SetBgLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RtlId6SetBgLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBackground(Drawable background) {
        String language = getResources().getConfiguration().locale.getLanguage();
        Log.i(TAG, "init: language=" + language);
        if (language.contains("ar")) {
            background = getResources().getDrawable(R.mipmap.id6_bg_rtl);
        }
        super.setBackground(background);
    }
}
