package com.chad.library.adapter.base;

import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import java.util.List;

public abstract class BaseSectionQuickAdapter<T extends SectionEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    protected static final int SECTION_HEADER_VIEW = 1092;
    protected int mSectionHeadResId;

    /* access modifiers changed from: protected */
    public abstract void convertHead(K k, T t);

    public BaseSectionQuickAdapter(int layoutResId, int sectionHeadResId, List<T> data) {
        super(layoutResId, data);
        this.mSectionHeadResId = sectionHeadResId;
    }

    /* access modifiers changed from: protected */
    public int getDefItemViewType(int position) {
        if (((SectionEntity) this.mData.get(position)).isHeader) {
            return SECTION_HEADER_VIEW;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_HEADER_VIEW) {
            return createBaseViewHolder(getItemView(this.mSectionHeadResId, parent));
        }
        return super.onCreateDefViewHolder(parent, viewType);
    }

    /* access modifiers changed from: protected */
    public boolean isFixedViewType(int type) {
        return super.isFixedViewType(type) || type == SECTION_HEADER_VIEW;
    }

    public void onBindViewHolder(K holder, int position) {
        switch (holder.getItemViewType()) {
            case SECTION_HEADER_VIEW /*1092*/:
                setFullSpan(holder);
                convertHead(holder, (SectionEntity) getItem(position - getHeaderLayoutCount()));
                return;
            default:
                super.onBindViewHolder(holder, position);
                return;
        }
    }
}
