package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class AppCompatDrawableManager {
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light};
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManag";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST = {R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material};
    private static final int[] TINT_COLOR_CONTROL_NORMAL = {R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST = {R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
    private ArrayMap<String, InflateDelegate> mDelegates;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<>(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArrayCompat<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;

    private interface InflateDelegate {
        Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme);
    }

    public static synchronized AppCompatDrawableManager get() {
        AppCompatDrawableManager appCompatDrawableManager;
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new AppCompatDrawableManager();
                installDefaultInflateDelegates(INSTANCE);
            }
            appCompatDrawableManager = INSTANCE;
        }
        return appCompatDrawableManager;
    }

    private static void installDefaultInflateDelegates(@NonNull AppCompatDrawableManager manager) {
        if (Build.VERSION.SDK_INT < 24) {
            manager.addDelegate("vector", new VdcInflateDelegate());
            manager.addDelegate("animated-vector", new AvdcInflateDelegate());
            manager.addDelegate("animated-selector", new AsldcInflateDelegate());
        }
    }

    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
        return getDrawable(context, resId, false);
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int resId, boolean failIfNotKnown) {
        Drawable drawable;
        checkVectorDrawableSetup(context);
        drawable = loadDrawableFromDelegates(context, resId);
        if (drawable == null) {
            drawable = createDrawableIfNeeded(context, resId);
        }
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(context, resId);
        }
        if (drawable != null) {
            drawable = tintDrawable(context, resId, failIfNotKnown, drawable);
        }
        if (drawable != null) {
            DrawableUtils.fixDrawable(drawable);
        }
        return drawable;
    }

    public synchronized void onConfigurationChanged(@NonNull Context context) {
        LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context);
        if (cache != null) {
            cache.clear();
        }
    }

    private static long createCacheKey(TypedValue tv) {
        return (((long) tv.assetCookie) << 32) | ((long) tv.data);
    }

    private Drawable createDrawableIfNeeded(@NonNull Context context, @DrawableRes int resId) {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        TypedValue tv = this.mTypedValue;
        context.getResources().getValue(resId, tv, true);
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

    private Drawable tintDrawable(@NonNull Context context, @DrawableRes int resId, boolean failIfNotKnown, @NonNull Drawable drawable) {
        ColorStateList tintList = getTintList(context, resId);
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
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
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), DEFAULT_MODE);
            return drawable;
        } else if (resId == R.drawable.abc_ratingbar_material || resId == R.drawable.abc_ratingbar_indicator_material || resId == R.drawable.abc_ratingbar_small_material) {
            LayerDrawable ld2 = (LayerDrawable) drawable;
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal), DEFAULT_MODE);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), DEFAULT_MODE);
            setPorterDuffColorFilter(ld2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), DEFAULT_MODE);
            return drawable;
        } else if (tintDrawableUsingColorFilter(context, resId, drawable) || !failIfNotKnown) {
            return drawable;
        } else {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c A[Catch:{ Exception -> 0x00ac }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a4 A[Catch:{ Exception -> 0x00ac }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable loadDrawableFromDelegates(@android.support.annotation.NonNull android.content.Context r12, @android.support.annotation.DrawableRes int r13) {
        /*
            r11 = this;
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r0 = r11.mDelegates
            r1 = 0
            if (r0 == 0) goto L_0x00be
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r0 = r11.mDelegates
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00be
            android.support.v4.util.SparseArrayCompat<java.lang.String> r0 = r11.mKnownDrawableIdTags
            if (r0 == 0) goto L_0x002e
            android.support.v4.util.SparseArrayCompat<java.lang.String> r0 = r11.mKnownDrawableIdTags
            java.lang.Object r0 = r0.get(r13)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "appcompat_skip_skip"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x002d
            if (r0 == 0) goto L_0x002c
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r2 = r11.mDelegates
            java.lang.Object r2 = r2.get(r0)
            if (r2 != 0) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            goto L_0x0035
        L_0x002d:
            return r1
        L_0x002e:
            android.support.v4.util.SparseArrayCompat r0 = new android.support.v4.util.SparseArrayCompat
            r0.<init>()
            r11.mKnownDrawableIdTags = r0
        L_0x0035:
            android.util.TypedValue r0 = r11.mTypedValue
            if (r0 != 0) goto L_0x0040
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r11.mTypedValue = r0
        L_0x0040:
            android.util.TypedValue r0 = r11.mTypedValue
            android.content.res.Resources r1 = r12.getResources()
            r2 = 1
            r1.getValue(r13, r0, r2)
            long r3 = createCacheKey(r0)
            android.graphics.drawable.Drawable r5 = r11.getCachedDrawable(r12, r3)
            if (r5 == 0) goto L_0x0055
            return r5
        L_0x0055:
            java.lang.CharSequence r6 = r0.string
            if (r6 == 0) goto L_0x00b4
            java.lang.CharSequence r6 = r0.string
            java.lang.String r6 = r6.toString()
            java.lang.String r7 = ".xml"
            boolean r6 = r6.endsWith(r7)
            if (r6 == 0) goto L_0x00b4
            android.content.res.XmlResourceParser r6 = r1.getXml(r13)     // Catch:{ Exception -> 0x00ac }
            android.util.AttributeSet r7 = android.util.Xml.asAttributeSet(r6)     // Catch:{ Exception -> 0x00ac }
        L_0x006f:
            int r8 = r6.next()     // Catch:{ Exception -> 0x00ac }
            r9 = r8
            r10 = 2
            if (r8 == r10) goto L_0x007a
            if (r9 == r2) goto L_0x007a
            goto L_0x006f
        L_0x007a:
            if (r9 != r10) goto L_0x00a4
            java.lang.String r2 = r6.getName()     // Catch:{ Exception -> 0x00ac }
            android.support.v4.util.SparseArrayCompat<java.lang.String> r8 = r11.mKnownDrawableIdTags     // Catch:{ Exception -> 0x00ac }
            r8.append(r13, r2)     // Catch:{ Exception -> 0x00ac }
            android.support.v4.util.ArrayMap<java.lang.String, android.support.v7.widget.AppCompatDrawableManager$InflateDelegate> r8 = r11.mDelegates     // Catch:{ Exception -> 0x00ac }
            java.lang.Object r8 = r8.get(r2)     // Catch:{ Exception -> 0x00ac }
            android.support.v7.widget.AppCompatDrawableManager$InflateDelegate r8 = (android.support.v7.widget.AppCompatDrawableManager.InflateDelegate) r8     // Catch:{ Exception -> 0x00ac }
            if (r8 == 0) goto L_0x0099
            android.content.res.Resources$Theme r10 = r12.getTheme()     // Catch:{ Exception -> 0x00ac }
            android.graphics.drawable.Drawable r10 = r8.createFromXmlInner(r12, r6, r7, r10)     // Catch:{ Exception -> 0x00ac }
            r5 = r10
        L_0x0099:
            if (r5 == 0) goto L_0x00a3
            int r10 = r0.changingConfigurations     // Catch:{ Exception -> 0x00ac }
            r5.setChangingConfigurations(r10)     // Catch:{ Exception -> 0x00ac }
            r11.addDrawableToCache(r12, r3, r5)     // Catch:{ Exception -> 0x00ac }
        L_0x00a3:
            goto L_0x00b4
        L_0x00a4:
            org.xmlpull.v1.XmlPullParserException r2 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ Exception -> 0x00ac }
            java.lang.String r8 = "No start tag found"
            r2.<init>(r8)     // Catch:{ Exception -> 0x00ac }
            throw r2     // Catch:{ Exception -> 0x00ac }
        L_0x00ac:
            r2 = move-exception
            java.lang.String r6 = "AppCompatDrawableManag"
            java.lang.String r7 = "Exception while inflating drawable"
            android.util.Log.e(r6, r7, r2)
        L_0x00b4:
            if (r5 != 0) goto L_0x00bd
            android.support.v4.util.SparseArrayCompat<java.lang.String> r2 = r11.mKnownDrawableIdTags
            java.lang.String r6 = "appcompat_skip_skip"
            r2.append(r13, r6)
        L_0x00bd:
            return r5
        L_0x00be:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatDrawableManager.loadDrawableFromDelegates(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized android.graphics.drawable.Drawable getCachedDrawable(@android.support.annotation.NonNull android.content.Context r5, long r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.WeakHashMap<android.content.Context, android.support.v4.util.LongSparseArray<java.lang.ref.WeakReference<android.graphics.drawable.Drawable$ConstantState>>> r0 = r4.mDrawableCaches     // Catch:{ all -> 0x002d }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x002d }
            android.support.v4.util.LongSparseArray r0 = (android.support.v4.util.LongSparseArray) r0     // Catch:{ all -> 0x002d }
            r1 = 0
            if (r0 != 0) goto L_0x000e
            monitor-exit(r4)
            return r1
        L_0x000e:
            java.lang.Object r2 = r0.get(r6)     // Catch:{ all -> 0x002d }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x002b
            java.lang.Object r3 = r2.get()     // Catch:{ all -> 0x002d }
            android.graphics.drawable.Drawable$ConstantState r3 = (android.graphics.drawable.Drawable.ConstantState) r3     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x0028
            android.content.res.Resources r1 = r5.getResources()     // Catch:{ all -> 0x002d }
            android.graphics.drawable.Drawable r1 = r3.newDrawable(r1)     // Catch:{ all -> 0x002d }
            monitor-exit(r4)
            return r1
        L_0x0028:
            r0.delete(r6)     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r4)
            return r1
        L_0x002d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatDrawableManager.getCachedDrawable(android.content.Context, long):android.graphics.drawable.Drawable");
    }

    private synchronized boolean addDrawableToCache(@NonNull Context context, long key, @NonNull Drawable drawable) {
        Drawable.ConstantState cs = drawable.getConstantState();
        if (cs == null) {
            return false;
        }
        LongSparseArray<WeakReference<Drawable.ConstantState>> cache = this.mDrawableCaches.get(context);
        if (cache == null) {
            cache = new LongSparseArray<>();
            this.mDrawableCaches.put(context, cache);
        }
        cache.put(key, new WeakReference(cs));
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized Drawable onDrawableLoadedFromResources(@NonNull Context context, @NonNull VectorEnabledTintResources resources, @DrawableRes int resId) {
        Drawable drawable = loadDrawableFromDelegates(context, resId);
        if (drawable == null) {
            drawable = resources.superGetDrawable(resId);
        }
        if (drawable == null) {
            return null;
        }
        return tintDrawable(context, resId, false, drawable);
    }

    static boolean tintDrawableUsingColorFilter(@NonNull Context context, @DrawableRes int resId, @NonNull Drawable drawable) {
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
        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, colorAttr), tintMode));
        if (alpha == -1) {
            return true;
        }
        drawable.setAlpha(alpha);
        return true;
    }

    private void addDelegate(@NonNull String tagName, @NonNull InflateDelegate delegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap<>();
        }
        this.mDelegates.put(tagName, delegate);
    }

    private void removeDelegate(@NonNull String tagName, @NonNull InflateDelegate delegate) {
        if (this.mDelegates != null && this.mDelegates.get(tagName) == delegate) {
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
    public synchronized ColorStateList getTintList(@NonNull Context context, @DrawableRes int resId) {
        ColorStateList tint;
        tint = getTintListFromCache(context, resId);
        if (tint == null) {
            if (resId == R.drawable.abc_edit_text_material) {
                tint = AppCompatResources.getColorStateList(context, R.color.abc_tint_edittext);
            } else if (resId == R.drawable.abc_switch_track_mtrl_alpha) {
                tint = AppCompatResources.getColorStateList(context, R.color.abc_tint_switch_track);
            } else if (resId == R.drawable.abc_switch_thumb_material) {
                tint = createSwitchThumbColorStateList(context);
            } else if (resId == R.drawable.abc_btn_default_mtrl_shape) {
                tint = createDefaultButtonColorStateList(context);
            } else if (resId == R.drawable.abc_btn_borderless_material) {
                tint = createBorderlessButtonColorStateList(context);
            } else if (resId == R.drawable.abc_btn_colored_material) {
                tint = createColoredButtonColorStateList(context);
            } else {
                if (resId != R.drawable.abc_spinner_mtrl_am_alpha) {
                    if (resId != R.drawable.abc_spinner_textfield_background_material) {
                        if (arrayContains(TINT_COLOR_CONTROL_NORMAL, resId)) {
                            tint = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
                        } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, resId)) {
                            tint = AppCompatResources.getColorStateList(context, R.color.abc_tint_default);
                        } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, resId)) {
                            tint = AppCompatResources.getColorStateList(context, R.color.abc_tint_btn_checkable);
                        } else if (resId == R.drawable.abc_seekbar_thumb_material) {
                            tint = AppCompatResources.getColorStateList(context, R.color.abc_tint_seek_thumb);
                        }
                    }
                }
                tint = AppCompatResources.getColorStateList(context, R.color.abc_tint_spinner);
            }
            if (tint != null) {
                addTintListToCache(context, resId, tint);
            }
        }
        return tint;
    }

    private ColorStateList getTintListFromCache(@NonNull Context context, @DrawableRes int resId) {
        SparseArrayCompat<ColorStateList> tints;
        if (this.mTintLists == null || (tints = this.mTintLists.get(context)) == null) {
            return null;
        }
        return tints.get(resId);
    }

    private void addTintListToCache(@NonNull Context context, @DrawableRes int resId, @NonNull ColorStateList tintList) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap<>();
        }
        SparseArrayCompat<ColorStateList> themeTints = this.mTintLists.get(context);
        if (themeTints == null) {
            themeTints = new SparseArrayCompat<>();
            this.mTintLists.put(context, themeTints);
        }
        themeTints.append(resId, tintList);
    }

    private ColorStateList createDefaultButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorButtonNormal));
    }

    private ColorStateList createBorderlessButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, 0);
    }

    private ColorStateList createColoredButtonColorStateList(@NonNull Context context) {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorAccent));
    }

    private ColorStateList createButtonColorStateList(@NonNull Context context, @ColorInt int baseColor) {
        int[][] states = new int[4][];
        int[] colors = new int[4];
        int colorControlHighlight = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
        int disabledColor = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
        states[0] = ThemeUtils.DISABLED_STATE_SET;
        colors[0] = disabledColor;
        int i = 0 + 1;
        states[i] = ThemeUtils.PRESSED_STATE_SET;
        colors[i] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
        int i2 = i + 1;
        states[i2] = ThemeUtils.FOCUSED_STATE_SET;
        colors[i2] = ColorUtils.compositeColors(colorControlHighlight, baseColor);
        int i3 = i2 + 1;
        states[i3] = ThemeUtils.EMPTY_STATE_SET;
        colors[i3] = baseColor;
        int i4 = i3 + 1;
        return new ColorStateList(states, colors);
    }

    private ColorStateList createSwitchThumbColorStateList(Context context) {
        int[][] states = new int[3][];
        int[] colors = new int[3];
        ColorStateList thumbColor = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
        if (thumbColor == null || !thumbColor.isStateful()) {
            states[0] = ThemeUtils.DISABLED_STATE_SET;
            colors[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
            int i = 0 + 1;
            states[i] = ThemeUtils.CHECKED_STATE_SET;
            colors[i] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            int i2 = i + 1;
            states[i2] = ThemeUtils.EMPTY_STATE_SET;
            colors[i2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
            int i3 = i2 + 1;
        } else {
            states[0] = ThemeUtils.DISABLED_STATE_SET;
            colors[0] = thumbColor.getColorForState(states[0], 0);
            int i4 = 0 + 1;
            states[i4] = ThemeUtils.CHECKED_STATE_SET;
            colors[i4] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            int i5 = i4 + 1;
            states[i5] = ThemeUtils.EMPTY_STATE_SET;
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

    static void tintDrawable(Drawable drawable, TintInfo tint, int[] state) {
        if (!DrawableUtils.canSafelyMutateDrawable(drawable) || drawable.mutate() == drawable) {
            if (tint.mHasTintList || tint.mHasTintMode) {
                drawable.setColorFilter(createTintFilter(tint.mHasTintList ? tint.mTintList : null, tint.mHasTintMode ? tint.mTintMode : DEFAULT_MODE, state));
            } else {
                drawable.clearColorFilter();
            }
            if (Build.VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
                return;
            }
            return;
        }
        Log.d(TAG, "Mutated drawable is not the same instance as the input.");
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList tint, PorterDuff.Mode tintMode, int[] state) {
        if (tint == null || tintMode == null) {
            return null;
        }
        return getPorterDuffColorFilter(tint.getColorForState(state, 0), tintMode);
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int color, PorterDuff.Mode mode) {
        PorterDuffColorFilter filter;
        synchronized (AppCompatDrawableManager.class) {
            filter = COLOR_FILTER_CACHE.get(color, mode);
            if (filter == null) {
                filter = new PorterDuffColorFilter(color, mode);
                COLOR_FILTER_CACHE.put(color, mode, filter);
            }
        }
        return filter;
    }

    private static void setPorterDuffColorFilter(Drawable d, int color, PorterDuff.Mode mode) {
        if (DrawableUtils.canSafelyMutateDrawable(d)) {
            d = d.mutate();
        }
        d.setColorFilter(getPorterDuffColorFilter(color, mode == null ? DEFAULT_MODE : mode));
    }

    private void checkVectorDrawableSetup(@NonNull Context context) {
        if (!this.mHasCheckedVectorDrawableSetup) {
            this.mHasCheckedVectorDrawableSetup = true;
            Drawable d = getDrawable(context, R.drawable.abc_vector_test);
            if (d == null || !isVectorDrawable(d)) {
                this.mHasCheckedVectorDrawableSetup = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    private static boolean isVectorDrawable(@NonNull Drawable d) {
        return (d instanceof VectorDrawableCompat) || PLATFORM_VD_CLAZZ.equals(d.getClass().getName());
    }

    private static class VdcInflateDelegate implements InflateDelegate {
        VdcInflateDelegate() {
        }

        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) {
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

        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    @RequiresApi(11)
    static class AsldcInflateDelegate implements InflateDelegate {
        AsldcInflateDelegate() {
        }

        public Drawable createFromXmlInner(@NonNull Context context, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) {
            try {
                return AnimatedStateListDrawableCompat.createFromXmlInner(context, context.getResources(), parser, attrs, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }
}
