package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.MusicEditorDataBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID8FragmentMusic extends Fragment {
    private static final String TAG = "FragmentMusic";
    public static final int iconResId = 2131233430;
    public static final String name = "MUSIC";
    public static final int nameResId = 2131558796;
    private MusicEditorDataBinding mEditBinding;
    private LauncherViewModel mViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.of((Fragment) this).get(LauncherViewModel.class);
        MusicEditorDataBinding musicEditorDataBinding = (MusicEditorDataBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_music_edit, container, false);
        this.mEditBinding = musicEditorDataBinding;
        musicEditorDataBinding.setMediaViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "MUSIC", R.drawable.id8_main_left_icon_music, R.string.ksw_id8_music));
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        view.setTag("MUSIC");
        return view;
    }
}
