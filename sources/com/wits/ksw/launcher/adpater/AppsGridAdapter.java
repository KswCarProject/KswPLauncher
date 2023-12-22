package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

/* loaded from: classes11.dex */
public class AppsGridAdapter extends BaseAdapter {
    private Context context;
    private IAppsCheckListener iAppsCheckListener;
    private int layoutRes;
    private List<LexusLsAppSelBean> list;
    private LayoutInflater mInflater;

    /* loaded from: classes11.dex */
    public interface IAppsCheckListener {
        void checkedListener(String pkg, String cls, int pos);
    }

    public AppsGridAdapter(List<LexusLsAppSelBean> list, Context context, int res) {
        this.mInflater = null;
        this.list = list;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutRes = res;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<LexusLsAppSelBean> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.mInflater.inflate(this.layoutRes, (ViewGroup) null);
            holder.iv_app_icon = (ImageView) convertView.findViewById(C0899R.C0901id.iv_app_icon);
            holder.tv_app_name = (TextView) convertView.findViewById(C0899R.C0901id.tv_app_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_app_icon.setBackground(this.list.get(position).getAppIcon());
        holder.tv_app_name.setText(this.list.get(position).getAppName());
        return convertView;
    }

    /* loaded from: classes11.dex */
    static class ViewHolder {
        public ImageView iv_app_icon;
        public TextView tv_app_name;

        ViewHolder() {
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }
}
