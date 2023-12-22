package com.wits.ksw.settings.lexus.adapter;

import android.support.p001v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.launcher.model.LauncherViewModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class HomePagerAdapter extends PagerAdapter {
    private List<View> mListViews;
    private LauncherViewModel mModel;

    public HomePagerAdapter(List<View> mListViews, LauncherViewModel model) {
        this.mListViews = mListViews;
        this.mModel = model;
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.mListViews.get(position));
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup container, final int position) {
        View v = this.mListViews.get(position);
        container.addView(v);
        List<View> childViews = getViewGroupChildViews((ViewGroup) v);
        for (int i = 0; i < childViews.size(); i++) {
            View childView = childViews.get(i);
            final int finalI = i;
            childView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.lexus.adapter.HomePagerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v2) {
                    Log.d("HomePagerAdapter", "onClick position=" + position + " finalI=" + finalI);
                    int i2 = position;
                    if (i2 == 0) {
                        HomePagerAdapter.this.onItemClickPage1(finalI, v2);
                    } else if (i2 == 1) {
                        HomePagerAdapter.this.onItemClickPage2(finalI, v2);
                    } else if (i2 == 2) {
                        HomePagerAdapter.this.onItemClickPage3(finalI, v2);
                    }
                }
            });
        }
        return v;
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        return this.mListViews.size();
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    private List<View> getViewGroupChildViews(ViewGroup vp) {
        List<View> allchildren = new ArrayList<>();
        for (int i = 0; i < vp.getChildCount(); i++) {
            View viewchild = vp.getChildAt(i);
            if (viewchild instanceof ViewGroup) {
                List<View> viewGroupChildViews = getViewGroupChildViews((ViewGroup) viewchild);
                allchildren.addAll(viewGroupChildViews);
            } else {
                allchildren.add(viewchild);
            }
        }
        Log.d("HomePagerAdapter", "getViewGroupChildViews allchildren size=" + allchildren.size());
        return allchildren;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClickPage1(int position, View v) {
        switch (position) {
            case 0:
                this.mModel.openNaviApp(v);
                return;
            case 1:
                this.mModel.openBtApp(v);
                return;
            case 2:
                this.mModel.openCar(v);
                return;
            case 3:
                this.mModel.openSettings(v);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClickPage2(int position, View v) {
        switch (position) {
            case 0:
                this.mModel.openMusic(v);
                return;
            case 1:
                this.mModel.openDashboard(v);
                return;
            case 2:
                this.mModel.openVideo(v);
                return;
            case 3:
                this.mModel.openFileManager(v);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClickPage3(int position, View v) {
        switch (position) {
            case 0:
                this.mModel.openDvr(v);
                return;
            case 1:
                this.mModel.openShouJiHuLian(v);
                return;
            case 2:
                this.mModel.openApps(v);
                return;
            default:
                return;
        }
    }
}
