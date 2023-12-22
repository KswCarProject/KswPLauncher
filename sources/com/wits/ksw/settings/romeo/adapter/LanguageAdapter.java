package com.wits.ksw.settings.romeo.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import com.wits.ksw.settings.romeo.interfaces.IUpdateListBg;
import java.util.List;

/* loaded from: classes8.dex */
public class LanguageAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;
    private IUpdateListBg updateListBg;

    /* loaded from: classes8.dex */
    public interface OnFunctionClickListener {
        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public LanguageAdapter(Context context, List<FunctionBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
    }

    public void setIUpdateListBg(IUpdateListBg updateListBg) {
        this.updateListBg = updateListBg;
        Log.d("LanguageAdapter", "setIUpdateListBg// updateListBg=" + this.updateListBg);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.romeo_list_settings_language, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_functionItem.setEnabled(true);
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setChecked(true);
        } else {
            holder.tv_functionItem.setChecked(false);
        }
        holder.tv_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.romeo.adapter.LanguageAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Log.d("LanguageAdapter", "onClick");
                if (LanguageAdapter.this.functionClickListener != null) {
                    LanguageAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        holder.tv_functionItem.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.settings.romeo.adapter.LanguageAdapter.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    LanguageAdapter.this.updateListBg.updateListBg(0, 0);
                    return;
                }
                Log.d("LanguageAdapter", "onFocusChange");
                LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 1);
            }
        });
        holder.tv_functionItem.setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.settings.romeo.adapter.LanguageAdapter.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("LanguageAdapter", "onTouch updateListBg=" + LanguageAdapter.this.updateListBg);
                if (event.getAction() == 0) {
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 2);
                } else if (event.getAction() == 1) {
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 0);
                } else if (event.getAction() == 3) {
                    LanguageAdapter.this.updateListBg.updateListBg(holder.itemView.getTop(), 0);
                }
                return false;
            }
        });
        if (this.data.size() - 1 == position) {
            holder.img_functionItem.setNextFocusDownId(C0899R.C0901id.img_functionItem);
        }
        if (position == 0) {
            holder.img_functionItem.setNextFocusUpId(C0899R.C0901id.img_functionItem);
        }
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
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_functionItem;
        RadioButton tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (RadioButton) itemView.findViewById(C0899R.C0901id.tv_functionItem);
            this.img_functionItem = (ImageView) itemView.findViewById(C0899R.C0901id.img_functionItem);
        }
    }
}
