package com.wits.ksw.settings.alsid7.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.alsid7.interfaces.IUpdateTwoLayout;

public class AlsID7SetTimeLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public AlsID7SetTimeLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.als_layout_set_time, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_timeSync = (TextView) view.findViewById(R.id.tv_timeSync);
        this.tv_timeZhis = (TextView) view.findViewById(R.id.tv_timeZhis);
        this.tv_timeSync.setOnClickListener(this);
        this.tv_timeZhis.setOnClickListener(this);
    }

    public void resetTextColor() {
        this.tv_timeSync.setTextColor(this.context.getColor(R.color.alsid7_text_bule));
        this.tv_timeZhis.setTextColor(-1);
        if (this.updateTwoLayout != null) {
            this.updateTwoLayout.updateTwoLayout(4, 0);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_timeSync:
                this.tv_timeSync.setTextColor(this.context.getColor(R.color.alsid7_text_bule));
                this.tv_timeZhis.setTextColor(-1);
                if (this.updateTwoLayout != null) {
                    this.updateTwoLayout.updateTwoLayout(4, 0);
                    return;
                }
                return;
            case R.id.tv_timeZhis:
                this.tv_timeZhis.setTextColor(this.context.getColor(R.color.alsid7_text_bule));
                this.tv_timeSync.setTextColor(-1);
                if (this.updateTwoLayout != null) {
                    this.updateTwoLayout.updateTwoLayout(4, 1);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
