package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;

public class ID8FragmentModus extends Fragment {
    private static final String TAG = "ID8FragmentModus";
    public static final int iconResId = 2131233429;
    public static final String name = "MODUS";
    public static final int nameResId = 2131558795;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modus_edit, container, false);
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "MODUS", R.drawable.id8_main_left_icon_modus, R.string.ksw_id8_modus));
        view.setTag("MODUS");
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
        TextView tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        if (TextUtils.isEmpty(skinName) || skinName.equals(ID8LauncherConstants.ID8_SKIN_PERSONAL)) {
            layout.setBackgroundResource(R.drawable.id8_main_edit_icon_modus_personal);
            tvDesc.setText(getString(R.string.ksw_id8_personal));
        } else if (skinName.equals(ID8LauncherConstants.ID8_SKIN_SPORT)) {
            layout.setBackgroundResource(R.drawable.id8_main_edit_icon_modus_sport);
            tvDesc.setText(getString(R.string.ksw_id8_sport));
        } else if (skinName.equals(ID8LauncherConstants.ID8_SKIN_EFFICIENT)) {
            layout.setBackgroundResource(R.drawable.id8_main_edit_icon_modus_efficient);
            tvDesc.setText(getString(R.string.ksw_id8_efficient));
        }
        return view;
    }
}
