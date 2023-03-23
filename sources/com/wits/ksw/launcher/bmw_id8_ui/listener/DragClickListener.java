package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.ClipData;
import android.content.Intent;
import android.view.View;

public class DragClickListener implements View.OnLongClickListener {
    private final int iconResId;
    private View itemView;
    private final String name;
    private final int nameResId;

    public DragClickListener(View itemView2, String name2, int iconResId2, int nameResId2) {
        this.itemView = itemView2;
        this.name = name2;
        this.iconResId = iconResId2;
        this.nameResId = nameResId2;
    }

    public boolean onLongClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", this.name);
        intent.putExtra("iconRes", this.iconResId);
        intent.putExtra("nameRes", this.nameResId);
        ClipData.Item item = new ClipData.Item(intent);
        this.itemView.startDragAndDrop(new ClipData(this.name, new String[]{"text/plain"}, item), new View.DragShadowBuilder(this.itemView), (Object) null, 256);
        return false;
    }
}
