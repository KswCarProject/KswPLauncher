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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.databinding.ViewDataBinding} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r5, android.view.View r6, android.view.ViewGroup r7) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L_0x0014
            android.view.LayoutInflater r1 = r4.mInflater
            int r2 = r4.resId
            r3 = 0
            android.databinding.ViewDataBinding r0 = android.databinding.DataBindingUtil.inflate(r1, r2, r7, r3)
            android.view.View r6 = r0.getRoot()
            r6.setTag(r0)
            goto L_0x001b
        L_0x0014:
            java.lang.Object r1 = r6.getTag()
            r0 = r1
            android.databinding.ViewDataBinding r0 = (android.databinding.ViewDataBinding) r0
        L_0x001b:
            r1 = 11
            java.util.List<T> r2 = r4.mlist
            java.lang.Object r2 = r2.get(r5)
            r0.setVariable(r1, r2)
            android.view.View r1 = r0.getRoot()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.base.BaseListAdpater.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }
}
