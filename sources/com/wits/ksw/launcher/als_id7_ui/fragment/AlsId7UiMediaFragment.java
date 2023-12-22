package com.wits.ksw.launcher.als_id7_ui.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.database.ContentObserver;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.p001v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.MainActivity;
import com.wits.ksw.databinding.AlsId7UiMediaBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes11.dex */
public class AlsId7UiMediaFragment extends Fragment {
    private static final String TAG = "AlsId7UiMediaFragment";
    private Animation animation;
    private AlsId7UiMediaBinding binding;
    private MainActivity mainActivity;
    private LauncherViewModel viewModel;
    public ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiMediaFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            Log.d(AlsId7UiMediaFragment.TAG, "onChange:");
            if (uri.equals(Settings.System.getUriFor(KswApplication.SKIN_NAME))) {
                String skinName = Settings.System.getString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME);
                if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setThumb(KswApplication.appContext.getDrawable(C0899R.C0900drawable.als_sp_id7_main_btn_music_red_progress_bar_slider));
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setCircleProgressColor(KswApplication.appContext.getColor(C0899R.color.als_id7_ui_status_red_text_color));
                } else if (ID8LauncherConstants.ID8_SKIN_PERSONAL.equals(skinName)) {
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setThumb(KswApplication.appContext.getDrawable(C0899R.C0900drawable.als_sp_id7_main_btn_music_yellow_progress_bar_slider));
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setCircleProgressColor(KswApplication.appContext.getColor(C0899R.color.als_id7_ui_status_yellow_text_color));
                } else {
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setThumb(KswApplication.appContext.getDrawable(C0899R.C0900drawable.als_sp_id7_main_btn_music_progress_bar_slider));
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setCircleProgressColor(KswApplication.appContext.getColor(C0899R.color.als_id7_ui_text_color));
                }
            }
        }
    };
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() { // from class: com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiMediaFragment.2
        @Override // com.wits.pms.IContentObserver
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i(AlsId7UiMediaFragment.TAG, "onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: MediaFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.m59of(getActivity()).get(LauncherViewModel.class);
        getActivity().getContentResolver().registerContentObserver(Settings.System.getUriFor(KswApplication.SKIN_NAME), true, this.contentObserver);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = (AlsId7UiMediaBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_als_id7_ui_media, null, false);
        String skinName = Settings.System.getString(KswApplication.appContext.getContentResolver(), KswApplication.SKIN_NAME);
        if (skinName == null || "".equals(skinName)) {
            skinName = ID8LauncherConstants.ID8_SKIN_EFFICIENT;
        }
        initProcess(skinName);
        return this.binding.getRoot();
    }

    private void initProcess(String skin2) {
        if (this.binding != null) {
            char c = '\uffff';
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
                    this.binding.musicInclude.alsProcess.setThumb(SkinCompatResources.getDrawable(getActivity(), C0899R.C0900drawable.als_sp_id7_main_btn_music_red_progress_bar_slider));
                    this.binding.musicInclude.alsProcess.setCircleProgressColor(SkinCompatResources.getColor(getActivity(), C0899R.color.als_id7_ui_status_red_text_color));
                    return;
                case 1:
                    this.binding.musicInclude.alsProcess.setThumb(SkinCompatResources.getDrawable(getActivity(), C0899R.C0900drawable.als_sp_id7_main_btn_music_yellow_progress_bar_slider));
                    this.binding.musicInclude.alsProcess.setCircleProgressColor(SkinCompatResources.getColor(getActivity(), C0899R.color.als_id7_ui_status_yellow_text_color));
                    return;
                default:
                    this.binding.musicInclude.alsProcess.setThumb(SkinCompatResources.getDrawable(getActivity(), C0899R.C0900drawable.als_sp_id7_main_btn_music_progress_bar_slider));
                    this.binding.musicInclude.alsProcess.setCircleProgressColor(SkinCompatResources.getColor(getActivity(), C0899R.color.als_id7_ui_text_color));
                    return;
            }
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        if (this.viewModel != null) {
            boolean isThreeMusic = LauncherViewModel.bThirdMusic.get().booleanValue();
            if (isThreeMusic) {
                isThreeMusic = true;
                setMusicMessage(false);
            } else {
                setMusicMessage(true);
            }
            Log.d(TAG, "onResume: isThreeMusic=" + isThreeMusic);
            setPlayOrPause(isThreeMusic);
        }
    }

    public void setPlayOrPause(boolean isThreeMusic) {
        String firstPackName = Settings.System.getString(KswApplication.appContext.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_CLS_1);
        String secondPackName = Settings.System.getString(KswApplication.appContext.getContentResolver(), LauncherViewModel.ALS_KEY_SHORTCUT_CLS_2);
        if (!"".equals(firstPackName) && firstPackName != null && !"".equals(secondPackName) && secondPackName != null && !KeyConfig.CLS_LOCAL_MUSIC.equals(firstPackName) && !KeyConfig.CLS_LOCAL_MUSIC.equals(secondPackName)) {
            try {
                boolean isPlaying = PowerManagerApp.getManager().getStatusBoolean("play");
                Log.d(TAG, "setPlayOrPause: isPlaying=" + isPlaying + "    isThreeMusic=" + isThreeMusic);
                if (isPlaying && isThreeMusic) {
                    this.viewModel.btnMusicPlayPause();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMusicMessage(boolean isEmpty) {
        if (isEmpty) {
            AlsId7UiMediaBinding alsId7UiMediaBinding = this.binding;
            if (alsId7UiMediaBinding != null) {
                alsId7UiMediaBinding.musicInclude.nameTextView.setVisibility(0);
                this.binding.musicInclude.inNameTextView.setVisibility(8);
                this.binding.musicInclude.artistTextView.setVisibility(0);
                this.binding.musicInclude.inArtistTextView.setVisibility(8);
                this.binding.musicInclude.albumTextView.setVisibility(0);
                this.binding.musicInclude.inAlbumTextView.setVisibility(8);
                this.binding.musicInclude.currentTimeTextView.setVisibility(0);
                this.binding.musicInclude.inCurrentTimeTextView.setVisibility(8);
                this.binding.musicInclude.totalTimeTextView.setVisibility(0);
                this.binding.musicInclude.inTotalTimeTextView.setVisibility(8);
                this.binding.musicInclude.timeMiddleTv.setVisibility(0);
                this.binding.musicInclude.inTimeMiddleTv.setVisibility(8);
                this.binding.musicInclude.alsProcess.setVisibility(0);
                this.binding.musicInclude.alsImage.setVisibility(0);
                this.binding.musicInclude.alsMiddlePoint.setVisibility(0);
                return;
            }
            return;
        }
        AlsId7UiMediaBinding alsId7UiMediaBinding2 = this.binding;
        if (alsId7UiMediaBinding2 != null) {
            alsId7UiMediaBinding2.musicInclude.nameTextView.setVisibility(8);
            this.binding.musicInclude.inNameTextView.setVisibility(0);
            this.binding.musicInclude.artistTextView.setVisibility(8);
            this.binding.musicInclude.inArtistTextView.setVisibility(0);
            this.binding.musicInclude.albumTextView.setVisibility(8);
            this.binding.musicInclude.inAlbumTextView.setVisibility(0);
            this.binding.musicInclude.currentTimeTextView.setVisibility(8);
            this.binding.musicInclude.inCurrentTimeTextView.setVisibility(0);
            this.binding.musicInclude.totalTimeTextView.setVisibility(8);
            this.binding.musicInclude.inTotalTimeTextView.setVisibility(0);
            this.binding.musicInclude.timeMiddleTv.setVisibility(8);
            this.binding.musicInclude.inTimeMiddleTv.setVisibility(0);
            this.binding.musicInclude.alsProcess.setVisibility(4);
            this.binding.musicInclude.alsImage.setVisibility(4);
            this.binding.musicInclude.alsMiddlePoint.setVisibility(4);
        }
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setMediaViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.i(TAG, "onActivityCreated: registerIContentObserver topApp ");
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        PowerManagerApp.unRegisterIContentObserver(this.topAppContentObserver);
        Log.i(TAG, "onDestroy: unRegisterIContentObserver topApp");
    }
}
