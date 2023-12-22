package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnLongClickListener;

/* loaded from: classes14.dex */
public class ID8FragmentAddWidget extends Fragment {
    public static final String name = "ADD WIDGET";

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_add_widget, container, false);
        view.setOnLongClickListener(new CardOnLongClickListener(getContext()));
        return view;
    }
}
