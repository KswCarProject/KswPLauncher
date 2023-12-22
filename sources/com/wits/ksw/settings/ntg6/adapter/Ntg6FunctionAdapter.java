package com.wits.ksw.settings.ntg6.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
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

/* loaded from: classes6.dex */
public class Ntg6FunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;

    /* loaded from: classes6.dex */
    public interface OnFunctionClickListener {
        void funSwitImage(int pos);

        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public Ntg6FunctionAdapter(Context context, List<FunctionBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.list_settings_ntg6_function, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position == 0) {
            holder.relat_functionItem.setNextFocusUpId(C0899R.C0901id.relat_ntg6functionItem);
        }
        if (position == this.data.size() - 1) {
            holder.relat_functionItem.setNextFocusDownId(C0899R.C0901id.relat_ntg6functionItem);
        }
        if (this.data.get(position).getIcon() != 0) {
            Drawable drawable = ContextCompat.getDrawable(this.context, this.data.get(position).getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_functionItem.setCompoundDrawables(drawable, null, null, null);
        }
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (position == 0) {
            holder.relat_functionItem.setBackground(ContextCompat.getDrawable(this.context, C0899R.C0900drawable.ntg6_start_item));
        } else if (position == this.data.size() - 1) {
            holder.relat_functionItem.setBackground(ContextCompat.getDrawable(this.context, C0899R.C0900drawable.ntg6_end_item));
        } else {
            holder.relat_functionItem.setBackground(ContextCompat.getDrawable(this.context, C0899R.C0900drawable.ntg6_zhong_item));
        }
        holder.relat_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.ntg6.adapter.Ntg6FunctionAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Ntg6FunctionAdapter.this.functionClickListener != null) {
                    Ntg6FunctionAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        holder.relat_functionItem.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.ntg6.adapter.Ntg6FunctionAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 1 && Ntg6FunctionAdapter.this.functionClickListener != null) {
                    Ntg6FunctionAdapter.this.functionClickListener.funSwitImage(position);
                    return false;
                }
                return false;
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

    /* loaded from: classes6.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relat_functionItem;
        TextView tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (TextView) itemView.findViewById(C0899R.C0901id.tv_functionItem);
            this.relat_functionItem = (RelativeLayout) itemView.findViewById(C0899R.C0901id.relat_ntg6functionItem);
        }
    }
}
