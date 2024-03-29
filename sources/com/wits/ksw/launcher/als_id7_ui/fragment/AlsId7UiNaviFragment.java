package com.wits.ksw.launcher.als_id7_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.database.ContentObserver;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AlsId7UiNaviBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class AlsId7UiNaviFragment extends Fragment {
    /* access modifiers changed from: private */
    public static final String TAG = AlsId7UiNaviFragment.class.getSimpleName();
    public ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange, Uri uri) {
            Log.d(AlsId7UiNaviFragment.TAG, "onChange:");
            if (uri.equals(Settings.System.getUriFor(KswApplication.SKIN_NAME))) {
                String skinName = Settings.System.getString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME);
                if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
                    AlsId7UiNaviFragment.this.naviSubViewBinding.naviLayout.alsId7MainNaviImg.setImageResource(R.drawable.als_sp_id7_main_btn_navi_red);
                    AlsId7UiNaviFragment.this.naviSubViewBinding.phoneLayout.alsId7MainPhoneImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_phone_red);
                } else if (ID8LauncherConstants.ID8_SKIN_PERSONAL.equals(skinName)) {
                    AlsId7UiNaviFragment.this.naviSubViewBinding.naviLayout.alsId7MainNaviImg.setImageResource(R.drawable.als_sp_id7_main_btn_navi_yellow);
                    AlsId7UiNaviFragment.this.naviSubViewBinding.phoneLayout.alsId7MainPhoneImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_phone_yellow);
                } else {
                    AlsId7UiNaviFragment.this.naviSubViewBinding.naviLayout.alsId7MainNaviImg.setImageResource(R.drawable.als_sp_id7_main_btn_navi);
                    AlsId7UiNaviFragment.this.naviSubViewBinding.phoneLayout.alsId7MainPhoneImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_phone);
                }
            }
        }
    };
    private LauncherViewModel launcherViewModel;
    /* access modifiers changed from: private */
    public AlsId7UiNaviBinding naviSubViewBinding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: AlsId7UiNaviFragment");
        this.launcherViewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
        getActivity().getContentResolver().registerContentObserver(Settings.System.getUriFor(KswApplication.SKIN_NAME), true, this.contentObserver);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.launcherViewModel.hicar.set(false);
        this.naviSubViewBinding = (AlsId7UiNaviBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_als_id7_ui_navi, (ViewGroup) null, false);
        String skinName = Settings.System.getString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME);
        if (skinName == null || "".equals(skinName)) {
            skinName = ID8LauncherConstants.ID8_SKIN_EFFICIENT;
        }
        initView(skinName);
        return this.naviSubViewBinding.getRoot();
    }

    private void initView(String skin2) {
        if (this.naviSubViewBinding != null) {
            char c = 65535;
            switch (skin2.hashCode()) {
                case -734239628:
                    if (skin2.equals(ID8LauncherConstants.ID8_SKIN_PERSONAL)) {
                        c = 1;
                        break;
                    }
                    break;
                case 112785:
                    if (skin2.equals(ID8LauncherConstants.ID8_SKIN_SPORT)) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.naviSubViewBinding.naviLayout.alsId7MainNaviImg.setImageResource(R.drawable.als_sp_id7_main_btn_navi_red);
                    this.naviSubViewBinding.phoneLayout.alsId7MainPhoneImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_phone_red);
                    return;
                case 1:
                    this.naviSubViewBinding.naviLayout.alsId7MainNaviImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_navi_yellow);
                    this.naviSubViewBinding.phoneLayout.alsId7MainPhoneImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_phone_yellow);
                    return;
                default:
                    this.naviSubViewBinding.naviLayout.alsId7MainNaviImg.setImageResource(R.drawable.als_sp_id7_main_btn_navi);
                    this.naviSubViewBinding.phoneLayout.alsId7MainPhoneImg.setBackgroundResource(R.drawable.als_sp_id7_main_btn_phone);
                    return;
            }
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AlsId7UiNaviBinding alsId7UiNaviBinding = this.naviSubViewBinding;
        if (alsId7UiNaviBinding != null) {
            alsId7UiNaviBinding.setNaviViewModel(this.launcherViewModel);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
