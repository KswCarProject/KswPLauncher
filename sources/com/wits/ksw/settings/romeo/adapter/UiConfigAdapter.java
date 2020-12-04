package com.wits.ksw.settings.romeo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

public class UiConfigAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public OnItemClickLisen rbtCheckListener;

    public interface OnItemClickLisen {
        void ItemClickLisen(int i);
    }

    public UiConfigAdapter(Context context2, List<FunctionBean> data2) {
        this.context = context2;
        this.data = data2;
    }

    public void registCheckListener(OnItemClickLisen listener) {
        this.rbtCheckListener = listener;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.list_tv_layout, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_mgs.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_mgs.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            holder.tv_mgs.setTextColor(-1);
        }
        if (this.rbtCheckListener != null) {
            holder.relat_listTv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UiConfigAdapter.this.rbtCheckListener.ItemClickLisen(position);
                }
            });
        }
        holder.relat_listTv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == 20) {
                    if (UiConfigAdapter.this.data.size() - 1 != position) {
                        return false;
                    }
                    holder.relat_listTv.setNextFocusDownId(R.id.relat_listTv);
                    return false;
                } else if (event.getKeyCode() == 19 && position == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public int getItemCount() {
        if (this.data == null) {
            return 0;
        }
        return this.data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relat_listTv;
        TextView tv_mgs;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.relat_listTv = (RelativeLayout) itemView.findViewById(R.id.relat_listTv);
            this.tv_mgs = (TextView) itemView.findViewById(R.id.tv_mgs);
        }
    }
}
