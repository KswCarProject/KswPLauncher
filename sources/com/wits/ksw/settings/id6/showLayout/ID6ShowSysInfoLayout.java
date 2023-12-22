package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.ksw.settings.utlis_view.UtilsInfo;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes13.dex */
public class ID6ShowSysInfoLayout extends RelativeLayout {
    private Context context;
    Handler handler;
    private TextView tv_infoAppv;
    private TextView tv_infoMcuv;
    private TextView tv_infoSysv;

    public ID6ShowSysInfoLayout(Context context) {
        super(context);
        this.handler = new Handler() { // from class: com.wits.ksw.settings.id6.showLayout.ID6ShowSysInfoLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    try {
                        String mvformat = String.format(ID6ShowSysInfoLayout.this.getResources().getString(C0899R.string.text_8), McuUtil.getMcuVersion());
                        SpannableString ss1 = new SpannableString(mvformat);
                        ss1.setSpan(new RelativeSizeSpan(0.7f), ID6ShowSysInfoLayout.this.getResources().getString(C0899R.string.text_8).length() - 2, mvformat.length(), 17);
                        ID6ShowSysInfoLayout.this.tv_infoMcuv.setText(ss1);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            }
        };
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_sys_show_info, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        String svformat;
        try {
            String mvformat = String.format(getResources().getString(C0899R.string.text_8), McuUtil.getMcuVersion());
            SpannableString ss1 = new SpannableString(mvformat);
            ss1.setSpan(new RelativeSizeSpan(0.7f), getResources().getString(C0899R.string.text_8).length() - 2, mvformat.length(), 17);
            TextView textView = (TextView) view.findViewById(C0899R.C0901id.tv_infoMcuv);
            this.tv_infoMcuv = textView;
            textView.setText(ss1);
        } catch (Exception e) {
            e.getStackTrace();
        }
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() { // from class: com.wits.ksw.settings.id6.showLayout.ID6ShowSysInfoLayout.2
            @Override // com.wits.pms.IContentObserver
            public void onChange() throws RemoteException {
                ID6ShowSysInfoLayout.this.handler.sendEmptyMessage(0);
            }
        });
        String avformat = String.format(getResources().getString(C0899R.string.text_9), getVersion());
        TextView textView2 = (TextView) view.findViewById(C0899R.C0901id.tv_infoAppv);
        this.tv_infoAppv = textView2;
        textView2.setText(avformat);
        String targetVer = UtilsInfo.changeVersion();
        if (!targetVer.equals(Build.VERSION.RELEASE)) {
            svformat = String.format(getResources().getString(C0899R.string.text_10), targetVer);
        } else if (Build.VERSION.SDK_INT == 29) {
            svformat = String.format(getResources().getString(C0899R.string.text_10), "10.0");
        } else if (Build.VERSION.SDK_INT == 30) {
            svformat = String.format(getResources().getString(C0899R.string.text_10), "11.0");
        } else if (Build.VERSION.SDK_INT == 31 || Build.VERSION.SDK_INT == 32) {
            svformat = String.format(getResources().getString(C0899R.string.text_10), "12.0");
        } else if (Build.VERSION.SDK_INT == 33) {
            svformat = String.format(getResources().getString(C0899R.string.text_10), "13.0");
        } else {
            svformat = String.format(getResources().getString(C0899R.string.text_10), "9.0");
        }
        TextView textView3 = (TextView) view.findViewById(C0899R.C0901id.tv_infoSysv);
        this.tv_infoSysv = textView3;
        textView3.setText(svformat);
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
}
