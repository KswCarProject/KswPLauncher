package com.wits.ksw;

import android.support.p001v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;

/* loaded from: classes17.dex */
public class MainActivity$$ViewInjector {
    public static void inject(ButterKnife.Finder finder, final MainActivity target, Object source) {
        View view = finder.findRequiredView(source, C0899R.C0901id.id6_main_view_pager, "field 'id6MainViewPager'");
        target.id6MainViewPager = (ViewPager) view;
        View view2 = finder.findRequiredView(source, C0899R.C0901id.id6_left_btn, "field 'id6LeftBtn'");
        target.id6LeftBtn = (ImageView) view2;
        View view3 = finder.findRequiredView(source, C0899R.C0901id.id6_right_btn, "field 'id6RightBtn'");
        target.id6RightBtn = (ImageView) view3;
    }

    public static void reset(MainActivity target) {
        target.id6MainViewPager = null;
        target.id6LeftBtn = null;
        target.id6RightBtn = null;
    }
}
