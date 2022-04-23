package com.wits.ksw.settings.als_id7_ui_set.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.wits.ksw.R;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import java.util.List;

public class AlsID7UiVerRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
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

    public AlsID7UiVerRecyclerAdapter(Context context2, List<LexusLsAppSelBean> appInfoList, int res) {
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
        if (UiThemeUtils.isID7_ALS(this.context)) {
            Log.d("display", "LauncherViewModel.screenWidth.get() = " + LauncherViewModel.screenWidth.get());
            if (LauncherViewModel.screenWidth.get().intValue() > 1280) {
                Log.d("display", "LauncherViewModel.screenWidth.get() >1280");
                drawable.setBounds(0, 0, 45, 45);
            } else {
                drawable.setBounds(0, 0, 35, 35);
            }
        } else {
            drawable.setBounds(0, 0, 45, 45);
        }
        holder.rbt_apps.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        holder.rbt_apps.setText(this.data.get(position).getAppName());
        holder.rbt_apps.setCompoundDrawablePadding(10);
        holder.rbt_apps.setEnabled(true);
        holder.rbt_apps.setTextColor(ContextCompat.getColor(this.context, R.color.color1));
        holder.rbt_apps.setChecked(this.data.get(position).isChecked());
        if (position == 0) {
            holder.rbt_apps.requestFocus();
        }
        holder.rbt_apps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("liuhaoid", v.getId() + "");
                if (AlsID7UiVerRecyclerAdapter.this.iFocusListener != null) {
                    AlsID7UiVerRecyclerAdapter.this.iFocusListener.isFocus(v, position);
                }
            }
        });
        holder.rbt_apps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (LexusLsAppSelBean bean : AlsID7UiVerRecyclerAdapter.this.data) {
                    bean.setChecked(false);
                }
                ((LexusLsAppSelBean) AlsID7UiVerRecyclerAdapter.this.data.get(position)).setChecked(true);
                AlsID7UiVerRecyclerAdapter.this.notifyDataSetChanged();
                if (AlsID7UiVerRecyclerAdapter.this.iAppsCheckListener != null) {
                    AlsID7UiVerRecyclerAdapter.this.iAppsCheckListener.checkedListener(((LexusLsAppSelBean) AlsID7UiVerRecyclerAdapter.this.data.get(position)).getAppPkg(), ((LexusLsAppSelBean) AlsID7UiVerRecyclerAdapter.this.data.get(position)).getAppMainAty(), position);
                }
            }
        });
        holder.rbt_apps.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("liuhao", "onKey: position=" + position + " action=" + event.getAction() + " keyCode =" + keyCode + " isFinish=" + AlsID7UiVerRecyclerAdapter.this.isFinish);
                if (event.getKeyCode() == 20) {
                    if (AlsID7UiVerRecyclerAdapter.this.data.size() - 1 == position) {
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
        public RadioButton rbt_apps;

        public ViewHolder(View itemView) {
            super(itemView);
            this.rbt_apps = (RadioButton) itemView.findViewById(R.id.rbt_apps);
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }

    public void setIFocusListener(IFocusListener i) {
        this.iFocusListener = i;
    }
}
