package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import skin.support.SkinCompatManager;
import skin.support.utils.ImageUtils;
import skin.support.utils.SkinPreference;
import skin.support.utils.Slog;

public class SkinCompatUserThemeManager {
    private static SkinCompatUserThemeManager INSTANCE = new SkinCompatUserThemeManager();
    private static final String KEY_DRAWABLE_NAME = "drawableName";
    private static final String KEY_DRAWABLE_PATH_AND_ANGLE = "drawablePathAndAngle";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TYPE_COLOR = "color";
    private static final String KEY_TYPE_DRAWABLE = "drawable";
    private static final String TAG = "SkinCompatUserThemeManager";
    private final Object mColorCacheLock = new Object();
    private final WeakHashMap<Integer, WeakReference<ColorStateList>> mColorCaches = new WeakHashMap<>();
    private boolean mColorEmpty;
    private final HashMap<String, ColorState> mColorNameStateMap = new HashMap<>();
    private final Object mDrawableCacheLock = new Object();
    private final WeakHashMap<Integer, WeakReference<Drawable>> mDrawableCaches = new WeakHashMap<>();
    private boolean mDrawableEmpty;
    private final HashMap<String, String> mDrawablePathAndAngleMap = new HashMap<>();

    private SkinCompatUserThemeManager() {
        try {
            startLoadFromSharedPreferences();
        } catch (JSONException e) {
            this.mColorNameStateMap.clear();
            this.mDrawablePathAndAngleMap.clear();
            if (Slog.DEBUG) {
                Slog.i(TAG, "startLoadFromSharedPreferences error: " + e);
            }
        }
    }

    private void startLoadFromSharedPreferences() throws JSONException {
        String colors = SkinPreference.getInstance().getUserTheme();
        if (!TextUtils.isEmpty(colors)) {
            JSONArray jsonArray = new JSONArray(colors);
            if (Slog.DEBUG) {
                Slog.i(TAG, "startLoadFromSharedPreferences: " + jsonArray.toString());
            }
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has(KEY_TYPE)) {
                    String type = jsonObject.getString(KEY_TYPE);
                    if (KEY_TYPE_COLOR.equals(type)) {
                        ColorState state = ColorState.fromJSONObject(jsonObject);
                        if (state != null) {
                            this.mColorNameStateMap.put(state.colorName, state);
                        }
                    } else if (KEY_TYPE_DRAWABLE.equals(type)) {
                        String drawableName = jsonObject.getString(KEY_DRAWABLE_NAME);
                        String drawablePathAndAngle = jsonObject.getString(KEY_DRAWABLE_PATH_AND_ANGLE);
                        if (!TextUtils.isEmpty(drawableName) && !TextUtils.isEmpty(drawablePathAndAngle)) {
                            this.mDrawablePathAndAngleMap.put(drawableName, drawablePathAndAngle);
                        }
                    }
                }
            }
            this.mColorEmpty = this.mColorNameStateMap.isEmpty();
            this.mDrawableEmpty = this.mDrawablePathAndAngleMap.isEmpty();
        }
    }

    public void apply() {
        JSONArray jsonArray = new JSONArray();
        for (String colorName : this.mColorNameStateMap.keySet()) {
            ColorState state = this.mColorNameStateMap.get(colorName);
            if (state != null) {
                try {
                    jsonArray.put(ColorState.toJSONObject(state).putOpt(KEY_TYPE, KEY_TYPE_COLOR));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        for (String drawableName : this.mDrawablePathAndAngleMap.keySet()) {
            try {
                jsonArray.put(new JSONObject().putOpt(KEY_TYPE, KEY_TYPE_DRAWABLE).putOpt(KEY_DRAWABLE_NAME, drawableName).putOpt(KEY_DRAWABLE_PATH_AND_ANGLE, this.mDrawablePathAndAngleMap.get(drawableName)));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (Slog.DEBUG) {
            Slog.i(TAG, "Apply user theme: " + jsonArray.toString());
        }
        SkinPreference.getInstance().setUserTheme(jsonArray.toString()).commitEditor();
        SkinCompatManager.getInstance().notifyUpdateSkin();
    }

    public static SkinCompatUserThemeManager get() {
        return INSTANCE;
    }

    public void addColorState(int colorRes, ColorState state) {
        String entry = getEntryName(colorRes, KEY_TYPE_COLOR);
        if (!TextUtils.isEmpty(entry) && state != null) {
            state.colorName = entry;
            this.mColorNameStateMap.put(entry, state);
            removeColorInCache(colorRes);
            this.mColorEmpty = false;
        }
    }

    public void addColorState(int colorRes, String colorDefault) {
        if (ColorState.checkColorValid("colorDefault", colorDefault)) {
            String entry = getEntryName(colorRes, KEY_TYPE_COLOR);
            if (!TextUtils.isEmpty(entry)) {
                this.mColorNameStateMap.put(entry, new ColorState(entry, colorDefault));
                removeColorInCache(colorRes);
                this.mColorEmpty = false;
            }
        }
    }

    public void removeColorState(int colorRes) {
        String entry = getEntryName(colorRes, KEY_TYPE_COLOR);
        if (!TextUtils.isEmpty(entry)) {
            this.mColorNameStateMap.remove(entry);
            removeColorInCache(colorRes);
            this.mColorEmpty = this.mColorNameStateMap.isEmpty();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeColorState(String colorName) {
        if (!TextUtils.isEmpty(colorName)) {
            this.mColorNameStateMap.remove(colorName);
            this.mColorEmpty = this.mColorNameStateMap.isEmpty();
        }
    }

    public ColorState getColorState(String colorName) {
        return this.mColorNameStateMap.get(colorName);
    }

    public ColorState getColorState(int colorRes) {
        String entry = getEntryName(colorRes, KEY_TYPE_COLOR);
        if (!TextUtils.isEmpty(entry)) {
            return this.mColorNameStateMap.get(entry);
        }
        return null;
    }

    public ColorStateList getColorStateList(int colorRes) {
        ColorState state;
        ColorStateList colorStateList = getCachedColor(colorRes);
        if (colorStateList == null) {
            String entry = getEntryName(colorRes, KEY_TYPE_COLOR);
            if (!(TextUtils.isEmpty(entry) || (state = this.mColorNameStateMap.get(entry)) == null || (colorStateList = state.parse()) == null)) {
                addColorToCache(colorRes, colorStateList);
            }
        }
        return colorStateList;
    }

    public void addDrawablePath(int drawableRes, String drawablePath) {
        if (checkPathValid(drawablePath)) {
            String entry = getEntryName(drawableRes, KEY_TYPE_DRAWABLE);
            if (!TextUtils.isEmpty(entry)) {
                this.mDrawablePathAndAngleMap.put(entry, drawablePath + ":" + String.valueOf(ImageUtils.getImageRotateAngle(drawablePath)));
                removeDrawableInCache(drawableRes);
                this.mDrawableEmpty = false;
            }
        }
    }

    public void addDrawablePath(int drawableRes, String drawablePath, int angle) {
        if (checkPathValid(drawablePath)) {
            String entry = getEntryName(drawableRes, KEY_TYPE_DRAWABLE);
            if (!TextUtils.isEmpty(entry)) {
                this.mDrawablePathAndAngleMap.put(entry, drawablePath + ":" + String.valueOf(angle));
                removeDrawableInCache(drawableRes);
                this.mDrawableEmpty = false;
            }
        }
    }

    public void removeDrawablePath(int drawableRes) {
        String entry = getEntryName(drawableRes, KEY_TYPE_DRAWABLE);
        if (!TextUtils.isEmpty(entry)) {
            this.mDrawablePathAndAngleMap.remove(entry);
            removeDrawableInCache(drawableRes);
            this.mDrawableEmpty = this.mDrawablePathAndAngleMap.isEmpty();
        }
    }

    public String getDrawablePath(String drawableName) {
        String drawablePathAndAngle = this.mDrawablePathAndAngleMap.get(drawableName);
        if (!TextUtils.isEmpty(drawablePathAndAngle)) {
            return drawablePathAndAngle.split(":")[0];
        }
        return "";
    }

    public int getDrawableAngle(String drawableName) {
        String drawablePathAndAngle = this.mDrawablePathAndAngleMap.get(drawableName);
        if (TextUtils.isEmpty(drawablePathAndAngle)) {
            return 0;
        }
        String[] splits = drawablePathAndAngle.split(":");
        if (splits.length == 2) {
            return Integer.valueOf(splits[1]).intValue();
        }
        return 0;
    }

    public Drawable getDrawable(int drawableRes) {
        int i = drawableRes;
        Drawable drawable = getCachedDrawable(drawableRes);
        if (drawable == null) {
            String entry = getEntryName(i, KEY_TYPE_DRAWABLE);
            if (!TextUtils.isEmpty(entry)) {
                String drawablePathAndAngle = this.mDrawablePathAndAngleMap.get(entry);
                if (!TextUtils.isEmpty(drawablePathAndAngle)) {
                    String[] splits = drawablePathAndAngle.split(":");
                    String path = splits[0];
                    int angle = 0;
                    if (splits.length == 2) {
                        angle = Integer.valueOf(splits[1]).intValue();
                    }
                    if (checkPathValid(path)) {
                        if (angle == 0) {
                            drawable = Drawable.createFromPath(path);
                        } else {
                            Matrix m = new Matrix();
                            m.postRotate((float) angle);
                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                            drawable = new BitmapDrawable((Resources) null, Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true));
                        }
                        if (drawable != null) {
                            addDrawableToCache(i, drawable);
                        }
                    }
                }
            }
        }
        return drawable;
    }

    public void clearColors() {
        this.mColorNameStateMap.clear();
        clearColorCaches();
        this.mColorEmpty = true;
        apply();
    }

    public void clearDrawables() {
        this.mDrawablePathAndAngleMap.clear();
        clearDrawableCaches();
        this.mDrawableEmpty = true;
        apply();
    }

    /* access modifiers changed from: package-private */
    public boolean isColorEmpty() {
        return this.mColorEmpty;
    }

    /* access modifiers changed from: package-private */
    public boolean isDrawableEmpty() {
        return this.mDrawableEmpty;
    }

    /* access modifiers changed from: package-private */
    public void clearCaches() {
        clearColorCaches();
        clearDrawableCaches();
    }

    private void clearColorCaches() {
        synchronized (this.mColorCacheLock) {
            this.mColorCaches.clear();
        }
    }

    private void clearDrawableCaches() {
        synchronized (this.mDrawableCacheLock) {
            this.mDrawableCaches.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.content.res.ColorStateList getCachedColor(int r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mColorCacheLock
            monitor-enter(r0)
            java.util.WeakHashMap<java.lang.Integer, java.lang.ref.WeakReference<android.content.res.ColorStateList>> r1 = r5.mColorCaches     // Catch:{ all -> 0x0027 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0027 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0027 }
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0024
            java.lang.Object r2 = r1.get()     // Catch:{ all -> 0x0027 }
            android.content.res.ColorStateList r2 = (android.content.res.ColorStateList) r2     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x001b:
            java.util.WeakHashMap<java.lang.Integer, java.lang.ref.WeakReference<android.content.res.ColorStateList>> r3 = r5.mColorCaches     // Catch:{ all -> 0x0027 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0027 }
            r3.remove(r4)     // Catch:{ all -> 0x0027 }
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            r0 = 0
            return r0
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.content.res.SkinCompatUserThemeManager.getCachedColor(int):android.content.res.ColorStateList");
    }

    private void addColorToCache(int colorRes, ColorStateList colorStateList) {
        if (colorStateList != null) {
            synchronized (this.mColorCacheLock) {
                this.mColorCaches.put(Integer.valueOf(colorRes), new WeakReference(colorStateList));
            }
        }
    }

    private void removeColorInCache(int colorRes) {
        synchronized (this.mColorCacheLock) {
            this.mColorCaches.remove(Integer.valueOf(colorRes));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable getCachedDrawable(int r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mDrawableCacheLock
            monitor-enter(r0)
            java.util.WeakHashMap<java.lang.Integer, java.lang.ref.WeakReference<android.graphics.drawable.Drawable>> r1 = r5.mDrawableCaches     // Catch:{ all -> 0x0027 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0027 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0027 }
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0024
            java.lang.Object r2 = r1.get()     // Catch:{ all -> 0x0027 }
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x001b:
            java.util.WeakHashMap<java.lang.Integer, java.lang.ref.WeakReference<android.graphics.drawable.Drawable>> r3 = r5.mDrawableCaches     // Catch:{ all -> 0x0027 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0027 }
            r3.remove(r4)     // Catch:{ all -> 0x0027 }
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            r0 = 0
            return r0
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.content.res.SkinCompatUserThemeManager.getCachedDrawable(int):android.graphics.drawable.Drawable");
    }

    private void addDrawableToCache(int drawableRes, Drawable drawable) {
        if (drawable != null) {
            synchronized (this.mDrawableCacheLock) {
                this.mDrawableCaches.put(Integer.valueOf(drawableRes), new WeakReference(drawable));
            }
        }
    }

    private void removeDrawableInCache(int drawableRes) {
        synchronized (this.mDrawableCacheLock) {
            this.mDrawableCaches.remove(Integer.valueOf(drawableRes));
        }
    }

    private String getEntryName(int resId, String entryType) {
        Context context = SkinCompatManager.getInstance().getContext();
        if (entryType.equalsIgnoreCase(context.getResources().getResourceTypeName(resId))) {
            return context.getResources().getResourceEntryName(resId);
        }
        return null;
    }

    private static boolean checkPathValid(String path) {
        boolean valid = !TextUtils.isEmpty(path) && new File(path).exists();
        if (Slog.DEBUG && !valid) {
            Slog.i(TAG, "Invalid drawable path : " + path);
        }
        return valid;
    }
}
