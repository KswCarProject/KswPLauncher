package com.wits.ksw.settings.als_id7_ui_set.layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;

/* loaded from: classes15.dex */
public class AlsID7UiSetImageTwo extends RelativeLayout {
    private Context context;

    public AlsID7UiSetImageTwo(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_layout_set_image_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initView(View view) {
    }

    public void showLayout(int index) {
    }
}
