package com.wits.ksw.launcher.view.lexusls.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

public class LexusLsTopAppAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public Context mContext;
    private List<LexusLsAppSelBean> mList;

    public LexusLsTopAppAdapter(Context context, List<LexusLsAppSelBean> data) {
        Log.e("liuhao", "LexusLsTopAppAdapter.........");
        this.mContext = context;
        this.mList = data;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("liuhao", "LexusLsTopAppAdapter.........onCreateViewHolder()");
        return new ViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.lexus_ls_desktop_app_item, viewGroup, false));
    }

    public void notifyData(List<LexusLsAppSelBean> poiItemList) {
        if (poiItemList != null) {
            this.mList.clear();
            this.mList.addAll(poiItemList);
            notifyItemRangeChanged(0, poiItemList.size());
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("liuhao", "LexusLsTopAppAdapter.........onBindViewHolder()");
        final LexusLsAppSelBean bean = this.mList.get(position);
        holder.name.setText(bean.getAppName());
        holder.icon.setBackground(bean.getAppIcon());
        holder.layoutLL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(bean.getAppPkg(), bean.getAppMainAty());
                Intent intent = new Intent();
                intent.addFlags(270532608);
                intent.setComponent(componentName);
                LexusLsTopAppAdapter.this.mContext.startActivity(intent);
            }
        });
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
        public ImageView icon;
        /* access modifiers changed from: private */
        public LinearLayout layoutLL;
        /* access modifiers changed from: private */
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("liuhao", "LexusLsTopAppAdapter.........ViewHolder()");
            this.layoutLL = (LinearLayout) itemView.findViewById(R.id.layoutLL_app_main);
            this.icon = (ImageView) itemView.findViewById(R.id.iv_lexus_main_appicon);
            this.name = (TextView) itemView.findViewById(R.id.tv_lexus_main_appname);
        }
    }
}
