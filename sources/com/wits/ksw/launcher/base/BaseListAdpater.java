package com.wits.ksw.launcher.base;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import com.wits.ksw.KswApplication;
import java.util.List;

public class BaseListAdpater<T> extends BaseAdapter {
    private LayoutInflater mInflater = LayoutInflater.from(KswApplication.appContext);
    private List<T> mlist;
    private int resId;

    public BaseListAdpater(List<T> mlist2, int resId2) {
        this.mlist = mlist2;
        this.resId = resId2;
    }

    public void setData(List<T> mlist2) {
        this.mlist = mlist2;
    }

    public int getCount() {
        List<T> list = this.mlist;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.mlist.size();
    }

    public Object getItem(int i) {
        List<T> list = this.mlist;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.mlist.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.databinding.ViewDataBinding} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0014
            android.view.LayoutInflater r1 = r5.mInflater
            int r2 = r5.resId
            r3 = 0
            android.databinding.ViewDataBinding r0 = android.databinding.DataBindingUtil.inflate(r1, r2, r8, r3)
            android.view.View r7 = r0.getRoot()
            r7.setTag(r0)
            goto L_0x001b
        L_0x0014:
            java.lang.Object r1 = r7.getTag()
            r0 = r1
            android.databinding.ViewDataBinding r0 = (android.databinding.ViewDataBinding) r0
        L_0x001b:
            android.content.Context r1 = com.wits.ksw.KswApplication.appContext
            boolean r1 = com.wits.ksw.launcher.utils.UiThemeUtils.isBMW_ID8_UI(r1)
            if (r1 != 0) goto L_0x002b
            android.content.Context r1 = com.wits.ksw.KswApplication.appContext
            boolean r1 = com.wits.ksw.launcher.utils.UiThemeUtils.isUI_GS_ID8(r1)
            if (r1 == 0) goto L_0x0046
        L_0x002b:
            android.view.View r1 = r0.getRoot()
            r2 = 2131297773(0x7f0905ed, float:1.82135E38)
            android.view.View r1 = r1.findViewById(r2)
            android.widget.TextView r1 = (android.widget.TextView) r1
            if (r1 == 0) goto L_0x0046
            r2 = 0
            java.lang.String r3 = "#bf000000"
            int r3 = android.graphics.Color.parseColor(r3)
            r4 = 1077936128(0x40400000, float:3.0)
            r1.setShadowLayer(r4, r2, r4, r3)
        L_0x0046:
            r1 = 20
            java.util.List<T> r2 = r5.mlist
            java.lang.Object r2 = r2.get(r6)
            r0.setVariable(r1, r2)
            android.view.View r1 = r0.getRoot()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.base.BaseListAdpater.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
