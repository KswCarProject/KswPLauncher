package com.wits.ksw.settings.lexus.layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.R;

public class LexusSetImageTwo extends RelativeLayout {
    private Context context;
    private ImageView img_twoDefaul_right;

    public LexusSetImageTwo(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.lexus_layout_set_image_two, (ViewGroup) null);
        this.img_twoDefaul_right = (ImageView) view.findViewById(R.id.img_twoDefaul_right);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void setResource(int res) {
        this.img_twoDefaul_right.setImageResource(res);
    }

    private void initView(View view) {
    }

    public void showLayout(int index) {
    }
}
