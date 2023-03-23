package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class TxzCaringModel extends FrameLayout {
    private static final String TAG = "TxzCaringModel";
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private int oil_value = 0;
    private SeekBar seek_oil;
    private SeekBar seek_speed;
    private SeekBar seek_temp;
    private int speed_value = 0;
    private int temp_value = 0;
    /* access modifiers changed from: private */
    public TextView tv_oil_value;
    /* access modifiers changed from: private */
    public TextView tv_speed_value;
    /* access modifiers changed from: private */
    public TextView tv_temp_value;
    private View view;

    public TxzCaringModel(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.factory_txz_caring_model, (ViewGroup) null);
        Log.d(TAG, " view " + this.view);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    private void initView() {
        this.seek_oil = (SeekBar) this.view.findViewById(R.id.seek_oil);
        this.tv_oil_value = (TextView) this.view.findViewById(R.id.tv_oil_value);
        this.seek_oil.setMax(10);
        this.seek_oil.setProgress(this.oil_value);
        this.tv_oil_value.setText(this.oil_value + "");
        this.seek_oil.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int process = seekBar.getProgress();
                TxzCaringModel.this.tv_oil_value.setText(process + "");
                Log.d(TxzCaringModel.TAG, "onProgressChanged: setTxzOil=" + process);
                FileUtils.savaIntData(KeyConfig.TXZ_OIL, process);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int process = seekBar.getProgress();
                TxzCaringModel.this.tv_oil_value.setText(process + "");
                Log.d(TxzCaringModel.TAG, "onStopTrackingTouch: setTxzOil=" + process);
                FileUtils.savaIntData(KeyConfig.TXZ_OIL, process);
            }
        });
        this.seek_speed = (SeekBar) this.view.findViewById(R.id.seek_speed);
        this.tv_speed_value = (TextView) this.view.findViewById(R.id.tv_speed_value);
        this.seek_speed.setMax(255);
        this.seek_speed.setProgress(this.speed_value);
        this.tv_speed_value.setText(this.speed_value + "");
        this.seek_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int process = seekBar.getProgress();
                TxzCaringModel.this.tv_speed_value.setText(process + "");
                Log.d(TxzCaringModel.TAG, "onProgressChanged: setTxzSpeed=" + process);
                FileUtils.savaIntData(KeyConfig.TXZ_SPEED, process);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int process = seekBar.getProgress();
                TxzCaringModel.this.tv_speed_value.setText(process + "");
                Log.d(TxzCaringModel.TAG, "onStopTrackingTouch: setTxzSpeed=" + process);
                FileUtils.savaIntData(KeyConfig.TXZ_SPEED, process);
            }
        });
        this.seek_temp = (SeekBar) this.view.findViewById(R.id.seek_temp);
        this.tv_temp_value = (TextView) this.view.findViewById(R.id.tv_temp_value);
        this.seek_temp.setMax(50);
        this.seek_temp.setMin(-50);
        this.seek_temp.setProgress(this.temp_value);
        this.tv_temp_value.setText(this.temp_value + "℃");
        this.seek_temp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int process = seekBar.getProgress();
                TxzCaringModel.this.tv_temp_value.setText(process + "℃");
                Log.d(TxzCaringModel.TAG, "onProgressChanged: setTxzSpeed=" + process);
                FileUtils.savaIntData(KeyConfig.TXZ_TEMP, process);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int process = seekBar.getProgress();
                TxzCaringModel.this.tv_temp_value.setText(process + "℃");
                Log.d(TxzCaringModel.TAG, "onStopTrackingTouch: setTxzSpeed=" + process);
                FileUtils.savaIntData(KeyConfig.TXZ_TEMP, process);
            }
        });
    }

    private void initData() {
        try {
            this.oil_value = PowerManagerApp.getSettingsInt(KeyConfig.TXZ_OIL);
            this.speed_value = PowerManagerApp.getSettingsInt(KeyConfig.TXZ_SPEED);
            this.temp_value = PowerManagerApp.getSettingsInt(KeyConfig.TXZ_TEMP);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
