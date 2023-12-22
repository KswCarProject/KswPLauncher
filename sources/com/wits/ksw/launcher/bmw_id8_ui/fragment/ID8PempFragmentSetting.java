package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;

/* loaded from: classes14.dex */
public class ID8PempFragmentSetting extends Fragment {
    private static final String TAG = "ID8PempFragmentSetting";
    public static final int iconResId = 2131234870;
    public static final String name = "SETTING";
    public static final int nameResId = 2131558786;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_pemp_setting_edit, container, false);
        view.setOnDragListener(new CardId8PempOnDragListener(TAG, this, (ID8PempEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "SETTING", C0899R.C0900drawable.pemp_id8_main_left_icon_set, C0899R.string.ksw_id8_abbr_setting));
        view.setOnClickListener(new CardId8PempOnClickListener((ID8PempEditActivity) getActivity()));
        view.setTag("SETTING");
        return view;
    }
}
