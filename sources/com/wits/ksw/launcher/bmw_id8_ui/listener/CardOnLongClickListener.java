package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;

public class CardOnLongClickListener implements View.OnLongClickListener {
    private final Context context;

    public CardOnLongClickListener(Context context2) {
        this.context = context2;
    }

    public boolean onLongClick(View v) {
        this.context.startActivity(new Intent(this.context, ID8EditActivity.class));
        return false;
    }
}
