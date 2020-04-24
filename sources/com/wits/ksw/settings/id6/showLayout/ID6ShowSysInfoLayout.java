package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.content.pm.PackageInfo;
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
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.McuUtil;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class ID6ShowSysInfoLayout extends RelativeLayout {
    private Context context;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                try {
                    String mvformat = String.format(ID6ShowSysInfoLayout.this.getResources().getString(R.string.text_8), new Object[]{McuUtil.getMcuVersion()});
                    SpannableString ss1 = new SpannableString(mvformat);
                    ss1.setSpan(new RelativeSizeSpan(0.7f), ID6ShowSysInfoLayout.this.getResources().getString(R.string.text_8).length() - 2, mvformat.length(), 17);
                    ID6ShowSysInfoLayout.this.tv_infoMcuv.setText(ss1);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    };
    private TextView tv_infoAppv;
    /* access modifiers changed from: private */
    public TextView tv_infoMcuv;
    private TextView tv_infoSysv;

    public ID6ShowSysInfoLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_sys_show_info, (ViewGroup) null);
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
            String mvformat = String.format(getResources().getString(R.string.text_8), new Object[]{McuUtil.getMcuVersion()});
            SpannableString ss1 = new SpannableString(mvformat);
            ss1.setSpan(new RelativeSizeSpan(0.7f), getResources().getString(R.string.text_8).length() - 2, mvformat.length(), 17);
            this.tv_infoMcuv = (TextView) view.findViewById(R.id.tv_infoMcuv);
            this.tv_infoMcuv.setText(ss1);
        } catch (Exception e) {
            e.getStackTrace();
        }
        PowerManagerApp.registerIContentObserver("mcuVerison", new IContentObserver.Stub() {
            public void onChange() throws RemoteException {
                ID6ShowSysInfoLayout.this.handler.sendEmptyMessage(0);
            }
        });
        String avformat = String.format(getResources().getString(R.string.text_9), new Object[]{getVersion()});
        this.tv_infoAppv = (TextView) view.findViewById(R.id.tv_infoAppv);
        this.tv_infoAppv.setText(avformat);
        if (Build.VERSION.SDK_INT > 28) {
            svformat = String.format(getResources().getString(R.string.text_10), new Object[]{"10.0"});
        } else {
            svformat = String.format(getResources().getString(R.string.text_10), new Object[]{"9.0"});
        }
        this.tv_infoSysv = (TextView) view.findViewById(R.id.tv_infoSysv);
        this.tv_infoSysv.setText(svformat);
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
}
