package com.wits.ksw.launcher.id7_new.adapter;

import android.support.p001v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import java.util.List;

/* loaded from: classes7.dex */
public class TabFragmentPagerAdapter extends PagerAdapter {
    private List<RelativeLayout> mListViews;

    public TabFragmentPagerAdapter(List<RelativeLayout> mListViews) {
        this.mListViews = mListViews;
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.mListViews.get(position));
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(this.mListViews.get(position), 0);
        return this.mListViews.get(position);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        return this.mListViews.size();
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
