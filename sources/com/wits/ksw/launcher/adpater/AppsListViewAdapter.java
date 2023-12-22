package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.settings.utlis_view.RtlNaviRadioButton;
import java.util.List;

/* loaded from: classes11.dex */
public class AppsListViewAdapter extends BaseAdapter {
    private Context context;
    private IAppsCheckListener iAppsCheckListener;
    private int layoutRes;
    private List<LexusLsAppSelBean> list;
    private LayoutInflater mInflater;

    /* loaded from: classes11.dex */
    public interface IAppsCheckListener {
        void checkedListener(String pkg, String cls, int pos);
    }

    public AppsListViewAdapter(Context context, List<LexusLsAppSelBean> list, int res) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Drawable drawable;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.mInflater.inflate(this.layoutRes, (ViewGroup) null);
            holder.rbt_apps = (RtlNaviRadioButton) convertView.findViewById(C0899R.C0901id.rbt_apps);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (this.list.get(position).getAppIcon() == null) {
            drawable = this.context.getDrawable(C0899R.mipmap.ic_launcher);
        } else {
            drawable = this.list.get(position).getAppIcon();
        }
        drawable.setBounds(0, 0, 50, 50);
        holder.rbt_apps.setCompoundDrawables(drawable, null, null, null);
        holder.rbt_apps.setText(this.list.get(position).getAppName());
        holder.rbt_apps.setCompoundDrawablePadding(10);
        holder.rbt_apps.setEnabled(true);
        holder.rbt_apps.setTextColor(ContextCompat.getColor(this.context, C0899R.color.color1));
        holder.rbt_apps.setChecked(this.list.get(position).isChecked());
        if (position == 0) {
            holder.rbt_apps.requestFocus();
        }
        holder.rbt_apps.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.adpater.AppsListViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (LexusLsAppSelBean bean : AppsListViewAdapter.this.list) {
                    bean.setChecked(false);
                }
                ((LexusLsAppSelBean) AppsListViewAdapter.this.list.get(position)).setChecked(true);
                AppsListViewAdapter.this.notifyDataSetChanged();
                if (AppsListViewAdapter.this.iAppsCheckListener != null) {
                    AppsListViewAdapter.this.iAppsCheckListener.checkedListener(((LexusLsAppSelBean) AppsListViewAdapter.this.list.get(position)).getAppPkg(), ((LexusLsAppSelBean) AppsListViewAdapter.this.list.get(position)).getAppMainAty(), position);
                }
            }
        });
        holder.rbt_apps.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.launcher.adpater.AppsListViewAdapter.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 20 && position == AppsListViewAdapter.this.getCount() - 1) {
                    Log.i("AppListAdapter", "onBindViewHolder: position" + position + ", count:" + AppsListViewAdapter.this.getCount());
                    return true;
                }
                return false;
            }
        });
        return convertView;
    }

    /* loaded from: classes11.dex */
    static class ViewHolder {
        public RtlNaviRadioButton rbt_apps;

        ViewHolder() {
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }
}
