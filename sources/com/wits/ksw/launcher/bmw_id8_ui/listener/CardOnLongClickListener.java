package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;

/* loaded from: classes10.dex */
public class CardOnLongClickListener implements View.OnLongClickListener {
    private final Context context;

    public CardOnLongClickListener(Context context) {
        this.context = context;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        Intent intent = new Intent(this.context, ID8EditActivity.class);
        this.context.startActivity(intent);
        return false;
    }
}
