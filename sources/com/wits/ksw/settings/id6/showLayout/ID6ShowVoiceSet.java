package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes13.dex */
public class ID6ShowVoiceSet extends RelativeLayout {
    private Context context;
    private int daohan;
    private int htongh;
    private int meiti;
    private SeekBar seek_daohvoicb;
    private SeekBar seek_mtb;
    private SeekBar seek_tonghb;
    private SeekBar seek_yuancthb;
    private TextView tv_daohvoicsize;
    private TextView tv_mtsize;
    private TextView tv_tonghsize;
    private TextView tv_yuancthsize;
    private View view;
    private int ytongh;

    public ID6ShowVoiceSet(Context context) {
        super(context);
        this.meiti = 26;
        this.htongh = 28;
        this.ytongh = 26;
        this.daohan = 26;
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_voice_shwo, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    private void initData() {
        try {
            this.meiti = PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL);
            this.htongh = PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_PHONE_VOL);
            this.ytongh = PowerManagerApp.getSettingsInt(KeyConfig.CAR_PHONE_VOL);
            this.daohan = PowerManagerApp.getSettingsInt(KeyConfig.CAR_NAVI_VOL);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void initView(View view) {
        this.seek_mtb = (SeekBar) view.findViewById(C0899R.C0901id.seek_mtb);
        this.tv_mtsize = (TextView) view.findViewById(C0899R.C0901id.tv_mtsize);
        this.seek_mtb.setMax(40);
        this.seek_mtb.setProgress(this.meiti);
        this.tv_mtsize.setText(this.meiti + "");
        this.seek_tonghb = (SeekBar) view.findViewById(C0899R.C0901id.seek_tonghb);
        this.tv_tonghsize = (TextView) view.findViewById(C0899R.C0901id.tv_tonghsize);
        this.seek_tonghb.setMax(40);
        this.seek_tonghb.setProgress(this.htongh);
        this.tv_tonghsize.setText(this.htongh + "");
        this.seek_yuancthb = (SeekBar) view.findViewById(C0899R.C0901id.seek_yuancthb);
        this.tv_yuancthsize = (TextView) view.findViewById(C0899R.C0901id.tv_yuancthsize);
        this.seek_yuancthb.setMax(40);
        this.seek_yuancthb.setProgress(this.ytongh);
        this.tv_yuancthsize.setText(this.ytongh + "");
        this.seek_daohvoicb = (SeekBar) view.findViewById(C0899R.C0901id.seek_daohvoicb);
        this.tv_daohvoicsize = (TextView) view.findViewById(C0899R.C0901id.tv_daohvoicsize);
        this.seek_daohvoicb.setMax(40);
        this.seek_daohvoicb.setProgress(this.daohan);
        this.tv_daohvoicsize.setText(this.daohan + "");
        this.seek_mtb.setEnabled(false);
        this.seek_tonghb.setEnabled(false);
        this.seek_yuancthb.setEnabled(false);
        this.seek_daohvoicb.setEnabled(false);
    }

    public void updateView() {
        initData();
        initView(this.view);
    }
}
