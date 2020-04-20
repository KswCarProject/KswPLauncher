package com.wits.ksw.settings.id6.oneLayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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

public class ID6VoiceModel extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private int barMax = 24;
    private Context context;
    private int di = 12;
    private int eqModel = 0;
    private int ga = 12;
    private SeekBar seekbar_mdi;
    private SeekBar seekbar_mgo;
    private SeekBar seekbar_mzh;
    private TextView tv_eqgud;
    private TextView tv_eqjues;
    private TextView tv_eqliux;
    private TextView tv_eqwuq;
    private TextView tv_eqyaog;
    private TextView tv_eqyongh;
    private TextView tv_mdiSize;
    private TextView tv_mgoSize;
    private TextView tv_mzhSize;
    private int zo = 12;

    public ID6VoiceModel(@NonNull Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_voice_model, (ViewGroup) null);
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
            Log.d("BenchiEQ", "di:" + this.di + "\tzo:" + this.zo + "\tga:" + this.ga + "\tmodel:" + this.eqModel);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.tv_mdiSize = (TextView) view.findViewById(R.id.tv_mdiSize);
        this.seekbar_mdi = (SeekBar) view.findViewById(R.id.seekbar_mdi);
        this.seekbar_mdi.setMax(this.barMax);
        this.tv_mzhSize = (TextView) view.findViewById(R.id.tv_mzhSize);
        this.seekbar_mzh = (SeekBar) view.findViewById(R.id.seekbar_mzh);
        this.seekbar_mzh.setMax(this.barMax);
        this.tv_mgoSize = (TextView) view.findViewById(R.id.tv_mgoSize);
        this.seekbar_mgo = (SeekBar) view.findViewById(R.id.seekbar_mgo);
        this.seekbar_mgo.setMax(this.barMax);
        this.seekbar_mdi.setOnSeekBarChangeListener(this);
        this.seekbar_mzh.setOnSeekBarChangeListener(this);
        this.seekbar_mgo.setOnSeekBarChangeListener(this);
        this.tv_eqyongh = (TextView) view.findViewById(R.id.tv_eqyongh);
        this.tv_eqliux = (TextView) view.findViewById(R.id.tv_eqliux);
        this.tv_eqgud = (TextView) view.findViewById(R.id.tv_eqgud);
        this.tv_eqyaog = (TextView) view.findViewById(R.id.tv_eqyaog);
        this.tv_eqjues = (TextView) view.findViewById(R.id.tv_eqjues);
        this.tv_eqwuq = (TextView) view.findViewById(R.id.tv_eqwuq);
        switch (this.eqModel) {
            case 0:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                break;
            case 1:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                break;
            case 2:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                break;
            case 3:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                break;
            case 4:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                break;
            case 5:
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                break;
        }
        initBarView(this.eqModel);
        this.tv_eqyongh.setOnClickListener(this);
        this.tv_eqliux.setOnClickListener(this);
        this.tv_eqgud.setOnClickListener(this);
        this.tv_eqyaog.setOnClickListener(this);
        this.tv_eqjues.setOnClickListener(this);
        this.tv_eqwuq.setOnClickListener(this);
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
        TextView textView = this.tv_mdiSize;
        StringBuilder sb = new StringBuilder();
        sb.append(this.seekbar_mdi.getProgress() - 12);
        sb.append("");
        textView.setText(sb.toString());
        TextView textView2 = this.tv_mzhSize;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.seekbar_mzh.getProgress() - 12);
        sb2.append("");
        textView2.setText(sb2.toString());
        TextView textView3 = this.tv_mgoSize;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.seekbar_mgo.getProgress() - 12);
        sb3.append("");
        textView3.setText(sb3.toString());
    }

    private void setUpdateTwoLayout(int index) {
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
            case R.id.seekbar_mdi:
                TextView textView = this.tv_mdiSize;
                StringBuilder sb = new StringBuilder();
                sb.append(progress - 12);
                sb.append("");
                textView.setText(sb.toString());
                if (this.eqModel == 0) {
                    this.di = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_BASS, progress);
                    Log.d("BenchiEQ", "save di:" + this.di);
                    return;
                }
                return;
            case R.id.seekbar_mgo:
                TextView textView2 = this.tv_mgoSize;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(progress - 12);
                sb2.append("");
                textView2.setText(sb2.toString());
                if (this.eqModel == 0) {
                    this.ga = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_TREBLE, progress);
                    Log.d("BenchiEQ", "save go:" + this.ga);
                    return;
                }
                return;
            case R.id.seekbar_mzh:
                TextView textView3 = this.tv_mzhSize;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(progress - 12);
                sb3.append("");
                textView3.setText(sb3.toString());
                if (this.eqModel == 0) {
                    this.zo = progress;
                    FileUtils.savaIntData(KeyConfig.EQ_MIDDLE, progress);
                    Log.d("BenchiEQ", "save zo:" + this.zo);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_eqgud:
                setUpdateTwoLayout(2);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                return;
            case R.id.tv_eqjues:
                setUpdateTwoLayout(4);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                return;
            case R.id.tv_eqliux:
                setUpdateTwoLayout(1);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                return;
            case R.id.tv_eqwuq:
                setUpdateTwoLayout(5);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                return;
            case R.id.tv_eqyaog:
                setUpdateTwoLayout(3);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                return;
            case R.id.tv_eqyongh:
                setUpdateTwoLayout(0);
                this.tv_eqyongh.setTextColor(ContextCompat.getColor(this.context, R.color.text_red));
                this.tv_eqliux.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqgud.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqyaog.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqjues.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                this.tv_eqwuq.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
                return;
            default:
                return;
        }
    }
}
