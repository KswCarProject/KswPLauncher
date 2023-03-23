package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8GsOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;

public class ID8GsFragmentModus extends Fragment {
    private static final String TAG = "ID8FragmentModus";
    public static final int iconResId = 2131233429;
    public static final String name = "MODUS";
    public static final int nameResId = 2131558795;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gs_modus_edit, container, false);
        view.setOnDragListener(new CardId8GsOnDragListener(TAG, this, (ID8GsEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "MODUS", R.drawable.id8_main_left_icon_modus, R.string.ksw_id8_modus));
        view.setTag("MODUS");
        view.setOnClickListener(new CardId8GsOnClickListener((ID8GsEditActivity) getActivity()));
        return view;
    }
}
