package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;

/* loaded from: classes14.dex */
public class ID8PempFragmentModus extends Fragment {
    private static final String TAG = "ID8PempFragmentModus";
    public static final int iconResId = 2131234867;
    public static final String name = "MODUS";
    public static final int nameResId = 2131558801;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_pemp_modus_edit, container, false);
        view.setOnDragListener(new CardId8PempOnDragListener(TAG, this, (ID8PempEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "MODUS", C0899R.C0900drawable.pemp_id8_main_left_icon_modus, C0899R.string.ksw_id8_modus));
        view.setTag("MODUS");
        view.setOnClickListener(new CardId8PempOnClickListener((ID8PempEditActivity) getActivity()));
        LinearLayout layout = (LinearLayout) view.findViewById(C0899R.C0901id.layout);
        TextView tvDesc = (TextView) view.findViewById(C0899R.C0901id.tv_desc);
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        if (TextUtils.isEmpty(skinName) || skinName.equals(ID8LauncherConstants.ID8_SKIN_PERSONAL)) {
            layout.setBackgroundResource(C0899R.C0900drawable.id8_main_edit_icon_modus_personal);
            tvDesc.setText(getString(C0899R.string.ksw_id8_personal));
        } else if (skinName.equals(ID8LauncherConstants.ID8_SKIN_SPORT)) {
            layout.setBackgroundResource(C0899R.C0900drawable.id8_main_edit_icon_modus_sport);
            tvDesc.setText(getString(C0899R.string.ksw_id8_sport));
        } else if (skinName.equals(ID8LauncherConstants.ID8_SKIN_EFFICIENT)) {
            layout.setBackgroundResource(C0899R.C0900drawable.id8_main_edit_icon_modus_efficient);
            tvDesc.setText(getString(C0899R.string.ksw_id8_efficient));
        }
        return view;
    }
}
