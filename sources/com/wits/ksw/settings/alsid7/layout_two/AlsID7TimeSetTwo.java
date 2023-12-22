package com.wits.ksw.settings.alsid7.layout_two;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes8.dex */
public class AlsID7TimeSetTwo extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    private Context context;
    private RadioGroup rdg_timeSy;
    private RadioGroup rdg_timeZhis;
    private RelativeLayout relate_timeSync;
    private RelativeLayout relate_timeZhis;
    private int timeSync;
    private int timeZhis;

    public AlsID7TimeSetTwo(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_layout_set_time_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
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
        this.relate_timeSync = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_timeSync);
        this.relate_timeZhis = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_timeZhis);
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
        this.rdg_timeSy.setOnCheckedChangeListener(this);
        this.rdg_timeZhis.setOnCheckedChangeListener(this);
    }

    public void showLayout(int index) {
        switch (index) {
            case 0:
                this.relate_timeSync.setVisibility(0);
                this.relate_timeZhis.setVisibility(8);
                return;
            case 1:
                this.relate_timeSync.setVisibility(8);
                this.relate_timeZhis.setVisibility(0);
                return;
            default:
                return;
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case C0899R.C0901id.rdb_sync1 /* 2131297495 */:
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                return;
            case C0899R.C0901id.rdb_sync2 /* 2131297496 */:
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                return;
            case C0899R.C0901id.rdb_zhis1 /* 2131297503 */:
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                return;
            case C0899R.C0901id.rdb_zhis2 /* 2131297504 */:
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                return;
            default:
                return;
        }
    }
}
