package com.wits.ksw.settings.romeo.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.launcher.model.LauncherViewModel;
import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends PagerAdapter {
    private List<View> mListViews;
    private LauncherViewModel mModel;

    public HomePagerAdapter(List<View> mListViews2, LauncherViewModel model) {
        this.mListViews = mListViews2;
        this.mModel = model;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.mListViews.get(position));
    }

    public Object instantiateItem(ViewGroup container, final int position) {
        View v = this.mListViews.get(position);
        container.addView(v);
        List<View> childViews = getViewGroupChildViews((ViewGroup) v);
        for (int i = 0; i < childViews.size(); i++) {
            final int finalI = i;
            childViews.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.d("HomePagerAdapter", "onClick position=" + position + " finalI=" + finalI);
                    int i = position;
                    if (i == 0) {
                        HomePagerAdapter.this.onItemClickPage1(finalI, v);
                    } else if (i == 1) {
                        HomePagerAdapter.this.onItemClickPage2(finalI, v);
                    } else if (i == 2) {
                        HomePagerAdapter.this.onItemClickPage3(finalI, v);
                    }
                }
            });
        }
        return v;
    }

    public int getCount() {
        return this.mListViews.size();
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    private List<View> getViewGroupChildViews(ViewGroup vp) {
        List<View> allchildren = new ArrayList<>();
        for (int i = 0; i < vp.getChildCount(); i++) {
            View viewchild = vp.getChildAt(i);
            if (viewchild instanceof ViewGroup) {
                allchildren.addAll(getViewGroupChildViews((ViewGroup) viewchild));
            } else {
                allchildren.add(viewchild);
            }
        }
        Log.d("HomePagerAdapter", "getViewGroupChildViews allchildren size=" + allchildren.size());
        return allchildren;
    }

    /* access modifiers changed from: private */
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

    /* access modifiers changed from: private */
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

    /* access modifiers changed from: private */
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
