package com.wits.ksw.settings.id6.adapter;

import android.content.Context;
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

/* loaded from: classes15.dex */
public class ID6FunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;

    /* loaded from: classes15.dex */
    public interface OnFunctionClickListener {
        void funSwitImage(int pos);

        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public ID6FunctionAdapter(Context context, List<FunctionBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.list_settings_id6_function, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        holder.relat_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.id6.adapter.ID6FunctionAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (ID6FunctionAdapter.this.functionClickListener != null) {
                    ID6FunctionAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        holder.relat_functionItem.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.id6.adapter.ID6FunctionAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 1 && ID6FunctionAdapter.this.functionClickListener != null) {
                    ID6FunctionAdapter.this.functionClickListener.funSwitImage(position);
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

    /* loaded from: classes15.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relat_functionItem;
        TextView tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (TextView) itemView.findViewById(C0899R.C0901id.tv_functionItem);
            this.relat_functionItem = (RelativeLayout) itemView.findViewById(C0899R.C0901id.relat_id6functionItem);
        }
    }
}
