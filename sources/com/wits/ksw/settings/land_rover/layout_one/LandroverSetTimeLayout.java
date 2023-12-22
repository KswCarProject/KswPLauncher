package com.wits.ksw.settings.land_rover.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.land_rover.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes3.dex */
public class LandroverSetTimeLayout extends RelativeLayout implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Context context;
    private RadioGroup rdg_timeSy;
    private RadioGroup rdg_timeZhis;
    private int timeSync;
    private int timeZhis;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public LandroverSetTimeLayout(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.land_rover_layout_set_time, (ViewGroup) null);
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
        this.rdg_timeSy.setOnCheckedChangeListener(this);
        this.rdg_timeZhis.setOnCheckedChangeListener(this);
    }

    public void updateView() {
        initData();
        RadioGroup radioGroup = this.rdg_timeSy;
        if (radioGroup != null) {
            switch (this.timeSync) {
                case 0:
                    radioGroup.check(C0899R.C0901id.rdb_sync2);
                    break;
                case 1:
                    radioGroup.check(C0899R.C0901id.rdb_sync1);
                    break;
            }
        }
        RadioGroup radioGroup2 = this.rdg_timeZhis;
        if (radioGroup2 != null) {
            switch (this.timeZhis) {
                case 0:
                    radioGroup2.check(C0899R.C0901id.rdb_zhis2);
                    return;
                case 1:
                    radioGroup2.check(C0899R.C0901id.rdb_zhis1);
                    return;
                default:
                    return;
            }
        }
    }

    public void resetTextColor() {
        this.tv_timeZhis.setTextColor(-1);
        this.tv_timeSync.setTextColor(-1);
        updateView();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.tv_timeSync /* 2131297993 */:
                this.tv_timeSync.setTextColor(this.context.getColor(C0899R.color.alsid7_text_bule));
                this.tv_timeZhis.setTextColor(-1);
                IUpdateTwoLayout iUpdateTwoLayout = this.updateTwoLayout;
                if (iUpdateTwoLayout != null) {
                    iUpdateTwoLayout.updateTwoLayout(4, 0);
                    return;
                }
                return;
            case C0899R.C0901id.tv_timeZhis /* 2131297994 */:
                this.tv_timeZhis.setTextColor(this.context.getColor(C0899R.color.alsid7_text_bule));
                this.tv_timeSync.setTextColor(-1);
                IUpdateTwoLayout iUpdateTwoLayout2 = this.updateTwoLayout;
                if (iUpdateTwoLayout2 != null) {
                    iUpdateTwoLayout2.updateTwoLayout(4, 1);
                    return;
                }
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
