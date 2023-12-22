package com.chad.library.adapter.base;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.chad.library.adapter.base.util.ProviderDelegate;
import java.util.List;

/* loaded from: classes.dex */
public abstract class MultipleItemRvAdapter<T, V extends BaseViewHolder> extends BaseQuickAdapter<T, V> {
    private SparseArray<BaseItemProvider> mItemProviders;
    private MultiTypeDelegate<T> mMultiTypeDelegate;
    protected ProviderDelegate mProviderDelegate;

    protected abstract int getViewType(T t);

    public abstract void registerItemProvider();

    public MultipleItemRvAdapter(List<T> data) {
        super(data);
    }

    public void finishInitialize() {
        this.mProviderDelegate = new ProviderDelegate();
        setMultiTypeDelegate(new MultiTypeDelegate<T>() { // from class: com.chad.library.adapter.base.MultipleItemRvAdapter.1
            @Override // com.chad.library.adapter.base.util.MultiTypeDelegate
            protected int getItemType(T t) {
                return MultipleItemRvAdapter.this.getViewType(t);
            }
        });
        registerItemProvider();
        this.mItemProviders = this.mProviderDelegate.getItemProviders();
        for (int i = 0; i < this.mItemProviders.size(); i++) {
            int key = this.mItemProviders.keyAt(i);
            BaseItemProvider provider = this.mItemProviders.get(key);
            provider.mData = this.mData;
            getMultiTypeDelegate().registerItemType(key, provider.layout());
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void bindViewClickListener(V baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        bindClick(baseViewHolder);
        super.bindViewClickListener(baseViewHolder);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected V onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (getMultiTypeDelegate() == null) {
            throw new IllegalStateException("please use setMultiTypeDelegate first!");
        }
        int layoutId = getMultiTypeDelegate().getLayoutId(viewType);
        return (V) createBaseViewHolder(parent, layoutId);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected int getDefItemViewType(int position) {
        if (getMultiTypeDelegate() == null) {
            throw new IllegalStateException("please use setMultiTypeDelegate first!");
        }
        return getMultiTypeDelegate().getDefItemViewType(this.mData, position);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convert(V helper, T item) {
        int itemViewType = helper.getItemViewType();
        BaseItemProvider provider = this.mItemProviders.get(itemViewType);
        provider.mContext = helper.itemView.getContext();
        int position = helper.getLayoutPosition() - getHeaderLayoutCount();
        provider.convert(helper, item, position);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected void convertPayloads(V helper, T item, List<Object> payloads) {
        int itemViewType = helper.getItemViewType();
        BaseItemProvider provider = this.mItemProviders.get(itemViewType);
        int position = helper.getLayoutPosition() - getHeaderLayoutCount();
        provider.convertPayloads(helper, item, position, payloads);
    }

    private void bindClick(final V helper) {
        BaseQuickAdapter.OnItemClickListener clickListener = getOnItemClickListener();
        BaseQuickAdapter.OnItemLongClickListener longClickListener = getOnItemLongClickListener();
        if (clickListener != null && longClickListener != null) {
            return;
        }
        if (clickListener == null) {
            helper.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.chad.library.adapter.base.MultipleItemRvAdapter.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    int position = helper.getAdapterPosition();
                    if (position == -1) {
                        return;
                    }
                    int position2 = position - MultipleItemRvAdapter.this.getHeaderLayoutCount();
                    int itemViewType = helper.getItemViewType();
                    BaseItemProvider provider = (BaseItemProvider) MultipleItemRvAdapter.this.mItemProviders.get(itemViewType);
                    provider.onClick(helper, MultipleItemRvAdapter.this.mData.get(position2), position2);
                }
            });
        }
        if (longClickListener == null) {
            helper.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.chad.library.adapter.base.MultipleItemRvAdapter.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View v) {
                    int position = helper.getAdapterPosition();
                    if (position == -1) {
                        return false;
                    }
                    int position2 = position - MultipleItemRvAdapter.this.getHeaderLayoutCount();
                    int itemViewType = helper.getItemViewType();
                    BaseItemProvider provider = (BaseItemProvider) MultipleItemRvAdapter.this.mItemProviders.get(itemViewType);
                    return provider.onLongClick(helper, MultipleItemRvAdapter.this.mData.get(position2), position2);
                }
            });
        }
    }

    public void setMultiTypeDelegate(MultiTypeDelegate<T> multiTypeDelegate) {
        this.mMultiTypeDelegate = multiTypeDelegate;
    }

    public MultiTypeDelegate<T> getMultiTypeDelegate() {
        return this.mMultiTypeDelegate;
    }
}
