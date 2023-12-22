package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.lexusls.drag.DragController;

/* loaded from: classes13.dex */
public class DragLayer extends LinearLayout implements DragController.DraggingListener {
    private int deleteZoneId;
    private DragController.DraggingListener listener;
    private DragController mDragController;
    private RecyclerView mRecyclerView;

    public DragLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDragController(DragController controller) {
        this.mDragController = controller;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mDragController.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LOGE.m43E("onInterceptTouchEvent");
        return this.mDragController.onInterceptTouchEvent(ev);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        LOGE.m43E("MotionEvent");
        return this.mDragController.onTouchEvent(ev);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return this.mDragController.dispatchUnhandledMove(focused, direction);
    }

    public RecyclerView getDragView() {
        return this.mRecyclerView;
    }

    public void setDragView(RecyclerView view) {
        this.mRecyclerView = view;
    }

    public void loadChildView(View view) {
        if (view != null) {
            DropTarget dropTarget = (DropTarget) view.findViewById(C0899R.C0901id.draggable_layout);
            this.mDragController.addDropTarget(dropTarget);
        }
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DragController.DraggingListener
    public void onDragStart(DragSource source, Object info, int dragAction) {
        if (this.mRecyclerView != null) {
            for (int i = 0; i < this.mRecyclerView.getChildCount(); i++) {
                DropTarget view = (DropTarget) this.mRecyclerView.getChildAt(i).findViewById(C0899R.C0901id.draggable_layout);
                this.mDragController.addDropTarget(view);
            }
        }
        int i2 = getDeleteZoneId();
        View v = findViewById(i2);
        if (v != null) {
            DeleteZone dz = (DeleteZone) v;
            this.mDragController.addDropTarget(dz);
        }
        DragController.DraggingListener draggingListener = this.listener;
        if (draggingListener != null) {
            draggingListener.onDragStart(source, info, dragAction);
        }
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DragController.DraggingListener
    public void onDragEnd() {
        this.mDragController.removeAllDropTargets();
    }

    public void setDeleteZone(DeleteZone deleteZone) {
        this.mDragController.addDropTarget(deleteZone);
    }

    public int getDeleteZoneId() {
        return this.deleteZoneId;
    }

    public void setDeleteZoneId(int deleteZoneId) {
        this.deleteZoneId = deleteZoneId;
    }

    public void setDraggingListener(DragController.DraggingListener listener) {
        this.listener = listener;
    }

    public DragController.DraggingListener getDraggingListener() {
        return this.listener;
    }
}
