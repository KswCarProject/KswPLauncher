package com.wits.ksw.launcher.als_id7.fragment;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.als_id7.model.AlsID7ViewModel;

/* loaded from: classes6.dex */
public class MusicPhoneFragment extends Fragment {
    private static final String TAG = "KswApplication";
    private com.wits.ksw.databinding.MusicPhoneFragment binding;
    private MainActivity mainActivity;
    private AlsID7ViewModel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mainActivity = (MainActivity) activity;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("aa", "onCreate: MediaFragment");
        this.viewModel = (AlsID7ViewModel) ViewModelProviders.m59of(getActivity()).get(AlsID7ViewModel.class);
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.wits.ksw.databinding.MusicPhoneFragment musicPhoneFragment = (com.wits.ksw.databinding.MusicPhoneFragment) DataBindingUtil.inflate(inflater, C0899R.C0902layout.als_id7_fragment_music, null, false);
        this.binding = musicPhoneFragment;
        return musicPhoneFragment.getRoot();
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.binding.setMusicPhoneViewModel(this.viewModel);
    }

    @Override // android.support.p001v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}
