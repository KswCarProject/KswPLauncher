package com.wits.ksw.settings.id6.oneLayout;

import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.NumberFormat;

public class ID6SetSystemLayout extends RelativeLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private int beiguangValue;
    private CheckBox cbox_sysDcgj;
    private CheckBox cbox_sysDcjy;
    private CheckBox cbox_sysDcld;
    private CheckBox cbox_sysHjs;
    private CheckBox cbox_sysXcjz;
    private int cheVideo = 0;
    /* access modifiers changed from: private */
    public Context context;
    private int dcgj = 0;
    private int dcjy = 0;
    private int dcld = 0;
    private int housi = 0;
    private TextView id6SysTempUnit;
    /* access modifiers changed from: private */
    public Handler mBackgroundHandler;
    private BrightnessObserver mBrightnessObserver;
    /* access modifiers changed from: private */
    public int mMaxBrightness;
    /* access modifiers changed from: private */
    public int mMinBrightness;
    /* access modifiers changed from: private */
    public final Runnable mUpdateSliderRunnable = new Runnable() {
        public void run() {
            int brightness = Settings.System.getInt(ID6SetSystemLayout.this.context.getContentResolver(), "screen_brightness", 255);
            ID6SetSystemLayout.this.setProgressText(brightness);
            ID6SetSystemLayout.this.setProgress(brightness);
        }
    };
    private int nbauxsw = 0;
    private SeekBar seekbar_brightness;
    private TextView tv_beigSize;
    private TextView tv_sysCaux;
    private TextView tv_sysDcsxt;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public ID6SetSystemLayout(Context context2) {
        super(context2);
        this.context = context2;
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_id6_set_system, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
        this.mBackgroundHandler = new Handler(Looper.getMainLooper());
        this.mBrightnessObserver = new BrightnessObserver(new Handler());
        this.mBrightnessObserver.startObserving();
    }

    private void initData() {
        try {
            this.housi = PowerManagerApp.getSettingsInt(KeyConfig.HOU_SHI_SX);
            this.cheVideo = PowerManagerApp.getSettingsInt(KeyConfig.XING_CHE_JZSP);
            this.dcgj = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_GJ);
            this.dcld = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_LD);
            this.dcjy = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_JY);
            this.nbauxsw = PowerManagerApp.getSettingsInt(KeyConfig.NBT_AUX_SW);
        } catch (Exception e) {
            e.getStackTrace();
        }
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        this.mMaxBrightness = getMaximumScreenBrightnessSetting();
        this.beiguangValue = Settings.System.getInt(this.context.getContentResolver(), "screen_brightness", 255);
    }

    private void initView(View view) {
        this.id6SysTempUnit = (TextView) view.findViewById(R.id.id6_sysTempUnit);
        this.tv_sysDcsxt = (TextView) view.findViewById(R.id.tv_sysDcsxt);
        this.cbox_sysHjs = (CheckBox) view.findViewById(R.id.cbox_sysHjs);
        this.cbox_sysXcjz = (CheckBox) view.findViewById(R.id.cbox_sysXcjz);
        this.cbox_sysDcgj = (CheckBox) view.findViewById(R.id.cbox_sysDcgj);
        this.cbox_sysDcld = (CheckBox) view.findViewById(R.id.cbox_sysDcld);
        this.tv_sysCaux = (TextView) view.findViewById(R.id.tv_sysCaux);
        this.cbox_sysDcjy = (CheckBox) view.findViewById(R.id.cbox_sysDcjy);
        boolean z = true;
        this.cbox_sysHjs.setChecked(this.housi != 0);
        this.cbox_sysXcjz.setChecked(this.cheVideo != 0);
        this.cbox_sysDcgj.setChecked(this.dcgj != 0);
        this.cbox_sysDcld.setChecked(this.dcld != 0);
        CheckBox checkBox = this.cbox_sysDcjy;
        if (this.dcjy == 0) {
            z = false;
        }
        checkBox.setChecked(z);
        this.tv_sysDcsxt.setOnClickListener(this);
        this.tv_sysCaux.setOnClickListener(this);
        this.id6SysTempUnit.setOnClickListener(this);
        this.cbox_sysHjs.setOnCheckedChangeListener(this);
        this.cbox_sysXcjz.setOnCheckedChangeListener(this);
        this.cbox_sysDcgj.setOnCheckedChangeListener(this);
        this.cbox_sysDcld.setOnCheckedChangeListener(this);
        this.cbox_sysDcjy.setOnCheckedChangeListener(this);
        if (this.nbauxsw == 3) {
            this.tv_sysCaux.setVisibility(0);
        } else {
            this.tv_sysCaux.setVisibility(8);
        }
        this.tv_beigSize = (TextView) view.findViewById(R.id.tv_beigSize);
        this.seekbar_brightness = (SeekBar) view.findViewById(R.id.seekbar_brightness);
        this.seekbar_brightness.setMax(BrightnessUtils.GAMMA_SPACE_MAX);
        setProgress(this.beiguangValue);
        setProgressText(this.beiguangValue);
        this.seekbar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int val = BrightnessUtils.convertGammaToLinear(progress, ID6SetSystemLayout.this.mMinBrightness, ID6SetSystemLayout.this.mMaxBrightness);
                    Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                    ID6SetSystemLayout.this.setBrightnessValueBg(ID6SetSystemLayout.this.context, val);
                    ID6SetSystemLayout.this.setSystemBrightness(val);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void setSystemBrightness(int brightness) {
        Log.i("SetSystemTwo", " setSystemBrightness=" + brightness);
        Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", brightness);
    }

    /* access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGamma(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage((double) value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i("SetSystemTwo", "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        int progress2 = (int) Math.round(100.0d * b);
        TextView textView = this.tv_beigSize;
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(progress2);
        textView.setText(sb.toString());
    }

    /* access modifiers changed from: private */
    public void setProgress(int brightness) {
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        this.mMaxBrightness = getMaximumScreenBrightnessSetting();
        int value = BrightnessUtils.convertLinearToGamma(brightness, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage((double) value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i("SetSystemTwo", "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.seekbar_brightness.setProgress(value);
    }

    public void onClick(View v) {
        if (this.updateTwoLayout == null) {
            ExceptionPrint.print("updateTwoLayout is null");
            return;
        }
        int id = v.getId();
        if (id != R.id.id6_sysTempUnit) {
            switch (id) {
                case R.id.tv_sysCaux:
                    this.updateTwoLayout.updateTwoLayout(1, 2);
                    return;
                case R.id.tv_sysDcsxt:
                    this.updateTwoLayout.updateTwoLayout(1, 1);
                    return;
                default:
                    return;
            }
        } else {
            this.updateTwoLayout.updateTwoLayout(1, 3);
        }
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbox_sysDcgj:
                FileUtils.savaData(KeyConfig.DAO_CHE_GJ, isChecked);
                return;
            case R.id.cbox_sysDcjy:
                FileUtils.savaData(KeyConfig.DAO_CHE_JY, isChecked);
                return;
            case R.id.cbox_sysDcld:
                FileUtils.savaData(KeyConfig.DAO_CHE_LD, isChecked);
                return;
            case R.id.cbox_sysHjs:
                FileUtils.savaData(KeyConfig.HOU_SHI_SX, isChecked);
                return;
            case R.id.cbox_sysXcjz:
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                return;
            default:
                return;
        }
    }

    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");

        public BrightnessObserver(Handler handler) {
            super(handler);
        }

        public void startObserving() {
            ID6SetSystemLayout.this.context.getContentResolver().unregisterContentObserver(this);
            ID6SetSystemLayout.this.context.getContentResolver().registerContentObserver(this.BRIGHTNESS_URI, false, this);
            Log.i("SetSystemTwo", "startObserving: registerContentObserver uri=" + this.BRIGHTNESS_URI);
        }

        public void stopObserving() {
            ID6SetSystemLayout.this.context.getContentResolver().unregisterContentObserver(this);
            Log.i("SetSystemTwo", "stopObserving: unregisterContentObserver");
        }

        public void onChange(boolean selfChange) {
            onChange(selfChange, (Uri) null);
        }

        public void onChange(boolean selfChange, Uri uri) {
            if (!selfChange && this.BRIGHTNESS_URI.equals(uri)) {
                Log.i("SetSystemTwo", "onChange: " + uri);
                ID6SetSystemLayout.this.mBackgroundHandler.post(ID6SetSystemLayout.this.mUpdateSliderRunnable);
            }
        }
    }

    public int getMinimumScreenBrightnessSetting() {
        return 10;
    }

    public int getMaximumScreenBrightnessSetting() {
        return 255;
    }

    public void setBrightnessValueBg(Context context2, int key) {
        try {
            Class.forName("android.hardware.display.DisplayManager").getMethod("setTemporaryBrightness", new Class[]{Integer.TYPE}).invoke((DisplayManager) context2.getSystemService("display"), new Object[]{Integer.valueOf(key)});
            Log.i("SetSystemTwo", "setBrightnessValueBg: " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
