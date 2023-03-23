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
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ReverseExitTimeSelect extends FrameLayout {
    private static final String TAG = "ReverseExitTimeSelect";
    private FrameLayout.LayoutParams layoutParams;
    private Context m_con;
    private String mode = "0-0";
    /* access modifiers changed from: private */
    public RadioGroup reverse_exit_time_radiogroup;
    private SeekBar seek_exit;
    /* access modifiers changed from: private */
    public TextView tv_exit_value;
    private View view;

    public ReverseExitTimeSelect(Context context) {
        super(context);
        this.m_con = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.factory_reverse_exit_time, (ViewGroup) null);
        Log.d(TAG, " view " + this.view);
        this.layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView();
        this.view.setLayoutParams(this.layoutParams);
        addView(this.view);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0072, code lost:
        if (r3.equals(com.wits.ksw.settings.TxzMessage.TXZ_DISMISS) != false) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initView() {
        /*
            r8 = this;
            android.view.View r0 = r8.view
            r1 = 2131297859(0x7f090643, float:1.8213675E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r8.tv_exit_value = r0
            android.view.View r0 = r8.view
            r1 = 2131297599(0x7f09053f, float:1.8213147E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.RadioGroup r0 = (android.widget.RadioGroup) r0
            r8.reverse_exit_time_radiogroup = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "initView: mode="
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r8.mode
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ReverseExitTimeSelect"
            android.util.Log.d(r1, r0)
            r0 = 2
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r2 = r8.mode
            if (r2 == 0) goto L_0x0043
            java.lang.String r3 = "null"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0047
        L_0x0043:
            java.lang.String r2 = "0-0"
            r8.mode = r2
        L_0x0047:
            java.lang.String r2 = r8.mode     // Catch:{ Exception -> 0x0051 }
            java.lang.String r3 = "-"
            java.lang.String[] r2 = r2.split(r3)     // Catch:{ Exception -> 0x0051 }
            r0 = r2
            goto L_0x0055
        L_0x0051:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0055:
            r2 = 0
            r3 = r0[r2]
            r4 = -1
            int r5 = r3.hashCode()
            java.lang.String r6 = "0"
            r7 = 1
            switch(r5) {
                case 48: goto L_0x006e;
                case 49: goto L_0x0064;
                default: goto L_0x0063;
            }
        L_0x0063:
            goto L_0x0075
        L_0x0064:
            java.lang.String r2 = "1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0063
            r2 = r7
            goto L_0x0076
        L_0x006e:
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x0063
            goto L_0x0076
        L_0x0075:
            r2 = r4
        L_0x0076:
            switch(r2) {
                case 0: goto L_0x0083;
                case 1: goto L_0x007a;
                default: goto L_0x0079;
            }
        L_0x0079:
            goto L_0x008c
        L_0x007a:
            android.widget.RadioGroup r2 = r8.reverse_exit_time_radiogroup
            r3 = 2131296637(0x7f09017d, float:1.8211196E38)
            r2.check(r3)
            goto L_0x008c
        L_0x0083:
            android.widget.RadioGroup r2 = r8.reverse_exit_time_radiogroup
            r3 = 2131297598(0x7f09053e, float:1.8213145E38)
            r2.check(r3)
        L_0x008c:
            android.view.View r2 = r8.view
            r3 = 2131297685(0x7f090595, float:1.8213322E38)
            android.view.View r2 = r2.findViewById(r3)
            android.widget.SeekBar r2 = (android.widget.SeekBar) r2
            r8.seek_exit = r2
            r3 = 10
            r2.setMax(r3)
            r2 = r0[r7]
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x00ae
            android.widget.TextView r2 = r8.tv_exit_value
            java.lang.String r3 = "10"
            r2.setText(r3)
            goto L_0x00b5
        L_0x00ae:
            android.widget.TextView r2 = r8.tv_exit_value
            r3 = r0[r7]
            r2.setText(r3)
        L_0x00b5:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "initView: reverseTime[1]="
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r0[r7]
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
            android.widget.SeekBar r1 = r8.seek_exit
            r2 = r0[r7]
            int r2 = java.lang.Integer.parseInt(r2)
            r1.setProgress(r2)
            android.widget.SeekBar r1 = r8.seek_exit
            com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect$1 r2 = new com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect$1
            r2.<init>()
            r1.setOnSeekBarChangeListener(r2)
            android.widget.RadioGroup r1 = r8.reverse_exit_time_radiogroup
            com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect$2 r2 = new com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect$2
            r2.<init>()
            r1.setOnCheckedChangeListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.settings.id7.layout_factory.ReverseExitTimeSelect.initView():void");
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
