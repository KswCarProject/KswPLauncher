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

public class DragController {
    public static int DRAG_ACTION_COPY = 1;
    public static int DRAG_ACTION_MOVE = 0;
    private static final boolean PROFILE_DRAWING_DURING_DRAG = false;
    private static final int REMAIN_TIME = 500;
    private static final String TAG = "liuhao DragController";
    private static final int VALID_ZONE = 60;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private Object dragInfo;
    private DragSource dragSource;
    private DragView dragView;
    private ArrayList<DropTarget> dropTargets = new ArrayList<>();
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    LOGE.E("Page Turning to right !!!");
                    DragController.this.scrollUtil.arrowScroll(false);
                    boolean unused = DragController.this.isRightControlPageTurn = false;
                    return;
                case 2:
                    LOGE.E("Page Turning to left !!!");
                    DragController.this.scrollUtil.arrowScroll(true);
                    boolean unused2 = DragController.this.isLeftControlPageTurn = false;
                    return;
                default:
                    return;
            }
        }
    };
    private boolean isDragging;
    private boolean isEnterFlag = false;
    /* access modifiers changed from: private */
    public boolean isLeftControlPageTurn = false;
    /* access modifiers changed from: private */
    public boolean isRightControlPageTurn = false;
    private Context mContext;
    private final int[] mCoordinatesTemp = new int[2];
    private InputMethodManager mInputMethodManager;
    private DropTarget mLastDropTarget;
    private DraggingListener mListener;
    private float mMotionDownX;
    private float mMotionDownY;
    private View mMoveTarget;
    private Rect mRectTemp = new Rect();
    private float mTouchOffsetX;
    private float mTouchOffsetY;
    private View mTuozhuaiView;
    private IBinder mWindowToken;
    /* access modifiers changed from: private */
    public ScrollController scrollUtil;

    public interface DraggingListener {
        void onDragEnd();

        void onDragStart(DragSource dragSource, Object obj, int i);
    }

    public DragController(Context context) {
        this.mContext = context;
    }

    public void startDragBitmap(View v, DragSource source, Object dragInfo2, int dragAction) {
        View view = v;
        this.mTuozhuaiView = view;
        Bitmap bitmap = getViewBitmap(v);
        if (bitmap != null) {
            int[] loc = this.mCoordinatesTemp;
            view.getLocationOnScreen(loc);
            int screenX = loc[0];
            startDragBitmap(bitmap, screenX, loc[1], 0, 0, bitmap.getWidth(), bitmap.getHeight(), source, dragInfo2, dragAction);
            bitmap.recycle();
            if (dragAction == DRAG_ACTION_MOVE) {
                view.setVisibility(8);
            }
        }
    }

    public void startDragBitmap(Bitmap bitmap, int screenX, int screenY, int textureLeft, int textureTop, int textureWidth, int textureHeight, DragSource source, Object dragInfo2, int dragAction) {
        int i = screenX;
        int i2 = screenY;
        DragSource dragSource2 = source;
        Object obj = dragInfo2;
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        }
        this.mInputMethodManager.hideSoftInputFromWindow(this.mWindowToken, 0);
        DraggingListener draggingListener = this.mListener;
        if (draggingListener != null) {
            draggingListener.onDragStart(dragSource2, obj, dragAction);
        } else {
            int i3 = dragAction;
        }
        float f = this.mMotionDownX;
        float f2 = this.mMotionDownY;
        this.mTouchOffsetX = f - ((float) i);
        this.mTouchOffsetY = f2 - ((float) i2);
        this.isDragging = true;
        this.dragSource = dragSource2;
        this.dragInfo = obj;
        DragView dragView2 = new DragView(this.mContext, bitmap, ((int) f) - i, ((int) f2) - i2, textureLeft, textureTop, textureWidth, textureHeight);
        this.dragView = dragView2;
        dragView2.show(this.mWindowToken, (int) this.mMotionDownX, (int) this.mMotionDownY);
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
            DragView dragView2 = this.dragView;
            if (dragView2 != null) {
                dragView2.remove();
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
                this.mMotionDownX = (float) downX;
                this.mMotionDownY = (float) downY;
                this.mLastDropTarget = null;
                break;
            case 1:
            case 3:
                if (this.isDragging) {
                    drop((float) downX, (float) downY);
                }
                endDrag();
                break;
        }
        return this.isDragging;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.isDragging) {
            return false;
        }
        int action = ev.getAction();
        int downX = clamp((int) ev.getRawX(), 0, this.displayMetrics.widthPixels);
        int downY = clamp((int) ev.getRawY(), 0, this.displayMetrics.heightPixels);
        switch (action) {
            case 0:
                MotionEvent motionEvent = ev;
                this.mMotionDownX = (float) downX;
                this.mMotionDownY = (float) downY;
                return true;
            case 1:
                MotionEvent motionEvent2 = ev;
                if (this.isDragging) {
                    drop((float) downX, (float) downY);
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
                MotionEvent motionEvent3 = ev;
                return true;
            default:
                MotionEvent motionEvent4 = ev;
                return true;
        }
    }

    private void monitorPageTurning(MotionEvent ev, DisplayMetrics metrics, DragView itemView) {
        if (this.isDragging) {
            if (((float) (metrics.widthPixels - 60)) > ev.getRawX()) {
                this.handler.removeMessages(1);
                this.isRightControlPageTurn = false;
            } else if (!this.isRightControlPageTurn) {
                this.handler.sendEmptyMessageDelayed(1, 500);
                this.isRightControlPageTurn = true;
            }
            if (((float) 60) < ev.getRawX()) {
                this.handler.removeMessages(2);
                this.isLeftControlPageTurn = false;
            } else if (!this.isLeftControlPageTurn) {
                this.handler.sendEmptyMessageDelayed(2, 500);
                this.isLeftControlPageTurn = true;
            }
        }
    }

    public void setScrollController(ScrollController controller) {
        this.scrollUtil = controller;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        LOGE.E("DragController dispatchKeyEvent ：isDragging = " + this.isDragging);
        return this.isDragging;
    }

    /* access modifiers changed from: package-private */
    public void setMoveTarget(View view) {
        this.mMoveTarget = view;
    }

    public boolean dispatchUnhandledMove(View focused, int direction) {
        LOGE.E("DragLayer dispatchUnhandledMove focused= " + focused + " direction= " + direction);
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
            LOGE.E("DragController============= view: left - >" + dropTarget.getLeft());
            this.dragSource.onDropCompleted((View) dropTarget, true);
            return true;
        }
        this.dragSource.onDropCompleted((View) dropTarget, false);
        return true;
    }

    public DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
        Rect r = this.mRectTemp;
        ArrayList<DropTarget> dropTargets2 = this.dropTargets;
        for (int i = dropTargets2.size() - 1; i >= 0; i--) {
            DropTarget target = dropTargets2.get(i);
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
