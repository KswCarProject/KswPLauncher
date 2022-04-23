package com.wits.ksw.settings.als_id7_ui_set.factory;

import android.view.View;
import com.wits.pms.statuscontrol.WitsCommand;

/* renamed from: com.wits.ksw.settings.als_id7_ui_set.factory.-$$Lambda$AlsID7UiFactoryInput$RD3B31deT64JfE03sMdBD6w9ItY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$AlsID7UiFactoryInput$RD3B31deT64JfE03sMdBD6w9ItY implements View.OnClickListener {
    public static final /* synthetic */ $$Lambda$AlsID7UiFactoryInput$RD3B31deT64JfE03sMdBD6w9ItY INSTANCE = new $$Lambda$AlsID7UiFactoryInput$RD3B31deT64JfE03sMdBD6w9ItY();

    private /* synthetic */ $$Lambda$AlsID7UiFactoryInput$RD3B31deT64JfE03sMdBD6w9ItY() {
    }

    public final void onClick(View view) {
        WitsCommand.sendCommand(1, 125, (String) null);
    }
}
