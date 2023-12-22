package com.wits.ksw.settings.id7.layout_one;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

/* loaded from: classes11.dex */
public class SetVoiceLayout extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
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
    private int ytongh;

    public SetVoiceLayout(Context context) {
        super(context);
        this.meiti = 26;
        this.htongh = 28;
        this.ytongh = 26;
        this.daohan = 26;
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_set_voice, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
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
        ContentResolver contentResolver = getContext().getContentResolver();
        contentResolver.registerContentObserver(Settings.System.getUriFor(KeyConfig.ANDROID_MEDIA_VOL), true, new ContentObserver(new Handler()) { // from class: com.wits.ksw.settings.id7.layout_one.SetVoiceLayout.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                try {
                    int hz_mediaVolume = PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL);
                    SetVoiceLayout.this.seek_mtb.setProgress(hz_mediaVolume);
                } catch (RemoteException e2) {
                }
            }
        });
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
        this.seek_mtb.setOnSeekBarChangeListener(this);
        this.seek_tonghb.setOnSeekBarChangeListener(this);
        this.seek_yuancthb.setOnSeekBarChangeListener(this);
        this.seek_daohvoicb.setOnSeekBarChangeListener(this);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case C0899R.C0901id.seek_daohvoicb /* 2131297731 */:
                this.tv_daohvoicsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
                return;
            case C0899R.C0901id.seek_mtb /* 2131297734 */:
                this.tv_mtsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
                return;
            case C0899R.C0901id.seek_tonghb /* 2131297738 */:
                this.tv_tonghsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
                return;
            case C0899R.C0901id.seek_yuancthb /* 2131297739 */:
                this.tv_yuancthsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, progress);
                return;
            default:
                return;
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
