package com.wits.ksw.launcher.als_id7.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.BuildConfig;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;
import com.wits.ksw.launcher.model.MediaImpl;
import com.wits.pms.IContentObserver;
import com.wits.pms.statuscontrol.PowerManagerApp;

public class MusicPhoneFragment extends Fragment {
    private static final String TAG = "KSWLauncher";
    private com.wits.ksw.databinding.MusicPhoneFragment binding;
    private IContentObserver.Stub mMusicObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            Log.i("KSWLauncher", "onChange: mMusicObserver");
            if (MusicPhoneFragment.this.viewModel != null) {
                try {
                    boolean playing = PowerManagerApp.getManager().getStatusBoolean("play");
                    boolean isMusicStop = PowerManagerApp.getManager().getStatusBoolean("music_stop");
                    Log.i("KSWLauncher", "onChange: mMusicObserver playing=" + playing + " isMusicStop=" + isMusicStop);
                    if (isMusicStop) {
                        MusicPhoneFragment.this.viewModel.setMusicPlayState(false);
                    } else {
                        MusicPhoneFragment.this.viewModel.setMusicPlayState(playing);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private MainActivity mainActivity;
    private IContentObserver.Stub topAppContentObserver = new IContentObserver.Stub() {
        public void onChange() throws RemoteException {
            try {
                String topApp = PowerManagerApp.getStatusString("topApp");
                Log.i("KSWLauncher", "111onChange: topApp=" + topApp);
                if (TextUtils.equals(topApp, BuildConfig.APPLICATION_ID)) {
                    MediaImpl.getInstance().initData();
                    if (MusicPhoneFragment.this.viewModel != null) {
                        Log.d("KSWLauncher", "111onChange ismusicplay =" + MusicPhoneFragment.this.viewModel.isMusicPlay());
                        MusicPhoneFragment.this.viewModel.setMusicPlayState(MusicPhoneFragment.this.viewModel.isMusicPlay());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    /* access modifiers changed from: private */
    public AlsID7ViewModel viewModel;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: MediaFragment");
        this.viewModel = (AlsID7ViewModel) ViewModelProviders.of(getActivity()).get(AlsID7ViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.wits.ksw.databinding.MusicPhoneFragment musicPhoneFragment = (com.wits.ksw.databinding.MusicPhoneFragment) DataBindingUtil.inflate(inflater, R.layout.als_id7_fragment_music, (ViewGroup) null, false);
        this.binding = musicPhoneFragment;
        return musicPhoneFragment.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setMusicPhoneViewModel(this.viewModel);
        PowerManagerApp.registerIContentObserver("topApp", this.topAppContentObserver);
        PowerManagerApp.registerIContentObserver("play", this.mMusicObserver);
        PowerManagerApp.registerIContentObserver("music_stop", this.mMusicObserver);
        Log.i("KSWLauncher", "onActivityCreated: registerIContentObserver topApp ");
    }

    public void onDestroy() {
        super.onDestroy();
        PowerManagerApp.unRegisterIContentObserver(this.topAppContentObserver);
        PowerManagerApp.unRegisterIContentObserver(this.mMusicObserver);
        Log.i("KSWLauncher", "onDestroy: unRegisterIContentObserver topApp");
    }
}
