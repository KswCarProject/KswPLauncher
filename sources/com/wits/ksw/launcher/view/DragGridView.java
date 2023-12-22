package com.wits.ksw.launcher.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

/* loaded from: classes16.dex */
public class DragGridView extends GridView {
    private static final String TAG = "DragGridView";
    private static final int scrollSpeed = 20;
    private long dragRespondTime;
    private Runnable dragRunnable;
    private Handler handler;
    private boolean isDrag;
    private boolean isMoveStart;
    onItemChangerListener listener;
    private int mDownX;
    private int mDownY;
    private Bitmap mDragBitmap;
    private ImageView mDragImageView;
    private int mDragPosition;
    private int mOffsetLeft;
    private int mOffsetTop;
    private int mPoint2ItemLeft;
    private int mPoint2ItemTop;
    private int mStartDownScrollHeight;
    private View mStartDragItemView;
    private int mStartUpScrollHeight;
    private int mStatusHeight;
    private int movePosXy;
    private int moveX;
    private int moveY;
    private Runnable scrollRunnable;
    WindowManager.LayoutParams winLayoutParams;
    private WindowManager windowManager;

    /* loaded from: classes16.dex */
    public interface onItemChangerListener {
        void onChange(GridView gridView, int from, int to);

        void onStartMoving();
    }

    public DragGridView(Context context) {
        this(context, null);
    }

    public DragGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.dragRespondTime = 1000L;
        this.movePosXy = 30;
        this.mStatusHeight = 0;
        this.mDragPosition = 0;
        this.isDrag = false;
        this.isMoveStart = true;
        this.handler = new Handler();
        this.dragRunnable = new Runnable() { // from class: com.wits.ksw.launcher.view.DragGridView.1
            @Override // java.lang.Runnable
            public void run() {
                DragGridView.this.isDrag = true;
                DragGridView.this.mStartDragItemView.setVisibility(4);
                DragGridView dragGridView = DragGridView.this;
                dragGridView.createDragImage(dragGridView.mDragBitmap, DragGridView.this.mDownX, DragGridView.this.mDownY);
            }
        };
        this.scrollRunnable = new Runnable() { // from class: com.wits.ksw.launcher.view.DragGridView.2
            @Override // java.lang.Runnable
            public void run() {
                int speed;
                if (DragGridView.this.moveY >= DragGridView.this.mStartDownScrollHeight) {
                    if (DragGridView.this.moveY > DragGridView.this.mStartUpScrollHeight) {
                        speed = 20;
                        DragGridView.this.handler.postDelayed(DragGridView.this.scrollRunnable, 25L);
                    } else {
                        speed = 0;
                        DragGridView.this.handler.removeCallbacks(DragGridView.this.scrollRunnable);
                    }
                } else {
                    speed = -20;
                    DragGridView.this.handler.postDelayed(DragGridView.this.scrollRunnable, 25L);
                }
                DragGridView.this.smoothScrollBy(speed, 10);
            }
        };
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.mStatusHeight = getStatusHeight(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.mDownX = (int) ev.getX();
                this.mDownY = (int) ev.getY();
                Log.i(TAG, "\u624b\u6307\u6309\u4e0bev.getX---------->" + this.mDownX + "  ev.getY-------->" + this.mDownY);
                int pointToPosition = pointToPosition(this.mDownX, this.mDownY);
                this.mDragPosition = pointToPosition;
                if (pointToPosition == -1) {
                    return super.dispatchTouchEvent(ev);
                }
                Log.i(TAG, "\u624b\u6307\u6309\u4e0bmDragPosition---------->" + this.mDragPosition);
                this.mStartDragItemView = getChildAt(this.mDragPosition - getFirstVisiblePosition());
                this.handler.postDelayed(this.dragRunnable, this.dragRespondTime);
                this.mPoint2ItemTop = this.mDownY - this.mStartDragItemView.getTop();
                this.mPoint2ItemLeft = this.mDownX - this.mStartDragItemView.getLeft();
                Log.i(TAG, "\u624b\u6307\u6309\u4e0bmPoint2ItemTop---------->" + this.mPoint2ItemTop + "  mPoint2ItemLeft-------->" + this.mPoint2ItemLeft);
                this.mOffsetLeft = ((int) ev.getRawX()) - this.mDownX;
                this.mOffsetTop = ((int) ev.getRawY()) - this.mDownY;
                this.mStartDownScrollHeight = getHeight() / 4;
                this.mStartUpScrollHeight = (getHeight() * 3) / 4;
                this.mStartDragItemView.setDrawingCacheEnabled(true);
                this.mDragBitmap = Bitmap.createBitmap(this.mStartDragItemView.getDrawingCache());
                this.mStartDragItemView.destroyDrawingCache();
                break;
            case 1:
                this.handler.removeCallbacks(this.dragRunnable);
                this.handler.removeCallbacks(this.scrollRunnable);
                this.isMoveStart = true;
                break;
            case 2:
                if (this.isDrag && this.isMoveStart && (Math.abs(ev.getX() - this.mDownX) > this.movePosXy || Math.abs(ev.getY() - this.mDownY) > this.movePosXy)) {
                    this.isMoveStart = false;
                    onItemChangerListener onitemchangerlistener = this.listener;
                    if (onitemchangerlistener != null) {
                        onitemchangerlistener.onStartMoving();
                        break;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.isDrag && this.mDragImageView != null) {
            switch (ev.getAction()) {
                case 1:
                    this.isDrag = false;
                    onStopDrag();
                    this.moveX = (int) ev.getX();
                    int y = (int) ev.getY();
                    this.moveY = y;
                    swapItem(this.moveX, y);
                    break;
                case 2:
                    this.moveX = (int) ev.getX();
                    int y2 = (int) ev.getY();
                    this.moveY = y2;
                    updateDragImage(this.moveX, y2);
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDragImage(Bitmap mDragBitmap, int mDownX, int mDownY) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.winLayoutParams = layoutParams;
        layoutParams.format = -3;
        this.winLayoutParams.gravity = 51;
        Log.i(TAG, "createDragImage ev.getX---------->" + mDownX + "  ev.getY-------->" + mDownY);
        this.winLayoutParams.x = (mDownX - this.mPoint2ItemLeft) + this.mOffsetLeft;
        this.winLayoutParams.y = ((mDownY - this.mPoint2ItemTop) + this.mOffsetTop) - this.mStatusHeight;
        this.winLayoutParams.alpha = 0.55f;
        this.winLayoutParams.width = -2;
        this.winLayoutParams.height = -2;
        this.winLayoutParams.flags = 24;
        ImageView imageView = new ImageView(getContext());
        this.mDragImageView = imageView;
        imageView.setImageBitmap(mDragBitmap);
        this.windowManager.addView(this.mDragImageView, this.winLayoutParams);
    }

    private void onStopDrag() {
        View view = getChildAt(this.mDragPosition - getFirstVisiblePosition());
        if (view != null) {
            view.setVisibility(0);
        }
        removeDragImage();
    }

    private void removeDragImage() {
        ImageView imageView = this.mDragImageView;
        if (imageView != null) {
            this.windowManager.removeView(imageView);
            this.mDragImageView = null;
        }
    }

    private void updateDragImage(int moveX, int moveY) {
        this.winLayoutParams.x = (moveX - this.mPoint2ItemLeft) + this.mOffsetLeft;
        this.winLayoutParams.y = ((moveY - this.mPoint2ItemTop) + this.mOffsetTop) - this.mStatusHeight;
        this.windowManager.updateViewLayout(this.mDragImageView, this.winLayoutParams);
        this.handler.post(this.scrollRunnable);
    }

    private void swapItem(int moveX2, int moveY2) {
        int tempPositon = pointToPosition(moveX2, moveY2);
        int i = this.mDragPosition;
        if (i != tempPositon && tempPositon != -1) {
            onItemChangerListener onitemchangerlistener = this.listener;
            if (onitemchangerlistener != null) {
                onitemchangerlistener.onChange(this, i, tempPositon);
            }
            this.mDragPosition = tempPositon;
        }
    }

    public static int getStatusHeight(Context context) {
        Rect rect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusHeight = rect.top;
        if (statusHeight == 0) {
            try {
                Class<?> localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                return context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
                return statusHeight;
            }
        }
        return statusHeight;
    }

    public void setOnItemChangeListener(onItemChangerListener listener) {
        this.listener = listener;
    }
}
