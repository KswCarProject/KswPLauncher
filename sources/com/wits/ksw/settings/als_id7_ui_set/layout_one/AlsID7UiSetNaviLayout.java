package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.als_id7_ui_set.interfaces.AlsID7UiIUpdateTwoLayout;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes6.dex */
public class AlsID7UiSetNaviLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private TextView tv_naviapp;
    private TextView tv_navihy;
    private AlsID7UiIUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(AlsID7UiIUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public AlsID7UiSetNaviLayout(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_id7_ui_layout_set_navi, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_navihy = (TextView) view.findViewById(C0899R.C0901id.tv_navihy);
        this.tv_naviapp = (TextView) view.findViewById(C0899R.C0901id.tv_naviapp);
        this.tv_navihy.setOnClickListener(this);
        this.tv_naviapp.setOnClickListener(this);
    }

    public void resetTextColor() {
        this.tv_navihy.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
        this.tv_naviapp.setTextColor(-1);
        AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout = this.updateTwoLayout;
        if (alsID7UiIUpdateTwoLayout != null) {
            alsID7UiIUpdateTwoLayout.updateTwoLayout(2, 1);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.tv_naviapp /* 2131297958 */:
                this.tv_naviapp.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.tv_navihy.setTextColor(-1);
                AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout = this.updateTwoLayout;
                if (alsID7UiIUpdateTwoLayout != null) {
                    alsID7UiIUpdateTwoLayout.updateTwoLayout(2, 1);
                    return;
                }
                return;
            case C0899R.C0901id.tv_navihy /* 2131297959 */:
                this.tv_navihy.setTextColor(SkinCompatResources.getColor(this.context, C0899R.color.als_id7_ui_text_color));
                this.tv_naviapp.setTextColor(-1);
                AlsID7UiIUpdateTwoLayout alsID7UiIUpdateTwoLayout2 = this.updateTwoLayout;
                if (alsID7UiIUpdateTwoLayout2 != null) {
                    alsID7UiIUpdateTwoLayout2.updateTwoLayout(2, 0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
