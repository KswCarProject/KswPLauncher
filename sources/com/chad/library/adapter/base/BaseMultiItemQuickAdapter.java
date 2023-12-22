package com.chad.library.adapter.base;

import android.util.SparseIntArray;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    private static final int DEFAULT_VIEW_TYPE = -255;
    public static final int TYPE_NOT_FOUND = -404;
    private SparseIntArray layouts;

    public BaseMultiItemQuickAdapter(List<T> data) {
        super(data);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected int getDefItemViewType(int position) {
        MultiItemEntity multiItemEntity = (MultiItemEntity) this.mData.get(position);
        if (multiItemEntity != null) {
            return multiItemEntity.getItemType();
        }
        return DEFAULT_VIEW_TYPE;
    }

    protected void setDefaultViewTypeLayout(int layoutResId) {
        addItemType(DEFAULT_VIEW_TYPE, layoutResId);
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    private int getLayoutId(int viewType) {
        return this.layouts.get(viewType, -404);
    }

    protected void addItemType(int type, int layoutResId) {
        if (this.layouts == null) {
            this.layouts = new SparseIntArray();
        }
        this.layouts.put(type, layoutResId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void remove(int position) {
        if (this.mData == null || position < 0 || position >= this.mData.size()) {
            return;
        }
        MultiItemEntity multiItemEntity = (MultiItemEntity) this.mData.get(position);
        if (multiItemEntity instanceof IExpandable) {
            removeAllChild((IExpandable) multiItemEntity, position);
        }
        removeDataFromParent(multiItemEntity);
        super.remove(position);
    }

    protected void removeAllChild(IExpandable parent, int parentPosition) {
        List<MultiItemEntity> chidChilds;
        if (!parent.isExpanded() || (chidChilds = parent.getSubItems()) == null || chidChilds.size() == 0) {
            return;
        }
        int childSize = chidChilds.size();
        for (int i = 0; i < childSize; i++) {
            remove(parentPosition + 1);
        }
    }

    protected void removeDataFromParent(T child) {
        IExpandable parent;
        int position = getParentPosition(child);
        if (position >= 0 && (parent = (IExpandable) this.mData.get(position)) != child) {
            parent.getSubItems().remove(child);
        }
    }

    public int getParentPositionInAll(int position) {
        List<T> data = getData();
        MultiItemEntity multiItemEntity = (MultiItemEntity) getItem(position);
        if (isExpandable(multiItemEntity)) {
            IExpandable IExpandable = (IExpandable) multiItemEntity;
            for (int i = position - 1; i >= 0; i--) {
                MultiItemEntity entity = (MultiItemEntity) data.get(i);
                if (isExpandable(entity) && IExpandable.getLevel() > ((IExpandable) entity).getLevel()) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = position - 1; i2 >= 0; i2--) {
            if (isExpandable((MultiItemEntity) data.get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public boolean isExpandable(MultiItemEntity item) {
        return item != null && (item instanceof IExpandable);
    }
}
