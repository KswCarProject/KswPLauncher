package com.wits.ksw.launcher.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.bean.AppInfo;
import com.wits.ksw.launcher.model.LauncherViewModel;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import java.util.List;

/* loaded from: classes14.dex */
public class BaseListAdpater<T> extends BaseAdapter {
    private LayoutInflater mInflater = LayoutInflater.from(KswApplication.appContext);
    private List<T> mlist;
    private int resId;

    public BaseListAdpater(List<T> mlist, int resId) {
        this.mlist = mlist;
        this.resId = resId;
    }

    public void setData(List<T> mlist) {
        this.mlist = mlist;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<T> list = this.mlist;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.mlist.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        List<T> list = this.mlist;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.mlist.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewDataBinding binding;
        TextView appName;
        String text;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(this.mInflater, this.resId, viewGroup, false);
            binding.getRoot().setTag(binding);
        } else {
            binding = (ViewDataBinding) convertView.getTag();
        }
        if ((UiThemeUtils.isBMW_ID8_UI(KswApplication.appContext) || UiThemeUtils.isUI_GS_ID8(KswApplication.appContext) || UiThemeUtils.isUI_PEMP_ID8(KswApplication.appContext)) && (appName = (TextView) binding.getRoot().findViewById(C0899R.C0901id.textView)) != null) {
            appName.setShadowLayer(3.0f, 0.0f, 3.0f, Color.parseColor("#bf000000"));
        }
        AppInfo appInfo = (AppInfo) this.mlist.get(position);
        TextView badgeNumber = (TextView) binding.getRoot().findViewById(C0899R.C0901id.badge_number);
        if (badgeNumber == null) {
            binding.setVariable(20, this.mlist.get(position));
            return binding.getRoot();
        }
        if (LauncherViewModel.mBubbleUnreadCountMap.containsKey(appInfo.apppkg)) {
            Integer unreadCount = LauncherViewModel.mBubbleUnreadCountMap.get(appInfo.apppkg);
            if (unreadCount == null || unreadCount.intValue() == 0) {
                badgeNumber.setVisibility(4);
            } else {
                if (unreadCount.intValue() > 99) {
                    text = "99+";
                } else {
                    text = String.valueOf(unreadCount);
                }
                badgeNumber.setText(text);
                badgeNumber.setVisibility(0);
            }
        } else {
            badgeNumber.setVisibility(4);
        }
        Log.d("boo2er", "getView: mlist=" + this.mlist.get(position).toString() + " position=" + position);
        binding.setVariable(20, this.mlist.get(position));
        return binding.getRoot();
    }
}
