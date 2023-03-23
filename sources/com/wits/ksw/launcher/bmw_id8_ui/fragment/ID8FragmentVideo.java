package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.VideoEditorDataBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

public class ID8FragmentVideo extends Fragment {
    private static final String TAG = "ID8FragmentVideo";
    public static final int iconResId = 2131233433;
    public static final String name = "VIDEO";
    public static final int nameResId = 2131558779;
    private VideoEditorDataBinding mEditBinding;
    private LauncherViewModel mViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.of((Fragment) this).get(LauncherViewModel.class);
        VideoEditorDataBinding videoEditorDataBinding = (VideoEditorDataBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_video_edit, container, false);
        this.mEditBinding = videoEditorDataBinding;
        videoEditorDataBinding.setMediaViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "VIDEO", R.drawable.id8_main_left_icon_video, R.string.ksw_id8_abbr_hd_video));
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        view.setTag("VIDEO");
        return view;
    }
}
