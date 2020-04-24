package com.wits.ksw.settings.id7.layout_factory;

import android.view.View;
import com.wits.pms.statuscontrol.WitsCommand;

/* renamed from: com.wits.ksw.settings.id7.layout_factory.-$$Lambda$FactoryInput$HW8JpZHdzbdm4OHzfpZZLWmuThA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$FactoryInput$HW8JpZHdzbdm4OHzfpZZLWmuThA implements View.OnClickListener {
    public static final /* synthetic */ $$Lambda$FactoryInput$HW8JpZHdzbdm4OHzfpZZLWmuThA INSTANCE = new $$Lambda$FactoryInput$HW8JpZHdzbdm4OHzfpZZLWmuThA();

    private /* synthetic */ $$Lambda$FactoryInput$HW8JpZHdzbdm4OHzfpZZLWmuThA() {
    }

    public final void onClick(View view) {
        WitsCommand.sendCommand(1, WitsCommand.MediaSubCommand.MUSIC_LIST_CLOSE, (String) null);
    }
}
