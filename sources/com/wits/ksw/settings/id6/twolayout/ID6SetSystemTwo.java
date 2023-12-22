package com.wits.ksw.settings.id6.twolayout;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.DividerItemDecoration;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
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
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.adpater.VerRecyclerAdapter;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.AppInfoUtils;
import com.wits.ksw.settings.utlis_view.FileUtils;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.ksw.settings.utlis_view.SystemProperties;
import com.wits.pms.statuscontrol.PowerManagerApp;
import java.util.List;

/* loaded from: classes3.dex */
public class ID6SetSystemTwo extends RelativeLayout {
    private static final String TAG = "KswApplication." + ID6SetSystemTwo.class.getSimpleName();
    private int aux_index1;
    private int aux_index2;
    private ContentResolver contentResolver;
    private Context context;
    private int fuelUnit;
    private int groupValue;
    private ImageView img_twoDefaul;
    private RecyclerView listview_music;
    private RecyclerView listview_video;
    private RadioGroup rdgFuelUnitRadioGroup;
    private RadioGroup rdgTempUnitRadioGroup;
    private RadioGroup rdg_shext;
    private LinearLayout relate_auxweiz;
    private RelativeLayout relate_shext;
    private SeekBar seekbar_caux;
    private SeekBar seekbar_caux2;
    private int tempUnit;
    private TextView tv_cauxSize;
    private TextView tv_cauxSize2;

    public ID6SetSystemTwo(Context context) {
        super(context);
        this.context = context;
        this.contentResolver = context.getContentResolver();
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_system_two, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
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
    }

    private void initView(View view) {
        this.img_twoDefaul = (ImageView) view.findViewById(C0899R.C0901id.img_twoDefaul);
        this.relate_shext = (RelativeLayout) view.findViewById(C0899R.C0901id.relate_shext);
        this.relate_auxweiz = (LinearLayout) view.findViewById(C0899R.C0901id.relate_auxweiz);
        this.tv_cauxSize = (TextView) view.findViewById(C0899R.C0901id.tv_cauxSize);
        SeekBar seekBar = (SeekBar) view.findViewById(C0899R.C0901id.seekbar_caux);
        this.seekbar_caux = seekBar;
        seekBar.setMax(12);
        this.seekbar_caux.setProgress(this.aux_index1);
        this.tv_cauxSize.setText(this.aux_index1 + "");
        this.seekbar_caux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                ID6SetSystemTwo.this.tv_cauxSize.setText(progress + "");
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
        this.seekbar_caux2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar3, int progress, boolean fromUser) {
                ID6SetSystemTwo.this.tv_cauxSize2.setText(progress + "");
                FileUtils.savaIntData(KeyConfig.CAR_AUX_INDEX2, progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar3) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar3) {
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
        this.rdg_shext.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.3
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
        this.rdgTempUnitRadioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_tempUnit_radioGroup);
        for (int i = 0; i < this.rdgTempUnitRadioGroup.getChildCount(); i++) {
            if (this.tempUnit == i) {
                RadioGroup radioGroup2 = this.rdgTempUnitRadioGroup;
                radioGroup2.check(radioGroup2.getChildAt(i).getId());
            }
        }
        this.rdgTempUnitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i2 = 0; i2 < count; i2++) {
                    if (checkedId == group.getChildAt(i2).getId()) {
                        FileUtils.savaIntData(KeyConfig.TempUnit, i2);
                        Log.i(ID6SetSystemTwo.TAG, "save tempUnit  : " + i2);
                    }
                    group.getChildAt(i2).setSelected(checkedId == group.getChildAt(i2).getId());
                }
            }
        });
        this.rdgFuelUnitRadioGroup = (RadioGroup) view.findViewById(C0899R.C0901id.rdg_fuelUnit_radioGroup);
        for (int i2 = 0; i2 < this.rdgFuelUnitRadioGroup.getChildCount(); i2++) {
            if (this.fuelUnit == i2) {
                RadioGroup radioGroup3 = this.rdgFuelUnitRadioGroup;
                radioGroup3.check(radioGroup3.getChildAt(i2).getId());
            }
        }
        this.rdgFuelUnitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.5
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int count = group.getChildCount();
                for (int i3 = 0; i3 < count; i3++) {
                    if (checkedId == group.getChildAt(i3).getId()) {
                        FileUtils.savaIntData(KeyConfig.FUEL_UNIT, i3);
                        Log.i(ID6SetSystemTwo.TAG, "save fuelUnit  : " + i3);
                    }
                    group.getChildAt(i3).setSelected(checkedId == group.getChildAt(i3).getId());
                }
            }
        });
        this.listview_music = (RecyclerView) view.findViewById(C0899R.C0901id.listview_music);
        List<LexusLsAppSelBean> listMusic = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 1, this.context);
        VerRecyclerAdapter adapterMusic = new VerRecyclerAdapter(this.context, listMusic, C0899R.C0902layout.app_third_item_id6);
        this.listview_music.setAdapter(adapterMusic);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.context);
        layoutManager.setOrientation(1);
        this.listview_music.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this.context, 1);
        divider.setDrawable(ContextCompat.getDrawable(this.context, C0899R.mipmap.als_id7_lp_line));
        adapterMusic.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.6
            @Override // com.wits.ksw.launcher.adpater.VerRecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "music app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(ID6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_PKG, pkg);
                Settings.System.putString(ID6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_MUSIC_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_MUSIC)) {
                    LauncherViewModel.setThirdMusic(false);
                } else {
                    LauncherViewModel.setThirdMusic(true);
                }
            }
        });
        this.listview_video = (RecyclerView) view.findViewById(C0899R.C0901id.listview_video);
        List<LexusLsAppSelBean> listVideo = AppInfoUtils.findAllAppsByExclude(AppInfoUtils.ATYS_DISMISS_MUSIC, 2, this.context);
        VerRecyclerAdapter adapterVideo = new VerRecyclerAdapter(this.context, listVideo, C0899R.C0902layout.app_third_item_id6);
        this.listview_video.setAdapter(adapterVideo);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this.context);
        layoutManager1.setOrientation(1);
        this.listview_video.setLayoutManager(layoutManager1);
        DividerItemDecoration divider1 = new DividerItemDecoration(this.context, 1);
        divider1.setDrawable(ContextCompat.getDrawable(this.context, C0899R.mipmap.als_id7_lp_line));
        adapterVideo.setAppsCheckListener(new VerRecyclerAdapter.IAppsCheckListener() { // from class: com.wits.ksw.settings.id6.twolayout.ID6SetSystemTwo.7
            @Override // com.wits.ksw.launcher.adpater.VerRecyclerAdapter.IAppsCheckListener
            public void checkedListener(String pkg, String cls, int pos) {
                Log.i("liuhao", "video app pkg =  " + pkg + "  cls = " + cls);
                Settings.System.putString(ID6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_PKG, pkg);
                Settings.System.putString(ID6SetSystemTwo.this.context.getContentResolver(), KeyConfig.KEY_THIRD_APP_VIDEO_CLS, cls);
                if (cls.equals(KeyConfig.CLS_LOCAL_VIDEO)) {
                    LauncherViewModel.setThirdVideo(false);
                } else {
                    LauncherViewModel.setThirdVideo(true);
                }
            }
        });
    }

    public void showLayout(int index) {
        switch (index) {
            case 1:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(0);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 2:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(0);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 3:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(0);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 4:
                this.rdgFuelUnitRadioGroup.setVisibility(0);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(8);
                return;
            case 5:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(0);
                this.listview_video.setVisibility(8);
                return;
            case 6:
                this.rdgFuelUnitRadioGroup.setVisibility(8);
                this.rdgTempUnitRadioGroup.setVisibility(8);
                this.relate_shext.setVisibility(8);
                this.relate_auxweiz.setVisibility(8);
                this.listview_music.setVisibility(8);
                this.listview_video.setVisibility(0);
                return;
            default:
                return;
        }
    }
}
