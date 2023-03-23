package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.MusicGsEditorDataBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID8GsFragmentMusic extends Fragment {
    private static final String TAG = "FragmentMusic";
    public static final int iconResId = 2131233079;
    public static final String name = "MUSIC";
    public static final int nameResId = 2131558796;
    private MusicGsEditorDataBinding mEditBinding;
    private LauncherViewModel mViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.of((Fragment) this).get(LauncherViewModel.class);
        MusicGsEditorDataBinding musicGsEditorDataBinding = (MusicGsEditorDataBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_gs_music_edit, container, false);
        this.mEditBinding = musicGsEditorDataBinding;
        musicGsEditorDataBinding.setMediaViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardId8GsOnDragListener(TAG, this, (ID8GsEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "MUSIC", R.drawable.id8_gs_main_left_icon_music, R.string.ksw_id8_music));
        view.setOnClickListener(new CardId8GsOnClickListener((ID8GsEditActivity) getActivity()));
        view.setTag("MUSIC");
        return view;
    }
}
