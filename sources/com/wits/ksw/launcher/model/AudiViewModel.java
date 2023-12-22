package com.wits.ksw.launcher.model;

import android.database.ContentObserver;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.LoopRotarySwitchView;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
public class AudiViewModel extends LauncherViewModel {
    private List<ObservableInt> listShowView;
    private int defaultLeftId = 1;
    private int defaultRightId = 0;
    private int leftUiId = 0;
    private int rightUiId = 0;
    public ObservableInt carPicId = new ObservableInt();
    public ObservableInt carBgPicId = new ObservableInt();
    private int[] carPic = {0, C0899R.C0900drawable.audi_left_car_a4, C0899R.C0900drawable.audi_left_car_a4_old, C0899R.C0900drawable.audi_left_car_a4l, C0899R.C0900drawable.audi_left_car_a3, C0899R.C0900drawable.audi_left_car_a5, C0899R.C0900drawable.audi_left_car_a6, C0899R.C0900drawable.audi_left_car_q3, C0899R.C0900drawable.audi_left_car_q5, C0899R.C0900drawable.audi_left_car_q7};
    public ObservableInt noView = new ObservableInt();
    public ObservableInt logoView = new ObservableInt();
    public ObservableInt carInfoView = new ObservableInt();
    public ObservableInt mediaView = new ObservableInt();
    private final int RIGHT_UI_NOTHING = 0;
    private final int RIGHT_UI_LOGO_AND_NAVI = 1;
    private final int RIGHT_UI_LOGO_ONLY = 2;
    private final int RIGHT_UI_CARINFO = 3;
    private final int RIGHT_UI_MEDIA = 4;
    public LoopRotarySwitchView.OnItemClickListener onItemClickListener = new LoopRotarySwitchView.OnItemClickListener() { // from class: com.wits.ksw.launcher.model.AudiViewModel.3
        @Override // com.wits.ksw.launcher.view.LoopRotarySwitchView.OnItemClickListener
        public void onItemClick(int item, View view) {
            AudiViewModel.this.onClick(view);
        }
    };
    public LoopRotarySwitchView.OnItemSelectedListener OnItemSelectedListener = new LoopRotarySwitchView.OnItemSelectedListener() { // from class: com.wits.ksw.launcher.model.AudiViewModel.4
        @Override // com.wits.ksw.launcher.view.LoopRotarySwitchView.OnItemSelectedListener
        public void selected(int item, View view) {
            AudiViewModel.this.refreshCarBgPic(view);
        }
    };
    Observable.OnPropertyChangedCallback onPropertyChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.wits.ksw.launcher.model.AudiViewModel.5
        @Override // android.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable sender, int propertyId) {
            if (AudiViewModel.this.rightUiId == 1) {
                if (AudiViewModel.this.naviInfo.showGuideView.get() == 8) {
                    AudiViewModel.this.logoView.set(0);
                } else {
                    AudiViewModel.this.logoView.set(8);
                }
            }
        }
    };

    public AudiViewModel() {
        this.listShowView = new ArrayList();
        this.listShowView = Arrays.asList(this.noView, this.naviInfo.showGuideView, this.logoView, this.carInfoView, this.mediaView);
        this.naviInfo.showGuideView.addOnPropertyChangedCallback(this.onPropertyChangedCallback);
        initLeftUi();
        initRightUi();
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.AUDI_UI_LEFT_ID), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.AudiViewModel.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                AudiViewModel.this.initLeftUi();
            }
        });
        this.context.getContentResolver().registerContentObserver(Settings.System.getUriFor(KeyConfig.AUDI_UI_RIGHT_ID), false, new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.model.AudiViewModel.2
            @Override // android.database.ContentObserver
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

    /* JADX INFO: Access modifiers changed from: private */
    public void initRightUi() {
        int i = Settings.System.getInt(this.context.getContentResolver(), KeyConfig.AUDI_UI_RIGHT_ID, this.defaultRightId);
        this.rightUiId = i;
        setRightUi(i);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C0899R.C0901id.rl_apps /* 2131297652 */:
                openApps(view);
                break;
            case C0899R.C0901id.rl_bt /* 2131297653 */:
                openBtApp(view);
                break;
            case C0899R.C0901id.rl_car /* 2131297654 */:
                openCar(view);
                break;
            case C0899R.C0901id.rl_dashboard /* 2131297656 */:
                openDashboard(view);
                break;
            case C0899R.C0901id.rl_dvr /* 2131297657 */:
                openDvr(view);
                break;
            case C0899R.C0901id.rl_easyconnection /* 2131297658 */:
                openShouJiHuLian(view);
                break;
            case C0899R.C0901id.rl_music /* 2131297660 */:
                openMusicMulti(view);
                break;
            case C0899R.C0901id.rl_navi /* 2131297661 */:
                openNaviApp(view);
                break;
            case C0899R.C0901id.rl_settings /* 2131297662 */:
                openSettings(view);
                break;
            case C0899R.C0901id.rl_video /* 2131297663 */:
                openVideoMulti(view);
                break;
        }
        refreshCarBgPic(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCarBgPic(View view) {
        switch (view.getId()) {
            case C0899R.C0901id.rl_apps /* 2131297652 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_app);
                return;
            case C0899R.C0901id.rl_bt /* 2131297653 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_bt);
                return;
            case C0899R.C0901id.rl_car /* 2131297654 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_car);
                return;
            case C0899R.C0901id.rl_contain /* 2131297655 */:
            case C0899R.C0901id.rl_modus_container /* 2131297659 */:
            default:
                return;
            case C0899R.C0901id.rl_dashboard /* 2131297656 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_dashboard);
                return;
            case C0899R.C0901id.rl_dvr /* 2131297657 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_dvr);
                return;
            case C0899R.C0901id.rl_easyconnection /* 2131297658 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_easyconnection);
                return;
            case C0899R.C0901id.rl_music /* 2131297660 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_music);
                return;
            case C0899R.C0901id.rl_navi /* 2131297661 */:
            case C0899R.C0901id.rl_video /* 2131297663 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_navi);
                return;
            case C0899R.C0901id.rl_settings /* 2131297662 */:
                this.carBgPicId.set(C0899R.C0900drawable.audi_left_bk_settings);
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

    @Override // com.wits.ksw.launcher.model.LauncherViewModel, com.wits.ksw.launcher.base.BaseViewModel, android.arch.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        if (this.onPropertyChangedCallback != null) {
            this.naviInfo.showGuideView.removeOnPropertyChangedCallback(this.onPropertyChangedCallback);
        }
        if (this.otherReceiver != null) {
            this.context.unregisterReceiver(this.otherReceiver);
        }
    }
}
