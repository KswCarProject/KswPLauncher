package com.wits.ksw.settings.land_rover.layout_two;

import android.content.ContentResolver;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.text.NumberFormat;

public class LandroverSetSystemTwo extends RelativeLayout {
    /* access modifiers changed from: private */
    public static final String TAG = ("KSWLauncher." + LandroverSetSystemTwo.class.getSimpleName());
    private int aux_index1;
    private int aux_index2;
    private int beiguangValue;
    /* access modifiers changed from: private */
    public ContentResolver contentResolver;
    /* access modifiers changed from: private */
    public Context context;
    private int groupValue;
    private ImageView img_twoDefaul;
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
            int brightness = Settings.System.getInt(LandroverSetSystemTwo.this.contentResolver, "screen_brightness", 255);
            LandroverSetSystemTwo.this.setProgressText(brightness);
            LandroverSetSystemTwo.this.setProgress(brightness);
        }
    };
    private RadioGroup rdg_shext;
    private LinearLayout relate_auxweiz;
    private RelativeLayout relate_beigld;
    private RelativeLayout relate_shext;
    private SeekBar seekbar_brightness;
    private SeekBar seekbar_caux;
    private SeekBar seekbar_caux2;
    private RadioGroup tempRadioGroup;
    private int tempUnit;
    private TextView tv_beigSize;
    /* access modifiers changed from: private */
    public TextView tv_cauxSize;
    /* access modifiers changed from: private */
    public TextView tv_cauxSize2;

    public LandroverSetSystemTwo(Context context2) {
        super(context2);
        this.context = context2;
        this.contentResolver = context2.getContentResolver();
        View view = LayoutInflater.from(context2).inflate(R.layout.land_rover_layout_set_system_two, (ViewGroup) null);
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
            this.groupValue = PowerManagerApp.getSettingsInt(KeyConfig.DAO_CHE_SXT);
            this.aux_index1 = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_INDEX1);
            this.aux_index2 = PowerManagerApp.getSettingsInt(KeyConfig.CAR_AUX_INDEX2);
            this.tempUnit = PowerManagerApp.getSettingsInt(KeyConfig.TempUnit);
            Log.i(TAG, "initData: TempUnit:" + this.tempUnit + "\tDAO_CHE_SXT:" + this.groupValue + "\tCAR_AUX_INDEX1:" + this.aux_index1 + "\tCAR_AUX_INDEX2:" + this.aux_index2);
        } catch (Exception e) {
            e.getStackTrace();
        }
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        this.mMaxBrightness = getMaximumScreenBrightnessSetting();
        this.beiguangValue = Settings.System.getInt(this.contentResolver, "screen_brightness", 255);
    }

    private void initView(View view) {
        this.img_twoDefaul = (ImageView) view.findViewById(R.id.img_twoDefaul);
        this.relate_shext = (RelativeLayout) view.findViewById(R.id.relate_shext);
        this.relate_auxweiz = (LinearLayout) view.findViewById(R.id.relate_auxweiz);
        this.tv_cauxSize = (TextView) view.findViewById(R.id.tv_cauxSize);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekbar_caux);
        this.seekbar_caux = seekBar;
        seekBar.setMax(12);
        this.seekbar_caux.setProgress(this.aux_index1);
        this.tv_cauxSize.setText(this.aux_index1 + "");
        this.seekbar_caux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LandroverSetSystemTwo.this.tv_cauxSize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX1, progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.seekbar_caux2 = (SeekBar) view.findViewById(R.id.seekbar_caux2);
        this.tv_cauxSize2 = (TextView) view.findViewById(R.id.tv_cauxSize2);
        this.seekbar_caux2.setMax(12);
        this.seekbar_caux2.setProgress(this.aux_index2);
        this.tv_cauxSize2.setText(this.aux_index2 + "");
        this.seekbar_caux2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LandroverSetSystemTwo.this.tv_cauxSize2.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.relate_beigld = (RelativeLayout) view.findViewById(R.id.relate_beigld);
        this.tv_beigSize = (TextView) view.findViewById(R.id.tv_beigSize);
        Log.i("SetSystemTwo", "initView: beiguangValue=" + this.beiguangValue);
        SeekBar seekBar2 = (SeekBar) view.findViewById(R.id.seekbar_brightness);
        this.seekbar_brightness = seekBar2;
        seekBar2.setMax(BrightnessUtils.GAMMA_SPACE_MAX);
        setProgress(this.beiguangValue);
        setProgressText(this.beiguangValue);
        this.seekbar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int val = BrightnessUtils.convertGammaToLinear(progress, LandroverSetSystemTwo.this.mMinBrightness, LandroverSetSystemTwo.this.mMaxBrightness);
                    Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                    LandroverSetSystemTwo landroverSetSystemTwo = LandroverSetSystemTwo.this;
                    landroverSetSystemTwo.setBrightnessValueBg(landroverSetSystemTwo.context, val);
                    LandroverSetSystemTwo.this.setSystemBrightness(val);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rdg_shext);
        this.rdg_shext = radioGroup;
        switch (this.groupValue) {
            case 0:
                radioGroup.check(R.id.rdb_shext1);
                break;
            case 1:
                radioGroup.check(R.id.rdb_shext2);
                break;
            case 2:
                radioGroup.check(R.id.rdb_shext3);
                break;
        }
        this.rdg_shext.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdb_shext1:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 0);
                        return;
                    case R.id.rdb_shext2:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 1);
                        return;
                    case R.id.rdb_shext3:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 2);
                        return;
                    default:
                        return;
                }
            }
        });
        RadioGroup radioGroup2 = (RadioGroup) view.findViewById(R.id.rdg_temp_group);
        this.tempRadioGroup = radioGroup2;
        switch (this.tempUnit) {
            case 0:
                radioGroup2.check(R.id.rdg_temp_group1);
                break;
            case 1:
                radioGroup2.check(R.id.rdg_temp_group2);
                break;
        }
        this.tempRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                switch (checkedId) {
                    case R.id.rdg_temp_group1:
                        FileUtils.savaIntData(KeyConfig.TempUnit, 0);
                        Log.i(LandroverSetSystemTwo.TAG, "save tempUnit  : 0");
                        return;
                    case R.id.rdg_temp_group2:
                        FileUtils.savaIntData(KeyConfig.TempUnit, 1);
                        Log.i(LandroverSetSystemTwo.TAG, "save tempUnit  : 1");
                        return;
                    default:
                        return;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setSystemBrightness(int brightness) {
        Log.i("SetSystemTwo", " setSystemBrightness=" + brightness);
        Settings.System.putInt(this.contentResolver, "screen_brightness", brightness);
    }

    /* access modifiers changed from: private */
    public void setProgressText(int progress) {
        int value = BrightnessUtils.convertLinearToGamma(progress, this.mMinBrightness, this.mMaxBrightness);
        double b = BrightnessUtils.getPercentage((double) value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        Log.i("SetSystemTwo", "setProgressText run: brightness=" + progress + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + NumberFormat.getPercentInstance().format(b));
        this.tv_beigSize.setText("" + ((int) Math.round(100.0d * b)));
    }

    /* access modifiers changed from: private */
    public void setProgress(int brightness) {
        this.mMinBrightness = getMinimumScreenBrightnessSetting();
        int maximumScreenBrightnessSetting = getMaximumScreenBrightnessSetting();
        this.mMaxBrightness = maximumScreenBrightnessSetting;
        int value = BrightnessUtils.convertLinearToGamma(brightness, this.mMinBrightness, maximumScreenBrightnessSetting);
        double b = BrightnessUtils.getPercentage((double) value, 0, BrightnessUtils.GAMMA_SPACE_MAX);
        Log.i("SetSystemTwo", "run: brightness=" + brightness + " : mMinBrightness=" + this.mMinBrightness + " mMaxBrightness=" + this.mMaxBrightness + " value=" + value + " b=" + b + " aaa=" + NumberFormat.getPercentInstance().format(b));
        this.seekbar_brightness.setProgress(value);
    }

    public void showLayout(int index) {
        switch (index) {
            case 0:
                this.img_twoDefaul.setVisibility(0);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.tempRadioGroup.setVisibility(8);
                return;
            case 1:
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(0);
                this.tempRadioGroup.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                return;
            case 2:
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.tempRadioGroup.setVisibility(8);
                this.relate_beigld.setVisibility(0);
                return;
            case 3:
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.tempRadioGroup.setVisibility(8);
                this.relate_auxweiz.setVisibility(0);
                return;
            case 4:
                this.tempRadioGroup.setVisibility(0);
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
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
            LandroverSetSystemTwo.this.contentResolver.unregisterContentObserver(this);
            LandroverSetSystemTwo.this.contentResolver.registerContentObserver(this.BRIGHTNESS_URI, false, this);
            Log.i("SetSystemTwo", "startObserving: registerContentObserver uri=" + this.BRIGHTNESS_URI);
        }

        public void stopObserving() {
            LandroverSetSystemTwo.this.contentResolver.unregisterContentObserver(this);
            Log.i("SetSystemTwo", "stopObserving: unregisterContentObserver");
        }

        public void onChange(boolean selfChange) {
            onChange(selfChange, (Uri) null);
        }

        public void onChange(boolean selfChange, Uri uri) {
            if (!selfChange && this.BRIGHTNESS_URI.equals(uri)) {
                Log.i("SetSystemTwo", "onChange: " + uri);
                LandroverSetSystemTwo.this.mBackgroundHandler.post(LandroverSetSystemTwo.this.mUpdateSliderRunnable);
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
