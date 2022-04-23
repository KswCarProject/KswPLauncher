package com.wits.ksw.launcher.view.lexusls.drag;

import android.view.View;

public interface DragSource {
    boolean isDelete();

    void onDropCompleted(View view, boolean z);

    void setDragController(DragController dragController);
}
