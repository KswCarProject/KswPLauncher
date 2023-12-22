package com.wits.ksw.launcher.view.lexusls.drag;

import android.graphics.Rect;

/* loaded from: classes13.dex */
public interface DropTarget {
    boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo, Rect recycle);

    void getHitRect(Rect outRect);

    int getLeft();

    void getLocationOnScreen(int[] loc);

    int getTop();

    void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);

    void onDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo);
}
