package com.wits.ksw.settings.ntg6.one_layout;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;

/* loaded from: classes17.dex */
public class Ntg6NaviLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private Handler handler;
    private ImageView img_TwoBack;
    private TextView tv_naviapp;
    private TextView tv_navihy;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public Ntg6NaviLayout(Context context, Handler handler) {
        super(context);
        this.context = context;
        this.handler = handler;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_ntg6_navi, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    public void getFocus() {
        this.img_TwoBack.requestFocus();
    }

    private void initView(View view) {
        this.img_TwoBack = (ImageView) view.findViewById(C0899R.C0901id.img_TwoBack);
        this.tv_navihy = (TextView) view.findViewById(C0899R.C0901id.tv_navihy);
        this.tv_naviapp = (TextView) view.findViewById(C0899R.C0901id.tv_naviapp);
        this.img_TwoBack.setOnClickListener(this);
        this.tv_naviapp.setOnClickListener(this);
        this.tv_naviapp.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.img_TwoBack /* 2131297053 */:
                this.handler.sendEmptyMessage(1);
                return;
            case C0899R.C0901id.tv_naviapp /* 2131297958 */:
                IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
                if (iUpdateTwoLayout != null) {
                    iUpdateTwoLayout.updateTwoLayout(2, 1);
                    return;
                }
                return;
            case C0899R.C0901id.tv_navihy /* 2131297959 */:
                IUpdateTwoLayout iUpdateTwoLayout2 = this.updateTwoLayout;
                if (iUpdateTwoLayout2 != null) {
                    iUpdateTwoLayout2.updateTwoLayout(2, 0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
