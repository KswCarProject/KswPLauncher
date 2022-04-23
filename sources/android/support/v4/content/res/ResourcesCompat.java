package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.Preconditions;
import android.util.TypedValue;

public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    public static Drawable getDrawable(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawable(id, theme);
        }
        return res.getDrawable(id);
    }

    public static Drawable getDrawableForDensity(Resources res, int id, int density, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return res.getDrawableForDensity(id, density, theme);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return res.getDrawableForDensity(id, density);
        }
        return res.getDrawable(id);
    }

    public static int getColor(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColor(id, theme);
        }
        return res.getColor(id);
    }

    public static ColorStateList getColorStateList(Resources res, int id, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return res.getColorStateList(id, theme);
        }
        return res.getColorStateList(id);
    }

    public static Typeface getFont(Context context, int id) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, new TypedValue(), 0, (FontCallback) null, (Handler) null, false);
    }

    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i);

        public abstract void onFontRetrieved(Typeface typeface);

        public final void callbackSuccessAsync(final Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        public final void callbackFailAsync(final int reason, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(reason);
                }
            });
        }
    }

    public static void getFont(Context context, int id, FontCallback fontCallback, Handler handler) throws Resources.NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
            return;
        }
        loadFont(context, id, new TypedValue(), 0, fontCallback, handler, false);
    }

    public static Typeface getFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return loadFont(context, id, value, style, fontCallback, (Handler) null, true);
    }

    private static Typeface loadFont(Context context, int id, TypedValue value, int style, FontCallback fontCallback, Handler handler, boolean isRequestFromLayoutInflator) {
        Resources resources = context.getResources();
        resources.getValue(id, value, true);
        Typeface typeface = loadFont(context, resources, value, id, style, fontCallback, handler, isRequestFromLayoutInflator);
        if (typeface != null || fontCallback != null) {
            return typeface;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(id) + " could not be retrieved.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface loadFont(android.content.Context r19, android.content.res.Resources r20, android.util.TypedValue r21, int r22, int r23, android.support.v4.content.res.ResourcesCompat.FontCallback r24, android.os.Handler r25, boolean r26) {
        /*
            r9 = r20
            r10 = r21
            r11 = r22
            r12 = r23
            r13 = r24
            r14 = r25
            java.lang.String r15 = "ResourcesCompat"
            java.lang.CharSequence r0 = r10.string
            if (r0 == 0) goto L_0x00f9
            java.lang.CharSequence r0 = r10.string
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "res/"
            boolean r0 = r8.startsWith(r0)
            r16 = 0
            r7 = -3
            if (r0 != 0) goto L_0x0029
            if (r13 == 0) goto L_0x0028
            r13.callbackFailAsync(r7, r14)
        L_0x0028:
            return r16
        L_0x0029:
            android.graphics.Typeface r6 = android.support.v4.graphics.TypefaceCompat.findFromCache(r9, r11, r12)
            if (r6 == 0) goto L_0x0035
            if (r13 == 0) goto L_0x0034
            r13.callbackSuccessAsync(r6, r14)
        L_0x0034:
            return r6
        L_0x0035:
            java.lang.String r0 = r8.toLowerCase()     // Catch:{ XmlPullParserException -> 0x00d5, IOException -> 0x00b8 }
            java.lang.String r1 = ".xml"
            boolean r0 = r0.endsWith(r1)     // Catch:{ XmlPullParserException -> 0x00d5, IOException -> 0x00b8 }
            if (r0 == 0) goto L_0x0094
            android.content.res.XmlResourceParser r0 = r9.getXml(r11)     // Catch:{ XmlPullParserException -> 0x008d, IOException -> 0x0086 }
            android.support.v4.content.res.FontResourcesParserCompat$FamilyResourceEntry r1 = android.support.v4.content.res.FontResourcesParserCompat.parse(r0, r9)     // Catch:{ XmlPullParserException -> 0x008d, IOException -> 0x0086 }
            r17 = r1
            if (r17 != 0) goto L_0x0065
            java.lang.String r1 = "Failed to find font-family tag"
            android.util.Log.e(r15, r1)     // Catch:{ XmlPullParserException -> 0x005f, IOException -> 0x0059 }
            if (r13 == 0) goto L_0x0058
            r13.callbackFailAsync(r7, r14)     // Catch:{ XmlPullParserException -> 0x005f, IOException -> 0x0059 }
        L_0x0058:
            return r16
        L_0x0059:
            r0 = move-exception
            r1 = r19
            r10 = r8
            goto L_0x00be
        L_0x005f:
            r0 = move-exception
            r1 = r19
            r10 = r8
            goto L_0x00db
        L_0x0065:
            r1 = r19
            r2 = r17
            r3 = r20
            r4 = r22
            r5 = r23
            r18 = r6
            r6 = r24
            r10 = r7
            r7 = r25
            r10 = r8
            r8 = r26
            android.graphics.Typeface r1 = android.support.v4.graphics.TypefaceCompat.createFromResourcesFamilyXml(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ XmlPullParserException -> 0x0082, IOException -> 0x007e }
            return r1
        L_0x007e:
            r0 = move-exception
            r1 = r19
            goto L_0x00b1
        L_0x0082:
            r0 = move-exception
            r1 = r19
            goto L_0x00b5
        L_0x0086:
            r0 = move-exception
            r18 = r6
            r10 = r8
            r1 = r19
            goto L_0x00be
        L_0x008d:
            r0 = move-exception
            r18 = r6
            r10 = r8
            r1 = r19
            goto L_0x00db
        L_0x0094:
            r18 = r6
            r10 = r8
            r1 = r19
            android.graphics.Typeface r0 = android.support.v4.graphics.TypefaceCompat.createFromResourcesFontFile(r1, r9, r11, r10, r12)     // Catch:{ XmlPullParserException -> 0x00b4, IOException -> 0x00b0 }
            r6 = r0
            if (r13 == 0) goto L_0x00af
            if (r6 == 0) goto L_0x00a6
            r13.callbackSuccessAsync(r6, r14)     // Catch:{ XmlPullParserException -> 0x00ad, IOException -> 0x00ab }
            goto L_0x00af
        L_0x00a6:
            r2 = -3
            r13.callbackFailAsync(r2, r14)     // Catch:{ XmlPullParserException -> 0x00ad, IOException -> 0x00ab }
            goto L_0x00af
        L_0x00ab:
            r0 = move-exception
            goto L_0x00be
        L_0x00ad:
            r0 = move-exception
            goto L_0x00db
        L_0x00af:
            return r6
        L_0x00b0:
            r0 = move-exception
        L_0x00b1:
            r6 = r18
            goto L_0x00be
        L_0x00b4:
            r0 = move-exception
        L_0x00b5:
            r6 = r18
            goto L_0x00db
        L_0x00b8:
            r0 = move-exception
            r1 = r19
            r18 = r6
            r10 = r8
        L_0x00be:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to read xml resource "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r15, r2, r0)
            goto L_0x00f2
        L_0x00d5:
            r0 = move-exception
            r1 = r19
            r18 = r6
            r10 = r8
        L_0x00db:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse xml resource "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r15, r2, r0)
        L_0x00f2:
            if (r13 == 0) goto L_0x00f8
            r2 = -3
            r13.callbackFailAsync(r2, r14)
        L_0x00f8:
            return r16
        L_0x00f9:
            r1 = r19
            android.content.res.Resources$NotFoundException r0 = new android.content.res.Resources$NotFoundException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Resource \""
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r9.getResourceName(r11)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "\" ("
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r22)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ") is not a Font: "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r21
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    private ResourcesCompat() {
    }
}
