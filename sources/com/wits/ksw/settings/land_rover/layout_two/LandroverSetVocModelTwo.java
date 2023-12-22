package com.wits.ksw.settings.land_rover.layout_two;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes9.dex */
public class LandroverSetVocModelTwo extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private int barMax;
    private Context context;

    /* renamed from: di */
    private int f231di;
    private int eqModel;

    /* renamed from: ga */
    private int f232ga;
    private SeekBar seekbar_mdi;
    private SeekBar seekbar_mgo;
    private SeekBar seekbar_mzh;
    private TextView tv_mdiSize;
    private TextView tv_mgoSize;
    private TextView tv_mzhSize;

    /* renamed from: zo */
    private int f233zo;

    public LandroverSetVocModelTwo(Context context) {
        super(context);
        this.barMax = 24;
        this.f231di = 12;
        this.f233zo = 12;
        this.f232ga = 12;
        this.eqModel = 0;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.als_layout_set_voc_model_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
        try {
            this.f231di = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.f233zo = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.f232ga = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d("EqData", "get settings data model:" + this.eqModel + "\tdi:" + this.f231di + "\tzo:" + this.f233zo + "\tga:" + this.f232ga);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.tv_mdiSize = (TextView) view.findViewById(C0899R.C0901id.tv_mdiSize);
        this.tv_mzhSize = (TextView) view.findViewById(C0899R.C0901id.tv_mzhSize);
        this.tv_mgoSize = (TextView) view.findViewById(C0899R.C0901id.tv_mgoSize);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mdi);
        this.seekbar_mdi = seekBar;
        seekBar.setMax(this.barMax);
        SeekBar seekBar2 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mzh);
        this.seekbar_mzh = seekBar2;
        seekBar2.setMax(this.barMax);
        SeekBar seekBar3 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mgo);
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
                this.seekbar_mdi.setProgress(this.f231di);
                this.seekbar_mzh.setProgress(this.f233zo);
                this.seekbar_mgo.setProgress(this.f232ga);
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
        int sdi = this.seekbar_mdi.getProgress() - 12;
        this.tv_mdiSize.setText(sdi + "");
        int zh = this.seekbar_mzh.getProgress() - 12;
        this.tv_mzhSize.setText(zh + "");
        int go = this.seekbar_mgo.getProgress() - 12;
        this.tv_mgoSize.setText(go + "");
        Log.d("EqData", "init view data model:" + this.eqModel + "\tdi:" + this.f231di + "\tzo:" + this.f233zo + "\tga:" + this.f232ga);
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
                this.seekbar_mdi.setProgress(this.f231di);
                this.seekbar_mzh.setProgress(this.f233zo);
                this.seekbar_mgo.setProgress(this.f232ga);
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

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case C0899R.C0901id.seekbar_mdi /* 2131297743 */:
                int go = progress - 12;
                this.tv_mdiSize.setText(go + "");
                if (this.eqModel == 0) {
                    this.f231di = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_BASS, progress);
                    break;
                }
                break;
            case C0899R.C0901id.seekbar_mgo /* 2131297744 */:
                int zh = progress - 12;
                this.tv_mgoSize.setText(zh + "");
                if (this.eqModel == 0) {
                    this.f232ga = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_TREBLE, progress);
                    break;
                }
                break;
            case C0899R.C0901id.seekbar_mzh /* 2131297745 */:
                int zh2 = progress - 12;
                this.tv_mzhSize.setText(zh2 + "");
                if (this.eqModel == 0) {
                    this.f233zo = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, progress);
                    break;
                }
                break;
        }
        Log.d("EqData", "set and select model data model:" + this.eqModel + "\tdi:" + this.f231di + "\tzo:" + this.f233zo + "\tga:" + this.f232ga);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
