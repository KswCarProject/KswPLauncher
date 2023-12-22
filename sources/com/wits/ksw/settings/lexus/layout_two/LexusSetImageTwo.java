package com.wits.ksw.settings.lexus.layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;

/* loaded from: classes16.dex */
public class LexusSetImageTwo extends RelativeLayout {
    private Context context;
    private ImageView img_twoDefaul_right;

    public LexusSetImageTwo(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.lexus_layout_set_image_two, (ViewGroup) null);
        this.img_twoDefaul_right = (ImageView) view.findViewById(C0899R.C0901id.img_twoDefaul_right);
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
