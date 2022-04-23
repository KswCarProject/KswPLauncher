package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.Preconditions;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback<E> extends FragmentContainer {
    private final Activity mActivity;
    private final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    private final Handler mHandler;
    private final int mWindowAnimations;

    public abstract E onGetHost();

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FragmentHostCallback(Context context, Handler handler, int windowAnimations) {
        this(context instanceof Activity ? (Activity) context : null, context, handler, windowAnimations);
    }

    FragmentHostCallback(FragmentActivity activity) {
        this(activity, activity, activity.mHandler, 0);
    }

    FragmentHostCallback(Activity activity, Context context, Handler handler, int windowAnimations) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = activity;
        this.mContext = (Context) Preconditions.checkNotNull(context, "context == null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "handler == null");
        this.mWindowAnimations = windowAnimations;
    }

    public void onDump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment) {
        return true;
    }

    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public void onSupportInvalidateOptionsMenu() {
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        onStartActivityFromFragment(fragment, intent, requestCode, (Bundle) null);
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int requestCode, Bundle options) {
        if (requestCode == -1) {
            this.mContext.startActivity(intent);
            return;
        }
        throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
    }

    public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        if (requestCode == -1) {
            ActivityCompat.startIntentSenderForResult(this.mActivity, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        } else {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
    }

    public void onRequestPermissionsFromFragment(Fragment fragment, String[] permissions, int requestCode) {
    }

    public boolean onShouldShowRequestPermissionRationale(String permission) {
        return false;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    public View onFindViewById(int id) {
        return null;
    }

    public boolean onHasView() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.mActivity;
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public Handler getHandler() {
        return this.mHandler;
    }

    /* access modifiers changed from: package-private */
    public FragmentManagerImpl getFragmentManagerImpl() {
        return this.mFragmentManager;
    }

    /* access modifiers changed from: package-private */
    public void onAttachFragment(Fragment fragment) {
    }
}
