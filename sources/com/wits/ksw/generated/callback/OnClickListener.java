package com.wits.ksw.generated.callback;

import android.view.View;

public final class OnClickListener implements View.OnClickListener {
    final Listener mListener;
    final int mSourceId;

    public interface Listener {
        void _internalCallbackOnClick(int i, View view);
    }

    public OnClickListener(Listener listener, int sourceId) {
        this.mListener = listener;
        this.mSourceId = sourceId;
    }

    public void onClick(View callbackArg_0) {
        this.mListener._internalCallbackOnClick(this.mSourceId, callbackArg_0);
    }
}
