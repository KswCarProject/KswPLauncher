package android.support.p004v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

/* renamed from: android.support.v7.app.ActionBarDrawerToggleHoneycomb */
/* loaded from: classes.dex */
class ActionBarDrawerToggleHoneycomb {
    private static final String TAG = "ActionBarDrawerToggleHC";
    private static final int[] THEME_ATTRS = {16843531};

    public static SetIndicatorInfo setActionBarUpIndicator(SetIndicatorInfo info, Activity activity, Drawable drawable, int contentDescRes) {
        SetIndicatorInfo info2 = new SetIndicatorInfo(activity);
        if (info2.setHomeAsUpIndicator != null) {
            try {
                ActionBar actionBar = activity.getActionBar();
                info2.setHomeAsUpIndicator.invoke(actionBar, drawable);
                info2.setHomeActionContentDescription.invoke(actionBar, Integer.valueOf(contentDescRes));
            } catch (Exception e) {
                Log.w(TAG, "Couldn't set home-as-up indicator via JB-MR2 API", e);
            }
        } else if (info2.upIndicatorView != null) {
            info2.upIndicatorView.setImageDrawable(drawable);
        } else {
            Log.w(TAG, "Couldn't set home-as-up indicator");
        }
        return info2;
    }

    public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo info, Activity activity, int contentDescRes) {
        if (info == null) {
            info = new SetIndicatorInfo(activity);
        }
        if (info.setHomeAsUpIndicator != null) {
            try {
                ActionBar actionBar = activity.getActionBar();
                info.setHomeActionContentDescription.invoke(actionBar, Integer.valueOf(contentDescRes));
                if (Build.VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
            } catch (Exception e) {
                Log.w(TAG, "Couldn't set content description via JB-MR2 API", e);
            }
        }
        return info;
    }

    public static Drawable getThemeUpIndicator(Activity activity) {
        TypedArray a = activity.obtainStyledAttributes(THEME_ATTRS);
        Drawable result = a.getDrawable(0);
        a.recycle();
        return result;
    }

    /* renamed from: android.support.v7.app.ActionBarDrawerToggleHoneycomb$SetIndicatorInfo */
    /* loaded from: classes.dex */
    static class SetIndicatorInfo {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        SetIndicatorInfo(Activity activity) {
            try {
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", Drawable.class);
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", Integer.TYPE);
            } catch (NoSuchMethodException e) {
                View home = activity.findViewById(16908332);
                if (home == null) {
                    return;
                }
                ViewGroup parent = (ViewGroup) home.getParent();
                int childCount = parent.getChildCount();
                if (childCount != 2) {
                    return;
                }
                View first = parent.getChildAt(0);
                View second = parent.getChildAt(1);
                View up = first.getId() == 16908332 ? second : first;
                if (up instanceof ImageView) {
                    this.upIndicatorView = (ImageView) up;
                }
            }
        }
    }

    private ActionBarDrawerToggleHoneycomb() {
    }
}
