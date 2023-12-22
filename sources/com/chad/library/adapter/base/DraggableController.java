package com.chad.library.adapter.base;

import android.graphics.Canvas;
import android.support.p001v4.view.MotionEventCompat;
import android.support.p004v7.widget.RecyclerView;
import android.support.p004v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.C0561R;
import com.chad.library.adapter.base.listener.IDraggableListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import java.util.Collections;

/* loaded from: classes.dex */
public class DraggableController implements IDraggableListener {
    private static final int NO_TOGGLE_VIEW = 0;
    private BaseQuickAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private OnItemDragListener mOnItemDragListener;
    private OnItemSwipeListener mOnItemSwipeListener;
    private View.OnLongClickListener mOnToggleViewLongClickListener;
    private View.OnTouchListener mOnToggleViewTouchListener;
    private int mToggleViewId = 0;
    private boolean itemDragEnabled = false;
    private boolean itemSwipeEnabled = false;
    private boolean mDragOnLongPress = true;

    public DraggableController(BaseQuickAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void initView(BaseViewHolder holder) {
        View toggleView;
        int viewType = holder.getItemViewType();
        if (this.mItemTouchHelper != null && this.itemDragEnabled && viewType != 546 && viewType != 273 && viewType != 1365 && viewType != 819 && hasToggleView() && (toggleView = holder.getView(this.mToggleViewId)) != null) {
            toggleView.setTag(C0561R.C0563id.BaseQuickAdapter_viewholder_support, holder);
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
            this.mOnToggleViewLongClickListener = new View.OnLongClickListener() { // from class: com.chad.library.adapter.base.DraggableController.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    if (DraggableController.this.mItemTouchHelper != null && DraggableController.this.itemDragEnabled) {
                        DraggableController.this.mItemTouchHelper.startDrag((RecyclerView.ViewHolder) v.getTag(C0561R.C0563id.BaseQuickAdapter_viewholder_support));
                        return true;
                    }
                    return true;
                }
            };
            return;
        }
        this.mOnToggleViewTouchListener = new View.OnTouchListener() { // from class: com.chad.library.adapter.base.DraggableController.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == 0 && !DraggableController.this.mDragOnLongPress) {
                    if (DraggableController.this.mItemTouchHelper != null && DraggableController.this.itemDragEnabled) {
                        DraggableController.this.mItemTouchHelper.startDrag((RecyclerView.ViewHolder) v.getTag(C0561R.C0563id.BaseQuickAdapter_viewholder_support));
                        return true;
                    }
                    return true;
                }
                return false;
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

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public boolean isItemDraggable() {
        return this.itemDragEnabled;
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public boolean hasToggleView() {
        return this.mToggleViewId != 0;
    }

    public void enableSwipeItem() {
        this.itemSwipeEnabled = true;
    }

    public void disableSwipeItem() {
        this.itemSwipeEnabled = false;
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public boolean isItemSwipeEnable() {
        return this.itemSwipeEnabled;
    }

    public void setOnItemDragListener(OnItemDragListener onItemDragListener) {
        this.mOnItemDragListener = onItemDragListener;
    }

    public int getViewHolderPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition() - this.mAdapter.getHeaderLayoutCount();
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder) {
        OnItemDragListener onItemDragListener = this.mOnItemDragListener;
        if (onItemDragListener != null && this.itemDragEnabled) {
            onItemDragListener.onItemDragStart(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
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

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder) {
        OnItemDragListener onItemDragListener = this.mOnItemDragListener;
        if (onItemDragListener != null && this.itemDragEnabled) {
            onItemDragListener.onItemDragEnd(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    public void setOnItemSwipeListener(OnItemSwipeListener listener) {
        this.mOnItemSwipeListener = listener;
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder) {
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null && this.itemSwipeEnabled) {
            onItemSwipeListener.onItemSwipeStart(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
    public void onItemSwipeClear(RecyclerView.ViewHolder viewHolder) {
        OnItemSwipeListener onItemSwipeListener = this.mOnItemSwipeListener;
        if (onItemSwipeListener != null && this.itemSwipeEnabled) {
            onItemSwipeListener.clearView(viewHolder, getViewHolderPosition(viewHolder));
        }
    }

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
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

    @Override // com.chad.library.adapter.base.listener.IDraggableListener
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
