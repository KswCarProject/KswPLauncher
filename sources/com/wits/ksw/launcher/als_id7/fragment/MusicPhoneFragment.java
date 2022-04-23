package com.wits.ksw.launcher.als_id7.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

public class MusicPhoneFragment extends Fragment {
    private static final String TAG = "KSWLauncher";
    private com.wits.ksw.databinding.MusicPhoneFragment binding;
    private MainActivity mainActivity;
    private AlsID7ViewModel viewModel;

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
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
