package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.p001v4.graphics.ColorUtils;
import android.support.p001v4.graphics.drawable.DrawableCompat;
import android.support.p001v4.util.ArrayMap;
import android.support.p001v4.util.LongSparseArray;
import android.support.p001v4.util.LruCache;
import android.support.p004v7.appcompat.C0365R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
final class SkinCompatDrawableManager {
    private static final boolean DEBUG = false;
    private static SkinCompatDrawableManager INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private ArrayMap<String, InflateDelegate> mDelegates;
    private final Object mDrawableCacheLock = new Object();
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<>(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;
    private static final String TAG = SkinCompatDrawableManager.class.getSimpleName();
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {C0365R.C0366drawable.abc_textfield_search_default_mtrl_alpha, C0365R.C0366drawable.abc_textfield_default_mtrl_alpha, C0365R.C0366drawable.abc_ab_share_pack_mtrl_alpha};
    private static final int[] TINT_COLOR_CONTROL_NORMAL = {C0365R.C0366drawable.abc_ic_commit_search_api_mtrl_alpha, C0365R.C0366drawable.abc_seekbar_tick_mark_material, C0365R.C0366drawable.abc_ic_menu_share_mtrl_alpha, C0365R.C0366drawable.abc_ic_menu_copy_mtrl_am_alpha, C0365R.C0366drawable.abc_ic_menu_cut_mtrl_alpha, C0365R.C0366drawable.abc_ic_menu_selectall_mtrl_alpha, C0365R.C0366drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {C0365R.C0366drawable.abc_textfield_activated_mtrl_alpha, C0365R.C0366drawable.abc_textfield_search_activated_mtrl_alpha, C0365R.C0366drawable.abc_cab_background_top_mtrl_alpha, C0365R.C0366drawable.abc_text_cursor_material, C0365R.C0366drawable.abc_text_select_handle_left_mtrl_dark, C0365R.C0366drawable.abc_text_select_handle_middle_mtrl_dark, C0365R.C0366drawable.abc_text_select_handle_right_mtrl_dark, C0365R.C0366drawable.abc_text_select_handle_left_mtrl_light, C0365R.C0366drawable.abc_text_select_handle_middle_mtrl_light, C0365R.C0366drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {C0365R.C0366drawable.abc_popup_background_mtrl_mult, C0365R.C0366drawable.abc_cab_background_internal_bg, C0365R.C0366drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST = {C0365R.C0366drawable.abc_tab_indicator_material, C0365R.C0366drawable.abc_textfield_search_material};
    private static final int[] TINT_CHECKABLE_BUTTON_LIST = {C0365R.C0366drawable.abc_btn_check_material, C0365R.C0366drawable.abc_btn_radio_material};

    /* loaded from: classes.dex */
    private interface InflateDelegate {
        Drawable createFromXmlInner(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    SkinCompatDrawableManager() {
    }

    public static SkinCompatDrawableManager get() {
        if (INSTANCE == null) {
            SkinCompatDrawableManager skinCompatDrawableManager = new SkinCompatDrawableManager();
            INSTANCE = skinCompatDrawableManager;
            installDefaultInflateDelegates(skinCompatDrawableManager);
        }
        return INSTANCE;
    }

    private static void installDefaultInflateDelegates(SkinCompatDrawableManager manager) {
        if (Build.VERSION.SDK_INT < 24) {
            manager.addDelegate("vector", new VdcInflateDelegate());
            manager.addDelegate("animated-vector", new AvdcInflateDelegate());
        }
    }

    void clearCaches() {
        this.mDrawableCaches.clear();
        SparseArray<String> sparseArray = this.mKnownDrawableIdTags;
        if (sparseArray != null) {
            sparseArray.clear();
        }
        WeakHashMap<Context, SparseArray<ColorStateList>> weakHashMap = this.mTintLists;
        if (weakHashMap != null) {
            weakHashMap.clear();
        }
        COLOR_FILTER_CACHE.evictAll();
    }

    public Drawable getDrawable(Context context, int resId) {
        return getDrawable(context, resId, false);
    }

    Drawable getDrawable(Context context, int resId, boolean failIfNotKnown) {
        checkVectorDrawableSetup(context);
        Drawable drawable = loadDrawableFromDelegates(context, resId);
        if (drawable == null) {
            drawable = createDrawableIfNeeded(context, resId);
        }
        if (drawable == null) {
            drawable = SkinCompatResources.getDrawable(context, resId);
        }
        if (drawable != null) {
            drawable = tintDrawable(context, resId, failIfNotKnown, drawable);
        }
        if (drawable != null) {
            SkinCompatDrawableUtils.fixDrawable(drawable);
        }
        return drawable;
    }

    public void onConfigurationChanged(Context context) {
        synchronized (this.mDrawableCacheLock) {
            LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context);
            if (cache != null) {
                cache.clear();
            }
        }
    }

    private static long createCacheKey(TypedValue tv) {
        return (tv.assetCookie << 32) | tv.data;
    }

    private Drawable createDrawableIfNeeded(Context context, int resId) {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue tv = this.mTypedValue;
        SkinCompatResources.getValue(context, resId, tv, true);
        long key = createCacheKey(tv);
        Drawable dr = getCachedDrawable(context, key);
        if (dr != null) {
            return dr;
        }
        if (resId == C0365R.C0366drawable.abc_cab_background_top_material) {
            dr = new LayerDrawable(new Drawable[]{getDrawable(context, C0365R.C0366drawable.abc_cab_background_internal_bg), getDrawable(context, C0365R.C0366drawable.abc_cab_background_top_mtrl_alpha)});
        }
        if (dr != null) {
            dr.setChangingConfigurations(tv.changingConfigurations);
            addDrawableToCache(context, key, dr);
        }
        return dr;
    }

    private Drawable tintDrawable(Context context, int resId, boolean failIfNotKnown, Drawable drawable) {
        ColorStateList tintList = getTintList(context, resId);
        if (tintList != null) {
            if (SkinCompatDrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable drawable2 = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(drawable2, tintList);
            PorterDuff.Mode tintMode = getTintMode(resId);
            if (tintMode != null) {
                DrawableCompat.setTintMode(drawable2, tintMode);
                return drawable2;
            }
            return drawable2;
        } else if (resId == C0365R.C0366drawable.abc_seekbar_track_material) {
            LayerDrawable ld = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId = ld.findDrawableByLayerId(16908288);
            int themeAttrColor = SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlNormal);
            PorterDuff.Mode mode = DEFAULT_MODE;
            setPorterDuffColorFilter(findDrawableByLayerId, themeAttrColor, mode);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908303), SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlNormal), mode);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908301), SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlActivated), mode);
            return drawable;
        } else if (resId == C0365R.C0366drawable.abc_ratingbar_material || resId == C0365R.C0366drawable.abc_ratingbar_indicator_material || resId == C0365R.C0366drawable.abc_ratingbar_small_material) {
            LayerDrawable ld2 = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId2 = ld2.findDrawableByLayerId(16908288);
            int disabledThemeAttrColor = SkinCompatThemeUtils.getDisabledThemeAttrColor(context, C0365R.attr.colorControlNormal);
            PorterDuff.Mode mode2 = DEFAULT_MODE;
            setPorterDuffColorFilter(findDrawableByLayerId2, disabledThemeAttrColor, mode2);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908303), SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlActivated), mode2);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908301), SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlActivated), mode2);
            return drawable;
        } else {
            boolean tinted = tintDrawableUsingColorFilter(context, resId, drawable);
            if (!tinted && failIfNotKnown) {
                return null;
            }
            return drawable;
        }
    }

    private Drawable loadDrawableFromDelegates(Context context, int resId) {
        int type;
        ArrayMap<String, InflateDelegate> arrayMap = this.mDelegates;
        if (arrayMap == null || arrayMap.isEmpty()) {
            return null;
        }
        SparseArray<String> sparseArray = this.mKnownDrawableIdTags;
        if (sparseArray != null) {
            String cachedTagName = sparseArray.get(resId);
            if (SKIP_DRAWABLE_TAG.equals(cachedTagName) || (cachedTagName != null && this.mDelegates.get(cachedTagName) == null)) {
                return null;
            }
        } else {
            this.mKnownDrawableIdTags = new SparseArray<>();
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue tv = this.mTypedValue;
        SkinCompatResources.getValue(context, resId, tv, true);
        long key = createCacheKey(tv);
        Drawable dr = getCachedDrawable(context, key);
        if (dr != null) {
            return dr;
        }
        if (tv.string != null && tv.string.toString().endsWith(".xml")) {
            try {
                XmlPullParser parser = SkinCompatResources.getXml(context, resId);
                AttributeSet attrs = Xml.asAttributeSet(parser);
                while (true) {
                    type = parser.next();
                    if (type == 2 || type == 1) {
                        break;
                    }
                }
                if (type != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String tagName = parser.getName();
                this.mKnownDrawableIdTags.append(resId, tagName);
                InflateDelegate delegate = this.mDelegates.get(tagName);
                if (delegate != null) {
                    dr = delegate.createFromXmlInner(context, parser, attrs, null);
                }
                if (dr != null) {
                    dr.setChangingConfigurations(tv.changingConfigurations);
                    addDrawableToCache(context, key, dr);
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception while inflating drawable", e);
            }
        }
        if (dr == null) {
            this.mKnownDrawableIdTags.append(resId, SKIP_DRAWABLE_TAG);
        }
        return dr;
    }

    private Drawable getCachedDrawable(Context context, long key) {
        synchronized (this.mDrawableCacheLock) {
            LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context);
            if (cache == null) {
                return null;
            }
            WeakReference<Drawable.ConstantState> wr = cache.get(key);
            if (wr != null) {
                Drawable.ConstantState entry = wr.get();
                if (entry != null) {
                    return entry.newDrawable(context.getResources());
                }
                cache.delete(key);
            }
            return null;
        }
    }

    private boolean addDrawableToCache(Context context, long key, Drawable drawable) {
        Drawable.ConstantState cs = drawable.getConstantState();
        if (cs != null) {
            synchronized (this.mDrawableCacheLock) {
                LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context);
                if (cache == null) {
                    cache = new LongSparseArray<>();
                    this.mDrawableCaches.put(context, cache);
                }
                cache.put(key, new WeakReference<>(cs));
            }
            return true;
        }
        return false;
    }

    static boolean tintDrawableUsingColorFilter(Context context, int resId, Drawable drawable) {
        PorterDuff.Mode tintMode = DEFAULT_MODE;
        boolean colorAttrSet = false;
        int colorAttr = 0;
        int alpha = -1;
        if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, resId)) {
            colorAttr = C0365R.attr.colorControlNormal;
            colorAttrSet = true;
        } else if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, resId)) {
            colorAttr = C0365R.attr.colorControlActivated;
            colorAttrSet = true;
        } else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, resId)) {
            colorAttr = 16842801;
            colorAttrSet = true;
            tintMode = PorterDuff.Mode.MULTIPLY;
        } else if (resId == C0365R.C0366drawable.abc_list_divider_mtrl_alpha) {
            colorAttr = 16842800;
            colorAttrSet = true;
            alpha = Math.round(40.8f);
        } else if (resId == C0365R.C0366drawable.abc_dialog_material_background) {
            colorAttr = 16842801;
            colorAttrSet = true;
        }
        if (colorAttrSet) {
            if (SkinCompatDrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            int color = SkinCompatThemeUtils.getThemeAttrColor(context, colorAttr);
            drawable.setColorFilter(getPorterDuffColorFilter(color, tintMode));
            if (alpha != -1) {
                drawable.setAlpha(alpha);
                return true;
            }
            return true;
        }
        return false;
    }

    private void addDelegate(String tagName, InflateDelegate delegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap<>();
        }
        this.mDelegates.put(tagName, delegate);
    }

    private void removeDelegate(String tagName, InflateDelegate delegate) {
        ArrayMap<String, InflateDelegate> arrayMap = this.mDelegates;
        if (arrayMap != null && arrayMap.get(tagName) == delegate) {
            this.mDelegates.remove(tagName);
        }
    }

    private static boolean arrayContains(int[] array, int value) {
        for (int id : array) {
            if (id == value) {
                return true;
            }
        }
        return false;
    }

    static PorterDuff.Mode getTintMode(int resId) {
        if (resId != C0365R.C0366drawable.abc_switch_thumb_material) {
            return null;
        }
        PorterDuff.Mode mode = PorterDuff.Mode.MULTIPLY;
        return mode;
    }

    ColorStateList getTintList(Context context, int resId) {
        ColorStateList tint = getTintListFromCache(context, resId);
        if (tint == null) {
            if (resId == C0365R.C0366drawable.abc_edit_text_material) {
                tint = SkinCompatResources.getColorStateList(context, C0365R.color.abc_tint_edittext);
            } else if (resId == C0365R.C0366drawable.abc_switch_track_mtrl_alpha) {
                tint = SkinCompatResources.getColorStateList(context, C0365R.color.abc_tint_switch_track);
            } else if (resId == C0365R.C0366drawable.abc_switch_thumb_material) {
                tint = createSwitchThumbColorStateList(context);
            } else if (resId == C0365R.C0366drawable.abc_btn_default_mtrl_shape) {
                tint = createDefaultButtonColorStateList(context);
            } else if (resId == C0365R.C0366drawable.abc_btn_borderless_material) {
                tint = createBorderlessButtonColorStateList(context);
            } else if (resId == C0365R.C0366drawable.abc_btn_colored_material) {
                tint = createColoredButtonColorStateList(context);
            } else if (resId == C0365R.C0366drawable.abc_spinner_mtrl_am_alpha || resId == C0365R.C0366drawable.abc_spinner_textfield_background_material) {
                tint = SkinCompatResources.getColorStateList(context, C0365R.color.abc_tint_spinner);
            } else if (arrayContains(TINT_COLOR_CONTROL_NORMAL, resId)) {
                tint = SkinCompatThemeUtils.getThemeAttrColorStateList(context, C0365R.attr.colorControlNormal);
            } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, resId)) {
                tint = SkinCompatResources.getColorStateList(context, C0365R.color.abc_tint_default);
            } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, resId)) {
                tint = SkinCompatResources.getColorStateList(context, C0365R.color.abc_tint_btn_checkable);
            } else if (resId == C0365R.C0366drawable.abc_seekbar_thumb_material) {
                tint = SkinCompatResources.getColorStateList(context, C0365R.color.abc_tint_seek_thumb);
            }
            if (tint != null) {
                addTintListToCache(context, resId, tint);
            }
        }
        return tint;
    }

    private ColorStateList getTintListFromCache(Context context, int resId) {
        SparseArray<ColorStateList> tints;
        WeakHashMap<Context, SparseArray<ColorStateList>> weakHashMap = this.mTintLists;
        if (weakHashMap == null || (tints = weakHashMap.get(context)) == null) {
            return null;
        }
        return tints.get(resId);
    }

    private void addTintListToCache(Context context, int resId, ColorStateList tintList) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap<>();
        }
        SparseArray<ColorStateList> themeTints = this.mTintLists.get(context);
        if (themeTints == null) {
            themeTints = new SparseArray<>();
            this.mTintLists.put(context, themeTints);
        }
        themeTints.append(resId, tintList);
    }

    private ColorStateList createDefaultButtonColorStateList(Context context) {
        return createButtonColorStateList(context, SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorButtonNormal));
    }

    private ColorStateList createBorderlessButtonColorStateList(Context context) {
        return createButtonColorStateList(context, 0);
    }

    private ColorStateList createColoredButtonColorStateList(Context context) {
        return createButtonColorStateList(context, SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorAccent));
    }

    private ColorStateList createButtonColorStateList(Context context, int baseColor) {
        int[][] states = new int[4];
        int[] colors = new int[4];
        int colorControlHighlight = SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlHighlight);
        int disabledColor = SkinCompatThemeUtils.getDisabledThemeAttrColor(context, C0365R.attr.colorButtonNormal);
        states[0] = SkinCompatThemeUtils.DISABLED_STATE_SET;
        colors[0] = disabledColor;
        int i = 0 + 1;
        states[i] = SkinCompatThemeUtils.PRESSED_STATE_SET;
        colors[i] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
        int i2 = i + 1;
        states[i2] = SkinCompatThemeUtils.FOCUSED_STATE_SET;
        colors[i2] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
        int i3 = i2 + 1;
        states[i3] = SkinCompatThemeUtils.EMPTY_STATE_SET;
        colors[i3] = baseColor;
        int i4 = i3 + 1;
        return new ColorStateList(states, colors);
    }

    private ColorStateList createSwitchThumbColorStateList(Context context) {
        int[][] states = new int[3];
        int[] colors = new int[3];
        ColorStateList thumbColor = SkinCompatThemeUtils.getThemeAttrColorStateList(context, C0365R.attr.colorSwitchThumbNormal);
        if (thumbColor == null || !thumbColor.isStateful()) {
            states[0] = SkinCompatThemeUtils.DISABLED_STATE_SET;
            colors[0] = SkinCompatThemeUtils.getDisabledThemeAttrColor(context, C0365R.attr.colorSwitchThumbNormal);
            int i = 0 + 1;
            states[i] = SkinCompatThemeUtils.CHECKED_STATE_SET;
            colors[i] = SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlActivated);
            int i2 = i + 1;
            states[i2] = SkinCompatThemeUtils.EMPTY_STATE_SET;
            colors[i2] = SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorSwitchThumbNormal);
            int i3 = i2 + 1;
        } else {
            states[0] = SkinCompatThemeUtils.DISABLED_STATE_SET;
            colors[0] = thumbColor.getColorForState(states[0], 0);
            int i4 = 0 + 1;
            states[i4] = SkinCompatThemeUtils.CHECKED_STATE_SET;
            colors[i4] = SkinCompatThemeUtils.getThemeAttrColor(context, C0365R.attr.colorControlActivated);
            int i5 = i4 + 1;
            states[i5] = SkinCompatThemeUtils.EMPTY_STATE_SET;
            colors[i5] = thumbColor.getDefaultColor();
            int i6 = i5 + 1;
        }
        return new ColorStateList(states, colors);
    }

    /* loaded from: classes.dex */
    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int maxSize) {
            super(maxSize);
        }

        PorterDuffColorFilter get(int color, PorterDuff.Mode mode) {
            return get(Integer.valueOf(generateCacheKey(color, mode)));
        }

        PorterDuffColorFilter put(int color, PorterDuff.Mode mode, PorterDuffColorFilter filter) {
            return put(Integer.valueOf(generateCacheKey(color, mode)), filter);
        }

        private static int generateCacheKey(int color, PorterDuff.Mode mode) {
            int hashCode = (1 * 31) + color;
            return (hashCode * 31) + mode.hashCode();
        }
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList tint, PorterDuff.Mode tintMode, int[] state) {
        if (tint == null || tintMode == null) {
            return null;
        }
        int color = tint.getColorForState(state, 0);
        return getPorterDuffColorFilter(color, tintMode);
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int color, PorterDuff.Mode mode) {
        ColorFilterLruCache colorFilterLruCache = COLOR_FILTER_CACHE;
        PorterDuffColorFilter filter = colorFilterLruCache.get(color, mode);
        if (filter == null) {
            PorterDuffColorFilter filter2 = new PorterDuffColorFilter(color, mode);
            colorFilterLruCache.put(color, mode, filter2);
            return filter2;
        }
        return filter;
    }

    private static void setPorterDuffColorFilter(Drawable d, int color, PorterDuff.Mode mode) {
        if (SkinCompatDrawableUtils.canSafelyMutateDrawable(d)) {
            d = d.mutate();
        }
        d.setColorFilter(getPorterDuffColorFilter(color, mode == null ? DEFAULT_MODE : mode));
    }

    private void checkVectorDrawableSetup(Context context) {
        if (this.mHasCheckedVectorDrawableSetup) {
            return;
        }
        this.mHasCheckedVectorDrawableSetup = true;
        Drawable d = getDrawable(context, C0365R.C0366drawable.abc_vector_test);
        if (d == null || !isVectorDrawable(d)) {
            this.mHasCheckedVectorDrawableSetup = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static boolean isVectorDrawable(Drawable d) {
        return (d instanceof VectorDrawableCompat) || PLATFORM_VD_CLAZZ.equals(d.getClass().getName());
    }

    /* loaded from: classes.dex */
    private static class VdcInflateDelegate implements InflateDelegate {
        VdcInflateDelegate() {
        }

        @Override // skin.support.content.res.SkinCompatDrawableManager.InflateDelegate
        public Drawable createFromXmlInner(Context context, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class AvdcInflateDelegate implements InflateDelegate {
        AvdcInflateDelegate() {
        }

        @Override // skin.support.content.res.SkinCompatDrawableManager.InflateDelegate
        public Drawable createFromXmlInner(Context context, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }
}
