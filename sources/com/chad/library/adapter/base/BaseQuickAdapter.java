package com.chad.library.adapter.base;

import android.animation.Animator;
import android.content.Context;
import android.support.p004v7.util.DiffUtil;
import android.support.p004v7.widget.GridLayoutManager;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.support.p004v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;
import com.chad.library.adapter.base.diff.BaseQuickAdapterListUpdateCallback;
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseQuickAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {
    public static final int ALPHAIN = 1;
    public static final int EMPTY_VIEW = 1365;
    public static final int FOOTER_VIEW = 819;
    public static final int HEADER_VIEW = 273;
    public static final int LOADING_VIEW = 546;
    public static final int SCALEIN = 2;
    public static final int SLIDEIN_BOTTOM = 3;
    public static final int SLIDEIN_LEFT = 4;
    public static final int SLIDEIN_RIGHT = 5;
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();
    private boolean footerViewAsFlow;
    private boolean headerViewAsFlow;
    protected Context mContext;
    private BaseAnimation mCustomAnimation;
    protected List<T> mData;
    private int mDuration;
    private FrameLayout mEmptyLayout;
    private boolean mEnableLoadMoreEndClick;
    private boolean mFirstOnlyEnable;
    private boolean mFootAndEmptyEnable;
    private LinearLayout mFooterLayout;
    private boolean mHeadAndEmptyEnable;
    private LinearLayout mHeaderLayout;
    private Interpolator mInterpolator;
    private boolean mIsUseEmpty;
    private int mLastPosition;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResId;
    private boolean mLoadMoreEnable;
    private LoadMoreView mLoadMoreView;
    private boolean mLoading;
    private boolean mNextLoadEnable;
    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemChildLongClickListener mOnItemChildLongClickListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private boolean mOpenAnimationEnable;
    private int mPreLoadNumber;
    private RecyclerView mRecyclerView;
    private RequestLoadMoreListener mRequestLoadMoreListener;
    private BaseAnimation mSelectAnimation;
    private SpanSizeLookup mSpanSizeLookup;
    private int mStartUpFetchPosition;
    private boolean mUpFetchEnable;
    private UpFetchListener mUpFetchListener;
    private boolean mUpFetching;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AnimationType {
    }

    /* loaded from: classes.dex */
    public interface OnItemChildClickListener {
        void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface OnItemChildLongClickListener {
        boolean onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface OnItemLongClickListener {
        boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);
    }

    /* loaded from: classes.dex */
    public interface RequestLoadMoreListener {
        void onLoadMoreRequested();
    }

    /* loaded from: classes.dex */
    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int i);
    }

    /* loaded from: classes.dex */
    public interface UpFetchListener {
        void onUpFetch();
    }

    protected abstract void convert(K k, T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseQuickAdapter<T, K>) ((BaseViewHolder) viewHolder), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        onBindViewHolder((BaseQuickAdapter<T, K>) ((BaseViewHolder) viewHolder), i, (List<Object>) list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        onViewAttachedToWindow((BaseQuickAdapter<T, K>) ((BaseViewHolder) viewHolder));
    }

    protected RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    private void checkNotNull() {
        if (getRecyclerView() == null) {
            throw new IllegalStateException("please bind recyclerView first!");
        }
    }

    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() == recyclerView) {
            throw new IllegalStateException("Don't bind twice");
        }
        setRecyclerView(recyclerView);
        getRecyclerView().setAdapter(this);
    }

    @Deprecated
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener) {
        openLoadMore(requestLoadMoreListener);
    }

    private void openLoadMore(RequestLoadMoreListener requestLoadMoreListener) {
        this.mRequestLoadMoreListener = requestLoadMoreListener;
        this.mNextLoadEnable = true;
        this.mLoadMoreEnable = true;
        this.mLoading = false;
    }

    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        openLoadMore(requestLoadMoreListener);
        if (getRecyclerView() == null) {
            setRecyclerView(recyclerView);
        }
    }

    public void disableLoadMoreIfNotFullPage() {
        checkNotNull();
        disableLoadMoreIfNotFullPage(getRecyclerView());
    }

    public void disableLoadMoreIfNotFullPage(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager;
        setEnableLoadMore(false);
        if (recyclerView == null || (manager = recyclerView.getLayoutManager()) == null) {
            return;
        }
        if (manager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            recyclerView.postDelayed(new Runnable() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    if (BaseQuickAdapter.this.isFullScreen(linearLayoutManager)) {
                        BaseQuickAdapter.this.setEnableLoadMore(true);
                    }
                }
            }, 50L);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            recyclerView.postDelayed(new Runnable() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    int[] positions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(positions);
                    int pos = BaseQuickAdapter.this.getTheBiggestNumber(positions) + 1;
                    if (pos != BaseQuickAdapter.this.getItemCount()) {
                        BaseQuickAdapter.this.setEnableLoadMore(true);
                    }
                }
            }, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFullScreen(LinearLayoutManager llm) {
        return (llm.findLastCompletelyVisibleItemPosition() + 1 == getItemCount() && llm.findFirstCompletelyVisibleItemPosition() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTheBiggestNumber(int[] numbers) {
        int tmp = -1;
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        for (int num : numbers) {
            if (num > tmp) {
                tmp = num;
            }
        }
        return tmp;
    }

    public void setUpFetchEnable(boolean upFetch) {
        this.mUpFetchEnable = upFetch;
    }

    public boolean isUpFetchEnable() {
        return this.mUpFetchEnable;
    }

    public void setStartUpFetchPosition(int startUpFetchPosition) {
        this.mStartUpFetchPosition = startUpFetchPosition;
    }

    private void autoUpFetch(int positions) {
        UpFetchListener upFetchListener;
        if (isUpFetchEnable() && !isUpFetching() && positions <= this.mStartUpFetchPosition && (upFetchListener = this.mUpFetchListener) != null) {
            upFetchListener.onUpFetch();
        }
    }

    public boolean isUpFetching() {
        return this.mUpFetching;
    }

    public void setUpFetching(boolean upFetching) {
        this.mUpFetching = upFetching;
    }

    public void setUpFetchListener(UpFetchListener upFetchListener) {
        this.mUpFetchListener = upFetchListener;
    }

    public void setNotDoAnimationCount(int count) {
        this.mLastPosition = count;
    }

    public void setLoadMoreView(LoadMoreView loadingView) {
        this.mLoadMoreView = loadingView;
    }

    public int getLoadMoreViewCount() {
        if (this.mRequestLoadMoreListener == null || !this.mLoadMoreEnable) {
            return 0;
        }
        return ((this.mNextLoadEnable || !this.mLoadMoreView.isLoadEndMoreGone()) && this.mData.size() != 0) ? 1 : 0;
    }

    public int getLoadMoreViewPosition() {
        return getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount();
    }

    public boolean isLoading() {
        return this.mLoading;
    }

    public void loadMoreEnd() {
        loadMoreEnd(false);
    }

    public void loadMoreEnd(boolean gone) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mNextLoadEnable = false;
        this.mLoadMoreView.setLoadMoreEndGone(gone);
        if (gone) {
            notifyItemRemoved(getLoadMoreViewPosition());
            return;
        }
        this.mLoadMoreView.setLoadMoreStatus(4);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    public void loadMoreComplete() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mNextLoadEnable = true;
        this.mLoadMoreView.setLoadMoreStatus(1);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    public void loadMoreFail() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        this.mLoading = false;
        this.mLoadMoreView.setLoadMoreStatus(3);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    public void setEnableLoadMore(boolean enable) {
        int oldLoadMoreCount = getLoadMoreViewCount();
        this.mLoadMoreEnable = enable;
        int newLoadMoreCount = getLoadMoreViewCount();
        if (oldLoadMoreCount == 1) {
            if (newLoadMoreCount == 0) {
                notifyItemRemoved(getLoadMoreViewPosition());
            }
        } else if (newLoadMoreCount == 1) {
            this.mLoadMoreView.setLoadMoreStatus(1);
            notifyItemInserted(getLoadMoreViewPosition());
        }
    }

    public boolean isLoadMoreEnable() {
        return this.mLoadMoreEnable;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public final void refreshNotifyItemChanged(int position) {
        notifyItemChanged(getHeaderLayoutCount() + position);
    }

    public final void refreshNotifyItemChanged(int position, Object payload) {
        notifyItemChanged(getHeaderLayoutCount() + position, payload);
    }

    public BaseQuickAdapter(int layoutResId, List<T> data) {
        this.mNextLoadEnable = false;
        this.mLoadMoreEnable = false;
        this.mLoading = false;
        this.mLoadMoreView = new SimpleLoadMoreView();
        this.mEnableLoadMoreEndClick = false;
        this.mFirstOnlyEnable = true;
        this.mOpenAnimationEnable = false;
        this.mInterpolator = new LinearInterpolator();
        this.mDuration = 300;
        this.mLastPosition = -1;
        this.mSelectAnimation = new AlphaInAnimation();
        this.mIsUseEmpty = true;
        this.mPreLoadNumber = 1;
        this.mStartUpFetchPosition = 1;
        this.mData = data == null ? new ArrayList<>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public BaseQuickAdapter(List<T> data) {
        this(0, data);
    }

    public BaseQuickAdapter(int layoutResId) {
        this(layoutResId, null);
    }

    public void setNewData(List<T> data) {
        this.mData = data == null ? new ArrayList<>() : data;
        if (this.mRequestLoadMoreListener != null) {
            this.mNextLoadEnable = true;
            this.mLoadMoreEnable = true;
            this.mLoading = false;
            this.mLoadMoreView.setLoadMoreStatus(1);
        }
        this.mLastPosition = -1;
        notifyDataSetChanged();
    }

    public void setNewDiffData(BaseQuickDiffCallback<T> baseQuickDiffCallback) {
        setNewDiffData((BaseQuickDiffCallback) baseQuickDiffCallback, false);
    }

    public void setNewDiffData(BaseQuickDiffCallback<T> baseQuickDiffCallback, boolean detectMoves) {
        if (getEmptyViewCount() == 1) {
            setNewData(baseQuickDiffCallback.getNewList());
            return;
        }
        baseQuickDiffCallback.setOldList(getData());
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(baseQuickDiffCallback, detectMoves);
        diffResult.dispatchUpdatesTo(new BaseQuickAdapterListUpdateCallback(this));
        this.mData = baseQuickDiffCallback.getNewList();
    }

    public void setNewDiffData(DiffUtil.DiffResult diffResult, List<T> newData) {
        if (getEmptyViewCount() == 1) {
            setNewData(newData);
            return;
        }
        diffResult.dispatchUpdatesTo(new BaseQuickAdapterListUpdateCallback(this));
        this.mData = newData;
    }

    @Deprecated
    public void add(int position, T item) {
        addData(position, (int) item);
    }

    public void addData(int position, T data) {
        this.mData.add(position, data);
        notifyItemInserted(getHeaderLayoutCount() + position);
        compatibilityDataSizeChanged(1);
    }

    public void addData(T data) {
        this.mData.add(data);
        notifyItemInserted(this.mData.size() + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void remove(int position) {
        this.mData.remove(position);
        int internalPosition = getHeaderLayoutCount() + position;
        notifyItemRemoved(internalPosition);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(internalPosition, this.mData.size() - internalPosition);
    }

    public void setData(int index, T data) {
        this.mData.set(index, data);
        notifyItemChanged(getHeaderLayoutCount() + index);
    }

    public void addData(int position, Collection<? extends T> newData) {
        this.mData.addAll(position, newData);
        notifyItemRangeInserted(getHeaderLayoutCount() + position, newData.size());
        compatibilityDataSizeChanged(newData.size());
    }

    public void addData(Collection<? extends T> newData) {
        this.mData.addAll(newData);
        notifyItemRangeInserted((this.mData.size() - newData.size()) + getHeaderLayoutCount(), newData.size());
        compatibilityDataSizeChanged(newData.size());
    }

    public void replaceData(Collection<? extends T> data) {
        List<T> list = this.mData;
        if (data != list) {
            list.clear();
            this.mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    private void compatibilityDataSizeChanged(int size) {
        List<T> list = this.mData;
        int dataSize = list == null ? 0 : list.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    public List<T> getData() {
        return this.mData;
    }

    public T getItem(int position) {
        if (position >= 0 && position < this.mData.size()) {
            return this.mData.get(position);
        }
        return null;
    }

    @Deprecated
    public int getHeaderViewsCount() {
        return getHeaderLayoutCount();
    }

    @Deprecated
    public int getFooterViewsCount() {
        return getFooterLayoutCount();
    }

    public int getHeaderLayoutCount() {
        LinearLayout linearLayout = this.mHeaderLayout;
        if (linearLayout == null || linearLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int getFooterLayoutCount() {
        LinearLayout linearLayout = this.mFooterLayout;
        if (linearLayout == null || linearLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int getEmptyViewCount() {
        FrameLayout frameLayout = this.mEmptyLayout;
        return (frameLayout == null || frameLayout.getChildCount() == 0 || !this.mIsUseEmpty || this.mData.size() != 0) ? 0 : 1;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (1 == getEmptyViewCount()) {
            int count = 1;
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                count = 1 + 1;
            }
            if (this.mFootAndEmptyEnable && getFooterLayoutCount() != 0) {
                return count + 1;
            }
            return count;
        }
        return getHeaderLayoutCount() + this.mData.size() + getFooterLayoutCount() + getLoadMoreViewCount();
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        boolean z = true;
        if (getEmptyViewCount() == 1) {
            boolean header = (!this.mHeadAndEmptyEnable || getHeaderLayoutCount() == 0) ? false : false;
            switch (position) {
                case 0:
                    return header ? HEADER_VIEW : EMPTY_VIEW;
                case 1:
                    return header ? EMPTY_VIEW : FOOTER_VIEW;
                case 2:
                    return FOOTER_VIEW;
                default:
                    return EMPTY_VIEW;
            }
        }
        int numHeaders = getHeaderLayoutCount();
        if (position < numHeaders) {
            return HEADER_VIEW;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = this.mData.size();
        if (adjPosition < adapterCount) {
            return getDefItemViewType(adjPosition);
        }
        int adjPosition2 = adjPosition - adapterCount;
        int numFooters = getFooterLayoutCount();
        return adjPosition2 < numFooters ? FOOTER_VIEW : LOADING_VIEW;
    }

    protected int getDefItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K baseViewHolder;
        Context context = parent.getContext();
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case HEADER_VIEW /* 273 */:
                ViewParent headerLayoutVp = this.mHeaderLayout.getParent();
                if (headerLayoutVp instanceof ViewGroup) {
                    ((ViewGroup) headerLayoutVp).removeView(this.mHeaderLayout);
                }
                baseViewHolder = createBaseViewHolder(this.mHeaderLayout);
                break;
            case LOADING_VIEW /* 546 */:
                baseViewHolder = getLoadingView(parent);
                break;
            case FOOTER_VIEW /* 819 */:
                ViewParent footerLayoutVp = this.mFooterLayout.getParent();
                if (footerLayoutVp instanceof ViewGroup) {
                    ((ViewGroup) footerLayoutVp).removeView(this.mFooterLayout);
                }
                baseViewHolder = createBaseViewHolder(this.mFooterLayout);
                break;
            case EMPTY_VIEW /* 1365 */:
                ViewParent emptyLayoutVp = this.mEmptyLayout.getParent();
                if (emptyLayoutVp instanceof ViewGroup) {
                    ((ViewGroup) emptyLayoutVp).removeView(this.mEmptyLayout);
                }
                baseViewHolder = createBaseViewHolder(this.mEmptyLayout);
                break;
            default:
                baseViewHolder = onCreateDefViewHolder(parent, viewType);
                bindViewClickListener(baseViewHolder);
                break;
        }
        baseViewHolder.setAdapter(this);
        return baseViewHolder;
    }

    private K getLoadingView(ViewGroup parent) {
        View view = getItemView(this.mLoadMoreView.getLayoutId(), parent);
        K holder = createBaseViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BaseQuickAdapter.this.mLoadMoreView.getLoadMoreStatus() == 3) {
                    BaseQuickAdapter.this.notifyLoadMoreToLoading();
                }
                if (BaseQuickAdapter.this.mEnableLoadMoreEndClick && BaseQuickAdapter.this.mLoadMoreView.getLoadMoreStatus() == 4) {
                    BaseQuickAdapter.this.notifyLoadMoreToLoading();
                }
            }
        });
        return holder;
    }

    public void notifyLoadMoreToLoading() {
        if (this.mLoadMoreView.getLoadMoreStatus() == 2) {
            return;
        }
        this.mLoadMoreView.setLoadMoreStatus(1);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    public void enableLoadMoreEndClick(boolean enable) {
        this.mEnableLoadMoreEndClick = enable;
    }

    public void onViewAttachedToWindow(K holder) {
        super.onViewAttachedToWindow((BaseQuickAdapter<T, K>) holder);
        int type = holder.getItemViewType();
        if (type == 1365 || type == 273 || type == 819 || type == 546) {
            setFullSpan(holder);
        } else {
            addAnimation(holder);
        }
    }

    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            final GridLayoutManager.SpanSizeLookup defSpanSizeLookup = gridManager.getSpanSizeLookup();
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.4
                @Override // android.support.p004v7.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int position) {
                    int type = BaseQuickAdapter.this.getItemViewType(position);
                    if (type == 273 && BaseQuickAdapter.this.isHeaderViewAsFlow()) {
                        return 1;
                    }
                    if (type == 819 && BaseQuickAdapter.this.isFooterViewAsFlow()) {
                        return 1;
                    }
                    return BaseQuickAdapter.this.mSpanSizeLookup == null ? BaseQuickAdapter.this.isFixedViewType(type) ? gridManager.getSpanCount() : defSpanSizeLookup.getSpanSize(position) : BaseQuickAdapter.this.isFixedViewType(type) ? gridManager.getSpanCount() : BaseQuickAdapter.this.mSpanSizeLookup.getSpanSize(gridManager, position - BaseQuickAdapter.this.getHeaderLayoutCount());
                }
            });
        }
    }

    protected boolean isFixedViewType(int type) {
        return type == 1365 || type == 273 || type == 819 || type == 546;
    }

    public void setHeaderViewAsFlow(boolean headerViewAsFlow) {
        this.headerViewAsFlow = headerViewAsFlow;
    }

    public boolean isHeaderViewAsFlow() {
        return this.headerViewAsFlow;
    }

    public void setFooterViewAsFlow(boolean footerViewAsFlow) {
        this.footerViewAsFlow = footerViewAsFlow;
    }

    public boolean isFooterViewAsFlow() {
        return this.footerViewAsFlow;
    }

    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    public void onBindViewHolder(K holder, int position) {
        autoUpFetch(position);
        autoLoadMore(position);
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 0:
                convert(holder, getItem(position - getHeaderLayoutCount()));
                return;
            case HEADER_VIEW /* 273 */:
            case FOOTER_VIEW /* 819 */:
            case EMPTY_VIEW /* 1365 */:
                return;
            case LOADING_VIEW /* 546 */:
                this.mLoadMoreView.convert(holder);
                return;
            default:
                convert(holder, getItem(position - getHeaderLayoutCount()));
                return;
        }
    }

    public void onBindViewHolder(K holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder((BaseQuickAdapter<T, K>) holder, position);
            return;
        }
        autoUpFetch(position);
        autoLoadMore(position);
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 0:
                convertPayloads(holder, getItem(position - getHeaderLayoutCount()), payloads);
                return;
            case HEADER_VIEW /* 273 */:
            case FOOTER_VIEW /* 819 */:
            case EMPTY_VIEW /* 1365 */:
                return;
            case LOADING_VIEW /* 546 */:
                this.mLoadMoreView.convert(holder);
                return;
            default:
                convertPayloads(holder, getItem(position - getHeaderLayoutCount()), payloads);
                return;
        }
    }

    protected void bindViewClickListener(final K baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        View view = baseViewHolder.itemView;
        if (getOnItemClickListener() != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    int position = baseViewHolder.getAdapterPosition();
                    if (position == -1) {
                        return;
                    }
                    BaseQuickAdapter.this.setOnItemClick(v, position - BaseQuickAdapter.this.getHeaderLayoutCount());
                }
            });
        }
        if (getOnItemLongClickListener() != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.6
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    int position = baseViewHolder.getAdapterPosition();
                    if (position == -1) {
                        return false;
                    }
                    return BaseQuickAdapter.this.setOnItemLongClick(v, position - BaseQuickAdapter.this.getHeaderLayoutCount());
                }
            });
        }
    }

    public void setOnItemClick(View v, int position) {
        getOnItemClickListener().onItemClick(this, v, position);
    }

    public boolean setOnItemLongClick(View v, int position) {
        return getOnItemLongClickListener().onItemLongClick(this, v, position);
    }

    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, this.mLayoutResId);
    }

    protected K createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return createBaseViewHolder(getItemView(layoutResId, parent));
    }

    protected K createBaseViewHolder(View view) {
        K k;
        Class z = null;
        for (Class temp = getClass(); z == null && temp != null; temp = temp.getSuperclass()) {
            z = getInstancedGenericKClass(temp);
        }
        if (z == null) {
            k = (K) new BaseViewHolder(view);
        } else {
            K k2 = createGenericKInstance(z, view);
            k = k2;
        }
        return k != null ? k : (K) new BaseViewHolder(view);
    }

    private K createGenericKInstance(Class z, View view) {
        try {
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                Constructor constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (K) constructor.newInstance(this, view);
            }
            Constructor constructor2 = z.getDeclaredConstructor(View.class);
            constructor2.setAccessible(true);
            return (K) constructor2.newInstance(view);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private Class getInstancedGenericKClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class tempClass = (Class) temp;
                    if (BaseViewHolder.class.isAssignableFrom(tempClass)) {
                        return tempClass;
                    }
                } else if (temp instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) temp).getRawType();
                    if ((rawType instanceof Class) && BaseViewHolder.class.isAssignableFrom((Class) rawType)) {
                        return (Class) rawType;
                    }
                } else {
                    continue;
                }
            }
            return null;
        }
        return null;
    }

    public LinearLayout getHeaderLayout() {
        return this.mHeaderLayout;
    }

    public LinearLayout getFooterLayout() {
        return this.mFooterLayout;
    }

    public int addHeaderView(View header) {
        return addHeaderView(header, -1);
    }

    public int addHeaderView(View header, int index) {
        return addHeaderView(header, index, 1);
    }

    public int addHeaderView(View header, int index, int orientation) {
        int position;
        if (this.mHeaderLayout == null) {
            LinearLayout linearLayout = new LinearLayout(header.getContext());
            this.mHeaderLayout = linearLayout;
            if (orientation == 1) {
                linearLayout.setOrientation(1);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                linearLayout.setOrientation(0);
                this.mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.mHeaderLayout.getChildCount();
        int mIndex = index;
        if (index < 0 || index > childCount) {
            mIndex = childCount;
        }
        this.mHeaderLayout.addView(header, mIndex);
        if (this.mHeaderLayout.getChildCount() == 1 && (position = getHeaderViewPosition()) != -1) {
            notifyItemInserted(position);
        }
        return mIndex;
    }

    public int setHeaderView(View header) {
        return setHeaderView(header, 0, 1);
    }

    public int setHeaderView(View header, int index) {
        return setHeaderView(header, index, 1);
    }

    public int setHeaderView(View header, int index, int orientation) {
        LinearLayout linearLayout = this.mHeaderLayout;
        if (linearLayout == null || linearLayout.getChildCount() <= index) {
            return addHeaderView(header, index, orientation);
        }
        this.mHeaderLayout.removeViewAt(index);
        this.mHeaderLayout.addView(header, index);
        return index;
    }

    public int addFooterView(View footer) {
        return addFooterView(footer, -1, 1);
    }

    public int addFooterView(View footer, int index) {
        return addFooterView(footer, index, 1);
    }

    public int addFooterView(View footer, int index, int orientation) {
        int position;
        if (this.mFooterLayout == null) {
            LinearLayout linearLayout = new LinearLayout(footer.getContext());
            this.mFooterLayout = linearLayout;
            if (orientation == 1) {
                linearLayout.setOrientation(1);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                linearLayout.setOrientation(0);
                this.mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.mFooterLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        this.mFooterLayout.addView(footer, index);
        if (this.mFooterLayout.getChildCount() == 1 && (position = getFooterViewPosition()) != -1) {
            notifyItemInserted(position);
        }
        return index;
    }

    public int setFooterView(View header) {
        return setFooterView(header, 0, 1);
    }

    public int setFooterView(View header, int index) {
        return setFooterView(header, index, 1);
    }

    public int setFooterView(View header, int index, int orientation) {
        LinearLayout linearLayout = this.mFooterLayout;
        if (linearLayout == null || linearLayout.getChildCount() <= index) {
            return addFooterView(header, index, orientation);
        }
        this.mFooterLayout.removeViewAt(index);
        this.mFooterLayout.addView(header, index);
        return index;
    }

    public void removeHeaderView(View header) {
        int position;
        if (getHeaderLayoutCount() == 0) {
            return;
        }
        this.mHeaderLayout.removeView(header);
        if (this.mHeaderLayout.getChildCount() == 0 && (position = getHeaderViewPosition()) != -1) {
            notifyItemRemoved(position);
        }
    }

    public void removeFooterView(View footer) {
        int position;
        if (getFooterLayoutCount() == 0) {
            return;
        }
        this.mFooterLayout.removeView(footer);
        if (this.mFooterLayout.getChildCount() == 0 && (position = getFooterViewPosition()) != -1) {
            notifyItemRemoved(position);
        }
    }

    public void removeAllHeaderView() {
        if (getHeaderLayoutCount() == 0) {
            return;
        }
        this.mHeaderLayout.removeAllViews();
        int position = getHeaderViewPosition();
        if (position != -1) {
            notifyItemRemoved(position);
        }
    }

    public void removeAllFooterView() {
        if (getFooterLayoutCount() == 0) {
            return;
        }
        this.mFooterLayout.removeAllViews();
        int position = getFooterViewPosition();
        if (position != -1) {
            notifyItemRemoved(position);
        }
    }

    private int getHeaderViewPosition() {
        return (getEmptyViewCount() != 1 || this.mHeadAndEmptyEnable) ? 0 : -1;
    }

    private int getFooterViewPosition() {
        if (getEmptyViewCount() == 1) {
            int position = 1;
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                position = 1 + 1;
            }
            if (this.mFootAndEmptyEnable) {
                return position;
            }
            return -1;
        }
        return getHeaderLayoutCount() + this.mData.size();
    }

    public void setEmptyView(int layoutResId, ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        setEmptyView(view);
    }

    @Deprecated
    public void setEmptyView(int layoutResId) {
        checkNotNull();
        setEmptyView(layoutResId, getRecyclerView());
    }

    public void setEmptyView(View emptyView) {
        int oldItemCount = getItemCount();
        boolean insert = false;
        if (this.mEmptyLayout == null) {
            this.mEmptyLayout = new FrameLayout(emptyView.getContext());
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(-1, -1);
            ViewGroup.LayoutParams lp = emptyView.getLayoutParams();
            if (lp != null) {
                layoutParams.width = lp.width;
                layoutParams.height = lp.height;
            }
            this.mEmptyLayout.setLayoutParams(layoutParams);
            insert = true;
        }
        this.mEmptyLayout.removeAllViews();
        this.mEmptyLayout.addView(emptyView);
        this.mIsUseEmpty = true;
        if (insert && getEmptyViewCount() == 1) {
            int position = 0;
            if (this.mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                position = 0 + 1;
            }
            if (getItemCount() > oldItemCount) {
                notifyItemInserted(position);
            } else {
                notifyDataSetChanged();
            }
        }
    }

    public void setHeaderAndEmpty(boolean isHeadAndEmpty) {
        setHeaderFooterEmpty(isHeadAndEmpty, false);
    }

    public void setHeaderFooterEmpty(boolean isHeadAndEmpty, boolean isFootAndEmpty) {
        this.mHeadAndEmptyEnable = isHeadAndEmpty;
        this.mFootAndEmptyEnable = isFootAndEmpty;
    }

    public void isUseEmpty(boolean isUseEmpty) {
        this.mIsUseEmpty = isUseEmpty;
    }

    public View getEmptyView() {
        return this.mEmptyLayout;
    }

    @Deprecated
    public void setAutoLoadMoreSize(int preLoadNumber) {
        setPreLoadNumber(preLoadNumber);
    }

    public void setPreLoadNumber(int preLoadNumber) {
        if (preLoadNumber > 1) {
            this.mPreLoadNumber = preLoadNumber;
        }
    }

    private void autoLoadMore(int position) {
        if (getLoadMoreViewCount() == 0 || position < getItemCount() - this.mPreLoadNumber || this.mLoadMoreView.getLoadMoreStatus() != 1) {
            return;
        }
        this.mLoadMoreView.setLoadMoreStatus(2);
        if (!this.mLoading) {
            this.mLoading = true;
            if (getRecyclerView() != null) {
                getRecyclerView().post(new Runnable() { // from class: com.chad.library.adapter.base.BaseQuickAdapter.7
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseQuickAdapter.this.mRequestLoadMoreListener.onLoadMoreRequested();
                    }
                });
            } else {
                this.mRequestLoadMoreListener.onLoadMoreRequested();
            }
        }
    }

    private void addAnimation(RecyclerView.ViewHolder holder) {
        BaseAnimation animation;
        Animator[] animators;
        if (this.mOpenAnimationEnable) {
            if (!this.mFirstOnlyEnable || holder.getLayoutPosition() > this.mLastPosition) {
                if (this.mCustomAnimation != null) {
                    animation = this.mCustomAnimation;
                } else {
                    animation = this.mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                this.mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    protected void startAnim(Animator anim, int index) {
        anim.setDuration(this.mDuration).start();
        anim.setInterpolator(this.mInterpolator);
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        return this.mLayoutInflater.inflate(layoutResId, parent, false);
    }

    public void openLoadAnimation(int animationType) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = null;
        switch (animationType) {
            case 1:
                this.mSelectAnimation = new AlphaInAnimation();
                return;
            case 2:
                this.mSelectAnimation = new ScaleInAnimation();
                return;
            case 3:
                this.mSelectAnimation = new SlideInBottomAnimation();
                return;
            case 4:
                this.mSelectAnimation = new SlideInLeftAnimation();
                return;
            case 5:
                this.mSelectAnimation = new SlideInRightAnimation();
                return;
            default:
                return;
        }
    }

    public void openLoadAnimation(BaseAnimation animation) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = animation;
    }

    public void openLoadAnimation() {
        this.mOpenAnimationEnable = true;
    }

    public void closeLoadAnimation() {
        this.mOpenAnimationEnable = false;
    }

    public void isFirstOnly(boolean firstOnly) {
        this.mFirstOnlyEnable = firstOnly;
    }

    protected void convertPayloads(K helper, T item, List<Object> payloads) {
    }

    public View getViewByPosition(int position, int viewId) {
        checkNotNull();
        return getViewByPosition(getRecyclerView(), position, viewId);
    }

    public View getViewByPosition(RecyclerView recyclerView, int position, int viewId) {
        BaseViewHolder viewHolder;
        if (recyclerView == null || (viewHolder = (BaseViewHolder) recyclerView.findViewHolderForLayoutPosition(position)) == null) {
            return null;
        }
        return viewHolder.getView(viewId);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    private int recursiveExpand(int position, List list) {
        int count = list.size();
        int pos = (list.size() + position) - 1;
        int i = list.size() - 1;
        while (i >= 0) {
            if (list.get(i) instanceof IExpandable) {
                IExpandable item = (IExpandable) list.get(i);
                if (item.isExpanded() && hasSubItems(item)) {
                    List subList = item.getSubItems();
                    this.mData.addAll(pos + 1, subList);
                    int subItemCount = recursiveExpand(pos + 1, subList);
                    count += subItemCount;
                }
            }
            i--;
            pos--;
        }
        return count;
    }

    public int expand(int position, boolean animate, boolean shouldNotify) {
        int position2 = position - getHeaderLayoutCount();
        IExpandable expandable = getExpandableItem(position2);
        if (expandable == null) {
            return 0;
        }
        if (!hasSubItems(expandable)) {
            expandable.setExpanded(true);
            notifyItemChanged(position2);
            return 0;
        }
        int subItemCount = 0;
        if (!expandable.isExpanded()) {
            List list = expandable.getSubItems();
            this.mData.addAll(position2 + 1, list);
            subItemCount = 0 + recursiveExpand(position2 + 1, list);
            expandable.setExpanded(true);
        }
        int parentPos = getHeaderLayoutCount() + position2;
        if (shouldNotify) {
            if (animate) {
                notifyItemChanged(parentPos);
                notifyItemRangeInserted(parentPos + 1, subItemCount);
            } else {
                notifyDataSetChanged();
            }
        }
        return subItemCount;
    }

    public int expand(int position, boolean animate) {
        return expand(position, animate, true);
    }

    public int expand(int position) {
        return expand(position, true, true);
    }

    public int expandAll(int position, boolean animate, boolean notify) {
        T item;
        int position2 = position - getHeaderLayoutCount();
        T endItem = null;
        if (position2 + 1 < this.mData.size()) {
            endItem = getItem(position2 + 1);
        }
        IExpandable expandable = getExpandableItem(position2);
        if (expandable == null) {
            return 0;
        }
        if (hasSubItems(expandable)) {
            int count = expand(getHeaderLayoutCount() + position2, false, false);
            for (int i = position2 + 1; i < this.mData.size() && ((item = getItem(i)) == null || !item.equals(endItem)); i++) {
                if (isExpandable(item)) {
                    count += expand(getHeaderLayoutCount() + i, false, false);
                }
            }
            if (notify) {
                if (animate) {
                    notifyItemRangeInserted(getHeaderLayoutCount() + position2 + 1, count);
                } else {
                    notifyDataSetChanged();
                }
            }
            return count;
        }
        expandable.setExpanded(true);
        notifyItemChanged(position2);
        return 0;
    }

    public int expandAll(int position, boolean init) {
        return expandAll(position, true, !init);
    }

    public void expandAll() {
        for (int i = (this.mData.size() - 1) + getHeaderLayoutCount(); i >= getHeaderLayoutCount(); i--) {
            expandAll(i, false, false);
        }
    }

    private int recursiveCollapse(int position) {
        T item = getItem(position);
        if (item == null || !isExpandable(item)) {
            return 0;
        }
        IExpandable expandable = (IExpandable) item;
        if (!expandable.isExpanded()) {
            return 0;
        }
        List<T> collapseList = new ArrayList<>();
        int itemLevel = expandable.getLevel();
        int n = this.mData.size();
        for (int i = position + 1; i < n; i++) {
            T itemTemp = this.mData.get(i);
            if ((itemTemp instanceof IExpandable) && ((IExpandable) itemTemp).getLevel() <= itemLevel) {
                break;
            }
            collapseList.add(itemTemp);
        }
        this.mData.removeAll(collapseList);
        return collapseList.size();
    }

    public int collapse(int position, boolean animate, boolean notify) {
        int position2 = position - getHeaderLayoutCount();
        IExpandable expandable = getExpandableItem(position2);
        if (expandable == null) {
            return 0;
        }
        int subItemCount = recursiveCollapse(position2);
        expandable.setExpanded(false);
        int parentPos = getHeaderLayoutCount() + position2;
        if (notify) {
            if (animate) {
                notifyItemChanged(parentPos);
                notifyItemRangeRemoved(parentPos + 1, subItemCount);
            } else {
                notifyDataSetChanged();
            }
        }
        return subItemCount;
    }

    public int collapse(int position) {
        return collapse(position, true, true);
    }

    public int collapse(int position, boolean animate) {
        return collapse(position, animate, true);
    }

    private int getItemPosition(T item) {
        List<T> list;
        if (item == null || (list = this.mData) == null || list.isEmpty()) {
            return -1;
        }
        return this.mData.indexOf(item);
    }

    public boolean hasSubItems(IExpandable item) {
        List list;
        return (item == null || (list = item.getSubItems()) == null || list.size() <= 0) ? false : true;
    }

    public boolean isExpandable(T item) {
        return item != null && (item instanceof IExpandable);
    }

    private IExpandable getExpandableItem(int position) {
        T item = getItem(position);
        if (isExpandable(item)) {
            return (IExpandable) item;
        }
        return null;
    }

    public int getParentPosition(T item) {
        int level;
        int position = getItemPosition(item);
        if (position == -1) {
            return -1;
        }
        if (item instanceof IExpandable) {
            level = ((IExpandable) item).getLevel();
        } else {
            level = Integer.MAX_VALUE;
        }
        if (level == 0) {
            return position;
        }
        if (level == -1) {
            return -1;
        }
        for (int i = position; i >= 0; i--) {
            T temp = this.mData.get(i);
            if (temp instanceof IExpandable) {
                IExpandable expandable = (IExpandable) temp;
                if (expandable.getLevel() >= 0 && expandable.getLevel() < level) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener listener) {
        this.mOnItemChildClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public void setOnItemChildLongClickListener(OnItemChildLongClickListener listener) {
        this.mOnItemChildLongClickListener = listener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public final OnItemChildClickListener getOnItemChildClickListener() {
        return this.mOnItemChildClickListener;
    }

    public final OnItemChildLongClickListener getOnItemChildLongClickListener() {
        return this.mOnItemChildLongClickListener;
    }
}
