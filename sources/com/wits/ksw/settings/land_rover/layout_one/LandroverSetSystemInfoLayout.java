package com.wits.ksw.settings.land_rover.layout_one;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class LandroverSetSystemInfoLayout extends RelativeLayout implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Context context;
    private DialogViews dialogViews;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                try {
                    LandroverSetSystemInfoLayout.this.tv_infoMcuv.setText(String.format(LandroverSetSystemInfoLayout.this.getResources().getString(R.string.text_8), new Object[]{McuUtil.getMcuVersion()}));
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else if (msg.what == 1) {
                Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                resetIntent.setPackage("android");
                resetIntent.setFlags(268435456);
                resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                LandroverSetSystemInfoLayout.this.context.sendBroadcast(resetIntent);
            }
        }
    };
    private TextView tv_infoAppv;
    private TextView tv_infoMcuUp;
    /* access modifiers changed from: private */
    public TextView tv_infoMcuv;
    private TextView tv_infoSysRest;
    private TextView tv_infoSysv;
    private TextView tv_systemInfo;
    private TextView tv_systemInfo2;

    public LandroverSetSystemInfoLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.land_rover_layout_set_sys_info, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        String pingtai;
        this.dialogViews = new DialogViews(this.context);
        try {
            String mvformat = String.format(getResources().getString(R.string.text_8), new Object[]{McuUtil.getMcuVersion()});
            this.tv_infoMcuv = (TextView) view.findViewById(R.id.tv_infoMcuv);
            this.tv_infoMcuv.setText(mvformat);
        } catch (Exception e) {
            e.getStackTrace();
        }
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                LandroverSetSystemInfoLayout.this.handler.sendEmptyMessage(0);
            }
        });
        String avformat = String.format(getResources().getString(R.string.text_9), new Object[]{getVersion()});
        this.tv_infoAppv = (TextView) view.findViewById(R.id.tv_infoAppv);
        this.tv_infoAppv.setText(avformat);
        String svformat = String.format(getResources().getString(R.string.text_10), new Object[]{Build.VERSION.RELEASE});
        this.tv_infoSysv = (TextView) view.findViewById(R.id.tv_infoSysv);
        if (!TextUtils.isEmpty(UtilsInfo.getBaseband_Ver())) {
            String substring = UtilsInfo.getBaseband_Ver().substring(0, 4);
            int index_NA = UtilsInfo.getBaseband_Ver().indexOf("NA");
            int index_platform_M506 = UtilsInfo.getBaseband_Ver().indexOf("M506");
            int index_platform_M501 = UtilsInfo.getBaseband_Ver().indexOf("M501");
            int index_platform_8953 = UtilsInfo.getBaseband_Ver().indexOf("8953");
            int index_platform_8937 = UtilsInfo.getBaseband_Ver().indexOf("8937");
            if (index_platform_M506 > -1) {
                pingtai = "M506";
            } else if (index_platform_M501 > -1) {
                pingtai = "8953";
            } else if (index_platform_8953 > -1) {
                pingtai = "8953";
            } else if (index_platform_8937 > -1) {
                pingtai = "8937";
            } else {
                pingtai = "8953";
            }
            TextView textView = this.tv_infoSysv;
            textView.setText(svformat + "-" + pingtai);
            if (index_NA > -1) {
                if (index_platform_M501 > -1) {
                    TextView textView2 = this.tv_infoSysv;
                    textView2.setText(svformat + "-" + pingtai + "NA-1");
                } else {
                    TextView textView3 = this.tv_infoSysv;
                    textView3.setText(svformat + "-" + pingtai + "NA");
                }
            } else if (index_platform_M501 > -1) {
                TextView textView4 = this.tv_infoSysv;
                textView4.setText(svformat + "-" + pingtai + "EA-1");
            } else {
                TextView textView5 = this.tv_infoSysv;
                textView5.setText(svformat + "-" + pingtai + "EA");
            }
        } else {
            this.tv_infoSysv.setText(svformat);
        }
        this.tv_infoMcuUp = (TextView) view.findViewById(R.id.tv_infoMcuUp);
        this.tv_infoMcuUp.setOnClickListener(this);
        this.tv_infoSysRest = (TextView) view.findViewById(R.id.tv_infoSysRest);
        this.tv_infoSysRest.setOnClickListener(this);
        String allRAM = String.format(getResources().getString(R.string.text_14), new Object[]{UtilsInfo.getRAMSize(this.context)});
        this.tv_systemInfo2 = (TextView) view.findViewById(R.id.tv_systemInfo2);
        this.tv_systemInfo2.setText(allRAM);
        String allROM = String.format(getResources().getString(R.string.text_15), new Object[]{UtilsInfo.getROMSize()});
        this.tv_systemInfo = (TextView) view.findViewById(R.id.tv_systemInfo);
        this.tv_systemInfo.setText(allROM);
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
        int id = v.getId();
        if (id == R.id.tv_infoMcuUp) {
            this.dialogViews.updateMcu(this.context.getString(R.string.update_mcu_file));
        } else if (id == R.id.tv_infoSysRest) {
            this.dialogViews.isQuestView(this.context.getString(R.string.update_reset_all), this.handler);
        }
    }
}
