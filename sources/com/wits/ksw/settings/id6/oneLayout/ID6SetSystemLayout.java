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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.ExceptionPrint;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.id7.interfaces.IUpdateTwoLayout;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.lang.reflect.Method;
import java.text.NumberFormat;

/* loaded from: classes7.dex */
public class ID6SetSystemLayout extends RelativeLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private int beiguangValue;
    private CheckBox cbox_sysDcgj;
    private CheckBox cbox_sysDcjy;
    private CheckBox cbox_sysDcld;
    private CheckBox cbox_sysHjs;
    private CheckBox cbox_sysXcjz;
    private int cheVideo;
    private Context context;
    private int dcgj;
    private int dcjy;
    private int dcld;
    private int housi;
    private TextView id6SysFuelUnit;
    private TextView id6SysTempUnit;
    private Handler mBackgroundHandler;
    private BrightnessObserver mBrightnessObserver;
    private int mMaxBrightness;
    private int mMinBrightness;
    private final Runnable mUpdateSliderRunnable;
    private int nbauxsw;
    private SeekBar seekbar_brightness;
    private TextView tv_beigSize;
    private TextView tv_music_app;
    private TextView tv_sysCaux;
    private TextView tv_sysDcsxt;
    private TextView tv_video_app;
    private IUpdateTwoLayout updateTwoLayout;

    public void registIUpdateTwoLayout(IUpdateTwoLayout twoLayout) {
        this.updateTwoLayout = twoLayout;
    }

    public ID6SetSystemLayout(Context context) {
        super(context);
        this.housi = 0;
        this.cheVideo = 0;
        this.dcgj = 0;
        this.dcld = 0;
        this.dcjy = 0;
        this.nbauxsw = 0;
        this.mUpdateSliderRunnable = new Runnable() { // from class: com.wits.ksw.settings.id6.oneLayout.ID6SetSystemLayout.2
            @Override // java.lang.Runnable
            public void run() {
                int brightness = Settings.System.getInt(ID6SetSystemLayout.this.context.getContentResolver(), "screen_brightness", 255);
                ID6SetSystemLayout.this.setProgressText(brightness);
                ID6SetSystemLayout.this.setProgress(brightness);
            }
        };
        this.context = context;
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_set_system, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
        this.mBackgroundHandler = new Handler(Looper.getMainLooper());
        BrightnessObserver brightnessObserver = new BrightnessObserver(new Handler());
        this.mBrightnessObserver = brightnessObserver;
        brightnessObserver.startObserving();
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
        this.id6SysTempUnit = (TextView) view.findViewById(C0899R.C0901id.id6_sysTempUnit);
        this.id6SysFuelUnit = (TextView) view.findViewById(C0899R.C0901id.id6_sysFuelUnit);
        this.tv_sysDcsxt = (TextView) view.findViewById(C0899R.C0901id.tv_sysDcsxt);
        this.cbox_sysHjs = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysHjs);
        this.cbox_sysXcjz = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysXcjz);
        this.cbox_sysDcgj = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysDcgj);
        this.cbox_sysDcld = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysDcld);
        this.tv_sysCaux = (TextView) view.findViewById(C0899R.C0901id.tv_sysCaux);
        this.cbox_sysDcjy = (CheckBox) view.findViewById(C0899R.C0901id.cbox_sysDcjy);
        this.cbox_sysHjs.setChecked(this.housi != 0);
        this.cbox_sysXcjz.setChecked(this.cheVideo != 0);
        this.cbox_sysDcgj.setChecked(this.dcgj != 0);
        this.cbox_sysDcld.setChecked(this.dcld != 0);
        this.cbox_sysDcjy.setChecked(this.dcjy != 0);
        this.tv_sysDcsxt.setOnClickListener(this);
        this.tv_sysCaux.setOnClickListener(this);
        this.id6SysTempUnit.setOnClickListener(this);
        this.id6SysFuelUnit.setOnClickListener(this);
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
        this.tv_beigSize = (TextView) view.findViewById(C0899R.C0901id.tv_beigSize);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_brightness);
        this.seekbar_brightness = seekBar;
        seekBar.setMax(BrightnessUtils.GAMMA_SPACE_MAX);
        setProgress(this.beiguangValue);
        setProgressText(this.beiguangValue);
        this.seekbar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.id6.oneLayout.ID6SetSystemLayout.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                if (fromUser) {
                    int val = BrightnessUtils.convertGammaToLinear(progress, ID6SetSystemLayout.this.mMinBrightness, ID6SetSystemLayout.this.mMaxBrightness);
                    Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                    ID6SetSystemLayout iD6SetSystemLayout = ID6SetSystemLayout.this;
                    iD6SetSystemLayout.setBrightnessValueBg(iD6SetSystemLayout.context, val);
                    ID6SetSystemLayout.this.setSystemBrightness(val);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        this.tv_music_app = (TextView) view.findViewById(C0899R.C0901id.tv_music_app);
        this.tv_video_app = (TextView) view.findViewById(C0899R.C0901id.tv_video_app);
        this.tv_music_app.setOnClickListener(this);
        this.tv_video_app.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemBrightness(int brightness) {
        Log.i("SetSystemTwo", " setSystemBrightness=" + brightness);
        Settings.System.putInt(this.context.getContentResolver(), "screen_brightness", brightness);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGamma(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage(value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i("SetSystemTwo", "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.tv_beigSize.setText("" + ((int) Math.round(100.0d * b)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(int brightness) {
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        int maximumScreenBrightnessSetting = getMaximumScreenBrightnessSetting();
        this.mMaxBrightness = maximumScreenBrightnessSetting;
        int value = BrightnessUtils.convertLinearToGamma(brightness, this.mMinBrightness, maximumScreenBrightnessSetting);
        double b = BrightnessUtils.getPercentage(value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        String aaa = NumberFormat.getPercentInstance().format(b);
        Log.i("SetSystemTwo", "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + aaa);
        this.seekbar_brightness.setProgress(value);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.updateTwoLayout == null) {
            ExceptionPrint.print("updateTwoLayout is null");
            return;
        }
        switch (v.getId()) {
            case C0899R.C0901id.id6_sysFuelUnit /* 2131297023 */:
                this.updateTwoLayout.updateTwoLayout(1, 4);
                return;
            case C0899R.C0901id.id6_sysTempUnit /* 2131297024 */:
                this.updateTwoLayout.updateTwoLayout(1, 3);
                return;
            case C0899R.C0901id.tv_music_app /* 2131297952 */:
                this.updateTwoLayout.updateTwoLayout(1, 5);
                return;
            case C0899R.C0901id.tv_sysCaux /* 2131297982 */:
                this.updateTwoLayout.updateTwoLayout(1, 2);
                return;
            case C0899R.C0901id.tv_sysDcsxt /* 2131297983 */:
                this.updateTwoLayout.updateTwoLayout(1, 1);
                return;
            case C0899R.C0901id.tv_video_app /* 2131298003 */:
                this.updateTwoLayout.updateTwoLayout(1, 6);
                return;
            default:
                return;
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case C0899R.C0901id.cbox_sysDcgj /* 2131296756 */:
                FileUtils.savaData(KeyConfig.DAO_CHE_GJ, isChecked);
                return;
            case C0899R.C0901id.cbox_sysDcjy /* 2131296757 */:
                FileUtils.savaData(KeyConfig.DAO_CHE_JY, isChecked);
                return;
            case C0899R.C0901id.cbox_sysDcld /* 2131296758 */:
                FileUtils.savaData(KeyConfig.DAO_CHE_LD, isChecked);
                return;
            case C0899R.C0901id.cbox_sysHjs /* 2131296759 */:
                FileUtils.savaData(KeyConfig.HOU_SHI_SX, isChecked);
                return;
            case C0899R.C0901id.cbox_sysXcjz /* 2131296760 */:
                FileUtils.savaData(KeyConfig.XING_CHE_JZSP, isChecked);
                return;
            default:
                return;
        }
    }

    /* loaded from: classes7.dex */
    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
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

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override // android.database.ContentObserver
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

    public void setBrightnessValueBg(Context context, int key) {
        try {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Class<?> dmclass = Class.forName("android.hardware.display.DisplayManager");
            Method set = dmclass.getMethod("setTemporaryBrightness", Integer.TYPE);
            set.invoke(displayManager, Integer.valueOf(key));
            Log.i("SetSystemTwo", "setBrightnessValueBg: " + key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
