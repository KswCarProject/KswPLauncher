package com.wits.ksw.settings.audi;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.wits.ksw.C0899R;

/* loaded from: classes13.dex */
public class AudiSubActivity$$ViewInjector {
    public static void inject(ButterKnife.Finder finder, final AudiSubActivity target, Object source) {
        View view = finder.findRequiredView(source, C0899R.C0901id.audiSubContentLinearLayout, "field 'contentLayout'");
        target.contentLayout = (RelativeLayout) view;
    }

    public static void reset(AudiSubActivity target) {
        target.contentLayout = null;
    }
}
