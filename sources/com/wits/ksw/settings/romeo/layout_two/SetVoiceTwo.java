package com.wits.ksw.settings.romeo.layout_two;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class SetVoiceTwo extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private String TAG = "SetVoiceTwo";
    private Context context;
    private int daohan = 26;
    private int htongh = 28;
    private ImageView img_twoDefaul;
    private LinearLayout ll_bv;
    private LinearLayout ll_ov;
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

    public SetVoiceTwo(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.romeo_layout_set_voice_two, (ViewGroup) null);
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
                    SetVoiceTwo.this.seek_mtb.setProgress(PowerManagerApp.getSettingsInt(KeyConfig.ANDROID_MEDIA_VOL));
                } catch (RemoteException e) {
                }
            }
        });
    }

    private void initView(View view) {
        this.ll_bv = (LinearLayout) view.findViewById(R.id.ll_bv);
        this.ll_ov = (LinearLayout) view.findViewById(R.id.ll_ov);
        this.seek_mtb = (SeekBar) view.findViewById(R.id.seek_mtb);
        this.tv_mtsize = (TextView) view.findViewById(R.id.tv_mtsize);
        this.img_twoDefaul = (ImageView) view.findViewById(R.id.img_twoDefaul);
        this.seek_mtb.setMax(40);
        this.seek_mtb.setProgress(this.meiti);
        this.tv_mtsize.setText(this.meiti + "");
        this.seek_tonghb = (SeekBar) view.findViewById(R.id.seek_tonghb);
        this.tv_tonghsize = (TextView) view.findViewById(R.id.tv_tonghsize);
        this.seek_tonghb.setMax(40);
        this.seek_tonghb.setProgress(this.htongh);
        this.tv_tonghsize.setText(this.htongh + "");
        this.seek_yuancthb = (SeekBar) view.findViewById(R.id.seek_yuancthb);
        this.tv_yuancthsize = (TextView) view.findViewById(R.id.tv_yuancthsize);
        this.seek_yuancthb.setMax(40);
        this.seek_yuancthb.setProgress(this.ytongh);
        this.tv_yuancthsize.setText(this.ytongh + "");
        this.seek_daohvoicb = (SeekBar) view.findViewById(R.id.seek_daohvoicb);
        this.tv_daohvoicsize = (TextView) view.findViewById(R.id.tv_daohvoicsize);
        this.seek_daohvoicb.setMax(40);
        this.seek_daohvoicb.setProgress(this.daohan);
        this.tv_daohvoicsize.setText(this.daohan + "");
        this.seek_mtb.setOnSeekBarChangeListener(this);
        this.seek_tonghb.setOnSeekBarChangeListener(this);
        this.seek_yuancthb.setOnSeekBarChangeListener(this);
        this.seek_daohvoicb.setOnSeekBarChangeListener(this);
    }

    public void showLayout(int index) {
        Log.d(this.TAG, "showLayout " + index);
        switch (index) {
            case 0:
                this.ll_bv.setVisibility(0);
                this.ll_ov.setVisibility(8);
                this.img_twoDefaul.setVisibility(8);
                return;
            case 1:
                this.ll_bv.setVisibility(8);
                this.ll_ov.setVisibility(0);
                this.img_twoDefaul.setVisibility(8);
                return;
            case 2:
                this.ll_bv.setVisibility(8);
                this.ll_ov.setVisibility(8);
                this.img_twoDefaul.setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_daohvoicb:
                this.tv_daohvoicsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_NAVI_VOL, progress);
                return;
            case R.id.seek_mtb:
                this.tv_mtsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.ANDROID_MEDIA_VOL, progress);
                return;
            case R.id.seek_tonghb:
                this.tv_tonghsize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.ANDROID_PHONE_VOL, progress);
                return;
            case R.id.seek_yuancthb:
                this.tv_yuancthsize.setText(progress + "");
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
