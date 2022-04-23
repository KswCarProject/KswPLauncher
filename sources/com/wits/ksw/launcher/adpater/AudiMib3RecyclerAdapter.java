package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

public class AudiMib3RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public static int index_old = -1;
    private Context context;
    /* access modifiers changed from: private */
    public List<LexusLsAppSelBean> data;
    /* access modifiers changed from: private */
    public IAppsCheckListener iAppsCheckListener;
    IFocusListener iFocusListener;
    /* access modifiers changed from: private */
    public boolean isFinish = false;
    private int layoutRes;

    public interface IAppsCheckListener {
        void checkedListener(String str, String str2, int i);
    }

    public interface IFocusListener {
        void isFocus(View view, int i);
    }

    public AudiMib3RecyclerAdapter(Context context2, List<LexusLsAppSelBean> appInfoList, int res) {
        this.context = context2;
        this.data = appInfoList;
        this.layoutRes = res;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(this.layoutRes, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        Drawable drawable;
        if (this.data.get(position).getAppIcon() == null) {
            drawable = this.context.getDrawable(R.mipmap.ic_launcher);
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
        holder.rbt_apps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhaoid", v.getId() + "");
                if (AudiMib3RecyclerAdapter.this.iFocusListener != null) {
                    AudiMib3RecyclerAdapter.this.iFocusListener.isFocus(v, position);
                }
            }
        });
        holder.rbt_apps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                view.findViewById(R.id.iv_check).setSelected(true);
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
        holder.rbt_apps.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("liuhao", "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + AudiMib3RecyclerAdapter.this.isFinish);
                if (event.getKeyCode() == 20) {
                    if (AudiMib3RecyclerAdapter.this.data.size() - 1 == position) {
                        return true;
                    }
                    return false;
                } else if (keyCode == 19 && position == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public int getItemCount() {
        List<LexusLsAppSelBean> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_app_icon;
        ImageView iv_check;
        public LinearLayout rbt_apps;
        TextView tv_app_name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rbt_apps = (LinearLayout) itemView.findViewById(R.id.rbt_apps);
            this.iv_check = (ImageView) itemView.findViewById(R.id.iv_check);
            this.iv_app_icon = (ImageView) itemView.findViewById(R.id.iv_app_icon);
            this.tv_app_name = (TextView) itemView.findViewById(R.id.tv_app_name);
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }

    public void setIFocusListener(IFocusListener i) {
        this.iFocusListener = i;
    }
}
