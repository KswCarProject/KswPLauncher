package android.support.p004v7.app;

import android.support.p004v7.view.ActionMode;

/* renamed from: android.support.v7.app.AppCompatCallback */
/* loaded from: classes.dex */
public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback);
}
