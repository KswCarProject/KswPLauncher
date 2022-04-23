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
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

final class SkinCompatDrawableManager {
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static SkinCompatDrawableManager INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = SkinCompatDrawableManager.class.getSimpleName();
    private static final int[] TINT_CHECKABLE_BUTTON_LIST = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material};
    private static final int[] TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
    private ArrayMap<String, InflateDelegate> mDelegates;
    private final Object mDrawableCacheLock = new Object();
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<>(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;

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

    /* access modifiers changed from: package-private */
    public void clearCaches() {
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

    /* access modifiers changed from: package-private */
    public Drawable getDrawable(Context context, int resId, boolean failIfNotKnown) {
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
        return (((long) tv.assetCookie) << 32) | ((long) tv.data);
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
        if (resId == R.drawable.abc_cab_background_top_material) {
            dr = new LayerDrawable(new Drawable[]{getDrawable(context, R.drawable.abc_cab_background_internal_bg), getDrawable(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
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
            if (tintMode == null) {
                return drawable2;
            }
            DrawableCompat.setTintMode(drawable2, tintMode);
            return drawable2;
        } else if (resId == R.drawable.abc_seekbar_track_material) {
            LayerDrawable ld = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId = ld.findDrawableByLayerId(16908288);
            int themeAttrColor = SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
            PorterDuff.Mode mode = DEFAULT_MODE;
            setPorterDuffColorFilter(findDrawableByLayerId, themeAttrColor, mode);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908303), SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), mode);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908301), SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), mode);
            return drawable;
        } else if (resId == R.drawable.abc_ratingbar_material || resId == R.drawable.abc_ratingbar_indicator_material || resId == R.drawable.abc_ratingbar_small_material) {
            LayerDrawable ld2 = (LayerDrawable) drawable;
            Drawable findDrawableByLayerId2 = ld2.findDrawableByLayerId(16908288);
            int disabledThemeAttrColor = SkinCompatThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
            PorterDuff.Mode mode2 = DEFAULT_MODE;
            setPorterDuffColorFilter(findDrawableByLayerId2, disabledThemeAttrColor, mode2);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908303), SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), mode2);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908301), SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), mode2);
            return drawable;
        } else if (tintDrawableUsingColorFilter(context, resId, drawable) || !failIfNotKnown) {
            return drawable;
        } else {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0074 A[Catch:{ Exception -> 0x009f }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0097 A[Catch:{ Exception -> 0x009f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable loadDrawableFromDelegates(android.content.Context r13, int r14) {
        /*
            r12 = this;
            android.support.v4.util.ArrayMap<java.lang.String, skin.support.content.res.SkinCompatDrawableManager$InflateDelegate> r0 = r12.mDelegates
            r1 = 0
            if (r0 == 0) goto L_0x00af
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00af
            android.util.SparseArray<java.lang.String> r0 = r12.mKnownDrawableIdTags
            java.lang.String r2 = "appcompat_skip_skip"
            if (r0 == 0) goto L_0x002a
            java.lang.Object r0 = r0.get(r14)
            java.lang.String r0 = (java.lang.String) r0
            boolean r3 = r2.equals(r0)
            if (r3 != 0) goto L_0x0029
            if (r0 == 0) goto L_0x0028
            android.support.v4.util.ArrayMap<java.lang.String, skin.support.content.res.SkinCompatDrawableManager$InflateDelegate> r3 = r12.mDelegates
            java.lang.Object r3 = r3.get(r0)
            if (r3 != 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            goto L_0x0031
        L_0x0029:
            return r1
        L_0x002a:
            android.util.SparseArray r0 = new android.util.SparseArray
            r0.<init>()
            r12.mKnownDrawableIdTags = r0
        L_0x0031:
            android.util.TypedValue r0 = r12.mTypedValue
            if (r0 != 0) goto L_0x003c
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r12.mTypedValue = r0
        L_0x003c:
            android.util.TypedValue r0 = r12.mTypedValue
            r3 = 1
            skin.support.content.res.SkinCompatResources.getValue(r13, r14, r0, r3)
            long r4 = createCacheKey(r0)
            android.graphics.drawable.Drawable r6 = r12.getCachedDrawable(r13, r4)
            if (r6 == 0) goto L_0x004d
            return r6
        L_0x004d:
            java.lang.CharSequence r7 = r0.string
            if (r7 == 0) goto L_0x00a7
            java.lang.CharSequence r7 = r0.string
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = ".xml"
            boolean r7 = r7.endsWith(r8)
            if (r7 == 0) goto L_0x00a7
            android.content.res.XmlResourceParser r7 = skin.support.content.res.SkinCompatResources.getXml(r13, r14)     // Catch:{ Exception -> 0x009f }
            android.util.AttributeSet r8 = android.util.Xml.asAttributeSet(r7)     // Catch:{ Exception -> 0x009f }
        L_0x0067:
            int r9 = r7.next()     // Catch:{ Exception -> 0x009f }
            r10 = r9
            r11 = 2
            if (r9 == r11) goto L_0x0072
            if (r10 == r3) goto L_0x0072
            goto L_0x0067
        L_0x0072:
            if (r10 != r11) goto L_0x0097
            java.lang.String r3 = r7.getName()     // Catch:{ Exception -> 0x009f }
            android.util.SparseArray<java.lang.String> r9 = r12.mKnownDrawableIdTags     // Catch:{ Exception -> 0x009f }
            r9.append(r14, r3)     // Catch:{ Exception -> 0x009f }
            android.support.v4.util.ArrayMap<java.lang.String, skin.support.content.res.SkinCompatDrawableManager$InflateDelegate> r9 = r12.mDelegates     // Catch:{ Exception -> 0x009f }
            java.lang.Object r9 = r9.get(r3)     // Catch:{ Exception -> 0x009f }
            skin.support.content.res.SkinCompatDrawableManager$InflateDelegate r9 = (skin.support.content.res.SkinCompatDrawableManager.InflateDelegate) r9     // Catch:{ Exception -> 0x009f }
            if (r9 == 0) goto L_0x008c
            android.graphics.drawable.Drawable r1 = r9.createFromXmlInner(r13, r7, r8, r1)     // Catch:{ Exception -> 0x009f }
            r6 = r1
        L_0x008c:
            if (r6 == 0) goto L_0x0096
            int r1 = r0.changingConfigurations     // Catch:{ Exception -> 0x009f }
            r6.setChangingConfigurations(r1)     // Catch:{ Exception -> 0x009f }
            r12.addDrawableToCache(r13, r4, r6)     // Catch:{ Exception -> 0x009f }
        L_0x0096:
            goto L_0x00a7
        L_0x0097:
            org.xmlpull.v1.XmlPullParserException r1 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x009f }
            java.lang.String r3 = "No start tag found"
            r1.<init>(r3)     // Catch:{ Exception -> 0x009f }
            throw r1     // Catch:{ Exception -> 0x009f }
        L_0x009f:
            r1 = move-exception
            java.lang.String r3 = TAG
            java.lang.String r7 = "Exception while inflating drawable"
            android.util.Log.e(r3, r7, r1)
        L_0x00a7:
            if (r6 != 0) goto L_0x00ae
            android.util.SparseArray<java.lang.String> r1 = r12.mKnownDrawableIdTags
            r1.append(r14, r2)
        L_0x00ae:
            return r6
        L_0x00af:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.content.res.SkinCompatDrawableManager.loadDrawableFromDelegates(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable getCachedDrawable(android.content.Context r6, long r7) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mDrawableCacheLock
            monitor-enter(r0)
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.LongSparseArray<java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>>> r1 = r5.mDrawableCaches     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x002f }
            android.support.v4.util.LongSparseArray r1 = (android.support.v4.util.LongSparseArray) r1     // Catch:{ all -> 0x002f }
            r2 = 0
            if (r1 != 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r2
        L_0x0010:
            java.lang.Object r3 = r1.get(r7)     // Catch:{ all -> 0x002f }
            java.lang.ref.WeakReference r3 = (java.lang.ref.WeakReference) r3     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x002d
            java.lang.Object r4 = r3.get()     // Catch:{ all -> 0x002f }
            android.graphics.drawable.Drawable$ConstantState r4 = (android.graphics.drawable.Drawable.ConstantState) r4     // Catch:{ all -> 0x002f }
            if (r4 == 0) goto L_0x002a
            android.content.res.Resources r2 = r6.getResources()     // Catch:{ all -> 0x002f }
            android.graphics.drawable.Drawable r2 = r4.newDrawable(r2)     // Catch:{ all -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r2
        L_0x002a:
            r1.delete(r7)     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r2
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.content.res.SkinCompatDrawableManager.getCachedDrawable(android.content.Context, long):android.graphics.drawable.Drawable");
    }

    private boolean addDrawableToCache(Context context, long key, Drawable drawable) {
        Drawable.ConstantState cs = drawable.getConstantState();
        if (cs == null) {
            return false;
        }
        synchronized (this.mDrawableCacheLock) {
            LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context);
            if (cache == null) {
                cache = new LongSparseArray<>();
                this.mDrawableCaches.put(context, cache);
            }
            cache.put(key, new WeakReference(cs));
        }
        return true;
    }

    static boolean tintDrawableUsingColorFilter(Context context, int resId, Drawable drawable) {
        PorterDuff.Mode tintMode = DEFAULT_MODE;
        boolean colorAttrSet = false;
        int colorAttr = 0;
        int alpha = -1;
        if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, resId)) {
            colorAttr = R.attr.colorControlNormal;
            colorAttrSet = true;
        } else if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, resId)) {
            colorAttr = R.attr.colorControlActivated;
            colorAttrSet = true;
        } else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, resId)) {
            colorAttr = 16842801;
            colorAttrSet = true;
            tintMode = PorterDuff.Mode.MULTIPLY;
        } else if (resId == R.drawable.abc_list_divider_mtrl_alpha) {
            colorAttr = 16842800;
            colorAttrSet = true;
            alpha = Math.round(40.8f);
        } else if (resId == R.drawable.abc_dialog_material_background) {
            colorAttr = 16842801;
            colorAttrSet = true;
        }
        if (!colorAttrSet) {
            return false;
        }
        if (SkinCompatDrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(getPorterDuffColorFilter(SkinCompatThemeUtils.getThemeAttrColor(context, colorAttr), tintMode));
        if (alpha == -1) {
            return true;
        }
        drawable.setAlpha(alpha);
        return true;
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
        if (resId == R.drawable.abc_switch_thumb_material) {
            return PorterDuff.Mode.MULTIPLY;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getTintList(Context context, int resId) {
        ColorStateList tint = getTintListFromCache(context, resId);
        if (tint == null) {
            if (resId == R.drawable.abc_edit_text_material) {
                tint = SkinCompatResources.getColorStateList(context, R.color.abc_tint_edittext);
            } else if (resId == R.drawable.abc_switch_track_mtrl_alpha) {
                tint = SkinCompatResources.getColorStateList(context, R.color.abc_tint_switch_track);
            } else if (resId == R.drawable.abc_switch_thumb_material) {
                tint = createSwitchThumbColorStateList(context);
            } else if (resId == R.drawable.abc_btn_default_mtrl_shape) {
                tint = createDefaultButtonColorStateList(context);
            } else if (resId == R.drawable.abc_btn_borderless_material) {
                tint = createBorderlessButtonColorStateList(context);
            } else if (resId == R.drawable.abc_btn_colored_material) {
                tint = createColoredButtonColorStateList(context);
            } else if (resId == R.drawable.abc_spinner_mtrl_am_alpha || resId == R.drawable.abc_spinner_textfield_background_material) {
                tint = SkinCompatResources.getColorStateList(context, R.color.abc_tint_spinner);
            } else if (arrayContains(TINT_COLOR_CONTROL_NORMAL, resId)) {
                tint = SkinCompatThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
            } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, resId)) {
                tint = SkinCompatResources.getColorStateList(context, R.color.abc_tint_default);
            } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, resId)) {
                tint = SkinCompatResources.getColorStateList(context, R.color.abc_tint_btn_checkable);
            } else if (resId == R.drawable.abc_seekbar_thumb_material) {
                tint = SkinCompatResources.getColorStateList(context, R.color.abc_tint_seek_thumb);
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
        return createButtonColorStateList(context, SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorButtonNormal));
    }

    private ColorStateList createBorderlessButtonColorStateList(Context context) {
        return createButtonColorStateList(context, 0);
    }

    private ColorStateList createColoredButtonColorStateList(Context context) {
        return createButtonColorStateList(context, SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorAccent));
    }

    private ColorStateList createButtonColorStateList(Context context, int baseColor) {
        int[][] states = new int[4][];
        int[] colors = new int[4];
        int colorControlHighlight = SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
        int disabledColor = SkinCompatThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
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
        int[][] states = new int[3][];
        int[] colors = new int[3];
        ColorStateList thumbColor = SkinCompatThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
        if (thumbColor == null || !thumbColor.isStateful()) {
            states[0] = SkinCompatThemeUtils.DISABLED_STATE_SET;
            colors[0] = SkinCompatThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
            int i = 0 + 1;
            states[i] = SkinCompatThemeUtils.CHECKED_STATE_SET;
            colors[i] = SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            int i2 = i + 1;
            states[i2] = SkinCompatThemeUtils.EMPTY_STATE_SET;
            colors[i2] = SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
            int i3 = i2 + 1;
        } else {
            states[0] = SkinCompatThemeUtils.DISABLED_STATE_SET;
            colors[0] = thumbColor.getColorForState(states[0], 0);
            int i4 = 0 + 1;
            states[i4] = SkinCompatThemeUtils.CHECKED_STATE_SET;
            colors[i4] = SkinCompatThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            int i5 = i4 + 1;
            states[i5] = SkinCompatThemeUtils.EMPTY_STATE_SET;
            colors[i5] = thumbColor.getDefaultColor();
            int i6 = i5 + 1;
        }
        return new ColorStateList(states, colors);
    }

    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int maxSize) {
            super(maxSize);
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter get(int color, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(generateCacheKey(color, mode)));
        }

        /* access modifiers changed from: package-private */
        public PorterDuffColorFilter put(int color, PorterDuff.Mode mode, PorterDuffColorFilter filter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(generateCacheKey(color, mode)), filter);
        }

        private static int generateCacheKey(int color, PorterDuff.Mode mode) {
            return (((1 * 31) + color) * 31) + mode.hashCode();
        }
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList tint, PorterDuff.Mode tintMode, int[] state) {
        if (tint == null || tintMode == null) {
            return null;
        }
        return getPorterDuffColorFilter(tint.getColorForState(state, 0), tintMode);
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int color, PorterDuff.Mode mode) {
        ColorFilterLruCache colorFilterLruCache = COLOR_FILTER_CACHE;
        PorterDuffColorFilter filter = colorFilterLruCache.get(color, mode);
        if (filter != null) {
            return filter;
        }
        PorterDuffColorFilter filter2 = new PorterDuffColorFilter(color, mode);
        colorFilterLruCache.put(color, mode, filter2);
        return filter2;
    }

    private static void setPorterDuffColorFilter(Drawable d, int color, PorterDuff.Mode mode) {
        if (SkinCompatDrawableUtils.canSafelyMutateDrawable(d)) {
            d = d.mutate();
        }
        d.setColorFilter(getPorterDuffColorFilter(color, mode == null ? DEFAULT_MODE : mode));
    }

    private void checkVectorDrawableSetup(Context context) {
        if (!this.mHasCheckedVectorDrawableSetup) {
            this.mHasCheckedVectorDrawableSetup = true;
            Drawable d = getDrawable(context, R.drawable.abc_vector_test);
            if (d == null || !isVectorDrawable(d)) {
                this.mHasCheckedVectorDrawableSetup = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    private static boolean isVectorDrawable(Drawable d) {
        return (d instanceof VectorDrawableCompat) || PLATFORM_VD_CLAZZ.equals(d.getClass().getName());
    }

    private static class VdcInflateDelegate implements InflateDelegate {
        VdcInflateDelegate() {
        }

        public Drawable createFromXmlInner(Context context, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    private static class AvdcInflateDelegate implements InflateDelegate {
        AvdcInflateDelegate() {
        }

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
