package com.chad.library.adapter.base.listener;

import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.Iterator;
import java.util.Set;

public abstract class SimpleClickListener implements RecyclerView.OnItemTouchListener {
    public static String TAG = "SimpleClickListener";
    protected BaseQuickAdapter baseQuickAdapter;
    private GestureDetectorCompat mGestureDetector;
    /* access modifiers changed from: private */
    public boolean mIsPrepressed = false;
    /* access modifiers changed from: private */
    public boolean mIsShowPress = false;
    /* access modifiers changed from: private */
    public View mPressedView = null;
    private RecyclerView recyclerView;

    public abstract void onItemChildClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public abstract void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public abstract void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public abstract void onItemLongClick(BaseQuickAdapter baseQuickAdapter2, View view, int i);

    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        BaseViewHolder vh;
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            this.recyclerView = rv;
            this.baseQuickAdapter = (BaseQuickAdapter) rv.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        } else if (recyclerView2 != rv) {
            this.recyclerView = rv;
            this.baseQuickAdapter = (BaseQuickAdapter) rv.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        }
        if (!this.mGestureDetector.onTouchEvent(e) && e.getActionMasked() == 1 && this.mIsShowPress) {
            View view = this.mPressedView;
            if (view != null && ((vh = (BaseViewHolder) this.recyclerView.getChildViewHolder(view)) == null || !isHeaderOrFooterView(vh.getItemViewType()))) {
                this.mPressedView.setPressed(false);
            }
            this.mIsShowPress = false;
            this.mIsPrepressed = false;
        }
        return false;
    }

    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        this.mGestureDetector.onTouchEvent(e);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private class ItemTouchHelperGestureListener implements GestureDetector.OnGestureListener {
        private RecyclerView recyclerView;

        public boolean onDown(MotionEvent e) {
            boolean unused = SimpleClickListener.this.mIsPrepressed = true;
            View unused2 = SimpleClickListener.this.mPressedView = this.recyclerView.findChildViewUnder(e.getX(), e.getY());
            return false;
        }

        public void onShowPress(MotionEvent e) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                boolean unused = SimpleClickListener.this.mIsShowPress = true;
            }
        }

        ItemTouchHelperGestureListener(RecyclerView recyclerView2) {
            this.recyclerView = recyclerView2;
        }

        public boolean onSingleTapUp(MotionEvent e) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                if (this.recyclerView.getScrollState() != 0) {
                    return false;
                }
                View pressedView = SimpleClickListener.this.mPressedView;
                BaseViewHolder vh = (BaseViewHolder) this.recyclerView.getChildViewHolder(pressedView);
                int position = vh.getAdapterPosition();
                if (position == -1 || SimpleClickListener.this.isHeaderOrFooterPosition(position)) {
                    return false;
                }
                int position2 = position - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount();
                Set<Integer> childClickViewIds = vh.getChildClickViewIds();
                Set<Integer> nestViewIds = vh.getNestViews();
                if (childClickViewIds == null || childClickViewIds.size() <= 0) {
                    SimpleClickListener.this.setPressViewHotSpot(e, pressedView);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    if (childClickViewIds != null && childClickViewIds.size() > 0) {
                        for (Integer childClickViewId : childClickViewIds) {
                            View childView = pressedView.findViewById(childClickViewId.intValue());
                            if (childView != null) {
                                childView.setPressed(false);
                            }
                        }
                    }
                    SimpleClickListener simpleClickListener = SimpleClickListener.this;
                    simpleClickListener.onItemClick(simpleClickListener.baseQuickAdapter, pressedView, position2);
                } else {
                    for (Integer childClickViewId2 : childClickViewIds) {
                        View childView2 = pressedView.findViewById(childClickViewId2.intValue());
                        if (childView2 != null) {
                            if (!SimpleClickListener.this.inRangeOfView(childView2, e) || !childView2.isEnabled()) {
                                childView2.setPressed(false);
                            } else if (nestViewIds != null && nestViewIds.contains(childClickViewId2)) {
                                return false;
                            } else {
                                SimpleClickListener.this.setPressViewHotSpot(e, childView2);
                                childView2.setPressed(true);
                                SimpleClickListener simpleClickListener2 = SimpleClickListener.this;
                                simpleClickListener2.onItemChildClick(simpleClickListener2.baseQuickAdapter, childView2, position2);
                                resetPressedView(childView2);
                                return true;
                            }
                        }
                    }
                    SimpleClickListener.this.setPressViewHotSpot(e, pressedView);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    for (Integer childClickViewId3 : childClickViewIds) {
                        View childView3 = pressedView.findViewById(childClickViewId3.intValue());
                        if (childView3 != null) {
                            childView3.setPressed(false);
                        }
                    }
                    SimpleClickListener simpleClickListener3 = SimpleClickListener.this;
                    simpleClickListener3.onItemClick(simpleClickListener3.baseQuickAdapter, pressedView, position2);
                }
                resetPressedView(pressedView);
            }
            return true;
        }

        private void resetPressedView(final View pressedView) {
            if (pressedView != null) {
                pressedView.postDelayed(new Runnable() {
                    public void run() {
                        View view = pressedView;
                        if (view != null) {
                            view.setPressed(false);
                        }
                    }
                }, 50);
            }
            boolean unused = SimpleClickListener.this.mIsPrepressed = false;
            View unused2 = SimpleClickListener.this.mPressedView = null;
        }

        public void onLongPress(MotionEvent e) {
            boolean isChildLongClick = false;
            if (this.recyclerView.getScrollState() == 0 && SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                SimpleClickListener.this.mPressedView.performHapticFeedback(0);
                BaseViewHolder vh = (BaseViewHolder) this.recyclerView.getChildViewHolder(SimpleClickListener.this.mPressedView);
                int position = vh.getAdapterPosition();
                if (position != -1 && !SimpleClickListener.this.isHeaderOrFooterPosition(position)) {
                    Set<Integer> longClickViewIds = vh.getItemChildLongClickViewIds();
                    Set<Integer> nestViewIds = vh.getNestViews();
                    if (longClickViewIds != null && longClickViewIds.size() > 0) {
                        Iterator<Integer> it = longClickViewIds.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Integer longClickViewId = it.next();
                            View childView = SimpleClickListener.this.mPressedView.findViewById(longClickViewId.intValue());
                            if (SimpleClickListener.this.inRangeOfView(childView, e) && childView.isEnabled()) {
                                if (nestViewIds == null || !nestViewIds.contains(longClickViewId)) {
                                    SimpleClickListener.this.setPressViewHotSpot(e, childView);
                                    SimpleClickListener simpleClickListener = SimpleClickListener.this;
                                    simpleClickListener.onItemChildLongClick(simpleClickListener.baseQuickAdapter, childView, position - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                    childView.setPressed(true);
                                    boolean unused = SimpleClickListener.this.mIsShowPress = true;
                                    isChildLongClick = true;
                                } else {
                                    isChildLongClick = true;
                                }
                            }
                        }
                    }
                    if (!isChildLongClick) {
                        SimpleClickListener simpleClickListener2 = SimpleClickListener.this;
                        simpleClickListener2.onItemLongClick(simpleClickListener2.baseQuickAdapter, SimpleClickListener.this.mPressedView, position - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                        SimpleClickListener simpleClickListener3 = SimpleClickListener.this;
                        simpleClickListener3.setPressViewHotSpot(e, simpleClickListener3.mPressedView);
                        SimpleClickListener.this.mPressedView.setPressed(true);
                        if (longClickViewIds != null) {
                            for (Integer longClickViewId2 : longClickViewIds) {
                                View childView2 = SimpleClickListener.this.mPressedView.findViewById(longClickViewId2.intValue());
                                if (childView2 != null) {
                                    childView2.setPressed(false);
                                }
                            }
                        }
                        boolean unused2 = SimpleClickListener.this.mIsShowPress = true;
                    }
                }
            }
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void setPressViewHotSpot(MotionEvent e, View mPressedView2) {
        if (Build.VERSION.SDK_INT >= 21 && mPressedView2 != null && mPressedView2.getBackground() != null) {
            mPressedView2.getBackground().setHotspot(e.getRawX(), e.getY() - mPressedView2.getY());
        }
    }

    public boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        if (view == null || !view.isShown()) {
            return false;
        }
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getRawX() < ((float) x) || ev.getRawX() > ((float) (view.getWidth() + x)) || ev.getRawY() < ((float) y) || ev.getRawY() > ((float) (view.getHeight() + y))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isHeaderOrFooterPosition(int position) {
        if (this.baseQuickAdapter == null) {
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 == null) {
                return false;
            }
            this.baseQuickAdapter = (BaseQuickAdapter) recyclerView2.getAdapter();
        }
        int type = this.baseQuickAdapter.getItemViewType(position);
        if (type == 1365 || type == 273 || type == 819 || type == 546) {
            return true;
        }
        return false;
    }

    private boolean isHeaderOrFooterView(int type) {
        return type == 1365 || type == 273 || type == 819 || type == 546;
    }
}
