package com.wits.ksw.settings.ntg6.two_layout;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.adpater.VerRecyclerAdapter;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.settings.BrightnessUtils;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.RtlRadioButton;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.List;

/* loaded from: classes5.dex */
public class Ntg6SetSystemTwo extends RelativeLayout {
    private static final String TAG = "KswApplication." + Ntg6SetSystemTwo.class.getSimpleName();
    private int aux_index1;
    private int aux_index2;
    private int beiguangValue;
    private ContentResolver contentResolver;
    private Context context;
    private int groupValue;
    private Handler handler;
    private ImageView img_TwoBack;
    private ImageView img_twoDefaul;
    private RecyclerView listview_music;
    private RecyclerView listview_video;
    private Handler mBackgroundHandler;
    private BrightnessObserver mBrightnessObserver;
    private int mMaxBrightness;
    private int mMinBrightness;
    private final Runnable mUpdateSliderRunnable;
    private RtlRadioButton rdb_shext1;
    private RtlRadioButton rdb_shext2;
    private RtlRadioButton rdb_shext3;
    private RtlRadioButton rdb_shext4;
    private RadioGroup rdgTempUnitRadioGroup;
    private RadioGroup rdg_shext;
    private LinearLayout relate_auxweiz;
    private LinearLayout relate_beigld;
    private RelativeLayout relate_shext;
    private SeekBar seekbar_brightness;
    private SeekBar seekbar_caux;
    private SeekBar seekbar_caux2;
    private int tempUnit;
    private TextView tv_TwoMsg;
    private TextView tv_beigSize;
    private TextView tv_cauxSize;
    private TextView tv_cauxSize2;

    public Ntg6SetSystemTwo(Context context, Handler handler) {
        super(context);
        this.mUpdateSliderRunnable = new Runnable() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.12
            @Override // java.lang.Runnable
            public void run() {
                int brightness = Settings.System.getInt(Ntg6SetSystemTwo.this.contentResolver, "screen_brightness", 255);
                Ntg6SetSystemTwo.this.setProgressText(brightness);
                Ntg6SetSystemTwo.this.setProgress(brightness);
            }
        };
        this.handler = handler;
        this.context = context;
        this.contentResolver = context.getContentResolver();
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_ntg6_system_two, (ViewGroup) null);
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
        this.img_TwoBack = (ImageView) view.findViewById(C0899R.C0901id.img_TwoBack);
        this.tv_TwoMsg = (TextView) view.findViewById(C0899R.C0901id.tv_TwoMsg);
        this.relate_auxweiz = (LinearLayout) view.findViewById(C0899R.C0901id.relate_auxweiz);
        this.tv_cauxSize = (TextView) view.findViewById(C0899R.C0901id.tv_cauxSize);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_caux);
        this.seekbar_caux = seekBar;
        seekBar.setMax(12);
        this.seekbar_caux.setProgress(this.aux_index1);
        this.tv_cauxSize.setText(this.aux_index1 + "");
        this.seekbar_caux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                Ntg6SetSystemTwo.this.tv_cauxSize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX1, progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        this.tv_cauxSize2 = (TextView) view.findViewById(C0899R.C0901id.tv_cauxSize2);
        SeekBar seekBar2 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_caux2);
        this.seekbar_caux2 = seekBar2;
        seekBar2.setMax(12);
        this.seekbar_caux2.setProgress(this.aux_index2);
        this.tv_cauxSize2.setText(this.aux_index2 + "");
        this.seekbar_caux2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int progress, boolean fromUser) {
                Ntg6SetSystemTwo.this.tv_cauxSize2.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
            }
        });
        this.img_twoDefaul = (ImageView) view.findViewById(C0899R.C0901id.img_twoDefaul);
        this.relate_shext = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_shext);
        this.relate_beigld = (LinearLayout) view.findViewById(C0899R.C0901id.relate_beigld);
        this.tv_beigSize = (TextView) view.findViewById(C0899R.C0901id.tv_beigSize);
        Log.i("SetSystemTwo", "initView: beiguangValue=" + this.beiguangValue);
        SeekBar seekBar3 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_brightness);
        this.seekbar_brightness = seekBar3;
        seekBar3.setMax(BrightnessUtils.GAMMA_SPACE_MAX);
        setProgress(this.beiguangValue);
        setProgressText(this.beiguangValue);
        this.seekbar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar4, int progress, boolean fromUser) {
                if (fromUser) {
                    int val = BrightnessUtils.convertGammaToLinear(progress, Ntg6SetSystemTwo.this.mMinBrightness, Ntg6SetSystemTwo.this.mMaxBrightness);
                    Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                    Ntg6SetSystemTwo ntg6SetSystemTwo = Ntg6SetSystemTwo.this;
                    ntg6SetSystemTwo.setBrightnessValueBg(ntg6SetSystemTwo.context, val);
                    Ntg6SetSystemTwo.this.setSystemBrightness(val);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar4) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar4) {
            }
        });
        this.rdg_shext = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_shext);
        this.rdb_shext1 = (RtlRadioButton) view.findViewById(C0899R.C0901id.rdb_shext1);
        this.rdb_shext2 = (RtlRadioButton) view.findViewById(C0899R.C0901id.rdb_shext2);
        this.rdb_shext3 = (RtlRadioButton) view.findViewById(C0899R.C0901id.rdb_shext3);
        this.rdb_shext4 = (RtlRadioButton) view.findViewById(C0899R.C0901id.rdb_shext4);
        switch (this.groupValue) {
            case 0:
                this.rdg_shext.check(C0899R.C0901id.rdb_shext1);
                break;
            case 1:
                this.rdg_shext.check(C0899R.C0901id.rdb_shext2);
                break;
            case 2:
                this.rdg_shext.check(C0899R.C0901id.rdb_shext3);
                break;
            case 3:
                this.rdg_shext.check(C0899R.C0901id.rdb_shext4);
                break;
        }
        this.rdb_shext1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Ntg6SetSystemTwo.this.rdb_shext2.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext3.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext4.setChecked(false);
                    Log.d(Ntg6SetSystemTwo.TAG, "onCheckedChanged: \u70b9\u51fbshext1");
                    FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 0);
                }
            }
        });
        this.rdb_shext2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Ntg6SetSystemTwo.this.rdb_shext1.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext3.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext4.setChecked(false);
                    Log.d(Ntg6SetSystemTwo.TAG, "onCheckedChanged: \u70b9\u51fbshext2");
                    FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 1);
                }
            }
        });
        this.rdb_shext3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Ntg6SetSystemTwo.this.rdb_shext1.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext2.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext4.setChecked(false);
                    Log.d(Ntg6SetSystemTwo.TAG, "onCheckedChanged: \u70b9\u51fbshext3");
                    FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 2);
                }
            }
        });
        this.rdb_shext4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Ntg6SetSystemTwo.this.rdb_shext1.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext2.setChecked(false);
                    Ntg6SetSystemTwo.this.rdb_shext3.setChecked(false);
                    Log.d(Ntg6SetSystemTwo.TAG, "onCheckedChanged: \u70b9\u51fbshext4");
                    FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 3);
                    SystemProperties.set("persist.sys.camera.preview.size", "1920x4344");
                }
            }
        });
        this.img_TwoBack.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.8
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Ntg6SetSystemTwo.this.handler.sendEmptyMessage(3);
            }
        });
        this.rdgTempUnitRadioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_benz_tempUnit_radioGroup);
        for (int i = 0; i < this.rdgTempUnitRadioGroup.getChildCount(); i++) {
            if (this.tempUnit == i) {
                RadioGroup radioGroup = this.rdgTempUnitRadioGroup;
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }
        }
        this.rdgTempUnitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.9
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i2 = 0; i2 < count; i2++) {
                    if (checkedId == group.getChildAt(i2).getId()) {
                        FileUtils.savaIntData(KeyConfig.TempUnit, i2);
                        Log.i(Ntg6SetSystemTwo.TAG, "save tempUnit  : " + i2);
                    }
                    group.getChildAt(i2).setSelected(checkedId == group.getChildAt(i2).getId());
                }
            }
        });
        this.listview_music = (RecyclerView) view.findViewById(C0899R.C0901id.listview_music);
        List<LexusLsAppSelBean> listMusic = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this.context);
        VerRecyclerAdapter adapterMusic = new VerRecyclerAdapter(this.context, listMusic, C0899R.C0902layout.app_third_item_benz_ntg6);
        this.listview_music.setAdapter(adapterMusic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(1);
        this.listview_music.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this.context, 1);
        divider.setDrawable(ContextCompat.getDrawable(this.context, C0899R.mipmap.ntg55_right_big_line1));
        this.listview_music.addItemDecoration(divider);
        adapterMusic.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.10
            @Override // com.wits.ksw.launcher.adpater.VerRecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "music app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(Ntg6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG, pkg);
                Settings.System.putString(Ntg6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                    LauncherViewModel.setThirdMusic(false);
                } else {
                    LauncherViewModel.setThirdMusic(true);
                }
            }
        });
        this.listview_video = (RecyclerView) view.findViewById(C0899R.C0901id.listview_video);
        List<LexusLsAppSelBean> listVideo = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 2, this.context);
        VerRecyclerAdapter adapterVideo = new VerRecyclerAdapter(this.context, listVideo, C0899R.C0902layout.app_third_item_benz_ntg6);
        this.listview_video.setAdapter(adapterVideo);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this.context);
        layoutManager1.setOrientation(1);
        this.listview_video.setLayoutManager(layoutManager1);
        DividerItemDecoration divider1 = new DividerItemDecoration(this.context, 1);
        divider1.setDrawable(ContextCompat.getDrawable(this.context, C0899R.mipmap.ntg55_right_big_line1));
        this.listview_video.addItemDecoration(divider1);
        adapterVideo.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.ntg6.two_layout.Ntg6SetSystemTwo.11
            @Override // com.wits.ksw.launcher.adpater.VerRecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "video app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(Ntg6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG, pkg);
                Settings.System.putString(Ntg6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                    LauncherViewModel.setThirdVideo(false);
                } else {
                    LauncherViewModel.setThirdVideo(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemBrightness(int brightness) {
        Log.i("SetSystemTwo", " setSystemBrightness=" + brightness);
        Settings.System.putInt(this.contentResolver, "screen_brightness", brightness);
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

    public void showLayout(int index) {
        switch (index) {
            case 1:
                this.tv_TwoMsg.setText(this.context.getString(C0899R.string.set_text5));
                this.relate_shext.setVisibility(0);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 2:
                this.tv_TwoMsg.setText(this.context.getString(C0899R.string.set_text6));
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_beigld.setVisibility(0);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 3:
                this.tv_TwoMsg.setText(this.context.getString(C0899R.string.set_caraux));
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_auxweiz.setVisibility(0);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 4:
                this.rdgTempUnitRadioGroup.setVisibility(0);
                this.tv_TwoMsg.setText(this.context.getString(C0899R.string.audi_set_temp_unit));
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 5:
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.tv_TwoMsg.setText(this.context.getString(C0899R.string.music_app_sel));
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(0);
                this.listview_video.setVisibility(8);
                return;
            case 6:
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.tv_TwoMsg.setText(this.context.getString(C0899R.string.video_app_sel));
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* loaded from: classes5.dex */
    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
        }

        public void startObserving() {
            Ntg6SetSystemTwo.this.contentResolver.unregisterContentObserver(this);
            Ntg6SetSystemTwo.this.contentResolver.registerContentObserver(this.BRIGHTNESS_URI, false, this);
            Log.i("SetSystemTwo", "startObserving: registerContentObserver uri=" + this.BRIGHTNESS_URI);
        }

        public void stopObserving() {
            Ntg6SetSystemTwo.this.contentResolver.unregisterContentObserver(this);
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
                Ntg6SetSystemTwo.this.mBackgroundHandler.post(Ntg6SetSystemTwo.this.mUpdateSliderRunnable);
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
