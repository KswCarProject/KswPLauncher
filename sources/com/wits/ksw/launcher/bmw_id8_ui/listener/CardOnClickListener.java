package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;

/* loaded from: classes10.dex */
public class CardOnClickListener implements View.OnClickListener {
    private final ID8EditActivity mActivity;

    public CardOnClickListener(ID8EditActivity activity) {
        this.mActivity = activity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        ID8EditActivity iD8EditActivity = this.mActivity;
        if (iD8EditActivity != null) {
            iD8EditActivity.finish();
        }
    }
}
