package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.PhoneGsEditorDataBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes14.dex */
public class ID8GsFragmentPhone extends Fragment {
    private static final String TAG = "ID8FragmentPhone";
    public static final int iconResId = 2131233098;
    public static final String name = "PHONE";
    public static final int nameResId = 2131558787;
    private PhoneGsEditorDataBinding mEditBinding;
    private LauncherViewModel mViewModel;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.m61of(this).get(LauncherViewModel.class);
        PhoneGsEditorDataBinding phoneGsEditorDataBinding = (PhoneGsEditorDataBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_gs_phone_edit, container, false);
        this.mEditBinding = phoneGsEditorDataBinding;
        phoneGsEditorDataBinding.setBtViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardId8GsOnDragListener(TAG, this, (ID8GsEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "PHONE", C0899R.C0900drawable.id8_gs_main_left_icon_bt, C0899R.string.ksw_id8_abbr_tel));
        view.setTag("PHONE");
        view.setOnClickListener(new CardId8GsOnClickListener((ID8GsEditActivity) getActivity()));
        int bluetooth = Settings.System.getInt(getContext().getContentResolver(), "ksw_bluetooth", 0);
        Log.w(TAG, "onCreateView: phoneConState " + bluetooth);
        this.mViewModel.setPhoneConState(bluetooth);
        return view;
    }
}
