package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnLongClickListener;

public class ID8GsFragmentAddWidget extends Fragment {
    public static final String name = "ADD WIDGET";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_widget, container, false);
        view.setOnLongClickListener(new CardOnLongClickListener(getContext()));
        return view;
    }
}
