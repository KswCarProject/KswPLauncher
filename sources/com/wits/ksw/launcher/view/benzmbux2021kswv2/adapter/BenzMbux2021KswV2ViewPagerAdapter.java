package com.wits.ksw.launcher.view.benzmbux2021kswv2.adapter;

import android.content.Context;
import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import android.util.DisplayMetrics;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.fragment.BenzMbux2021KswV2FragmentOne;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.fragment.BenzMbux2021KswV2FragmentThree;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.fragment.BenzMbux2021KswV2FragmentTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class BenzMbux2021KswV2ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment fragmentPage1;
    private Fragment fragmentPage2;
    private Fragment fragmentPage3;
    private Context mContext;

    public BenzMbux2021KswV2ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        this.fragmentList = new ArrayList();
        this.fragmentPage1 = new BenzMbux2021KswV2FragmentOne();
        this.fragmentPage2 = new BenzMbux2021KswV2FragmentTwo();
        this.fragmentPage3 = new BenzMbux2021KswV2FragmentThree();
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
        if (width == 1280 && height == 720) {
            this.fragmentList.add(this.fragmentPage3);
        }
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public BenzMbux2021FragmentTwo getFragmentPage2() {
        return (BenzMbux2021FragmentTwo) this.fragmentPage2;
    }
}
