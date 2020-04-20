package com.wits.ksw.settings.id7.layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;

public class SetImageTwo extends RelativeLayout {
    private Context context;

    public SetImageTwo(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_set_image_two, (ViewGroup) null);
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
