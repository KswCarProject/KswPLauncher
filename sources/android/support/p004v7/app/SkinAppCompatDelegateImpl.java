package android.support.p004v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.p004v7.app.ActionBarDrawerToggle;
import android.support.p004v7.view.ActionMode;
import android.support.p004v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;

/* renamed from: android.support.v7.app.SkinAppCompatDelegateImpl */
/* loaded from: classes.dex */
public class SkinAppCompatDelegateImpl extends AppCompatDelegate {
    private static Map<Activity, AppCompatDelegate> sDelegateMap = new HashMap();
    private final Activity mActivity;
    private final AppCompatDelegate mDelegate;

    public static AppCompatDelegate get(Activity activity, AppCompatCallback callback) {
        AppCompatDelegate delegate = sDelegateMap.get(activity);
        if (delegate == null) {
            AppCompatDelegate delegate2 = new SkinAppCompatDelegateImpl(activity, callback);
            sDelegateMap.put(activity, delegate2);
            return delegate2;
        }
        return delegate;
    }

    private SkinAppCompatDelegateImpl(Activity activity, AppCompatCallback callback) {
        this.mActivity = activity;
        this.mDelegate = AppCompatDelegate.create(activity, callback);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public ActionBar getSupportActionBar() {
        return this.mDelegate.getSupportActionBar();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setSupportActionBar(Toolbar toolbar) {
        this.mDelegate.setSupportActionBar(toolbar);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public MenuInflater getMenuInflater() {
        return this.mDelegate.getMenuInflater();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onCreate(Bundle savedInstanceState) {
        this.mDelegate.onCreate(savedInstanceState);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onPostCreate(Bundle savedInstanceState) {
        this.mDelegate.onPostCreate(savedInstanceState);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onConfigurationChanged(Configuration newConfig) {
        this.mDelegate.onConfigurationChanged(newConfig);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onStart() {
        this.mDelegate.onStart();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onStop() {
        this.mDelegate.onStop();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onPostResume() {
        this.mDelegate.onPostResume();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public View findViewById(int id) {
        return this.mDelegate.findViewById(id);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setContentView(View v) {
        this.mDelegate.setContentView(v);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setContentView(int resId) {
        this.mDelegate.setContentView(resId);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setContentView(View v, ViewGroup.LayoutParams lp) {
        this.mDelegate.setContentView(v, lp);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void addContentView(View v, ViewGroup.LayoutParams lp) {
        this.mDelegate.addContentView(v, lp);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setTitle(CharSequence title) {
        this.mDelegate.setTitle(title);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void invalidateOptionsMenu() {
        this.mDelegate.invalidateOptionsMenu();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onDestroy() {
        this.mDelegate.onDestroy();
        sDelegateMap.remove(this.mActivity);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return this.mDelegate.getDrawerToggleDelegate();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public boolean requestWindowFeature(int featureId) {
        return this.mDelegate.requestWindowFeature(featureId);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public boolean hasWindowFeature(int featureId) {
        return this.mDelegate.hasWindowFeature(featureId);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        return this.mDelegate.startSupportActionMode(callback);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void installViewFactory() {
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        return this.mDelegate.createView(parent, name, context, attrs);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setHandleNativeActionModesEnabled(boolean enabled) {
        this.mDelegate.setHandleNativeActionModesEnabled(enabled);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public boolean isHandleNativeActionModesEnabled() {
        return this.mDelegate.isHandleNativeActionModesEnabled();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void onSaveInstanceState(Bundle outState) {
        this.mDelegate.onSaveInstanceState(outState);
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public boolean applyDayNight() {
        return this.mDelegate.applyDayNight();
    }

    @Override // android.support.p004v7.app.AppCompatDelegate
    public void setLocalNightMode(int mode) {
        this.mDelegate.setLocalNightMode(mode);
    }
}
