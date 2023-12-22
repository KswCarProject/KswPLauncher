package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;

/* loaded from: classes10.dex */
public class CardId8PempOnClickListener implements View.OnClickListener {
    private final ID8PempEditActivity mActivity;

    public CardId8PempOnClickListener(ID8PempEditActivity activity) {
        this.mActivity = activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        ID8PempEditActivity iD8PempEditActivity = this.mActivity;
        if (iD8PempEditActivity != null) {
            iD8PempEditActivity.finish();
        }
    }
}
