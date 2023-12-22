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

/* loaded from: classes.dex */
public class SkinCompatUserThemeManager {
    private static SkinCompatUserThemeManager INSTANCE = new SkinCompatUserThemeManager();
    private static final String KEY_DRAWABLE_NAME = "drawableName";
    private static final String KEY_DRAWABLE_PATH_AND_ANGLE = "drawablePathAndAngle";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TYPE_COLOR = "color";
    private static final String KEY_TYPE_DRAWABLE = "drawable";
    private static final String TAG = "SkinCompatUserThemeManager";
    private boolean mColorEmpty;
    private boolean mDrawableEmpty;
    private final HashMap<String, ColorState> mColorNameStateMap = new HashMap<>();
    private final Object mColorCacheLock = new Object();
    private final WeakHashMap<Integer, WeakReference<ColorStateList>> mColorCaches = new WeakHashMap<>();
    private final HashMap<String, String> mDrawablePathAndAngleMap = new HashMap<>();
    private final Object mDrawableCacheLock = new Object();
    private final WeakHashMap<Integer, WeakReference<Drawable>> mDrawableCaches = new WeakHashMap<>();

    private SkinCompatUserThemeManager() {
        try {
            startLoadFromSharedPreferences();
        } catch (JSONException e) {
            this.mColorNameStateMap.clear();
            this.mDrawablePathAndAngleMap.clear();
            if (Slog.DEBUG) {
                Slog.m2i(TAG, "startLoadFromSharedPreferences error: " + e);
            }
        }
    }

    private void startLoadFromSharedPreferences() throws JSONException {
        String colors = SkinPreference.getInstance().getUserTheme();
        if (!TextUtils.isEmpty(colors)) {
            JSONArray jsonArray = new JSONArray(colors);
            if (Slog.DEBUG) {
                Slog.m2i(TAG, "startLoadFromSharedPreferences: " + jsonArray.toString());
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
            JSONObject object = new JSONObject();
            try {
                jsonArray.put(object.putOpt(KEY_TYPE, KEY_TYPE_DRAWABLE).putOpt(KEY_DRAWABLE_NAME, drawableName).putOpt(KEY_DRAWABLE_PATH_AND_ANGLE, this.mDrawablePathAndAngleMap.get(drawableName)));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (Slog.DEBUG) {
            Slog.m2i(TAG, "Apply user theme: " + jsonArray.toString());
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
        if (!ColorState.checkColorValid("colorDefault", colorDefault)) {
            return;
        }
        String entry = getEntryName(colorRes, KEY_TYPE_COLOR);
        if (!TextUtils.isEmpty(entry)) {
            this.mColorNameStateMap.put(entry, new ColorState(entry, colorDefault));
            removeColorInCache(colorRes);
            this.mColorEmpty = false;
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

    void removeColorState(String colorName) {
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
            if (!TextUtils.isEmpty(entry) && (state = this.mColorNameStateMap.get(entry)) != null && (colorStateList = state.parse()) != null) {
                addColorToCache(colorRes, colorStateList);
            }
        }
        return colorStateList;
    }

    public void addDrawablePath(int drawableRes, String drawablePath) {
        if (!checkPathValid(drawablePath)) {
            return;
        }
        String entry = getEntryName(drawableRes, KEY_TYPE_DRAWABLE);
        if (!TextUtils.isEmpty(entry)) {
            int angle = ImageUtils.getImageRotateAngle(drawablePath);
            String drawablePathAndAngle = drawablePath + ":" + String.valueOf(angle);
            this.mDrawablePathAndAngleMap.put(entry, drawablePathAndAngle);
            removeDrawableInCache(drawableRes);
            this.mDrawableEmpty = false;
        }
    }

    public void addDrawablePath(int drawableRes, String drawablePath, int angle) {
        if (!checkPathValid(drawablePath)) {
            return;
        }
        String entry = getEntryName(drawableRes, KEY_TYPE_DRAWABLE);
        if (!TextUtils.isEmpty(entry)) {
            String drawablePathAndAngle = drawablePath + ":" + String.valueOf(angle);
            this.mDrawablePathAndAngleMap.put(entry, drawablePathAndAngle);
            removeDrawableInCache(drawableRes);
            this.mDrawableEmpty = false;
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
            String[] splits = drawablePathAndAngle.split(":");
            return splits[0];
        }
        return "";
    }

    public int getDrawableAngle(String drawableName) {
        String drawablePathAndAngle = this.mDrawablePathAndAngleMap.get(drawableName);
        if (!TextUtils.isEmpty(drawablePathAndAngle)) {
            String[] splits = drawablePathAndAngle.split(":");
            if (splits.length == 2) {
                return Integer.valueOf(splits[1]).intValue();
            }
            return 0;
        }
        return 0;
    }

    public Drawable getDrawable(int drawableRes) {
        Drawable drawable = getCachedDrawable(drawableRes);
        if (drawable == null) {
            String entry = getEntryName(drawableRes, KEY_TYPE_DRAWABLE);
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
                            m.postRotate(angle);
                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                            drawable = new BitmapDrawable((Resources) null, Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true));
                        }
                        if (drawable != null) {
                            addDrawableToCache(drawableRes, drawable);
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

    boolean isColorEmpty() {
        return this.mColorEmpty;
    }

    boolean isDrawableEmpty() {
        return this.mDrawableEmpty;
    }

    void clearCaches() {
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

    private ColorStateList getCachedColor(int colorRes) {
        synchronized (this.mColorCacheLock) {
            WeakReference<ColorStateList> colorRef = this.mColorCaches.get(Integer.valueOf(colorRes));
            if (colorRef != null) {
                ColorStateList colorStateList = colorRef.get();
                if (colorStateList != null) {
                    return colorStateList;
                }
                this.mColorCaches.remove(Integer.valueOf(colorRes));
            }
            return null;
        }
    }

    private void addColorToCache(int colorRes, ColorStateList colorStateList) {
        if (colorStateList != null) {
            synchronized (this.mColorCacheLock) {
                this.mColorCaches.put(Integer.valueOf(colorRes), new WeakReference<>(colorStateList));
            }
        }
    }

    private void removeColorInCache(int colorRes) {
        synchronized (this.mColorCacheLock) {
            this.mColorCaches.remove(Integer.valueOf(colorRes));
        }
    }

    private Drawable getCachedDrawable(int drawableRes) {
        synchronized (this.mDrawableCacheLock) {
            WeakReference<Drawable> drawableRef = this.mDrawableCaches.get(Integer.valueOf(drawableRes));
            if (drawableRef != null) {
                Drawable drawable = drawableRef.get();
                if (drawable != null) {
                    return drawable;
                }
                this.mDrawableCaches.remove(Integer.valueOf(drawableRes));
            }
            return null;
        }
    }

    private void addDrawableToCache(int drawableRes, Drawable drawable) {
        if (drawable != null) {
            synchronized (this.mDrawableCacheLock) {
                this.mDrawableCaches.put(Integer.valueOf(drawableRes), new WeakReference<>(drawable));
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
        String type = context.getResources().getResourceTypeName(resId);
        if (entryType.equalsIgnoreCase(type)) {
            return context.getResources().getResourceEntryName(resId);
        }
        return null;
    }

    private static boolean checkPathValid(String path) {
        boolean valid = !TextUtils.isEmpty(path) && new File(path).exists();
        if (Slog.DEBUG && !valid) {
            Slog.m2i(TAG, "Invalid drawable path : " + path);
        }
        return valid;
    }
}
