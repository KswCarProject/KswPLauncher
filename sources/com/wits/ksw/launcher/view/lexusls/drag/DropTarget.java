package com.wits.ksw.launcher.view.lexusls.drag;

import android.graphics.Rect;

public interface DropTarget {
    boolean acceptDrop(DragSource dragSource, int i, int i2, int i3, int i4, DragView dragView, Object obj);

    Rect estimateDropLocation(DragSource dragSource, int i, int i2, int i3, int i4, DragView dragView, Object obj, Rect rect);

    void getHitRect(Rect rect);

    int getLeft();

    void getLocationOnScreen(int[] iArr);

    int getTop();

    void onDragEnter(DragSource dragSource, int i, int i2, int i3, int i4, DragView dragView, Object obj);

    void onDragExit(DragSource dragSource, int i, int i2, int i3, int i4, DragView dragView, Object obj);

    void onDragOver(DragSource dragSource, int i, int i2, int i3, int i4, DragView dragView, Object obj);

    void onDrop(DragSource dragSource, int i, int i2, int i3, int i4, DragView dragView, Object obj);
}
