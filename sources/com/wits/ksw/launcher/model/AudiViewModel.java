package com.wits.ksw.launcher.model;

import android.database.ContentObserver;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.LoopRotarySwitchView;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AudiViewModel extends LauncherViewModel {
    public LoopRotarySwitchView.OnItemSelectedListener OnItemSelectedListener;
    private final int RIGHT_UI_CARINFO;
    private final int RIGHT_UI_LOGO_AND_NAVI;
    private final int RIGHT_UI_LOGO_ONLY;
    private final int RIGHT_UI_MEDIA;
    private final int RIGHT_UI_NOTHING;
    public ObservableInt carBgPicId;
    public ObservableInt carInfoView;
    private int[] carPic;
    public ObservableInt carPicId;
    private int defaultLeftId;
    private int defaultRightId;
    private int leftUiId;
    private List<ObservableInt> listShowView;
    public ObservableInt logoView;
    public ObservableInt mediaView;
    public ObservableInt noView;
    public LoopRotarySwitchView.OnItemClickListener onItemClickListener;
    Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    /* access modifiers changed from: private */
    public int rightUiId;

    public AudiViewModel() {
        this.defaultLeftId = 1;
        this.defaultRightId = 0;
        this.leftUiId = 0;
        this.rightUiId = 0;
        this.carPicId = new ObservableInt();
        this.carBgPicId = new ObservableInt();
        this.carPic = new int[]{0, R.drawable.audi_left_car_a4, R.drawable.audi_left_car_a4_old, R.drawable.audi_left_car_a4l, R.drawable.audi_left_car_a3, R.drawable.audi_left_car_a5, R.drawable.audi_left_car_a6, R.drawable.audi_left_car_q3, R.drawable.audi_left_car_q5, R.drawable.audi_left_car_q7};
        this.noView = new ObservableInt();
        this.logoView = new ObservableInt();
        this.carInfoView = new ObservableInt();
        this.mediaView = new ObservableInt();
        this.RIGHT_UI_NOTHING = 0;
        this.RIGHT_UI_LOGO_AND_NAVI = 1;
        this.RIGHT_UI_LOGO_ONLY = 2;
        this.RIGHT_UI_CARINFO = 3;
        this.RIGHT_UI_MEDIA = 4;
        this.listShowView = new ArrayList();
        this.onItemClickListener = new LoopRotarySwitchView.OnItemClickListener() {
            public void onItemClick(int item, View view) {
                AudiViewModel.this.onClick(view);
            }
        };
        this.OnItemSelectedListener = new LoopRotarySwitchView.OnItemSelectedListener() {
            public void selected(int item, View view) {
                AudiViewModel.this.refreshCarBgPic(view);
            }
        };
        this.onPropertyChangedCallback = new Observable.OnPropertyChangedCallback() {
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (AudiViewModel.this.rightUiId != 1) {
                    return;
                }
                if (AudiViewModel.this.naviInfo.showGuideView.get() == 8) {
                    AudiViewModel.this.logoView.set(0);
                } else {
                    AudiViewModel.this.logoView.set(8);
                }
            }
        };
        this.listShowView = Arrays.asList(new ObservableInt[]{this.noView, this.naviInfo.showGuideView, this.logoView, this.carInfoView, this.mediaView});
        this.naviInfo.showGuideView.addOnPropertyChangedCallback(this.onPropertyChangedCallback);
        initLeftUi();
        initRightUi();
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.AUDI_UI_LEFT_ID), false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                AudiViewModel.this.initLeftUi();
            }
        });
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.AUDI_UI_RIGHT_ID), false, new ContentObserver(new Handler()) {
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                AudiViewModel.this.initRightUi();
            }
        });
    }

    public void initLeftUi() {
        int i = Settings.System.getInt(this.context.getContentResolver(), KeyConfig.AUDI_UI_LEFT_ID, this.defaultLeftId);
        this.leftUiId = i;
        if (i >= 0) {
            int[] iArr = this.carPic;
            if (i < iArr.length) {
                this.carPicId.set(iArr[i]);
            }
        }
    }

    /* access modifiers changed from: private */
    public void initRightUi() {
        int i = Settings.System.getInt(this.context.getContentResolver(), KeyConfig.AUDI_UI_RIGHT_ID, this.defaultRightId);
        this.rightUiId = i;
        setRightUi(i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_apps /*2131297605*/:
                openApps(view);
                break;
            case R.id.rl_bt /*2131297606*/:
                openBtApp(view);
                break;
            case R.id.rl_car /*2131297607*/:
                openCar(view);
                break;
            case R.id.rl_dashboard /*2131297609*/:
                openDashboard(view);
                break;
            case R.id.rl_dvr /*2131297610*/:
                openDvr(view);
                break;
            case R.id.rl_easyconnection /*2131297611*/:
                openShouJiHuLian(view);
                break;
            case R.id.rl_music /*2131297613*/:
                openMusicMulti(view);
                break;
            case R.id.rl_navi /*2131297614*/:
                openNaviApp(view);
                break;
            case R.id.rl_settings /*2131297615*/:
                openSettings(view);
                break;
            case R.id.rl_video /*2131297616*/:
                openVideoMulti(view);
                break;
        }
        refreshCarBgPic(view);
    }

    /* access modifiers changed from: private */
    public void refreshCarBgPic(View view) {
        switch (view.getId()) {
            case R.id.rl_apps /*2131297605*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_app);
                return;
            case R.id.rl_bt /*2131297606*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_bt);
                return;
            case R.id.rl_car /*2131297607*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_car);
                return;
            case R.id.rl_dashboard /*2131297609*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_dashboard);
                return;
            case R.id.rl_dvr /*2131297610*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_dvr);
                return;
            case R.id.rl_easyconnection /*2131297611*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_easyconnection);
                return;
            case R.id.rl_music /*2131297613*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_music);
                return;
            case R.id.rl_navi /*2131297614*/:
            case R.id.rl_video /*2131297616*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_navi);
                return;
            case R.id.rl_settings /*2131297615*/:
                this.carBgPicId.set(R.drawable.audi_left_bk_settings);
                return;
            default:
                return;
        }
    }

    private void setRightUi(int ui) {
        this.rightUiId = ui;
        for (int i = 0; i < this.listShowView.size(); i++) {
            if (ui == i) {
                this.listShowView.get(i).set(0);
            } else {
                this.listShowView.get(i).set(8);
            }
        }
        if (ui == 1) {
            this.listShowView.get(1).set(8);
            this.listShowView.get(2).set(0);
            this.naviInfo.setShowGuideEnable(true);
            return;
        }
        this.naviInfo.setShowGuideEnable(false);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        if (this.onPropertyChangedCallback != null) {
            this.naviInfo.showGuideView.removeOnPropertyChangedCallback(this.onPropertyChangedCallback);
        }
        if (this.otherReceiver != null) {
            this.context.unregisterReceiver(this.otherReceiver);
        }
    }
}
