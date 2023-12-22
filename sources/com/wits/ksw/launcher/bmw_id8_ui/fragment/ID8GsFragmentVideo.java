package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.VideoGsEditorDataBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes14.dex */
public class ID8GsFragmentVideo extends Fragment {
    private static final String TAG = "ID8FragmentVideo";
    public static final int iconResId = 2131233454;
    public static final String name = "VIDEO";
    public static final int nameResId = 2131558785;
    private VideoGsEditorDataBinding mEditBinding;
    private LauncherViewModel mViewModel;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.m61of(this).get(LauncherViewModel.class);
        VideoGsEditorDataBinding videoGsEditorDataBinding = (VideoGsEditorDataBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_gs_video_edit, container, false);
        this.mEditBinding = videoGsEditorDataBinding;
        videoGsEditorDataBinding.setMediaViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardId8GsOnDragListener(TAG, this, (ID8GsEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "VIDEO", C0899R.C0900drawable.id8_main_left_icon_video, C0899R.string.ksw_id8_abbr_hd_video));
        view.setOnClickListener(new CardId8GsOnClickListener((ID8GsEditActivity) getActivity()));
        view.setTag("VIDEO");
        return view;
    }
}
