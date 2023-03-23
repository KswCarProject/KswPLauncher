package com.wits.ksw.launcher.view.benzmbux2021kswv2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021FragmentTwo;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.fragment.BenzMbux2021KswV2FragmentOne;
import com.wits.ksw.launcher.view.benzmbux2021kswv2.fragment.BenzMbux2021KswV2FragmentTwo;
import java.util.ArrayList;
import java.util.List;

public class BenzMbux2021KswV2ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment fragmentPage1 = new BenzMbux2021KswV2FragmentOne();
    private Fragment fragmentPage2 = new BenzMbux2021KswV2FragmentTwo();

    public BenzMbux2021KswV2ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
    }

    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

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
