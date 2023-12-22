package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;

/* loaded from: classes14.dex */
public class ID8GsFragmentSetting extends Fragment {
    private static final String TAG = "ID8FragmentSetting";
    public static final int iconResId = 2131233453;
    public static final String name = "SETTING";
    public static final int nameResId = 2131558786;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_gs_setting_edit, container, false);
        view.setOnDragListener(new CardId8GsOnDragListener(TAG, this, (ID8GsEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "SETTING", C0899R.C0900drawable.id8_main_left_icon_set, C0899R.string.ksw_id8_abbr_setting));
        view.setOnClickListener(new CardId8GsOnClickListener((ID8GsEditActivity) getActivity()));
        view.setTag("SETTING");
        return view;
    }
}