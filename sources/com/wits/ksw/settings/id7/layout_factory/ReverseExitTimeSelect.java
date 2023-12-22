package com.wits.ksw.settings.id7.layout_factory;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes17.dex */
public class ReverseExitTimeSelect extends FrameLayout {
    private static final String TAG = "ReverseExitTimeSelect";
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private String mode;
    private RadioGroup reverse_exit_time_radiogroup;
    private SeekBar seek_exit;
    private TextView tv_exit_value;
    private View view;

    public ReverseExitTimeSelect(Context context) {
        super(context);
        this.mode = "0-0";
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.factory_reverse_exit_time, (ViewGroup) null);
        Log.d(TAG, " view " + this.view);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0072, code lost:
        if (r3.equals(com.wits.ksw.settings.TxzMessage.TXZ_DISMISS) != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void initView() {
        this.tv_exit_value = (TextView) this.view.findViewById(C0899R.C0901id.tv_exit_value);
        this.reverse_exit_time_radiogroup = (RadioGroup) this.view.findViewById(C0899R.C0901id.reverse_exit_time_radiogroup);
        Log.d(TAG, "initView: mode=" + this.mode);
        String[] reverseTime = new String[2];
        String str = this.mode;
        if (str == null || "null".equals(str)) {
            this.mode = "0-0";
        }
        try {
            reverseTime = this.mode.split("-");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Exception e2 = null;
        String str2 = reverseTime[0];
        switch (str2.hashCode()) {
            case 48:
                break;
            case 49:
                if (str2.equals(TxzMessage.TXZ_SHOW)) {
                    e2 = 1;
                    break;
                }
                e2 = -1;
                break;
            default:
                e2 = -1;
                break;
        }
        switch (e2) {
            case null:
                this.reverse_exit_time_radiogroup.check(C0899R.C0901id.reverse_exit_time_agreement);
                break;
            case 1:
                this.reverse_exit_time_radiogroup.check(C0899R.C0901id.boot_record_mode_custom);
                break;
        }
        SeekBar seekBar = (SeekBar) this.view.findViewById(C0899R.C0901id.seek_exit);
        this.seek_exit = seekBar;
        seekBar.setMax(10);
        if (TxzMessage.TXZ_DISMISS.equals(reverseTime[1])) {
            this.tv_exit_value.setText("10");
        } else {
            this.tv_exit_value.setText(reverseTime[1]);
        }
        Log.d(TAG, "initView: reverseTime[1]=" + reverseTime[1]);
        this.seek_exit.setProgress(Integer.parseInt(reverseTime[1]));
        this.seek_exit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i, boolean b) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                ReverseExitTimeSelect.this.reverse_exit_time_radiogroup.check(C0899R.C0901id.boot_record_mode_custom);
                int process = seekBar2.getProgress();
                ReverseExitTimeSelect.this.tv_exit_value.setText(process + "");
                Log.d(ReverseExitTimeSelect.TAG, "onStopTrackingTouch: setMic=" + process);
                FileUtils.savaStringData(KeyConfig.REVERSE_TIME, "1-" + process);
            }
        });
        this.reverse_exit_time_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case C0899R.C0901id.reverse_exit_time_agreement /* 2131297645 */:
                        FileUtils.savaStringData(KeyConfig.REVERSE_TIME, "0-0");
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void initData() {
        try {
            this.mode = PowerManagerApp.getSettingsString(KeyConfig.REVERSE_TIME);
            Log.d(TAG, "initData: mode=" + this.mode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
