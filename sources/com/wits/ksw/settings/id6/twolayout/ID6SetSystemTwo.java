package com.wits.ksw.settings.id6.twolayout;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ID6SetSystemTwo extends RelativeLayout {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + ID6SetSystemTwo.class.getSimpleName());
    private int aux_index1;
    private int aux_index2;
    private ContentResolver contentResolver;
    private Context context;
    private int fuelUnit;
    private int groupValue;
    private ImageView img_twoDefaul;
    private RadioGroup rdgFuelUnitRadioGroup;
    private RadioGroup rdgTempUnitRadioGroup;
    private RadioGroup rdg_shext;
    private LinearLayout relate_auxweiz;
    private RelativeLayout relate_shext;
    private SeekBar seekbar_caux;
    private SeekBar seekbar_caux2;
    private int tempUnit;
    /* access modifiers changed from: private */
    public TextView tv_cauxSize;
    /* access modifiers changed from: private */
    public TextView tv_cauxSize2;

    public ID6SetSystemTwo(Context context2) {
        super(context2);
        this.context = context2;
        this.contentResolver = context2.getContentResolver();
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_system_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.groupValue = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_SXT);
            this.aux_index1 = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_INDEX1);
            this.aux_index2 = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_INDEX2);
            this.tempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
            Log.i(TAG, "initData: TempUnit:" + this.tempUnit + "\tDAO_CHE_SXT:" + this.groupValue + "\tCAR_AUX_INDEX1:" + this.aux_index1 + "\tCAR_AUX_INDEX2:" + this.aux_index2);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.img_twoDefaul = (ImageView) view.findViewById(R.id.img_twoDefaul);
        this.relate_shext = (RelativeLayout) view.findViewById(R.id.relate_shext);
        this.relate_auxweiz = (LinearLayout) view.findViewById(R.id.relate_auxweiz);
        this.tv_cauxSize = (TextView) view.findViewById(R.id.tv_cauxSize);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar_caux);
        this.seekbar_caux = seekBar;
        seekBar.setMax(12);
        this.seekbar_caux.setProgress(this.aux_index1);
        this.tv_cauxSize.setText(this.aux_index1 + "");
        this.seekbar_caux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ID6SetSystemTwo.this.tv_cauxSize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX1, progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.tv_cauxSize2 = (TextView) view.findViewById(R.id.tv_cauxSize2);
        SeekBar seekBar2 = (SeekBar) view.findViewById(R.id.seekbar_caux2);
        this.seekbar_caux2 = seekBar2;
        seekBar2.setMax(12);
        this.seekbar_caux2.setProgress(this.aux_index2);
        this.tv_cauxSize2.setText(this.aux_index2 + "");
        this.seekbar_caux2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ID6SetSystemTwo.this.tv_cauxSize2.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rdg_shext);
        this.rdg_shext = radioGroup;
        switch (this.groupValue) {
            case 0:
                radioGroup.check(R.id.rdb_shext1);
                break;
            case 1:
                radioGroup.check(R.id.rdb_shext2);
                break;
            case 2:
                radioGroup.check(R.id.rdb_shext3);
                break;
        }
        this.rdg_shext.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_shext1 /*2131297087*/:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 0);
                        return;
                    case R.id.rdb_shext2 /*2131297088*/:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 1);
                        return;
                    case R.id.rdb_shext3 /*2131297089*/:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 2);
                        return;
                    default:
                        return;
                }
            }
        });
        this.rdgTempUnitRadioGroup = (RadioGroup) view.findViewById(R.id.rdg_tempUnit_radioGroup);
        for (int i = 0; i < this.rdgTempUnitRadioGroup.getChildCount(); i++) {
            if (this.tempUnit == i) {
                RadioGroup radioGroup2 = this.rdgTempUnitRadioGroup;
                radioGroup2.check(radioGroup2.getChildAt(i).getId());
            }
        }
        this.rdgTempUnitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.TempUnit, i);
                        Log.i(ID6SetSystemTwo.TAG, "save tempUnit  : " + i);
                    }
                    group.getChildAt(i).setSelected(checkedId == group.getChildAt(i).getId());
                }
            }
        });
        this.rdgFuelUnitRadioGroup = (RadioGroup) view.findViewById(R.id.rdg_fuelUnit_radioGroup);
        for (int i2 = 0; i2 < this.rdgFuelUnitRadioGroup.getChildCount(); i2++) {
            if (this.fuelUnit == i2) {
                RadioGroup radioGroup3 = this.rdgFuelUnitRadioGroup;
                radioGroup3.check(radioGroup3.getChildAt(i2).getId());
            }
        }
        this.rdgFuelUnitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        FileUtils.savaIntData(KeyConfig.FUEL_UNIT, i);
                        Log.i(ID6SetSystemTwo.TAG, "save fuelUnit  : " + i);
                    }
                    group.getChildAt(i).setSelected(checkedId == group.getChildAt(i).getId());
                }
            }
        });
    }

    public void showLayout(int index) {
        switch (index) {
            case 1:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(0);
                this.relate_auxweiz.setVisibility(8);
                return;
            case 2:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(0);
                return;
            case 3:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(0);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                return;
            case 4:
                this.rdgFuelUnitRadioGroup.setVisibility(0);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
