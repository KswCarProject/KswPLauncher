package com.wits.ksw.launcher.view.lexusls.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.ArrayList;
import java.util.List;

public class LexusLsSelAppAdapter extends RecyclerView.Adapter<ViewHolder> {
    public List<LexusLsAppSelBean> checkedList = new ArrayList();
    /* access modifiers changed from: private */
    public SparseBooleanArray mCheckStates = new SparseBooleanArray();
    /* access modifiers changed from: private */
    public Context mContext;
    private List<LexusLsAppSelBean> mList;

    public LexusLsSelAppAdapter(Context context, List<LexusLsAppSelBean> data) {
        Log.e("liuhao", "LexusLsSelAppAdapter.........");
        this.mContext = context;
        this.mList = data;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("liuhao", "LexusLsSelAppAdapter.........onCreateViewHolder()");
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.lexus_ls_add_app_item, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("liuhao", "LexusLsSelAppAdapter.........onBindViewHolder()" + position);
        final LexusLsAppSelBean bean = this.mList.get(position);
        holder.layoutLL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!LexusLsSelAppAdapter.this.checkedList.contains(bean) && LexusLsSelAppAdapter.this.checkedList.size() < 4) {
                    LexusLsSelAppAdapter.this.mCheckStates.put(position, true);
                    holder.cb.setChecked(true);
                    LexusLsSelAppAdapter.this.checkedList.add(bean);
                } else if (LexusLsSelAppAdapter.this.checkedList.contains(bean)) {
                    LexusLsSelAppAdapter.this.mCheckStates.delete(position);
                    LexusLsSelAppAdapter.this.checkedList.remove(bean);
                    holder.cb.setChecked(false);
                } else if (LexusLsSelAppAdapter.this.checkedList.size() >= 4) {
                    Toast.makeText(LexusLsSelAppAdapter.this.mContext, LexusLsSelAppAdapter.this.mContext.getResources().getString(R.string.add_app_size), 0).show();
                }
            }
        });
        holder.tv_name.setText(bean.getAppName());
        holder.iv_icon.setBackground(bean.getAppIcon());
        holder.cb.setTag(Integer.valueOf(position));
        holder.cb.setChecked(this.mCheckStates.get(position, false));
    }

    class MyClickListener implements View.OnClickListener {
        boolean bCheck = false;
        ViewHolder mHolder;

        MyClickListener(ViewHolder tmpHolder) {
            this.mHolder = tmpHolder;
        }

        public void onClick(View v) {
            this.bCheck = !this.bCheck;
            this.mHolder.cb.setChecked(this.bCheck);
        }
    }

    public List<LexusLsAppSelBean> getCheckBoxIDList() {
        return this.checkedList;
    }

    public void setCheckBoxIDList(List<LexusLsAppSelBean> checkBoxIDList) {
        this.checkedList = checkBoxIDList;
    }

    public int getItemCount() {
        if (this.mList == null) {
            return 0;
        }
        Log.e("liuhao", "mlist.size = " + this.mList.size());
        return this.mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public CheckBox cb;
        /* access modifiers changed from: private */
        public ImageView iv_icon;
        /* access modifiers changed from: private */
        public LinearLayout layoutLL;
        /* access modifiers changed from: private */
        public TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("liuhao", "LexusLsSelAppAdapter.........ViewHolder()");
            this.layoutLL = (LinearLayout) itemView.findViewById(R.id.layoutLL);
            this.iv_icon = (ImageView) itemView.findViewById(R.id.iv_lexusls_app_icon);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_lexusls_app_name);
            CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.cb_lexusls_sel_app);
            this.cb = checkBox;
            checkBox.setChecked(false);
        }
    }
}
