package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;

/* loaded from: classes13.dex */
public class DragController {
    private static final boolean PROFILE_DRAWING_DURING_DRAG = false;
    private static final int REMAIN_TIME = 500;
    private static final String TAG = "liuhao DragController";
    private static final int VALID_ZONE = 60;
    private Object dragInfo;
    private DragSource dragSource;
    private DragView dragView;
    private boolean isDragging;
    private Context mContext;
    private InputMethodManager mInputMethodManager;
    private DropTarget mLastDropTarget;
    private DraggingListener mListener;
    private float mMotionDownX;
    private float mMotionDownY;
    private View mMoveTarget;
    private float mTouchOffsetX;
    private float mTouchOffsetY;
    private View mTuozhuaiView;
    private IBinder mWindowToken;
    private ScrollController scrollUtil;
    public static int DRAG_ACTION_MOVE = 0;
    public static int DRAG_ACTION_COPY = 1;
    private boolean isEnterFlag = false;
    private boolean isRightControlPageTurn = false;
    private boolean isLeftControlPageTurn = false;
    private Rect mRectTemp = new Rect();
    private final int[] mCoordinatesTemp = new int[2];
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private ArrayList<DropTarget> dropTargets = new ArrayList<>();
    Handler handler = new Handler() { // from class: com.wits.ksw.launcher.view.lexusls.drag.DragController.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    LOGE.m43E("Page Turning to right !!!");
                    DragController.this.scrollUtil.arrowScroll(false);
                    DragController.this.isRightControlPageTurn = false;
                    return;
                case 2:
                    LOGE.m43E("Page Turning to left !!!");
                    DragController.this.scrollUtil.arrowScroll(true);
                    DragController.this.isLeftControlPageTurn = false;
                    return;
                default:
                    return;
            }
        }
    };

    /* loaded from: classes13.dex */
    public interface DraggingListener {
        void onDragEnd();

        void onDragStart(DragSource source, Object info, int dragAction);
    }

    public DragController(Context context) {
        this.mContext = context;
    }

    public void startDragBitmap(View v, DragSource source, Object dragInfo, int dragAction) {
        this.mTuozhuaiView = v;
        Bitmap bitmap = getViewBitmap(v);
        if (bitmap == null) {
            return;
        }
        int[] loc = this.mCoordinatesTemp;
        v.getLocationOnScreen(loc);
        int screenX = loc[0];
        int screenY = loc[1];
        startDragBitmap(bitmap, screenX, screenY, 0, 0, bitmap.getWidth(), bitmap.getHeight(), source, dragInfo, dragAction);
        bitmap.recycle();
        if (dragAction == DRAG_ACTION_MOVE) {
            v.setVisibility(8);
        }
    }

    public void startDragBitmap(Bitmap bitmap, int screenX, int screenY, int textureLeft, int textureTop, int textureWidth, int textureHeight, DragSource source, Object dragInfo, int dragAction) {
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        }
        this.mInputMethodManager.hideSoftInputFromWindow(this.mWindowToken, 0);
        DraggingListener draggingListener = this.mListener;
        if (draggingListener != null) {
            draggingListener.onDragStart(source, dragInfo, dragAction);
        }
        float f = this.mMotionDownX;
        int registrationX = ((int) f) - screenX;
        float f2 = this.mMotionDownY;
        int registrationY = ((int) f2) - screenY;
        this.mTouchOffsetX = f - screenX;
        this.mTouchOffsetY = f2 - screenY;
        this.isDragging = true;
        this.dragSource = source;
        this.dragInfo = dragInfo;
        DragView dragView = new DragView(this.mContext, bitmap, registrationX, registrationY, textureLeft, textureTop, textureWidth, textureHeight);
        this.dragView = dragView;
        dragView.show(this.mWindowToken, (int) this.mMotionDownX, (int) this.mMotionDownY);
    }

    private void endDrag() {
        if (this.isDragging) {
            this.isDragging = false;
            View view = this.mTuozhuaiView;
            if (view != null) {
                view.setVisibility(0);
            }
            DraggingListener draggingListener = this.mListener;
            if (draggingListener != null) {
                draggingListener.onDragEnd();
            }
            DragView dragView = this.dragView;
            if (dragView != null) {
                dragView.remove();
                this.dragView = null;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            recordScreenSize();
        }
        int downX = clamp((int) ev.getRawX(), 0, this.displayMetrics.widthPixels);
        int downY = clamp((int) ev.getRawY(), 0, this.displayMetrics.heightPixels);
        switch (action) {
            case 0:
                this.mMotionDownX = downX;
                this.mMotionDownY = downY;
                this.mLastDropTarget = null;
                break;
            case 1:
            case 3:
                if (this.isDragging) {
                    drop(downX, downY);
                }
                endDrag();
                break;
        }
        return this.isDragging;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.isDragging) {
            int action = ev.getAction();
            int downX = clamp((int) ev.getRawX(), 0, this.displayMetrics.widthPixels);
            int downY = clamp((int) ev.getRawY(), 0, this.displayMetrics.heightPixels);
            switch (action) {
                case 0:
                    this.mMotionDownX = downX;
                    this.mMotionDownY = downY;
                    return true;
                case 1:
                    if (this.isDragging) {
                        drop(downX, downY);
                    }
                    endDrag();
                    return true;
                case 2:
                    this.dragView.move((int) ev.getRawX(), (int) ev.getRawY());
                    monitorPageTurning(ev, this.displayMetrics, this.dragView);
                    int[] coordinates = this.mCoordinatesTemp;
                    DropTarget dropTarget = findDropTarget(downX, downY, coordinates);
                    if (dropTarget != null) {
                        DropTarget dropTarget2 = this.mLastDropTarget;
                        if (dropTarget2 == dropTarget) {
                            dropTarget.onDragOver(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo);
                        } else {
                            if (dropTarget2 != null) {
                                dropTarget2.onDragExit(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo);
                            }
                            dropTarget.onDragEnter(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo);
                        }
                    } else {
                        DropTarget dropTarget3 = this.mLastDropTarget;
                        if (dropTarget3 != null) {
                            dropTarget3.onDragExit(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo);
                        }
                    }
                    this.mLastDropTarget = dropTarget;
                    return true;
                case 3:
                    endDrag();
                    return true;
                default:
                    return true;
            }
        }
        return false;
    }

    private void monitorPageTurning(MotionEvent ev, DisplayMetrics metrics, DragView itemView) {
        if (this.isDragging) {
            int rightValidZone = metrics.widthPixels - 60;
            if (rightValidZone <= ev.getRawX()) {
                if (!this.isRightControlPageTurn) {
                    this.handler.sendEmptyMessageDelayed(1, 500L);
                    this.isRightControlPageTurn = true;
                }
            } else {
                this.handler.removeMessages(1);
                this.isRightControlPageTurn = false;
            }
            if (60 >= ev.getRawX()) {
                if (!this.isLeftControlPageTurn) {
                    this.handler.sendEmptyMessageDelayed(2, 500L);
                    this.isLeftControlPageTurn = true;
                    return;
                }
                return;
            }
            this.handler.removeMessages(2);
            this.isLeftControlPageTurn = false;
        }
    }

    public void setScrollController(ScrollController controller) {
        this.scrollUtil = controller;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        LOGE.m43E("DragController dispatchKeyEvent \uff1aisDragging = " + this.isDragging);
        return this.isDragging;
    }

    void setMoveTarget(View view) {
        this.mMoveTarget = view;
    }

    public boolean dispatchUnhandledMove(View focused, int direction) {
        LOGE.m43E("DragLayer dispatchUnhandledMove focused= " + focused + " direction= " + direction);
        View view = this.mMoveTarget;
        return view != null && view.dispatchUnhandledMove(focused, direction);
    }

    private boolean drop(float x, float y) {
        int[] coordinates = this.mCoordinatesTemp;
        DropTarget dropTarget = findDropTarget((int) x, (int) y, coordinates);
        this.mListener.onDragEnd();
        if (dropTarget == null) {
            dropTarget = new DraggableLayout(this.mContext);
        }
        dropTarget.onDragExit(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo);
        if (dropTarget.acceptDrop(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo)) {
            dropTarget.onDrop(this.dragSource, coordinates[0], coordinates[1], (int) this.mTouchOffsetX, (int) this.mTouchOffsetY, this.dragView, this.dragInfo);
            View v = (View) dropTarget;
            LOGE.m43E("DragController============= view: left - >" + dropTarget.getLeft());
            this.dragSource.onDropCompleted(v, true);
            return true;
        }
        this.dragSource.onDropCompleted((View) dropTarget, false);
        return true;
    }

    public DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
        Rect r = this.mRectTemp;
        ArrayList<DropTarget> dropTargets = this.dropTargets;
        int count = dropTargets.size();
        for (int i = count - 1; i >= 0; i--) {
            DropTarget target = dropTargets.get(i);
            target.getHitRect(r);
            target.getLocationOnScreen(dropCoordinates);
            r.offset(dropCoordinates[0] - target.getLeft(), dropCoordinates[1] - target.getTop());
            if (r.contains(x, y)) {
                dropCoordinates[0] = x - dropCoordinates[0];
                dropCoordinates[1] = y - dropCoordinates[1];
                return target;
            }
        }
        return null;
    }

    private void recordScreenSize() {
        ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(this.displayMetrics);
    }

    private static int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        }
        if (val >= max) {
            return max - 1;
        }
        return val;
    }

    private Bitmap getViewBitmap(View v) {
        if (v == null) {
            return null;
        }
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e(TAG, "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public void setWindowToken(IBinder token) {
        this.mWindowToken = token;
    }

    public void setDraggingListener(DraggingListener l) {
        this.mListener = l;
    }

    public DraggingListener getDraggingListener() {
        return this.mListener;
    }

    public void removeDragListener(DragListener l) {
        this.mListener = null;
    }

    public void addDropTarget(DropTarget target) {
        if (!this.dropTargets.contains(target)) {
            this.dropTargets.add(target);
        }
    }

    public void removeDropTarget(DropTarget target) {
        this.dropTargets.remove(target);
    }

    public void removeAllDropTargets() {
        this.dropTargets = new ArrayList<>();
    }
}
