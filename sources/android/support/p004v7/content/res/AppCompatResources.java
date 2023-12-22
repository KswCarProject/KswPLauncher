package android.support.p004v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p001v4.content.ContextCompat;
import android.support.p001v4.content.res.ColorStateListInflaterCompat;
import android.support.p004v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: android.support.v7.content.res.AppCompatResources */
/* loaded from: classes.dex */
public final class AppCompatResources {
    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal<>();
    private static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap<>(0);
    private static final Object sColorStateCacheLock = new Object();

    private AppCompatResources() {
    }

    public static ColorStateList getColorStateList(Context context, int resId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(resId);
        }
        ColorStateList csl = getCachedColorStateList(context, resId);
        if (csl != null) {
            return csl;
        }
        ColorStateList csl2 = inflateColorStateList(context, resId);
        if (csl2 != null) {
            addColorStateListToCache(context, resId, csl2);
            return csl2;
        }
        return ContextCompat.getColorStateList(context, resId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return AppCompatDrawableManager.get().getDrawable(context, resId);
    }

    private static ColorStateList inflateColorStateList(Context context, int resId) {
        if (isColorInt(context, resId)) {
            return null;
        }
        Resources r = context.getResources();
        XmlPullParser xml = r.getXml(resId);
        try {
            return ColorStateListInflaterCompat.createFromXml(r, xml, context.getTheme());
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static ColorStateList getCachedColorStateList(Context context, int resId) {
        ColorStateListCacheEntry entry;
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> entries = sColorStateCaches.get(context);
            if (entries != null && entries.size() > 0 && (entry = entries.get(resId)) != null) {
                if (entry.configuration.equals(context.getResources().getConfiguration())) {
                    return entry.value;
                }
                entries.remove(resId);
            }
            return null;
        }
    }

    private static void addColorStateListToCache(Context context, int resId, ColorStateList value) {
        synchronized (sColorStateCacheLock) {
            WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> weakHashMap = sColorStateCaches;
            SparseArray<ColorStateListCacheEntry> entries = weakHashMap.get(context);
            if (entries == null) {
                entries = new SparseArray<>();
                weakHashMap.put(context, entries);
            }
            entries.append(resId, new ColorStateListCacheEntry(value, context.getResources().getConfiguration()));
        }
    }

    private static boolean isColorInt(Context context, int resId) {
        Resources r = context.getResources();
        TypedValue value = getTypedValue();
        r.getValue(resId, value, true);
        return value.type >= 28 && value.type <= 31;
    }

    private static TypedValue getTypedValue() {
        ThreadLocal<TypedValue> threadLocal = TL_TYPED_VALUE;
        TypedValue tv = threadLocal.get();
        if (tv == null) {
            TypedValue tv2 = new TypedValue();
            threadLocal.set(tv2);
            return tv2;
        }
        return tv;
    }

    /* renamed from: android.support.v7.content.res.AppCompatResources$ColorStateListCacheEntry */
    /* loaded from: classes.dex */
    private static class ColorStateListCacheEntry {
        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(ColorStateList value, Configuration configuration) {
            this.value = value;
            this.configuration = configuration;
        }
    }
}
