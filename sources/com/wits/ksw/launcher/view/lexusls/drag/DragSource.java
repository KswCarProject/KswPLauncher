package com.wits.ksw.launcher.view.lexusls.drag;

import android.view.View;

/* loaded from: classes13.dex */
public interface DragSource {
    boolean isDelete();

    void onDropCompleted(View target, boolean success);

    void setDragController(DragController dragger);
}
