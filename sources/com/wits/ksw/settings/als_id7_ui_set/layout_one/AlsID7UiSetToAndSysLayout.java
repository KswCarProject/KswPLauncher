package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;

/* loaded from: classes6.dex */
public class AlsID7UiSetToAndSysLayout extends RelativeLayout {
    public AlsID7UiSetToAndSysLayout(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_layout_set_none, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        view.setLayoutParams(layoutParams);
        addView(view);
    }
}
