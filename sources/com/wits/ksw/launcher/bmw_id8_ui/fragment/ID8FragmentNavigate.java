package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;

/* loaded from: classes14.dex */
public class ID8FragmentNavigate extends Fragment {
    private static final String TAG = "FragmentNavigate";
    public static final int iconResId = 2131233452;
    public static final String name = "NAVIGATE";
    public static final int nameResId = 2131558788;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_navigate_edit, container, false);
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "NAVIGATE", C0899R.C0900drawable.id8_main_left_icon_navi, C0899R.string.ksw_id8_abbr_tel_navigate));
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        view.setTag("NAVIGATE");
        return view;
    }
}
