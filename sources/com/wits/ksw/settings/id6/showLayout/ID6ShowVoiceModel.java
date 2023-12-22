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
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes13.dex */
public class ID6ShowVoiceModel extends RelativeLayout {
    private int barMax;
    private Context context;

    /* renamed from: di */
    private int f223di;
    private int eqModel;

    /* renamed from: ga */
    private int f224ga;
    private SeekBar seekbar_mdi;
    private SeekBar seekbar_mgo;
    private SeekBar seekbar_mzh;
    private TextView tv_mdiSize;
    private TextView tv_mgoSize;
    private TextView tv_mzhSize;
    private View view;

    /* renamed from: zo */
    private int f225zo;

    public ID6ShowVoiceModel(Context context) {
        super(context);
        this.barMax = 24;
        this.f223di = 12;
        this.f225zo = 12;
        this.f224ga = 12;
        this.eqModel = 0;
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_voice_model_shwo, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.f223di = PowerManagerApp.getSettingsInt(KeyConfig.EQ_BASS);
            this.f225zo = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MIDDLE);
            this.f224ga = PowerManagerApp.getSettingsInt(KeyConfig.EQ_TREBLE);
            this.eqModel = PowerManagerApp.getSettingsInt(KeyConfig.EQ_MODE);
            Log.d("BenchiEQ", "di:" + this.f223di + "\tzo:" + this.f225zo + "\tga:" + this.f224ga + "\tmodel:" + this.eqModel);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.tv_mdiSize = (TextView) view.findViewById(C0899R.C0901id.tv_mdiSize);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mdi);
        this.seekbar_mdi = seekBar;
        seekBar.setMax(this.barMax);
        this.tv_mzhSize = (TextView) view.findViewById(C0899R.C0901id.tv_mzhSize);
        SeekBar seekBar2 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mzh);
        this.seekbar_mzh = seekBar2;
        seekBar2.setMax(this.barMax);
        this.tv_mgoSize = (TextView) view.findViewById(C0899R.C0901id.tv_mgoSize);
        SeekBar seekBar3 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_mgo);
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
                this.seekbar_mdi.setProgress(this.f223di);
                this.seekbar_mzh.setProgress(this.f225zo);
                this.seekbar_mgo.setProgress(this.f224ga);
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
    }

    public void updateView() {
        initData();
        initView(this.view);
    }
}
