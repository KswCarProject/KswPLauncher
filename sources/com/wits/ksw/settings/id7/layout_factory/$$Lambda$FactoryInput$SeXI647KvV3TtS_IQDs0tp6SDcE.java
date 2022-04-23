package com.wits.ksw.settings.id7.layout_factory;

import android.view.View;
import com.wits.pms.statuscontrol.WitsCommand;

/* renamed from: com.wits.ksw.settings.id7.layout_factory.-$$Lambda$FactoryInput$SeXI647KvV3TtS_IQDs0tp6SDcE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$FactoryInput$SeXI647KvV3TtS_IQDs0tp6SDcE implements View.OnClickListener {
    public static final /* synthetic */ $$Lambda$FactoryInput$SeXI647KvV3TtS_IQDs0tp6SDcE INSTANCE = new $$Lambda$FactoryInput$SeXI647KvV3TtS_IQDs0tp6SDcE();

    private /* synthetic */ $$Lambda$FactoryInput$SeXI647KvV3TtS_IQDs0tp6SDcE() {
    }

    public final void onClick(View view) {
        WitsCommand.sendCommand(1, 125, (String) null);
    }
}
