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
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;

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

    public Ntg6NaviLayout(Context context2, Handler handler2) {
        super(context2);
        this.context = context2;
        this.handler = handler2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_ntg6_navi, (ViewGroup) null);
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
        this.img_TwoBack = (ImageView) view.findViewById(R.id.img_TwoBack);
        this.tv_navihy = (TextView) view.findViewById(R.id.tv_navihy);
        this.tv_naviapp = (TextView) view.findViewById(R.id.tv_naviapp);
        this.img_TwoBack.setOnClickListener(this);
        this.tv_naviapp.setOnClickListener(this);
        this.tv_naviapp.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_TwoBack /*2131296841*/:
                this.handler.sendEmptyMessage(1);
                return;
            case R.id.tv_naviapp /*2131297603*/:
                IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
                if (iUpdateTwoLayout != null) {
                    iUpdateTwoLayout.updateTwoLayout(2, 1);
                    return;
                }
                return;
            case R.id.tv_navihy /*2131297604*/:
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
