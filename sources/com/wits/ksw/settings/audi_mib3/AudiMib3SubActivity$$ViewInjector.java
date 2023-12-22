package com.wits.ksw.settings.audi_mib3;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.wits.ksw.C0899R;

/* loaded from: classes15.dex */
public class AudiMib3SubActivity$$ViewInjector {
    public static void inject(ButterKnife.Finder finder, final AudiMib3SubActivity target, Object source) {
        View view = finder.findRequiredView(source, C0899R.C0901id.audiSubContentLinearLayout, "field 'contentLayout'");
        target.contentLayout = (RelativeLayout) view;
    }

    public static void reset(AudiMib3SubActivity target) {
        target.contentLayout = null;
    }
}
