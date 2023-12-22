package com.wits.ksw.launcher.bmw_id8_ui.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class BmwId8UiLauncherAdapter<T, H, F> extends RecyclerView.Adapter<RecycleHolder> {
    public static final int ITEM_TYPE_BOTTOM = 2;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_HEADER = 0;
    private List<T> mBodyDatas;
    public int mBodyLayoutId;
    private int mBottomCount;
    private Context mContext;
    private F mFooterData;
    public int mFooterLayoutId;
    private int mHeaderCount;
    private H mHeaderData;
    public int mHeaderLayoutId;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;

    /* loaded from: classes6.dex */
    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

    public abstract void convertBody(RecycleHolder holder, T data, int position);

    public BmwId8UiLauncherAdapter(Context mContext, List<T> mBodyDatas, int bodyLayoutId) {
        this(mContext, bodyLayoutId, mBodyDatas, 0, null, 0, null);
    }

    public BmwId8UiLauncherAdapter(Context context, int bodyLayoutId, List<T> bodyDatas, int headerLayoutId, H headerData, int footerLayoutId, F footerData) {
        this.mContext = context;
        this.mBodyDatas = bodyDatas;
        this.mHeaderData = headerData;
        this.mFooterData = footerData;
        this.mBodyLayoutId = bodyLayoutId;
        this.mHeaderLayoutId = headerLayoutId;
        this.mFooterLayoutId = footerLayoutId;
        this.mInflater = LayoutInflater.from(context);
        if (headerLayoutId == 0) {
            this.mHeaderCount = 0;
        } else {
            this.mHeaderCount = 1;
        }
        if (footerLayoutId == 0) {
            this.mBottomCount = 0;
        } else {
            this.mBottomCount = 1;
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new RecycleHolder(this.mInflater.inflate(this.mHeaderLayoutId, parent, false));
        }
        if (viewType == 1) {
            return new RecycleHolder(this.mInflater.inflate(this.mBodyLayoutId, parent, false));
        }
        if (viewType == 2) {
            return new RecycleHolder(this.mInflater.inflate(this.mFooterLayoutId, parent, false));
        }
        return null;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecycleHolder holder, int position) {
        if (isHeaderView(position)) {
            convertHeader(holder, this.mHeaderData, position);
        } else if (isBottomView(position)) {
            convertFooter(holder, this.mFooterData, position);
        } else {
            convertBody(holder, this.mBodyDatas.get(position - this.mHeaderCount), position);
        }
        if (this.onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.adapter.BmwId8UiLauncherAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    BmwId8UiLauncherAdapter.this.onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    public void convertHeader(RecycleHolder holder, H data, int position) {
    }

    public void convertFooter(RecycleHolder holder, F data, int position) {
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mBodyDatas.size() + this.mHeaderCount + this.mBottomCount;
    }

    public int getContentItemCount() {
        return this.mBodyDatas.size();
    }

    public boolean isHeaderView(int position) {
        int i = this.mHeaderCount;
        return i != 0 && position < i;
    }

    public boolean isBottomView(int position) {
        return this.mBottomCount != 0 && position >= this.mHeaderCount + getContentItemCount();
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        int i = this.mHeaderCount;
        if (i != 0 && position < i) {
            return 0;
        }
        if (this.mBottomCount != 0 && position >= i + dataItemCount) {
            return 2;
        }
        return 1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
