package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes17.dex */
public class MicConfig extends FrameLayout {
    private static final String TAG = "MicConfig";
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private int mic_value;
    private SeekBar seek_mic;
    private TextView tv_mic_value;
    private View view;

    public MicConfig(Context context) {
        super(context);
        this.mic_value = 0;
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.factory_mic, (ViewGroup) null);
        Log.d(TAG, " view " + this.view);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initView() {
        this.seek_mic = (SeekBar) this.view.findViewById(C0899R.C0901id.seek_mic);
        this.tv_mic_value = (TextView) this.view.findViewById(C0899R.C0901id.tv_mic_value);
        if (Build.VERSION.RELEASE.contains("10")) {
            this.seek_mic.setMax(8);
        } else {
            this.seek_mic.setMax(20);
        }
        this.seek_mic.setProgress(this.mic_value);
        this.tv_mic_value.setText(this.mic_value + "");
        this.seek_mic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.id7.layout_factory.MicConfig.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int process = seekBar.getProgress();
                MicConfig.this.tv_mic_value.setText(process + "");
                Log.d(MicConfig.TAG, "onProgressChanged: setMic=" + process);
                if (Build.VERSION.RELEASE.contains("10")) {
                    FileUtils.savaIntData(KeyConfig.MIC_GAIN_M501, process);
                } else {
                    FileUtils.savaIntData(KeyConfig.MIC_GAIN_M600, process);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                int process = seekBar.getProgress();
                MicConfig.this.tv_mic_value.setText(process + "");
                Log.d(MicConfig.TAG, "onStopTrackingTouch: setMic=" + process);
                if (Build.VERSION.RELEASE.contains("10")) {
                    FileUtils.savaIntData(KeyConfig.MIC_GAIN_M501, process);
                } else {
                    FileUtils.savaIntData(KeyConfig.MIC_GAIN_M600, process);
                }
            }
        });
    }

    private void initData() {
        try {
            if (Build.VERSION.RELEASE.contains("10")) {
                this.mic_value = PowerManagerApp.getSettingsInt(KeyConfig.MIC_GAIN_M501);
            } else {
                this.mic_value = PowerManagerApp.getSettingsInt(KeyConfig.MIC_GAIN_M600);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
