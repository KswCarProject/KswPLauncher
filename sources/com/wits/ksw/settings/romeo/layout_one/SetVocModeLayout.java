package com.wits.ksw.settings.romeo.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import com.wits.ksw.settings.romeo.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class SetVocModeLayout extends RelativeLayout {
    private Context context;
    private int eqModel = 0;
    private RadioGroup rdg_vocmd;
    /* access modifiers changed from: private */
    public IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public SetVocModeLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_set_voc_model, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rdg_vocmd);
        this.rdg_vocmd = radioGroup;
        switch (this.eqModel) {
            case 0:
                radioGroup.check(R.id.rdb_vocmd1);
                break;
            case 1:
                radioGroup.check(R.id.rdb_vocmd2);
                break;
            case 2:
                radioGroup.check(R.id.rdb_vocmd3);
                break;
            case 3:
                radioGroup.check(R.id.rdb_vocmd4);
                break;
            case 4:
                radioGroup.check(R.id.rdb_vocmd5);
                break;
            case 5:
                radioGroup.check(R.id.rdb_vocmd6);
                break;
        }
        this.rdg_vocmd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (SetVocModeLayout.this.updateTwoLayout != null) {
                    switch (checkedId) {
                        case R.id.rdb_vocmd1:
                            SetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 0);
                            return;
                        case R.id.rdb_vocmd2:
                            SetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 1);
                            return;
                        case R.id.rdb_vocmd3:
                            SetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 2);
                            return;
                        case R.id.rdb_vocmd4:
                            SetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 3);
                            return;
                        case R.id.rdb_vocmd5:
                            SetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 4);
                            return;
                        case R.id.rdb_vocmd6:
                            SetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 5);
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }
}
