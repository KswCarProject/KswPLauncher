package com.wits.ksw.settings.ntg6.one_layout;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class Ntg6TimeLayout extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    private Context context;
    /* access modifiers changed from: private */
    public Handler handler;
    private ImageView img_TwoBack;
    public onTimeListener listener;
    private RadioGroup rdg_timeSy;
    private RadioGroup rdg_timeZhis;
    private int timeSync;
    private int timeZhis;
    private TextView tv_timeSync;
    private TextView tv_timeZhis;
    private IUpdateTwoLayout updateTwoLayout;
    private View view;

    public interface onTimeListener {
        void getTieType();
    }

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public Ntg6TimeLayout(Context context2, Handler handler2) {
        super(context2);
        this.context = context2;
        this.handler = handler2;
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_ntg6_time, (ViewGroup) null);
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

    public void getFocus() {
        this.img_TwoBack.requestFocus();
    }

    private void initView(View view2) {
        this.tv_timeSync = (TextView) view2.findViewById(R.id.tv_timeSync);
        this.tv_timeZhis = (TextView) view2.findViewById(R.id.tv_timeZhis);
        this.img_TwoBack = (ImageView) view2.findViewById(R.id.img_TwoBack);
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
        this.rdg_timeSy.setOnCheckedChangeListener(this);
        this.rdg_timeZhis.setOnCheckedChangeListener(this);
        this.img_TwoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ntg6TimeLayout.this.handler.sendEmptyMessage(1);
            }
        });
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rdb_sync1:
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 1);
                onTimeListener ontimelistener = this.listener;
                if (ontimelistener != null) {
                    ontimelistener.getTieType();
                    return;
                }
                return;
            case R.id.rdb_sync2:
                FileUtils.savaIntData(KeyConfig.TIME_SOURCE, 0);
                onTimeListener ontimelistener2 = this.listener;
                if (ontimelistener2 != null) {
                    ontimelistener2.getTieType();
                    return;
                }
                return;
            case R.id.rdb_zhis1:
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 1);
                onTimeListener ontimelistener3 = this.listener;
                if (ontimelistener3 != null) {
                    ontimelistener3.getTieType();
                    return;
                }
                return;
            case R.id.rdb_zhis2:
                FileUtils.savaIntData(KeyConfig.TIME_FORMAT, 0);
                onTimeListener ontimelistener4 = this.listener;
                if (ontimelistener4 != null) {
                    ontimelistener4.getTieType();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setOnTimeListener(onTimeListener listener2) {
        this.listener = listener2;
    }
}
