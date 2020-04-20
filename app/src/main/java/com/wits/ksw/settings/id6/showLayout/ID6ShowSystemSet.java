package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ID6ShowSystemSet extends RelativeLayout {
    private CheckBox cbox_sysDcgj;
    private CheckBox cbox_sysDcld;
    private CheckBox cbox_sysHjs;
    private CheckBox cbox_sysXcjz;
    private int cheVideo = 0;
    private Context context;
    private int dcgj = 0;
    private int dcld = 0;
    private int housi = 0;
    private View view;

    public ID6ShowSystemSet(Context context2) {
        super(context2);
        this.context = context2;
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_system_set_shwo, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.housi = PowerManagerApp.getSettingsInt(KeyConfig.HOU_SHI_SX);
            this.cheVideo = PowerManagerApp.getSettingsInt(KeyConfig.XING_CHE_JZSP);
            this.dcgj = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_GJ);
            this.dcld = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_LD);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view2) {
        this.cbox_sysHjs = (CheckBox) view2.findViewById(R.id.scbox_sysHjs);
        this.cbox_sysXcjz = (CheckBox) view2.findViewById(R.id.scbox_sysXcjz);
        this.cbox_sysDcgj = (CheckBox) view2.findViewById(R.id.scbox_sysDcgj);
        this.cbox_sysDcld = (CheckBox) view2.findViewById(R.id.scbox_sysDcld);
        boolean z = true;
        this.cbox_sysHjs.setChecked(this.housi != 0);
        this.cbox_sysXcjz.setChecked(this.cheVideo != 0);
        this.cbox_sysDcgj.setChecked(this.dcgj != 0);
        CheckBox checkBox = this.cbox_sysDcld;
        if (this.dcld == 0) {
            z = false;
        }
        checkBox.setChecked(z);
        this.cbox_sysHjs.setEnabled(false);
        this.cbox_sysXcjz.setEnabled(false);
        this.cbox_sysDcgj.setEnabled(false);
        this.cbox_sysDcld.setEnabled(false);
    }

    public void updateView() {
        initData();
        initView(this.view);
    }
}
