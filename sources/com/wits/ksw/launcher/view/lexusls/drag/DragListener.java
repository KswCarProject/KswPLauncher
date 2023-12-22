package com.wits.ksw.launcher.view.lexusls.drag;

import android.view.View;

/* loaded from: classes13.dex */
public interface DragListener {
    void onDragStarted(View source);

    void onDropCompleted(View source, View target, boolean success);
}
