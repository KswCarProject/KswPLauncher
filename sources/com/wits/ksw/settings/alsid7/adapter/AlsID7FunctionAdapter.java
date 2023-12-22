package com.wits.ksw.settings.alsid7.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.utils.KswUtils;
import com.wits.ksw.settings.id7.bean.FunctionBean;
import java.util.List;

/* loaded from: classes12.dex */
public class AlsID7FunctionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = AlsID7FunctionAdapter.class.getName();
    private Context context;
    private List<FunctionBean> data;
    private OnFunctionClickListener functionClickListener;
    private boolean isFinish = false;

    /* loaded from: classes12.dex */
    public interface OnFunctionClickListener {
        void functonClick(int pos);
    }

    public void registOnFunctionClickListener(OnFunctionClickListener clickListener) {
        this.functionClickListener = clickListener;
    }

    public AlsID7FunctionAdapter(Context context, List<FunctionBean> appInfoList) {
        this.context = context;
        this.data = appInfoList;
        Log.i(TAG, "FunctionAdapter: ");
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(C0899R.C0902layout.als_list_settings_function, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (this.data.get(position).getIcon() != 0) {
            Drawable drawable = ContextCompat.getDrawable(this.context, this.data.get(position).getIcon());
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.tv_functionItem.setCompoundDrawables(drawable, null, null, null);
        }
        holder.tv_functionItem.setText(this.data.get(position).getTitle());
        if (this.data.get(position).isIscheck()) {
            holder.tv_functionItem.setTextColor(this.context.getColor(C0899R.color.alsid7_text_bule));
        } else {
            holder.tv_functionItem.setTextColor(-1);
        }
        holder.relat_functionItem.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.alsid7.adapter.AlsID7FunctionAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (AlsID7FunctionAdapter.this.functionClickListener != null) {
                    AlsID7FunctionAdapter.this.functionClickListener.functonClick(position);
                }
            }
        });
        holder.relat_functionItem.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.settings.alsid7.adapter.AlsID7FunctionAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i(AlsID7FunctionAdapter.TAG, "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + AlsID7FunctionAdapter.this.isFinish);
                if (event.getKeyCode() == 20) {
                    return AlsID7FunctionAdapter.this.data.size() - 1 == position;
                } else if (keyCode == 19) {
                    return position == 0;
                } else {
                    if (keyCode != 21) {
                        AlsID7FunctionAdapter.this.isFinish = false;
                    }
                    AlsID7FunctionAdapter.this.onBackPressed(keyCode, event);
                    return false;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackPressed(int keyCode, KeyEvent event) {
        if (event.getAction() == 1 && keyCode == 21) {
            if (this.isFinish) {
                KswUtils.sendKeyDownUpSync(4);
                this.isFinish = false;
            }
            this.isFinish = true;
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

    /* loaded from: classes12.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relat_functionItem;
        TextView tv_functionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_functionItem = (TextView) itemView.findViewById(C0899R.C0901id.tv_functionItem);
            this.relat_functionItem = (RelativeLayout) itemView.findViewById(C0899R.C0901id.relat_functionItem);
        }
    }
}
