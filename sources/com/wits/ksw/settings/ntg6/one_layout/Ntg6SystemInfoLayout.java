package com.wits.ksw.settings.ntg6.one_layout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.DialogViews;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class Ntg6SystemInfoLayout extends RelativeLayout implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Context context;
    private DialogViews dialogViews;
    /* access modifiers changed from: private */
    public Handler fhandler;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                try {
                    String mvformat = String.format(Ntg6SystemInfoLayout.this.getResources().getString(R.string.text_8), new Object[]{PowerManagerApp.getStatusString("mcuVerison")});
                    SpannableString ss1 = new SpannableString(mvformat);
                    ss1.setSpan(new RelativeSizeSpan(0.7f), Ntg6SystemInfoLayout.this.getResources().getString(R.string.text_8).length() - 2, mvformat.length(), 17);
                    Ntg6SystemInfoLayout.this.tv_infoMcuv.setText(ss1);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else if (msg.what == 1) {
                Intent resetIntent = new Intent("android.intent.action.FACTORY_RESET");
                resetIntent.setPackage("android");
                resetIntent.setFlags(268435456);
                resetIntent.putExtra("android.intent.extra.REASON", "ResetConfirmFragment");
                Ntg6SystemInfoLayout.this.context.sendBroadcast(resetIntent);
            }
        }
    };
    private ImageView img_TwoBack;
    private TextView tv_infoAppv;
    private TextView tv_infoMcuUp;
    /* access modifiers changed from: private */
    public TextView tv_infoMcuv;
    private TextView tv_infoSysRest;
    private TextView tv_infoSysv;

    public Ntg6SystemInfoLayout(Context context2, Handler handler2) {
        super(context2);
        this.context = context2;
        this.fhandler = handler2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_ntg6_sys_info, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    public void getFocus() {
        this.img_TwoBack.requestFocus();
    }

    private void initView(View view) {
        String pingtai;
        this.dialogViews = new DialogViews(this.context);
        this.img_TwoBack = (ImageView) view.findViewById(R.id.img_TwoBack);
        try {
            String mvformat = String.format(getResources().getString(R.string.text_8), new Object[]{PowerManagerApp.getStatusString("mcuVerison")});
            SpannableString ss1 = new SpannableString(mvformat);
            ss1.setSpan(new RelativeSizeSpan(0.7f), getResources().getString(R.string.text_8).length() - 2, mvformat.length(), 17);
            this.tv_infoMcuv = (TextView) view.findViewById(R.id.tv_infoMcuv);
            this.tv_infoMcuv.setText(ss1);
        } catch (Exception e) {
            e.getStackTrace();
        }
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                Ntg6SystemInfoLayout.this.handler.sendEmptyMessage(0);
            }
        });
        String avformat = String.format(getResources().getString(R.string.text_9), new Object[]{getVersion()});
        SpannableString ss2 = new SpannableString(avformat);
        ss2.setSpan(new RelativeSizeSpan(0.7f), getResources().getString(R.string.text_9).length() - 2, avformat.length(), 17);
        this.tv_infoAppv = (TextView) view.findViewById(R.id.tv_infoAppv);
        this.tv_infoAppv.setText(ss2);
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
                pingtai = "M501";
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
                TextView textView2 = this.tv_infoSysv;
                textView2.setText(svformat + "-" + pingtai + "NA");
            } else {
                TextView textView3 = this.tv_infoSysv;
                textView3.setText(svformat + "-" + pingtai + "EA");
            }
        } else {
            this.tv_infoSysv.setText(svformat);
        }
        this.tv_infoMcuUp = (TextView) view.findViewById(R.id.tv_infoMcuUp);
        this.tv_infoMcuUp.setOnClickListener(this);
        this.tv_infoSysRest = (TextView) view.findViewById(R.id.tv_infoSysRest);
        this.tv_infoSysRest.setOnClickListener(this);
        this.img_TwoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ntg6SystemInfoLayout.this.fhandler.sendEmptyMessage(1);
            }
        });
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
