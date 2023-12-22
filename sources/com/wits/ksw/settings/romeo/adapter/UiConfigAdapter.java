package com.wits.ksw.settings.romeo.adapter;

import android.content.Context;
import android.support.p001v4.internal.view.SupportMenu;
import android.support.p004v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

/* loaded from: classes8.dex */
public class UiConfigAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    private OnItemClickLisen rbtCheckListener;

    /* loaded from: classes8.dex */
    public interface OnItemClickLisen {
        void ItemClickLisen(int position);
    }

    public UiConfigAdapter(Context context, List<FunctionBean> data) {
        this.context = context;
        this.data = data;
    }

    public void registCheckListener(OnItemClickLisen listener) {
        this.rbtCheckListener = listener;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.list_tv_layout, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv_mgs.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_mgs.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            holder.tv_mgs.setTextColor(-1);
        }
        if (this.rbtCheckListener != null) {
            holder.relat_listTv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.romeo.adapter.UiConfigAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    UiConfigAdapter.this.rbtCheckListener.ItemClickLisen(position);
                }
            });
        }
        holder.relat_listTv.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.romeo.adapter.UiConfigAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() != 20) {
                    return event.getKeyCode() == 19 && position == 0;
                } else if (UiConfigAdapter.this.data.size() - 1 == position) {
                    holder.relat_listTv.setNextFocusDownId(C0899R.C0901id.relat_listTv);
                    return false;
                } else {
                    return false;
                }
            }
        });
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FunctionBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes8.dex */
    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relat_listTv;
        TextView tv_mgs;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.relat_listTv = (RelativeLayout) itemView.findViewById(C0899R.C0901id.relat_listTv);
            this.tv_mgs = (TextView) itemView.findViewById(C0899R.C0901id.tv_mgs);
        }
    }
}
