package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8GsEditActivity;

/* loaded from: classes10.dex */
public class CardId8GsOnClickListener implements View.OnClickListener {
    private final ID8GsEditActivity mActivity;

    public CardId8GsOnClickListener(ID8GsEditActivity activity) {
        this.mActivity = activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        ID8GsEditActivity iD8GsEditActivity = this.mActivity;
        if (iD8GsEditActivity != null) {
            iD8GsEditActivity.finish();
        }
    }
}
