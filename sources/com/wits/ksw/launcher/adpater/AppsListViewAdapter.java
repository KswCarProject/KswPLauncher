package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import com.wits.ksw.settings.utlis_view.RtlNaviRadioButton;
import java.util.List;

public class AppsListViewAdapter extends BaseAdapter {
    private Context context;
    /* access modifiers changed from: private */
    public IAppsCheckListener iAppsCheckListener;
    private int layoutRes;
    /* access modifiers changed from: private */
    public List<LexusLsAppSelBean> list;
    private LayoutInflater mInflater = null;

    public interface IAppsCheckListener {
        void checkedListener(String str, String str2, int i);
    }

    public AppsListViewAdapter(Context context2, List<LexusLsAppSelBean> list2, int res) {
        this.list = list2;
        this.context = context2;
        this.mInflater = LayoutInflater.from(context2);
        this.layoutRes = res;
    }

    public int getCount() {
        List<LexusLsAppSelBean> list2 = this.list;
        if (list2 == null) {
            return 0;
        }
        return list2.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.wits.ksw.launcher.adpater.AppsListViewAdapter$ViewHolder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(final int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            if (r7 != 0) goto L_0x0021
            com.wits.ksw.launcher.adpater.AppsListViewAdapter$ViewHolder r2 = new com.wits.ksw.launcher.adpater.AppsListViewAdapter$ViewHolder
            r2.<init>()
            r0 = r2
            android.view.LayoutInflater r2 = r5.mInflater
            int r3 = r5.layoutRes
            android.view.View r7 = r2.inflate(r3, r1)
            r2 = 2131297172(0x7f090394, float:1.8212281E38)
            android.view.View r2 = r7.findViewById(r2)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r2 = (com.wits.ksw.settings.utlis_view.RtlNaviRadioButton) r2
            r0.rbt_apps = r2
            r7.setTag(r0)
            goto L_0x0028
        L_0x0021:
            java.lang.Object r2 = r7.getTag()
            r0 = r2
            com.wits.ksw.launcher.adpater.AppsListViewAdapter$ViewHolder r0 = (com.wits.ksw.launcher.adpater.AppsListViewAdapter.ViewHolder) r0
        L_0x0028:
            r2 = 0
            java.util.List<com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean> r3 = r5.list
            java.lang.Object r3 = r3.get(r6)
            com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean r3 = (com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean) r3
            android.graphics.drawable.Drawable r3 = r3.getAppIcon()
            if (r3 != 0) goto L_0x0041
            android.content.Context r3 = r5.context
            r4 = 2131492978(0x7f0c0072, float:1.8609423E38)
            android.graphics.drawable.Drawable r2 = r3.getDrawable(r4)
            goto L_0x004d
        L_0x0041:
            java.util.List<com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean> r3 = r5.list
            java.lang.Object r3 = r3.get(r6)
            com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean r3 = (com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean) r3
            android.graphics.drawable.Drawable r2 = r3.getAppIcon()
        L_0x004d:
            r3 = 0
            r4 = 50
            r2.setBounds(r3, r3, r4, r4)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r3 = r0.rbt_apps
            r3.setCompoundDrawables(r2, r1, r1, r1)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            java.util.List<com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean> r3 = r5.list
            java.lang.Object r3 = r3.get(r6)
            com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean r3 = (com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean) r3
            java.lang.String r3 = r3.getAppName()
            r1.setText(r3)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            r3 = 10
            r1.setCompoundDrawablePadding(r3)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            r3 = 1
            r1.setEnabled(r3)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            android.content.Context r3 = r5.context
            r4 = 2131099696(0x7f060030, float:1.7811752E38)
            int r3 = android.support.v4.content.ContextCompat.getColor(r3, r4)
            r1.setTextColor(r3)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            java.util.List<com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean> r3 = r5.list
            java.lang.Object r3 = r3.get(r6)
            com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean r3 = (com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean) r3
            boolean r3 = r3.isChecked()
            r1.setChecked(r3)
            if (r6 != 0) goto L_0x009c
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            r1.requestFocus()
        L_0x009c:
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            com.wits.ksw.launcher.adpater.AppsListViewAdapter$1 r3 = new com.wits.ksw.launcher.adpater.AppsListViewAdapter$1
            r3.<init>(r6)
            r1.setOnClickListener(r3)
            com.wits.ksw.settings.utlis_view.RtlNaviRadioButton r1 = r0.rbt_apps
            com.wits.ksw.launcher.adpater.AppsListViewAdapter$2 r3 = new com.wits.ksw.launcher.adpater.AppsListViewAdapter$2
            r3.<init>(r6)
            r1.setOnKeyListener(r3)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.adpater.AppsListViewAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    static class ViewHolder {
        public RtlNaviRadioButton rbt_apps;

        ViewHolder() {
        }
    }

    public void setAppsCheckListener(IAppsCheckListener listener) {
        this.iAppsCheckListener = listener;
    }
}
