package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;

public class AlsID7UiSetToAndSysLayout extends RelativeLayout {
    public AlsID7UiSetToAndSysLayout(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.als_id7_ui_layout_set_none, (ViewGroup) null);
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(view);
    }
}
