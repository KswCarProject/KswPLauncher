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
import com.wits.ksw.databinding.PhoneEditorPempDataBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;
import java.util.Date;

/* loaded from: classes14.dex */
public class ID8PempFragmentPhone extends Fragment {
    private static final String TAG = "ID8PempFragmentPhone";
    public static final int iconResId = 2131234864;
    public static final String name = "PHONE";
    public static final int nameResId = 2131558787;
    private PhoneEditorPempDataBinding mEditBinding;
    private LauncherViewModel mViewModel;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.m61of(this).get(LauncherViewModel.class);
        PhoneEditorPempDataBinding phoneEditorPempDataBinding = (PhoneEditorPempDataBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_pemp_phone_edit, container, false);
        this.mEditBinding = phoneEditorPempDataBinding;
        phoneEditorPempDataBinding.setBtViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardId8PempOnDragListener(TAG, this, (ID8PempEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "PHONE", C0899R.C0900drawable.pemp_id8_main_left_icon_bt, C0899R.string.ksw_id8_abbr_tel));
        view.setTag("PHONE");
        view.setOnClickListener(new CardId8PempOnClickListener((ID8PempEditActivity) getActivity()));
        int bluetooth = Settings.System.getInt(getContext().getContentResolver(), "ksw_bluetooth", 0);
        Log.w(TAG, "onCreateView: phoneConState " + bluetooth);
        this.mViewModel.setPhoneConState(bluetooth);
        this.mViewModel.setBtState();
        this.mViewModel.setMonthDay(new Date());
        return view;
    }
}
