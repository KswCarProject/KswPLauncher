package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.lexus.interfaces.IUpdateTwoLayout;

public class LexusSetNaviLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private TextView tv_naviapp;
    private TextView tv_navihy;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public LexusSetNaviLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_set_navi, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_navihy = (TextView) view.findViewById(R.id.tv_navihy);
        this.tv_naviapp = (TextView) view.findViewById(R.id.tv_naviapp);
        this.tv_navihy.setOnClickListener(this);
        this.tv_naviapp.setOnClickListener(this);
    }

    public void resetTextColor() {
        this.tv_navihy.setTextColor(SupportMenu.CATEGORY_MASK);
        this.tv_naviapp.setTextColor(-1);
        IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
        if (iUpdateTwoLayout != null) {
            iUpdateTwoLayout.updateTwoLayout(2, 1);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_naviapp:
                this.tv_naviapp.setTextColor(SupportMenu.CATEGORY_MASK);
                this.tv_navihy.setTextColor(-1);
                IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
                if (iUpdateTwoLayout != null) {
                    iUpdateTwoLayout.updateTwoLayout(2, 1);
                    return;
                }
                return;
            case R.id.tv_navihy:
                this.tv_navihy.setTextColor(SupportMenu.CATEGORY_MASK);
                this.tv_naviapp.setTextColor(-1);
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
