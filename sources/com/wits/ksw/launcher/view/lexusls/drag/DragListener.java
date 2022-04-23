package com.wits.ksw.launcher.view.lexusls.drag;

import android.view.View;

public interface DragListener {
    void onDragStarted(View view);

    void onDropCompleted(View view, View view2, boolean z);
}
