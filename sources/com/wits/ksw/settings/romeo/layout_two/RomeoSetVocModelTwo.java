package com.wits.ksw.settings.romeo.layout_two;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class RomeoSetVocModelTwo extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private int barMax = 24;
    private Context context;
    private int di = 12;
    private int eqModel = 0;
    private int ga = 12;
    private SeekBar seekbar_mdi;
    private SeekBar seekbar_mgo;
    private SeekBar seekbar_mzh;
    private TextView tv_mdiSize;
    private TextView tv_mgoSize;
    private TextView tv_mzhSize;
    private int zo = 12;

    public RomeoSetVocModelTwo(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_set_voc_model_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.di = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.zo = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.ga = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d("EqData", "get settings data model:" + this.eqModel + "\tdi:" + this.di + "\tzo:" + this.zo + "\tga:" + this.ga);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.tv_mdiSize = (TextView) view.findViewById(R.id.tv_mdiSize);
        this.tv_mzhSize = (TextView) view.findViewById(R.id.tv_mzhSize);
        this.tv_mgoSize = (TextView) view.findViewById(R.id.tv_mgoSize);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar_mdi);
        this.seekbar_mdi = seekBar;
        seekBar.setMax(this.barMax);
        SeekBar seekBar2 = (SeekBar) view.findViewById(R.id.seekbar_mzh);
        this.seekbar_mzh = seekBar2;
        seekBar2.setMax(this.barMax);
        SeekBar seekBar3 = (SeekBar) view.findViewById(R.id.seekbar_mgo);
        this.seekbar_mgo = seekBar3;
        seekBar3.setMax(this.barMax);
        initBarView(this.eqModel);
        this.seekbar_mdi.setOnSeekBarChangeListener(this);
        this.seekbar_mzh.setOnSeekBarChangeListener(this);
        this.seekbar_mgo.setOnSeekBarChangeListener(this);
    }

    private void initBarView(int index) {
        switch (index) {
            case 0:
                this.seekbar_mdi.setProgress(this.di);
                this.seekbar_mzh.setProgress(this.zo);
                this.seekbar_mgo.setProgress(this.ga);
                break;
            case 1:
                this.seekbar_mdi.setProgress(16);
                this.seekbar_mzh.setProgress(9);
                this.seekbar_mgo.setProgress(16);
                break;
            case 2:
                this.seekbar_mdi.setProgress(19);
                this.seekbar_mzh.setProgress(13);
                this.seekbar_mgo.setProgress(15);
                break;
            case 3:
                this.seekbar_mdi.setProgress(15);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(17);
                break;
            case 4:
                this.seekbar_mdi.setProgress(13);
                this.seekbar_mzh.setProgress(16);
                this.seekbar_mgo.setProgress(16);
                break;
            case 5:
                this.seekbar_mdi.setProgress(17);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(19);
                break;
        }
        this.tv_mdiSize.setText((this.seekbar_mdi.getProgress() - 12) + "");
        this.tv_mzhSize.setText((this.seekbar_mzh.getProgress() - 12) + "");
        this.tv_mgoSize.setText((this.seekbar_mgo.getProgress() - 12) + "");
        Log.d("EqData", "init view data model:" + this.eqModel + "\tdi:" + this.di + "\tzo:" + this.zo + "\tga:" + this.ga);
    }

    public void showLayout(int index) {
        FileUtils.savaIntData(KeyConfig.EQ_MODE, index);
        this.eqModel = index;
        if (index == 0) {
            this.seekbar_mdi.setEnabled(true);
            this.seekbar_mzh.setEnabled(true);
            this.seekbar_mgo.setEnabled(true);
        } else {
            this.seekbar_mdi.setEnabled(false);
            this.seekbar_mzh.setEnabled(false);
            this.seekbar_mgo.setEnabled(false);
        }
        switch (index) {
            case 0:
                this.seekbar_mdi.setProgress(this.di);
                this.seekbar_mzh.setProgress(this.zo);
                this.seekbar_mgo.setProgress(this.ga);
                return;
            case 1:
                this.seekbar_mdi.setProgress(16);
                this.seekbar_mzh.setProgress(9);
                this.seekbar_mgo.setProgress(16);
                return;
            case 2:
                this.seekbar_mdi.setProgress(19);
                this.seekbar_mzh.setProgress(13);
                this.seekbar_mgo.setProgress(15);
                return;
            case 3:
                this.seekbar_mdi.setProgress(15);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(17);
                return;
            case 4:
                this.seekbar_mdi.setProgress(13);
                this.seekbar_mzh.setProgress(16);
                this.seekbar_mgo.setProgress(16);
                return;
            case 5:
                this.seekbar_mdi.setProgress(17);
                this.seekbar_mzh.setProgress(11);
                this.seekbar_mgo.setProgress(19);
                return;
            default:
                return;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbar_mdi /*2131297411*/:
                this.tv_mdiSize.setText((progress - 12) + "");
                if (this.eqModel == 0) {
                    this.di = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_BASS, progress);
                    break;
                }
                break;
            case R.id.seekbar_mgo /*2131297412*/:
                this.tv_mgoSize.setText((progress - 12) + "");
                if (this.eqModel == 0) {
                    this.ga = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_TREBLE, progress);
                    break;
                }
                break;
            case R.id.seekbar_mzh /*2131297413*/:
                this.tv_mzhSize.setText((progress - 12) + "");
                if (this.eqModel == 0) {
                    this.zo = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, progress);
                    break;
                }
                break;
        }
        Log.d("EqData", "set and select model data model:" + this.eqModel + "\tdi:" + this.di + "\tzo:" + this.zo + "\tga:" + this.ga);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
