package com.wits.ksw.settings.als_id7_ui_set.layout_one;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class AlsID7UiSetSystemInfoLayout extends RelativeLayout implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Context context;
    private DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                try {
                    AlsID7UiSetSystemInfoLayout.this.tv_infoMcuv.setText(String.format(AlsID7UiSetSystemInfoLayout.this.getResources().getString(R.string.text_8), new Object[]{McuUtil.getMcuVersion()}));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else if (msg.what == 1) {
                Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                resetIntent.setPackage("android");
                resetIntent.setFlags(268435456);
                resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                AlsID7UiSetSystemInfoLayout.this.context.sendBroadcast(resetIntent);
            }
        }
    };
    private TextView tv_infoAppv;
    private TextView tv_infoMcuUp;
    /* access modifiers changed from: private */
    public TextView tv_infoMcuv;
    private TextView tv_infoSysRest;
    private TextView tv_infoSysUpDate;
    private TextView tv_infoSysv;
    private TextView tv_systemInfo;
    private TextView tv_systemInfo2;

    public AlsID7UiSetSystemInfoLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.als_id7_ui_layout_set_sys_info, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.dialogViews = new DialogViews(this.context);
        try {
            String mvformat = String.format(getResources().getString(R.string.text_8), new Object[]{McuUtil.getMcuVersion()});
            TextView textView = (TextView) view.findViewById(R.id.tv_infoMcuv);
            this.tv_infoMcuv = textView;
            textView.setText(mvformat);
        } catch (Exception e) {
            e.getStackTrace();
        }
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                AlsID7UiSetSystemInfoLayout.this.handler.sendEmptyMessage(0);
            }
        });
        String avformat = String.format(getResources().getString(R.string.text_9), new Object[]{getVersion()});
        TextView textView2 = (TextView) view.findViewById(R.id.tv_infoAppv);
        this.tv_infoAppv = textView2;
        textView2.setText(avformat);
        String format = String.format(getResources().getString(R.string.text_10), new Object[]{Build.VERSION.RELEASE});
        TextView textView3 = (TextView) view.findViewById(R.id.tv_infoSysv);
        this.tv_infoSysv = textView3;
        textView3.setText(UtilsInfo.getSettingsVer(this.context) + "-" + UtilsInfo.getIMEI());
        this.tv_infoSysv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UtilsInfo.showQRCode(AlsID7UiSetSystemInfoLayout.this.context);
            }
        });
        TextView textView4 = (TextView) view.findViewById(R.id.tv_infoMcuUp);
        this.tv_infoMcuUp = textView4;
        textView4.setOnClickListener(this);
        TextView textView5 = (TextView) view.findViewById(R.id.tv_infoSysRest);
        this.tv_infoSysRest = textView5;
        textView5.setOnClickListener(this);
        String allRAM = String.format(getResources().getString(R.string.text_14), new Object[]{UtilsInfo.getRAMSize(this.context)});
        TextView textView6 = (TextView) view.findViewById(R.id.tv_systemInfo2);
        this.tv_systemInfo2 = textView6;
        textView6.setText(allRAM);
        String allROM = String.format(getResources().getString(R.string.text_15), new Object[]{UtilsInfo.getROMSize()});
        TextView textView7 = (TextView) view.findViewById(R.id.tv_systemInfo);
        this.tv_systemInfo = textView7;
        textView7.setText(allROM);
        TextView textView8 = (TextView) view.findViewById(R.id.tv_infoSysUpDate);
        this.tv_infoSysUpDate = textView8;
        textView8.setOnClickListener(this);
    }

    private String getVersion() {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            return Build.DISPLAY;
        } catch (Exception e) {
            e.printStackTrace();
            return "找不到版本号";
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_infoMcuUp /*2131297875*/:
                this.dialogViews.updateMcu(this.context.getString(R.string.update_mcu_file));
                return;
            case R.id.tv_infoSysRest /*2131297878*/:
                this.dialogViews.isQuestView(this.context.getString(R.string.update_reset_all), this.handler);
                return;
            case R.id.tv_infoSysUpDate /*2131297879*/:
                new TxzMessage(2070, "system.ota.check", (Bundle) null).sendBroadCast(this.context);
                return;
            default:
                return;
        }
    }
}
