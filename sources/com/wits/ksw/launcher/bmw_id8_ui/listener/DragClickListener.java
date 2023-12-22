package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.ClipData;
import android.content.Intent;
import android.view.View;

/* loaded from: classes10.dex */
public class DragClickListener implements View.OnLongClickListener {
    private final int iconResId;
    private View itemView;
    private final String name;
    private final int nameResId;

    public DragClickListener(View itemView, String name, int iconResId, int nameResId) {
        this.itemView = itemView;
        this.name = name;
        this.iconResId = iconResId;
        this.nameResId = nameResId;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", this.name);
        intent.putExtra("iconRes", this.iconResId);
        intent.putExtra("nameRes", this.nameResId);
        ClipData.Item item = new ClipData.Item(intent);
        String[] mimeTypes = {"text/plain"};
        ClipData dragData = new ClipData(this.name, mimeTypes, item);
        View.DragShadowBuilder shadow = new View.DragShadowBuilder(this.itemView);
        this.itemView.startDragAndDrop(dragData, shadow, null, 256);
        return false;
    }
}
