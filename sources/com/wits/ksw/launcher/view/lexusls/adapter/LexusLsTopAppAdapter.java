package com.wits.ksw.launcher.view.lexusls.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

/* loaded from: classes9.dex */
public class LexusLsTopAppAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<LexusLsAppSelBean> mList;

    public LexusLsTopAppAdapter(Context context, List<LexusLsAppSelBean> data) {
        Log.e("liuhao", "LexusLsTopAppAdapter.........");
        this.mContext = context;
        this.mList = data;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.e("liuhao", "LexusLsTopAppAdapter.........onCreateViewHolder()");
        View view = LayoutInflater.from(this.mContext).inflate(C0899R.C0902layout.lexus_ls_desktop_app_item, viewGroup, false);
        return new ViewHolder(view);
    }

    public void notifyData(List<LexusLsAppSelBean> poiItemList) {
        if (poiItemList != null) {
            this.mList.clear();
            this.mList.addAll(poiItemList);
            notifyItemRangeChanged(0, poiItemList.size());
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("liuhao", "LexusLsTopAppAdapter.........onBindViewHolder()");
        final LexusLsAppSelBean bean = this.mList.get(position);
        holder.name.setText(bean.getAppName());
        holder.icon.setBackground(bean.getAppIcon());
        holder.layoutLL.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.lexusls.adapter.LexusLsTopAppAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String pkg = bean.getAppPkg();
                String cls = bean.getAppMainAty();
                ComponentName componentName = new ComponentName(pkg, cls);
                Intent intent = new Intent();
                intent.addFlags(270532608);
                intent.setComponent(componentName);
                LexusLsTopAppAdapter.this.mContext.startActivity(intent);
            }
        });
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
        private ImageView icon;
        private LinearLayout layoutLL;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("liuhao", "LexusLsTopAppAdapter.........ViewHolder()");
            this.layoutLL = (LinearLayout) itemView.findViewById(C0899R.C0901id.layoutLL_app_main);
            this.icon = (ImageView) itemView.findViewById(C0899R.C0901id.iv_lexus_main_appicon);
            this.name = (TextView) itemView.findViewById(C0899R.C0901id.tv_lexus_main_appname);
        }
    }
}
