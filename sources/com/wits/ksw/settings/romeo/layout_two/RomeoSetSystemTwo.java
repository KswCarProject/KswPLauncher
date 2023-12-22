package com.wits.ksw.settings.romeo.layout_two;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.List;

/* loaded from: classes17.dex */
public class RomeoSetSystemTwo extends RelativeLayout {
    private static final String TAG = "KswApplication." + RomeoSetSystemTwo.class.getSimpleName();
    private int aux_index1;
    private int aux_index2;
    private int beiguangValue;
    private ContentResolver contentResolver;
    private Context context;
    private int downY;
    private int groupValue;
    private ImageView img_twoDefaul;
    private RecyclerView listview_music;
    private RecyclerView listview_video;
    private Handler mBackgroundHandler;
    private BrightnessObserver mBrightnessObserver;
    private int mMaxBrightness;
    private int mMinBrightness;
    private final Runnable mUpdateSliderRunnable;
    private RadioGroup rdg_shext;
    private LinearLayout relate_auxweiz;
    private RelativeLayout relate_beigld;
    private RelativeLayout relate_shext;
    private ImageView romeo_list_r1;
    private ImageView romeo_list_r2;
    private ImageView romeo_list_r3;
    private SeekBar seekbar_brightness;
    private SeekBar seekbar_caux;
    private SeekBar seekbar_caux2;
    private RadioGroup tempRadioGroup;
    private int tempUnit;
    private TextView tv_beigSize;
    private TextView tv_cauxSize;
    private TextView tv_cauxSize2;
    private IUpdateListBg updateListBg;

    public RomeoSetSystemTwo(Context context) {
        super(context);
        this.mUpdateSliderRunnable = new Runnable() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.14
            @Override // java.lang.Runnable
            public void run() {
                int brightness = Settings.System.getInt(RomeoSetSystemTwo.this.contentResolver, "screen_brightness", 255);
                RomeoSetSystemTwo.this.setProgressText(brightness);
                RomeoSetSystemTwo.this.setProgress(brightness);
            }
        };
        this.context = context;
        this.contentResolver = context.getContentResolver();
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.romeo_layout_set_system_two, (ViewGroup) null);
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
        this.img_twoDefaul = (ImageView) view.findViewById(C0899R.C0901id.img_twoDefaul);
        this.relate_shext = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_shext);
        this.relate_auxweiz = (LinearLayout) view.findViewById(C0899R.C0901id.relate_auxweiz);
        this.tv_cauxSize = (TextView) view.findViewById(C0899R.C0901id.tv_cauxSize);
        this.seekbar_caux = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_caux);
        this.romeo_list_r1 = (ImageView) view.findViewById(C0899R.C0901id.romeo_list_r1);
        this.romeo_list_r2 = (ImageView) view.findViewById(C0899R.C0901id.romeo_list_r2);
        this.romeo_list_r3 = (ImageView) view.findViewById(C0899R.C0901id.romeo_list_r3);
        this.seekbar_caux.setMax(12);
        this.seekbar_caux.setProgress(this.aux_index1);
        this.tv_cauxSize.setText(this.aux_index1 + "");
        this.seekbar_caux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RomeoSetSystemTwo.this.tv_cauxSize.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX1, progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.seekbar_caux2 = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_caux2);
        this.tv_cauxSize2 = (TextView) view.findViewById(C0899R.C0901id.tv_cauxSize2);
        this.seekbar_caux2.setMax(12);
        this.seekbar_caux2.setProgress(this.aux_index2);
        this.tv_cauxSize2.setText(this.aux_index2 + "");
        this.seekbar_caux2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RomeoSetSystemTwo.this.tv_cauxSize2.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.relate_beigld = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_beigld);
        this.tv_beigSize = (TextView) view.findViewById(C0899R.C0901id.tv_beigSize);
        Log.i("SetSystemTwo", "initView: beiguangValue=" + this.beiguangValue);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_brightness);
        this.seekbar_brightness = seekBar;
        seekBar.setMax(BrightnessUtils.GAMMA_SPACE_MAX);
        setProgress(this.beiguangValue);
        setProgressText(this.beiguangValue);
        this.seekbar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                if (fromUser) {
                    int val = BrightnessUtils.convertGammaToLinear(progress, RomeoSetSystemTwo.this.mMinBrightness, RomeoSetSystemTwo.this.mMaxBrightness);
                    Log.e("SetSystemTwo", "onProgressChanged: fromUser=" + fromUser + " : progress=" + progress + " : val=" + val);
                    RomeoSetSystemTwo romeoSetSystemTwo = RomeoSetSystemTwo.this;
                    romeoSetSystemTwo.setBrightnessValueBg(romeoSetSystemTwo.context, val);
                    RomeoSetSystemTwo.this.setSystemBrightness(val);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
        RadioGroup radioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_shext);
        this.rdg_shext = radioGroup;
        switch (this.groupValue) {
            case 0:
                radioGroup.check(C0899R.C0901id.rdb_shext1);
                break;
            case 1:
                radioGroup.check(C0899R.C0901id.rdb_shext2);
                break;
            case 2:
                radioGroup.check(C0899R.C0901id.rdb_shext3);
                break;
            case 3:
                radioGroup.check(C0899R.C0901id.rdb_shext4);
                break;
        }
        this.rdg_shext.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case C0899R.C0901id.rdb_shext1 /* 2131297491 */:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 0);
                        return;
                    case C0899R.C0901id.rdb_shext2 /* 2131297492 */:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 1);
                        return;
                    case C0899R.C0901id.rdb_shext3 /* 2131297493 */:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 2);
                        return;
                    case C0899R.C0901id.rdb_shext4 /* 2131297494 */:
                        FileUtils.savaIntData(KeyConfig.DAO_CHE_SXT, 3);
                        SystemProperties.set("persist.sys.camera.preview.size", "1920x4344");
                        return;
                    default:
                        return;
                }
            }
        });
        for (int i = 0; i < this.rdg_shext.getChildCount(); i++) {
            final int finalI = i;
            this.rdg_shext.getChildAt(i).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.5
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        int i2 = finalI;
                        if (i2 == 0) {
                            RomeoSetSystemTwo.this.romeo_list_r1.setVisibility(0);
                        } else if (i2 == 1) {
                            RomeoSetSystemTwo.this.romeo_list_r2.setVisibility(0);
                        } else if (i2 == 2) {
                            RomeoSetSystemTwo.this.romeo_list_r3.setVisibility(0);
                        }
                    } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        int i3 = finalI;
                        if (i3 == 0) {
                            RomeoSetSystemTwo.this.romeo_list_r1.setVisibility(4);
                        } else if (i3 == 1) {
                            RomeoSetSystemTwo.this.romeo_list_r2.setVisibility(4);
                        } else if (i3 == 2) {
                            RomeoSetSystemTwo.this.romeo_list_r3.setVisibility(4);
                        }
                    }
                    return false;
                }
            });
            this.rdg_shext.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.6
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view2, boolean b) {
                    int i2 = finalI;
                    if (i2 == 0) {
                        RomeoSetSystemTwo.this.romeo_list_r1.setVisibility(b ? 0 : 4);
                    } else if (i2 == 1) {
                        RomeoSetSystemTwo.this.romeo_list_r2.setVisibility(b ? 0 : 4);
                    } else if (i2 == 2) {
                        RomeoSetSystemTwo.this.romeo_list_r3.setVisibility(b ? 0 : 4);
                    }
                }
            });
        }
        this.tempRadioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_temp_group);
        for (int i2 = 0; i2 < this.tempRadioGroup.getChildCount(); i2++) {
            if (i2 == this.tempUnit) {
                RadioGroup radioGroup2 = this.tempRadioGroup;
                radioGroup2.check(radioGroup2.getChildAt(i2).getId());
            }
            final int finalI2 = i2;
            this.tempRadioGroup.getChildAt(i2).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.7
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        int i3 = finalI2;
                        if (i3 == 0) {
                            RomeoSetSystemTwo.this.romeo_list_r1.setVisibility(0);
                        } else if (i3 == 1) {
                            RomeoSetSystemTwo.this.romeo_list_r2.setVisibility(0);
                        }
                    } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        int i4 = finalI2;
                        if (i4 == 0) {
                            RomeoSetSystemTwo.this.romeo_list_r1.setVisibility(4);
                        } else if (i4 == 1) {
                            RomeoSetSystemTwo.this.romeo_list_r2.setVisibility(4);
                        }
                    }
                    return false;
                }
            });
            this.tempRadioGroup.getChildAt(i2).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.8
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view2, boolean b) {
                    int i3 = finalI2;
                    if (i3 == 0) {
                        RomeoSetSystemTwo.this.romeo_list_r1.setVisibility(b ? 0 : 4);
                    } else if (i3 == 1) {
                        RomeoSetSystemTwo.this.romeo_list_r2.setVisibility(b ? 0 : 4);
                    }
                }
            });
        }
        this.tempRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.9
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i3 = 0; i3 < count; i3++) {
                    if (checkedId == group.getChildAt(i3).getId()) {
                        FileUtils.savaIntData(KeyConfig.TempUnit, i3);
                        Log.i(RomeoSetSystemTwo.TAG, "save tempUnit  : " + i3);
                    }
                    group.getChildAt(i3).setSelected(checkedId == group.getChildAt(i3).getId());
                }
            }
        });
        this.listview_music = (RecyclerView) view.findViewById(C0899R.C0901id.listview_music);
        List<LexusLsAppSelBean> listMusic = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this.context);
        VerRecyclerAdapter adapterMusic = new VerRecyclerAdapter(this.context, listMusic, C0899R.C0902layout.app_third_item_romeo);
        this.listview_music.setAdapter(adapterMusic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(1);
        this.listview_music.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this.context, 1);
        divider.setDrawable(ContextCompat.getDrawable(this.context, C0899R.C0900drawable.lexus_settings_line_left));
        adapterMusic.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.10
            @Override // com.wits.ksw.launcher.adpater.VerRecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "music app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(RomeoSetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG, pkg);
                Settings.System.putString(RomeoSetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                    LauncherViewModel.setThirdMusic(false);
                } else {
                    LauncherViewModel.setThirdMusic(true);
                }
            }
        });
        this.listview_video = (RecyclerView) view.findViewById(C0899R.C0901id.listview_video);
        List<LexusLsAppSelBean> listVideo = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 2, this.context);
        VerRecyclerAdapter adapterVideo = new VerRecyclerAdapter(this.context, listVideo, C0899R.C0902layout.app_third_item_romeo);
        this.listview_video.setAdapter(adapterVideo);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this.context);
        layoutManager1.setOrientation(1);
        this.listview_video.setLayoutManager(layoutManager1);
        DividerItemDecoration divider1 = new DividerItemDecoration(this.context, 1);
        divider1.setDrawable(ContextCompat.getDrawable(this.context, C0899R.C0900drawable.lexus_settings_line_left));
        adapterVideo.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.11
            @Override // com.wits.ksw.launcher.adpater.VerRecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "video app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(RomeoSetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG, pkg);
                Settings.System.putString(RomeoSetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                    LauncherViewModel.setThirdVideo(false);
                } else {
                    LauncherViewModel.setThirdVideo(true);
                }
            }
        });
    }

    private void changeItemBg(final ViewGroup viewGroup, Context context) {
        Log.d(TAG, "changeItemBg");
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final int finalI = i;
            viewGroup.getChildAt(i).setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.12
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Log.d(RomeoSetSystemTwo.TAG, "changeItemBg onTouch i=" + finalI + " action=" + motionEvent.getAction());
                    int[] locW = new int[2];
                    viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                    if (motionEvent.getAction() == 0) {
                        RomeoSetSystemTwo.this.downY = (int) motionEvent.getY();
                        RomeoSetSystemTwo.this.updateListBg.updateListBg(locW[1] - 78, 2);
                    } else if (motionEvent.getAction() == 1) {
                        RomeoSetSystemTwo.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    } else if (motionEvent.getAction() == 3) {
                        RomeoSetSystemTwo.this.updateListBg.updateListBg(locW[1] - 78, 0);
                    }
                    return false;
                }
            });
            viewGroup.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.layout_two.RomeoSetSystemTwo.13
                @Override // android.view.View.OnFocusChangeListener
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        Log.d(RomeoSetSystemTwo.TAG, "changeItemBg onFocusChange i=" + finalI + " downY=" + RomeoSetSystemTwo.this.downY);
                        int[] locW = new int[2];
                        viewGroup.getChildAt(finalI).getLocationInWindow(locW);
                        RomeoSetSystemTwo.this.updateListBg.updateListBg(locW[1] - 78, 1);
                        return;
                    }
                    RomeoSetSystemTwo.this.updateListBg.updateListBg(0, 0);
                }
            });
        }
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
            case 0:
                this.img_twoDefaul.setVisibility(0);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.tempRadioGroup.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 1:
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(0);
                this.tempRadioGroup.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 2:
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.tempRadioGroup.setVisibility(8);
                this.relate_beigld.setVisibility(0);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 3:
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.tempRadioGroup.setVisibility(8);
                this.relate_auxweiz.setVisibility(0);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 4:
                this.tempRadioGroup.setVisibility(0);
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 5:
            default:
                return;
            case 6:
                this.tempRadioGroup.setVisibility(8);
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(0);
                this.listview_video.setVisibility(8);
                return;
            case 7:
                this.tempRadioGroup.setVisibility(8);
                this.img_twoDefaul.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_beigld.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(0);
                return;
        }
    }

    /* loaded from: classes17.dex */
    private class BrightnessObserver extends ContentObserver {
        private Uri BRIGHTNESS_URI;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
        }

        public void startObserving() {
            RomeoSetSystemTwo.this.contentResolver.unregisterContentObserver(this);
            RomeoSetSystemTwo.this.contentResolver.registerContentObserver(this.BRIGHTNESS_URI, false, this);
            Log.i("SetSystemTwo", "startObserving: registerContentObserver uri=" + this.BRIGHTNESS_URI);
        }

        public void stopObserving() {
            RomeoSetSystemTwo.this.contentResolver.unregisterContentObserver(this);
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
                RomeoSetSystemTwo.this.mBackgroundHandler.post(RomeoSetSystemTwo.this.mUpdateSliderRunnable);
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
