package com.wits.ksw.settings.ntg6.one_layout;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class Ntg6VoiceLayout extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private Context context;
    private int daohan = 26;
    /* access modifiers changed from: private */
    public Handler handler;
    private int htongh = 28;
    private ImageView img_TwoBack;
    private int meiti = 26;
    private SeekBar seek_daohvoicb;
    /* access modifiers changed from: private */
    public SeekBar seek_mtb;
    private SeekBar seek_tonghb;
    private SeekBar seek_yuancthb;
    private TextView tv_daohvoicsize;
    private TextView tv_mtsize;
    private TextView tv_tonghsize;
    private TextView tv_yuancthsize;
    private int ytongh = 26;

    public Ntg6VoiceLayout(Context context2, Handler handler2) {
        super(context2);
        this.handler = handler2;
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_ntg6_voice, (ViewGroup) null);
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
        getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.ANDROID_MEDIA_VOL), true, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange) {
                try {
                    Ntg6VoiceLayout.this.seek_mtb.setProgress(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL));
                } catch (RemoteException e) {
                }
            }
        });
    }

    public void getFocus() {
        this.img_TwoBack.requestFocus();
    }

    private void initView(View view) {
        this.img_TwoBack = (ImageView) view.findViewById(R.id.img_TwoBack);
        this.seek_mtb = (SeekBar) view.findViewById(R.id.seek_mtb);
        this.tv_mtsize = (TextView) view.findViewById(R.id.tv_mtsize);
        this.seek_mtb.setMax(40);
        this.seek_mtb.setProgress(this.meiti);
        TextView textView = this.tv_mtsize;
        textView.setText(this.meiti + "");
        this.seek_tonghb = (SeekBar) view.findViewById(R.id.seek_tonghb);
        this.tv_tonghsize = (TextView) view.findViewById(R.id.tv_tonghsize);
        this.seek_tonghb.setMax(40);
        this.seek_tonghb.setProgress(this.htongh);
        TextView textView2 = this.tv_tonghsize;
        textView2.setText(this.htongh + "");
        this.seek_yuancthb = (SeekBar) view.findViewById(R.id.seek_yuancthb);
        this.tv_yuancthsize = (TextView) view.findViewById(R.id.tv_yuancthsize);
        this.seek_yuancthb.setMax(40);
        this.seek_yuancthb.setProgress(this.ytongh);
        TextView textView3 = this.tv_yuancthsize;
        textView3.setText(this.ytongh + "");
        this.seek_daohvoicb = (SeekBar) view.findViewById(R.id.seek_daohvoicb);
        this.tv_daohvoicsize = (TextView) view.findViewById(R.id.tv_daohvoicsize);
        this.seek_daohvoicb.setMax(40);
        this.seek_daohvoicb.setProgress(this.daohan);
        TextView textView4 = this.tv_daohvoicsize;
        textView4.setText(this.daohan + "");
        this.seek_mtb.setOnSeekBarChangeListener(this);
        this.seek_tonghb.setOnSeekBarChangeListener(this);
        this.seek_yuancthb.setOnSeekBarChangeListener(this);
        this.seek_daohvoicb.setOnSeekBarChangeListener(this);
        this.img_TwoBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Ntg6VoiceLayout.this.handler.sendEmptyMessage(1);
            }
        });
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_daohvoicb:
                TextView textView = this.tv_daohvoicsize;
                textView.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
                return;
            case R.id.seek_mtb:
                TextView textView2 = this.tv_mtsize;
                textView2.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
                return;
            case R.id.seek_tonghb:
                TextView textView3 = this.tv_tonghsize;
                textView3.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
                return;
            case R.id.seek_yuancthb:
                TextView textView4 = this.tv_yuancthsize;
                textView4.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_PHONE_VOL, progress);
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
