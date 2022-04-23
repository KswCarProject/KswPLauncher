package com.wits.ksw.settings.als_id7_ui_set.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

public class AlsID7UiUiConfigAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    /* access modifiers changed from: private */
    public List<FunctionBean> data;
    /* access modifiers changed from: private */
    public OnItemClickLisen rbtCheckListener;

    public interface OnItemClickLisen {
        void ItemClickLisen(int i);
    }

    public AlsID7UiUiConfigAdapter(Context context2, List<FunctionBean> data2) {
        this.context = context2;
        this.data = data2;
    }

    public void registCheckListener(OnItemClickLisen listener) {
        this.rbtCheckListener = listener;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.als_id7_ui_list_tv_layout, viewGroup, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (!TextUtils.isEmpty(this.data.get(position).getDisplay())) {
            holder.tv_mgs.setText(this.data.get(position).getDisplay());
        } else {
            holder.tv_mgs.setText(this.data.get(position).getTitle());
        }
        if (this.data.get(position).isIscheck()) {
            holder.tv_mgs.setTextColor(SkinCompatResources.getColor(this.context, R.color.als_id7_ui_text_color));
        } else {
            holder.tv_mgs.setTextColor(-1);
        }
        if (this.rbtCheckListener != null) {
            holder.relat_listTv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlsID7UiUiConfigAdapter.this.rbtCheckListener.ItemClickLisen(position);
                }
            });
        }
        holder.relat_listTv.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == 20) {
                    if (AlsID7UiUiConfigAdapter.this.data.size() - 1 != position) {
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
        List<FunctionBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relat_listTv;
        TextView tv_mgs;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.relat_listTv = (RelativeLayout) itemView.findViewById(R.id.relat_listTv);
            this.tv_mgs = (TextView) itemView.findViewById(R.id.tv_mgs);
        }
    }
}
