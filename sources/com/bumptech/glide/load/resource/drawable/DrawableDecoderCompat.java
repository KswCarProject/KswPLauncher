package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.p001v4.content.ContextCompat;
import android.support.p001v4.content.res.ResourcesCompat;
import android.support.p004v7.content.res.AppCompatResources;
import android.support.p004v7.view.ContextThemeWrapper;

/* loaded from: classes.dex */
public final class DrawableDecoderCompat {
    private static volatile boolean shouldCallAppCompatResources = true;

    private DrawableDecoderCompat() {
    }

    public static Drawable getDrawable(Context ourContext, Context targetContext, int id) {
        return getDrawable(ourContext, targetContext, id, null);
    }

    public static Drawable getDrawable(Context ourContext, int id, Resources.Theme theme) {
        return getDrawable(ourContext, ourContext, id, theme);
    }

    private static Drawable getDrawable(Context ourContext, Context targetContext, int id, Resources.Theme theme) {
        try {
            if (shouldCallAppCompatResources) {
                return loadDrawableV7(targetContext, id, theme);
            }
        } catch (Resources.NotFoundException e) {
        } catch (IllegalStateException e2) {
            if (ourContext.getPackageName().equals(targetContext.getPackageName())) {
                throw e2;
            }
            return ContextCompat.getDrawable(targetContext, id);
        } catch (NoClassDefFoundError e3) {
            shouldCallAppCompatResources = false;
        }
        return loadDrawableV4(targetContext, id, theme != null ? theme : targetContext.getTheme());
    }

    private static Drawable loadDrawableV7(Context context, int id, Resources.Theme theme) {
        Context resourceContext = theme != null ? new ContextThemeWrapper(context, theme) : context;
        return AppCompatResources.getDrawable(resourceContext, id);
    }

    private static Drawable loadDrawableV4(Context context, int id, Resources.Theme theme) {
        Resources resources = context.getResources();
        return ResourcesCompat.getDrawable(resources, id, theme);
    }
}
