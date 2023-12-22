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
public class ID8FragmentCarInfo extends Fragment {
    private static final String TAG = "ID8FragmentCarInfo";
    public static final int iconResId = 2131233448;
    public static final String name = "CAR INFO";
    public static final int nameResId = 2131558784;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_car_info_edit, container, false);
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "CAR INFO", C0899R.C0900drawable.id8_main_left_icon_car, C0899R.string.ksw_id8_abbr_car_info));
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        view.setTag("CAR INFO");
        return view;
    }
}
