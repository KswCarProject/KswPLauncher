package com.wits.ksw.settings.id6.showLayout;

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
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ID6ShowVoiceModel extends RelativeLayout {
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
    private View view;
    private int zo = 12;

    public ID6ShowVoiceModel(Context context2) {
        super(context2);
        this.context = context2;
        this.view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_voice_model_shwo, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.di = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.zo = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.ga = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d("BenchiEQ", "di:" + this.di + "\tzo:" + this.zo + "\tga:" + this.ga + "\tmodel:" + this.eqModel);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view2) {
        this.tv_mdiSize = (TextView) view2.findViewById(R.id.tv_mdiSize);
        SeekBar seekBar = (SeekBar) view2.findViewById(R.id.seekbar_mdi);
        this.seekbar_mdi = seekBar;
        seekBar.setMax(this.barMax);
        this.tv_mzhSize = (TextView) view2.findViewById(R.id.tv_mzhSize);
        SeekBar seekBar2 = (SeekBar) view2.findViewById(R.id.seekbar_mzh);
        this.seekbar_mzh = seekBar2;
        seekBar2.setMax(this.barMax);
        this.tv_mgoSize = (TextView) view2.findViewById(R.id.tv_mgoSize);
        SeekBar seekBar3 = (SeekBar) view2.findViewById(R.id.seekbar_mgo);
        this.seekbar_mgo = seekBar3;
        seekBar3.setMax(this.barMax);
        initBarView(this.eqModel);
        this.seekbar_mdi.setEnabled(false);
        this.seekbar_mzh.setEnabled(false);
        this.seekbar_mgo.setEnabled(false);
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
    }

    public void updateView() {
        initData();
        initView(this.view);
    }
}
