package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.TxzMessage;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes10.dex */
public class LexusSetSystemInfoLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private DialogViews dialogViews;
    Handler handler;
    private TextView tv_infoAppv;
    private TextView tv_infoMcuUp;
    private TextView tv_infoMcuv;
    private TextView tv_infoSysRest;
    private TextView tv_infoSysUpDate;
    private TextView tv_infoSysv;
    private TextView tv_systemInfo;
    private TextView tv_systemInfo2;

    public LexusSetSystemInfoLayout(Context context) {
        super(context);
        this.handler = new Handler() { // from class: com.wits.ksw.settings.lexus.layout_one.LexusSetSystemInfoLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    try {
                        String mvformat = String.format(LexusSetSystemInfoLayout.this.getResources().getString(C0899R.string.text_8), McuUtil.getMcuVersion());
                        LexusSetSystemInfoLayout.this.tv_infoMcuv.setText(mvformat);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                } else if (msg.what == 1) {
                    Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                    resetIntent.setPackage("android");
                    resetIntent.setFlags(268435456);
                    resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                    LexusSetSystemInfoLayout.this.context.sendBroadcast(resetIntent);
                }
            }
        };
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.lexus_layout_set_sys_info, (ViewGroup) null);
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
            String mvformat = String.format(getResources().getString(C0899R.string.text_8), McuUtil.getMcuVersion());
            TextView textView = (TextView) view.findViewById(C0899R.C0901id.tv_infoMcuv);
            this.tv_infoMcuv = textView;
            textView.setText(mvformat);
        } catch (Exception e) {
            e.getStackTrace();
        }
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() { // from class: com.wits.ksw.settings.lexus.layout_one.LexusSetSystemInfoLayout.2
            @Override // com.wits.pms.IContentObserver
            public void onChange() throws RemoteException {
                LexusSetSystemInfoLayout.this.handler.sendEmptyMessage(0);
            }
        });
        String avformat = String.format(getResources().getString(C0899R.string.text_9), getVersion());
        TextView textView2 = (TextView) view.findViewById(C0899R.C0901id.tv_infoAppv);
        this.tv_infoAppv = textView2;
        textView2.setText(avformat);
        String.format(getResources().getString(C0899R.string.text_10), Build.VERSION.RELEASE);
        this.tv_infoSysv = (TextView) view.findViewById(C0899R.C0901id.tv_infoSysv);
        Log.d("SetSystemInfoLayout", " ver " + UtilsInfo.getBaseband_Ver());
        this.tv_infoSysv.setText(UtilsInfo.getSettingsVer(this.context) + "-" + UtilsInfo.getIMEI());
        this.tv_infoSysv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.lexus.layout_one.LexusSetSystemInfoLayout.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                UtilsInfo.showQRCode(LexusSetSystemInfoLayout.this.context);
            }
        });
        TextView textView3 = (TextView) view.findViewById(C0899R.C0901id.tv_infoMcuUp);
        this.tv_infoMcuUp = textView3;
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) view.findViewById(C0899R.C0901id.tv_infoSysRest);
        this.tv_infoSysRest = textView4;
        textView4.setOnClickListener(this);
        String allRAM = String.format(getResources().getString(C0899R.string.text_14), UtilsInfo.getRAMSize(this.context));
        TextView textView5 = (TextView) view.findViewById(C0899R.C0901id.tv_systemInfo2);
        this.tv_systemInfo2 = textView5;
        textView5.setText(allRAM);
        String allROM = String.format(getResources().getString(C0899R.string.text_15), UtilsInfo.getROMSize());
        TextView textView6 = (TextView) view.findViewById(C0899R.C0901id.tv_systemInfo);
        this.tv_systemInfo = textView6;
        textView6.setText(allROM);
        TextView textView7 = (TextView) view.findViewById(C0899R.C0901id.tv_infoSysUpDate);
        this.tv_infoSysUpDate = textView7;
        textView7.setOnClickListener(this);
    }

    private String getVersion() {
        try {
            PackageManager manager = this.context.getPackageManager();
            manager.getPackageInfo(this.context.getPackageName(), 0);
            String version = "Witstek-" + Build.DISPLAY;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "\u627e\u4e0d\u5230\u7248\u672c\u53f7";
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case C0899R.C0901id.tv_infoMcuUp /* 2131297927 */:
                this.dialogViews.updateMcu(this.context.getString(C0899R.string.update_mcu_file));
                return;
            case C0899R.C0901id.tv_infoMcuv /* 2131297928 */:
            case C0899R.C0901id.tv_infoRam /* 2131297929 */:
            default:
                return;
            case C0899R.C0901id.tv_infoSysRest /* 2131297930 */:
                this.dialogViews.isQuestView(this.context.getString(C0899R.string.update_reset_all), this.handler);
                return;
            case C0899R.C0901id.tv_infoSysUpDate /* 2131297931 */:
                TxzMessage txzMessage = new TxzMessage(2070, "system.ota.check", null);
                txzMessage.sendBroadCast(this.context);
                return;
        }
    }
}
