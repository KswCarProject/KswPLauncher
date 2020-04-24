package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ID6ShowTimeLayout extends RelativeLayout {
    private Context context;
    private RadioGroup rdg_timeSy;
    private RadioGroup rdg_timeZhis;
    private int timeSync;
    private int timeZhis;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private IUpdateTwoLayout updateTwoLayout;
    private View view;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public ID6ShowTimeLayout(Context context2) {
        super(context2);
        this.context = context2;
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_show_time, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    public void onUpdateView() {
        initData();
        initView(this.view);
    }

    private void initData() {
        try {
            this.timeSync = PowerManagerApp.getSettingsInt(KeyConfig.TIME_SOURCE);
            this.timeZhis = PowerManagerApp.getSettingsInt(KeyConfig.TIME_FORMAT);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view2) {
        this.tv_timeSync = (TextView) view2.findViewById(R.id.tv_timeSync);
        this.tv_timeZhis = (TextView) view2.findViewById(R.id.tv_timeZhis);
        this.rdg_timeSy = (RadioGroup) view2.findViewById(R.id.rdg_timeSy);
        this.rdg_timeZhis = (RadioGroup) view2.findViewById(R.id.rdg_timeZhis);
        switch (this.timeSync) {
            case 0:
                this.rdg_timeSy.check(R.id.rdb_sync2);
                break;
            case 1:
                this.rdg_timeSy.check(R.id.rdb_sync1);
                break;
        }
        switch (this.timeZhis) {
            case 0:
                this.rdg_timeZhis.check(R.id.rdb_zhis2);
                break;
            case 1:
                this.rdg_timeZhis.check(R.id.rdb_zhis1);
                break;
        }
        this.rdg_timeSy.setEnabled(false);
        this.rdg_timeZhis.setEnabled(false);
    }
}
