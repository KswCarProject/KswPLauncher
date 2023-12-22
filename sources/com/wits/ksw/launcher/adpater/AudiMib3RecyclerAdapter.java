package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

/* loaded from: classes11.dex */
public class AudiMib3RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static int index_old = -1;
    private Context context;
    private List<LexusLsAppSelBean> data;
    private IAppsCheckListener iAppsCheckListener;
    IFocusListener iFocusListener;
    private boolean isFinish = false;
    private int layoutRes;

    /* loaded from: classes11.dex */
    public interface IAppsCheckListener {
        void checkedListener(String pkg, String cls, int pos);
    }

    /* loaded from: classes11.dex */
    public interface IFocusListener {
        void isFocus(View v, int postion);
    }

    public AudiMib3RecyclerAdapter(Context context, List<LexusLsAppSelBean> appInfoList, int res) {
        this.context = context;
        this.data = appInfoList;
        this.layoutRes = res;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(this.context).inflate(this.layoutRes, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Drawable drawable;
        if (this.data.get(position).getAppIcon() == null) {
            drawable = this.context.getDrawable(C0899R.mipmap.ic_launcher);
        } else {
            drawable = this.data.get(position).getAppIcon();
        }
        if (this.data.get(position).isChecked()) {
            index_old = position;
        }
        holder.iv_check.setSelected(this.data.get(position).isChecked());
        holder.iv_app_icon.setImageDrawable(drawable);
        holder.tv_app_name.setText(this.data.get(position).getAppName());
        holder.rbt_apps.setEnabled(true);
        if (position == 0) {
            holder.rbt_apps.requestFocus();
        }
        holder.rbt_apps.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.adpater.AudiMib3RecyclerAdapter.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhaoid", v.getId() + "");
                if (AudiMib3RecyclerAdapter.this.iFocusListener != null) {
                    AudiMib3RecyclerAdapter.this.iFocusListener.isFocus(v, position);
                }
            }
        });
        holder.rbt_apps.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.adpater.AudiMib3RecyclerAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                view.findViewById(C0899R.C0901id.iv_check).setSelected(true);
                if (-1 != AudiMib3RecyclerAdapter.index_old) {
                    AudiMib3RecyclerAdapter.this.notifyItemChanged(AudiMib3RecyclerAdapter.index_old);
                    ((LexusLsAppSelBean) AudiMib3RecyclerAdapter.this.data.get(AudiMib3RecyclerAdapter.index_old)).setChecked(false);
                }
                ((LexusLsAppSelBean) AudiMib3RecyclerAdapter.this.data.get(position)).setChecked(true);
                int unused = AudiMib3RecyclerAdapter.index_old = position;
                if (AudiMib3RecyclerAdapter.this.iAppsCheckListener != null) {
                    AudiMib3RecyclerAdapter.this.iAppsCheckListener.checkedListener(((LexusLsAppSelBean) AudiMib3RecyclerAdapter.this.data.get(position)).getAppPkg(), ((LexusLsAppSelBean) AudiMib3RecyclerAdapter.this.data.get(position)).getAppMainAty(), position);
                }
            }
        });
        holder.rbt_apps.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.launcher.adpater.AudiMib3RecyclerAdapter.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("liuhao", "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + AudiMib3RecyclerAdapter.this.isFinish);
                return event.getKeyCode() == 20 ? AudiMib3RecyclerAdapter.this.data.size() - 1 == position : keyCode == 19 && position == 0;
            }
        });
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<LexusLsAppSelBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* loaded from: classes11.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_app_icon;
        ImageView iv_check;
        public LinearLayout rbt_apps;
        TextView tv_app_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rbt_apps = (LinearLayout) itemView.findViewById(C0899R.C0901id.rbt_apps);
            this.iv_check = (ImageView) itemView.findViewById(C0899R.C0901id.iv_check);
            this.iv_app_icon = (ImageView) itemView.findViewById(C0899R.C0901id.iv_app_icon);
            this.tv_app_name = (TextView) itemView.findViewById(C0899R.C0901id.tv_app_name);
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }

    public void setIFocusListener(IFocusListener i) {
        this.iFocusListener = i;
    }
}
