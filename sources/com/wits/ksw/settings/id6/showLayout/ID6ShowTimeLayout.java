package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes13.dex */
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

    public ID6ShowTimeLayout(Context context) {
        super(context);
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_show_time, (ViewGroup) null);
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

    private void initView(View view) {
        this.tv_timeSync = (TextView) view.findViewById(C0899R.C0901id.tv_timeSync);
        this.tv_timeZhis = (TextView) view.findViewById(C0899R.C0901id.tv_timeZhis);
        this.rdg_timeSy = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_timeSy);
        this.rdg_timeZhis = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_timeZhis);
        switch (this.timeSync) {
            case 0:
                this.rdg_timeSy.check(C0899R.C0901id.rdb_sync2);
                break;
            case 1:
                this.rdg_timeSy.check(C0899R.C0901id.rdb_sync1);
                break;
        }
        switch (this.timeZhis) {
            case 0:
                this.rdg_timeZhis.check(C0899R.C0901id.rdb_zhis2);
                break;
            case 1:
                this.rdg_timeZhis.check(C0899R.C0901id.rdb_zhis1);
                break;
        }
        this.rdg_timeSy.setEnabled(false);
        this.rdg_timeZhis.setEnabled(false);
    }
}
