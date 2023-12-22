package com.wits.ksw.launcher.view.lexusls.adapter;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class LexusLsBottomViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentLexusLsBottomOne fragmentLexusLsBottomOne;
    public FragmentLexusLsBottomTwo fragmentLexusLsBottomTwo;
    private List<Fragment> fragmentList;

    public LexusLsBottomViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentLexusLsBottomOne = new FragmentLexusLsBottomOne();
        this.fragmentLexusLsBottomTwo = new FragmentLexusLsBottomTwo();
        this.fragmentList.add(this.fragmentLexusLsBottomOne);
        this.fragmentList.add(this.fragmentLexusLsBottomTwo);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list != null && !list.isEmpty()) {
            return this.fragmentList.get(i);
        }
        return null;
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list != null && !list.isEmpty()) {
            return this.fragmentList.size();
        }
        return 0;
    }
}
