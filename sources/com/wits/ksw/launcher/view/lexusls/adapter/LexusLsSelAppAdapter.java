package com.wits.ksw.launcher.view.lexusls.adapter;

import android.content.Context;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class LexusLsSelAppAdapter extends RecyclerView.Adapter<ViewHolder> {
    public List<LexusLsAppSelBean> checkedList = new ArrayList();
    private SparseBooleanArray mCheckStates = new SparseBooleanArray();
    private Context mContext;
    private List<LexusLsAppSelBean> mList;

    public LexusLsSelAppAdapter(Context context, List<LexusLsAppSelBean> data) {
        Log.e("liuhao", "LexusLsSelAppAdapter.........");
        this.mContext = context;
        this.mList = data;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("liuhao", "LexusLsSelAppAdapter.........onCreateViewHolder()");
        View view = LayoutInflater.from(this.mContext).inflate(C0899R.C0902layout.lexus_ls_add_app_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("liuhao", "LexusLsSelAppAdapter.........onBindViewHolder()" + position);
        final LexusLsAppSelBean bean = this.mList.get(position);
        holder.layoutLL.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.adapter.LexusLsSelAppAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (!LexusLsSelAppAdapter.this.checkedList.contains(bean) && LexusLsSelAppAdapter.this.checkedList.size() < 4) {
                    LexusLsSelAppAdapter.this.mCheckStates.put(position, true);
                    holder.f208cb.setChecked(true);
                    LexusLsSelAppAdapter.this.checkedList.add(bean);
                } else if (LexusLsSelAppAdapter.this.checkedList.contains(bean)) {
                    LexusLsSelAppAdapter.this.mCheckStates.delete(position);
                    LexusLsSelAppAdapter.this.checkedList.remove(bean);
                    holder.f208cb.setChecked(false);
                } else if (LexusLsSelAppAdapter.this.checkedList.size() >= 4) {
                    Toast.makeText(LexusLsSelAppAdapter.this.mContext, LexusLsSelAppAdapter.this.mContext.getResources().getString(C0899R.string.add_app_size), 0).show();
                }
            }
        });
        holder.tv_name.setText(bean.getAppName());
        holder.iv_icon.setBackground(bean.getAppIcon());
        holder.f208cb.setTag(Integer.valueOf(position));
        holder.f208cb.setChecked(this.mCheckStates.get(position, false));
    }

    /* loaded from: classes9.dex */
    class MyClickListener implements View.OnClickListener {
        boolean bCheck = false;
        ViewHolder mHolder;

        MyClickListener(ViewHolder tmpHolder) {
            this.mHolder = tmpHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            this.bCheck = !this.bCheck;
            this.mHolder.f208cb.setChecked(this.bCheck);
        }
    }

    public List<LexusLsAppSelBean> getCheckBoxIDList() {
        return this.checkedList;
    }

    public void setCheckBoxIDList(List<LexusLsAppSelBean> checkBoxIDList) {
        this.checkedList = checkBoxIDList;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mList != null) {
            Log.e("liuhao", "mlist.size = " + this.mList.size());
            return this.mList.size();
        }
        return 0;
    }

    /* loaded from: classes9.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: cb */
        private CheckBox f208cb;
        private ImageView iv_icon;
        private LinearLayout layoutLL;
        private TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("liuhao", "LexusLsSelAppAdapter.........ViewHolder()");
            this.layoutLL = (LinearLayout) itemView.findViewById(C0899R.C0901id.layoutLL);
            this.iv_icon = (ImageView) itemView.findViewById(C0899R.C0901id.iv_lexusls_app_icon);
            this.tv_name = (TextView) itemView.findViewById(C0899R.C0901id.tv_lexusls_app_name);
            CheckBox checkBox = (CheckBox) itemView.findViewById(C0899R.C0901id.cb_lexusls_sel_app);
            this.f208cb = checkBox;
            checkBox.setChecked(false);
        }
    }
}
