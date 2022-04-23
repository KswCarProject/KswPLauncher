package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.lexusls.drag.DragController;

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

    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mDragController.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LOGE.E("onInterceptTouchEvent");
        return this.mDragController.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        LOGE.E("MotionEvent");
        return this.mDragController.onTouchEvent(ev);
    }

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
            this.mDragController.addDropTarget((DropTarget) view.findViewById(R.id.draggable_layout));
        }
    }

    public void onDragStart(DragSource source, Object info, int dragAction) {
        if (this.mRecyclerView != null) {
            for (int i = 0; i < this.mRecyclerView.getChildCount(); i++) {
                this.mDragController.addDropTarget((DropTarget) this.mRecyclerView.getChildAt(i).findViewById(R.id.draggable_layout));
            }
        }
        View v = findViewById(getDeleteZoneId());
        if (v != null) {
            this.mDragController.addDropTarget((DeleteZone) v);
        }
        DragController.DraggingListener draggingListener = this.listener;
        if (draggingListener != null) {
            draggingListener.onDragStart(source, info, dragAction);
        }
    }

    public void onDragEnd() {
        this.mDragController.removeAllDropTargets();
    }

    public void setDeleteZone(DeleteZone deleteZone) {
        this.mDragController.addDropTarget(deleteZone);
    }

    public int getDeleteZoneId() {
        return this.deleteZoneId;
    }

    public void setDeleteZoneId(int deleteZoneId2) {
        this.deleteZoneId = deleteZoneId2;
    }

    public void setDraggingListener(DragController.DraggingListener listener2) {
        this.listener = listener2;
    }

    public DragController.DraggingListener getDraggingListener() {
        return this.listener;
    }
}
