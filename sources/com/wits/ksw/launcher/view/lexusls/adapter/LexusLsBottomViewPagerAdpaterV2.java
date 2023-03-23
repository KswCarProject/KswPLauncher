package com.wits.ksw.launcher.view.lexusls.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwoV2;
import java.util.ArrayList;
import java.util.List;

public class LexusLsBottomViewPagerAdpaterV2 extends FragmentPagerAdapter {
    public FragmentLexusLsBottomOne fragmentLexusLsBottomOne = new FragmentLexusLsBottomOne();
    public FragmentLexusLsBottomTwoV2 fragmentLexusLsBottomTwoV2 = new FragmentLexusLsBottomTwoV2();
    private List<Fragment> fragmentList = new ArrayList();

    public LexusLsBottomViewPagerAdpaterV2(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentLexusLsBottomOne);
        this.fragmentList.add(this.fragmentLexusLsBottomTwoV2);
    }

    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.fragmentList.get(i);
    }

    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.fragmentList.size();
    }
}
