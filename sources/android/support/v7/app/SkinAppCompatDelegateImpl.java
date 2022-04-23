package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;

public class SkinAppCompatDelegateImpl extends AppCompatDelegate {
    private static Map<Activity, AppCompatDelegate> sDelegateMap = new HashMap();
    private final Activity mActivity;
    private final AppCompatDelegate mDelegate;

    public static AppCompatDelegate get(Activity activity, AppCompatCallback callback) {
        AppCompatDelegate delegate = sDelegateMap.get(activity);
        if (delegate != null) {
            return delegate;
        }
        AppCompatDelegate delegate2 = new SkinAppCompatDelegateImpl(activity, callback);
        sDelegateMap.put(activity, delegate2);
        return delegate2;
    }

    private SkinAppCompatDelegateImpl(Activity activity, AppCompatCallback callback) {
        this.mActivity = activity;
        this.mDelegate = AppCompatDelegate.create(activity, callback);
    }

    public ActionBar getSupportActionBar() {
        return this.mDelegate.getSupportActionBar();
    }

    public void setSupportActionBar(Toolbar toolbar) {
        this.mDelegate.setSupportActionBar(toolbar);
    }

    public MenuInflater getMenuInflater() {
        return this.mDelegate.getMenuInflater();
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mDelegate.onCreate(savedInstanceState);
    }

    public void onPostCreate(Bundle savedInstanceState) {
        this.mDelegate.onPostCreate(savedInstanceState);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        this.mDelegate.onConfigurationChanged(newConfig);
    }

    public void onStart() {
        this.mDelegate.onStart();
    }

    public void onStop() {
        this.mDelegate.onStop();
    }

    public void onPostResume() {
        this.mDelegate.onPostResume();
    }

    public View findViewById(int id) {
        return this.mDelegate.findViewById(id);
    }

    public void setContentView(View v) {
        this.mDelegate.setContentView(v);
    }

    public void setContentView(int resId) {
        this.mDelegate.setContentView(resId);
    }

    public void setContentView(View v, ViewGroup.LayoutParams lp) {
        this.mDelegate.setContentView(v, lp);
    }

    public void addContentView(View v, ViewGroup.LayoutParams lp) {
        this.mDelegate.addContentView(v, lp);
    }

    public void setTitle(CharSequence title) {
        this.mDelegate.setTitle(title);
    }

    public void invalidateOptionsMenu() {
        this.mDelegate.invalidateOptionsMenu();
    }

    public void onDestroy() {
        this.mDelegate.onDestroy();
        sDelegateMap.remove(this.mActivity);
    }

    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return this.mDelegate.getDrawerToggleDelegate();
    }

    public boolean requestWindowFeature(int featureId) {
        return this.mDelegate.requestWindowFeature(featureId);
    }

    public boolean hasWindowFeature(int featureId) {
        return this.mDelegate.hasWindowFeature(featureId);
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        return this.mDelegate.startSupportActionMode(callback);
    }

    public void installViewFactory() {
    }

    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        return this.mDelegate.createView(parent, name, context, attrs);
    }

    public void setHandleNativeActionModesEnabled(boolean enabled) {
        this.mDelegate.setHandleNativeActionModesEnabled(enabled);
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.mDelegate.isHandleNativeActionModesEnabled();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.mDelegate.onSaveInstanceState(outState);
    }

    public boolean applyDayNight() {
        return this.mDelegate.applyDayNight();
    }

    public void setLocalNightMode(int mode) {
        this.mDelegate.setLocalNightMode(mode);
    }
}
