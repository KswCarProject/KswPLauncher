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
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.KswApplication;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AlsId7UiMediaBinding;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.ksw.settings.utlis_view.KeyConfig;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;
import skin.support.content.res.SkinCompatResources;

public class AlsId7UiMediaFragment extends Fragment {
    private static final String TAG = "AlsId7UiMediaFragment";
    private Animation animation;
    /* access modifiers changed from: private */
    public AlsId7UiMediaBinding binding;
    public ContentObserver contentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange, Uri uri) {
            Log.d(AlsId7UiMediaFragment.TAG, "onChange: 11111111111");
            if (uri.equals(Settings.System.getUriFor("SkinName"))) {
                String skinName = Settings.System.getString(KswApplication.appContext.getContentResolver(), "SkinName");
                if ("red".equals(skinName)) {
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setThumb(KswApplication.appContext.getDrawable(R.drawable.als_sp_id7_main_btn_music_red_progress_bar_slider));
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setCircleProgressColor(KswApplication.appContext.getColor(R.color.als_id7_ui_status_red_text_color));
                } else if ("yellow".equals(skinName)) {
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setThumb(KswApplication.appContext.getDrawable(R.drawable.als_sp_id7_main_btn_music_yellow_progress_bar_slider));
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setCircleProgressColor(KswApplication.appContext.getColor(R.color.als_id7_ui_status_yellow_text_color));
                } else {
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setThumb(KswApplication.appContext.getDrawable(R.drawable.als_sp_id7_main_btn_music_progress_bar_slider));
                    AlsId7UiMediaFragment.this.binding.musicInclude.alsProcess.setCircleProgressColor(KswApplication.appContext.getColor(R.color.als_id7_ui_text_color));
                }
            }
        }
    };
    private MainActivity mainActivity;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
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
    private LauncherViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: MediaFragment");
        this.viewModel = (LauncherViewModel) ViewModelProviders.of(getActivity()).get(LauncherViewModel.class);
        getActivity().getContentResolver().registerContentObserver(Settings.System.getUriFor("SkinName"), true, this.contentObserver);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = (AlsId7UiMediaBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_als_id7_ui_media, (ViewGroup) null, false);
        initProcess();
        return this.binding.getRoot();
    }

    private void initProcess() {
        AlsId7UiMediaBinding alsId7UiMediaBinding = this.binding;
        if (alsId7UiMediaBinding != null) {
            alsId7UiMediaBinding.musicInclude.alsProcess.setThumb(SkinCompatResources.getDrawable(getActivity(), R.drawable.als_sp_id7_main_btn_music_progress_bar_slider));
            this.binding.musicInclude.alsProcess.setCircleProgressColor(SkinCompatResources.getColor(getActivity(), R.color.als_id7_ui_text_color));
        }
    }

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
        String firstPackName = Settings.System.getString(KswApplication.appContext.getContentResolver(), LauncherViewModel.KEY_SHORTCUT_CLS_1);
        String secondPackName = Settings.System.getString(KswApplication.appContext.getContentResolver(), LauncherViewModel.KEY_SHORTCUT_CLS_2);
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

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setMediaViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        Log.i(TAG, "onActivityCreated: registerIContentObserver topApp ");
    }

    public void onDestroy() {
        super.onDestroy();
        PowerManagerApp.unRegisterIContentObserver(this.topAppContentObserver);
        Log.i(TAG, "onDestroy: unRegisterIContentObserver topApp");
    }
}
