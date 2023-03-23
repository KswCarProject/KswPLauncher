package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean;
import java.util.List;

public class AppsGridAdapter extends BaseAdapter {
    private Context context;
    private IAppsCheckListener iAppsCheckListener;
    private int layoutRes;
    private List<LexusLsAppSelBean> list;
    private LayoutInflater mInflater = null;

    public interface IAppsCheckListener {
        void checkedListener(String str, String str2, int i);
    }

    public AppsGridAdapter(List<LexusLsAppSelBean> list2, Context context2, int res) {
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.wits.ksw.launcher.adpater.AppsGridAdapter$ViewHolder} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r5, android.view.View r6, android.view.ViewGroup r7) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L_0x002c
            com.wits.ksw.launcher.adpater.AppsGridAdapter$ViewHolder r1 = new com.wits.ksw.launcher.adpater.AppsGridAdapter$ViewHolder
            r1.<init>()
            r0 = r1
            android.view.LayoutInflater r1 = r4.mInflater
            int r2 = r4.layoutRes
            r3 = 0
            android.view.View r6 = r1.inflate(r2, r3)
            r1 = 2131297066(0x7f09032a, float:1.8212066E38)
            android.view.View r1 = r6.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            r0.iv_app_icon = r1
            r1 = 2131297817(0x7f090619, float:1.821359E38)
            android.view.View r1 = r6.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            r0.tv_app_name = r1
            r6.setTag(r0)
            goto L_0x0033
        L_0x002c:
            java.lang.Object r1 = r6.getTag()
            r0 = r1
            com.wits.ksw.launcher.adpater.AppsGridAdapter$ViewHolder r0 = (com.wits.ksw.launcher.adpater.AppsGridAdapter.ViewHolder) r0
        L_0x0033:
            android.widget.ImageView r1 = r0.iv_app_icon
            java.util.List<com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean> r2 = r4.list
            java.lang.Object r2 = r2.get(r5)
            com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean r2 = (com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean) r2
            android.graphics.drawable.Drawable r2 = r2.getAppIcon()
            r1.setBackground(r2)
            android.widget.TextView r1 = r0.tv_app_name
            java.util.List<com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean> r2 = r4.list
            java.lang.Object r2 = r2.get(r5)
            com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean r2 = (com.wits.ksw.launcher.bean.lexusls.LexusLsAppSelBean) r2
            java.lang.String r2 = r2.getAppName()
            r1.setText(r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.adpater.AppsGridAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

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
