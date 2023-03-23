package com.chad.library.adapter.base;

import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.R;
import com.chad.library.adapter.base.listener.IDraggableListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import java.util.Collections;

public class DraggableController implements IDraggableListener {
    private static final int NO_TOGGLE_VIEW = 0;
    /* access modifiers changed from: private */
    public boolean itemDragEnabled = false;
    private boolean itemSwipeEnabled = false;
    private BaseQuickAdapter mAdapter;
    /* access modifiers changed from: private */
    public boolean mDragOnLongPress = true;
    /* access modifiers changed from: private */
    public ItemTouchHelper mItemTouchHelper;
    private OnItemDragListener mOnItemDragListener;
    private OnItemSwipeListener mOnItemSwipeListener;
    private View.OnLongClickListener mOnToggleViewLongClickListener;
    private View.OnTouchListener mOnToggleViewTouchListener;
    private int mToggleViewId = 0;

    public DraggableController(BaseQuickAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void initView(BaseViewHolder holder) {
        View toggleView;
        int viewType = holder.getItemViewType();
        if (this.mItemTouchHelper != null && this.itemDragEnabled && viewType != 546 && viewType != 273 && viewType != 1365 && viewType != 819 && hasToggleView() && (toggleView = holder.getView(this.mToggleViewId)) != null) {
            toggleView.setTag(R.id.BaseQuickAdapter_viewholder_support, holder);
            if (this.mDragOnLongPress) {
                toggleView.setOnLongClickListener(this.mOnToggleViewLongClickListener);
            } else {
                toggleView.setOnTouchListener(this.mOnToggleViewTouchListener);
            }
        }
    }

    public void setToggleViewId(int toggleViewId) {
        this.mToggleViewId = toggleViewId;
    }

    public void setToggleDragOnLongPress(boolean longPress) {
        this.mDragOnLongPress = longPress;
        if (longPress) {
            this.mOnToggleViewTouchListener = null;
            this.mOnToggleViewLongClickListener = new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (DraggableController.this.mItemTouchHelper == null || !DraggableController.this.itemDragEnabled) {
                        return true;
                    }
                    DraggableController.this.mItemTouchHelper.startDrag((RecyclerView.ViewHolder) v.getTag(R.id.BaseQuickAdapter_viewholder_support));
                    return true;
                }
            };
            return;
        }
        this.mOnToggleViewTouchListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) != 0 || DraggableController.this.mDragOnLongPress) {
                    return false;
                }
                if (DraggableController.this.mItemTouchHelper == null || !DraggableController.this.itemDragEnabled) {
                    return true;
                }
                DraggableController.this.mItemTouchHelper.startDrag((RecyclerView.ViewHolder) v.getTag(R.id.BaseQuickAdapter_viewholder_support));
                return true;
            }
        };
        this.mOnToggleViewLongClickListener = null;
    }

    public void enableDragItem(ItemTouchHelper itemTouchHelper) {
        enableDragItem(itemTouchHelper, 0, true);
    }

    public void enableDragItem(ItemTouchHelper itemTouchHelper, int toggleViewId) {
        enableDragItem(itemTouchHelper, toggleViewId, true);
    }

    public void enableDragItem(ItemTouchHelper itemTouchHelper, int toggleViewId, boolean dragOnLongPress) {
        this.itemDragEnabled = true;
        this.mItemTouchHelper = itemTouchHelper;
        setToggleViewId(toggleViewId);
        setToggleDragOnLongPress(dragOnLongPress);
    }

    public void disableDragItem() {
        this.itemDragEnabled = false;
        this.mItemTouchHelper = null;
    }

    public boolean isItemDraggable() {
        return this.itemDragEnabled;
    }

    public boolean hasToggleView() {
        return this.mToggleViewId != 0;
    }

    public void enableSwipeItem() {
        this.itemSwipeEnabled = true;
    }

    public void disableSwipeItem() {
        this.itemSwipeEnabled = false;
    }

    public boolean isItemSwipeEnable() {
        return this.itemSwipeEnabled;
    }

    public void setOnItemDragListener(OnItemDragListener onItemDragListener) {
        this.mOnItemDragListener = onItemDragListener;
    }

    public int getViewHolderPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - this.mAdapter.getHeaderLayoutCount();
    }

    public void onItemDragStart(RecyclerView.ViewHolder viewHolder) {
        OnItemDragListener onItemDragListener = this.mOnItemDragListener;
        if (onItemDragListener != null && this.itemDragEnabled) {
            onItemDragListener.onItemDragStart(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void onItemDragMoving(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        int from = getViewHolderPosition(source);
        int to = getViewHolderPosition(target);
        if (inRange(from) && inRange(to)) {
            if (from < to) {
                for (int i = from; i < to; i++) {
                    Collections.swap(this.mAdapter.getData(), i, i + 1);
                }
            } else {
                for (int i2 = from; i2 > to; i2--) {
                    Collections.swap(this.mAdapter.getData(), i2, i2 - 1);
                }
            }
            this.mAdapter.notifyItemMoved(source.getAdapterPosition(), target.getAdapterPosition());
        }
        OnItemDragListener onItemDragListener = this.mOnItemDragListener;
        if (onItemDragListener != null && this.itemDragEnabled) {
            onItemDragListener.onItemDragMoving(source, from, target, to);
        }
    }

    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder) {
        OnItemDragListener onItemDragListener = this.mOnItemDragListener;
        if (onItemDragListener != null && this.itemDragEnabled) {
            onItemDragListener.onItemDragEnd(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void setOnItemSwipeListener(OnItemSwipeListener listener) {
        this.mOnItemSwipeListener = listener;
    }

    public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder) {
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null && this.itemSwipeEnabled) {
            onItemSwipeListener.onItemSwipeStart(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void onItemSwipeClear(RecyclerView.ViewHolder viewHolder) {
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null && this.itemSwipeEnabled) {
            onItemSwipeListener.clearView(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void onItemSwiped(RecyclerView.ViewHolder viewHolder) {
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null && this.itemSwipeEnabled) {
            onItemSwipeListener.onItemSwiped(viewHolder, getViewHolderPosition(viewHolder));
        }
        int pos = getViewHolderPosition(viewHolder);
        if (inRange(pos)) {
            this.mAdapter.getData().remove(pos);
            this.mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }

    public void onItemSwiping(Canvas canvas, RecyclerView.ViewHolder viewHolder, float x, float y, boolean isCurrentlyActive) {
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null && this.itemSwipeEnabled) {
            onItemSwipeListener.onItemSwipeMoving(canvas, viewHolder, x, y, isCurrentlyActive);
        }
    }

    private boolean inRange(int position) {
        return position >= 0 && position < this.mAdapter.getData().size();
    }
}
