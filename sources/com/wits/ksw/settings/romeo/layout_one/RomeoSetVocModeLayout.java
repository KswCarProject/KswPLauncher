package com.wits.ksw.settings.romeo.layout_one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.romeo.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes11.dex */
public class RomeoSetVocModeLayout extends RelativeLayout {
    private Context context;
    private int eqModel;
    private RadioGroup rdg_vocmd;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public RomeoSetVocModeLayout(Context context) {
        super(context);
        this.eqModel = 0;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_set_voc_model, (ViewGroup) null);
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
        RadioGroup radioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_vocmd);
        this.rdg_vocmd = radioGroup;
        switch (this.eqModel) {
            case 0:
                radioGroup.check(C0899R.C0901id.rdb_vocmd1);
                break;
            case 1:
                radioGroup.check(C0899R.C0901id.rdb_vocmd2);
                break;
            case 2:
                radioGroup.check(C0899R.C0901id.rdb_vocmd3);
                break;
            case 3:
                radioGroup.check(C0899R.C0901id.rdb_vocmd4);
                break;
            case 4:
                radioGroup.check(C0899R.C0901id.rdb_vocmd5);
                break;
            case 5:
                radioGroup.check(C0899R.C0901id.rdb_vocmd6);
                break;
        }
        this.rdg_vocmd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_one.RomeoSetVocModeLayout.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (RomeoSetVocModeLayout.this.updateTwoLayout == null) {
                    return;
                }
                switch (checkedId) {
                    case C0899R.C0901id.rdb_vocmd1 /* 2131297497 */:
                        RomeoSetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 0);
                        return;
                    case C0899R.C0901id.rdb_vocmd2 /* 2131297498 */:
                        RomeoSetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 1);
                        return;
                    case C0899R.C0901id.rdb_vocmd3 /* 2131297499 */:
                        RomeoSetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 2);
                        return;
                    case C0899R.C0901id.rdb_vocmd4 /* 2131297500 */:
                        RomeoSetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 3);
                        return;
                    case C0899R.C0901id.rdb_vocmd5 /* 2131297501 */:
                        RomeoSetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 4);
                        return;
                    case C0899R.C0901id.rdb_vocmd6 /* 2131297502 */:
                        RomeoSetVocModeLayout.this.updateTwoLayout.updateTwoLayout(3, 5);
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
