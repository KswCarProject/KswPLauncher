package android.support.p001v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.p001v4.graphics.drawable.WrappedDrawableApi14;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: android.support.v4.graphics.drawable.WrappedDrawableApi21 */
/* loaded from: classes.dex */
class WrappedDrawableApi21 extends WrappedDrawableApi14 {
    private static final String TAG = "WrappedDrawableApi21";
    private static Method sIsProjectedDrawableMethod;

    WrappedDrawableApi21(Drawable drawable) {
        super(drawable);
        findAndCacheIsProjectedDrawableMethod();
    }

    WrappedDrawableApi21(WrappedDrawableApi14.DrawableWrapperState state, Resources resources) {
        super(state, resources);
        findAndCacheIsProjectedDrawableMethod();
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float x, float y) {
        this.mDrawable.setHotspot(x, y);
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int left, int top, int right, int bottom) {
        this.mDrawable.setHotspotBounds(left, top, right, bottom);
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    @Override // android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable, android.support.p001v4.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList tint) {
        if (isCompatTintEnabled()) {
            super.setTintList(tint);
        } else {
            this.mDrawable.setTintList(tint);
        }
    }

    @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable, android.support.p001v4.graphics.drawable.TintAwareDrawable
    public void setTint(int tintColor) {
        if (isCompatTintEnabled()) {
            super.setTint(tintColor);
        } else {
            this.mDrawable.setTint(tintColor);
        }
    }

    @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable, android.support.p001v4.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode tintMode) {
        if (isCompatTintEnabled()) {
            super.setTintMode(tintMode);
        } else {
            this.mDrawable.setTintMode(tintMode);
        }
    }

    @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14, android.graphics.drawable.Drawable
    public boolean setState(int[] stateSet) {
        if (super.setState(stateSet)) {
            invalidateSelf();
            return true;
        }
        return false;
    }

    @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14
    protected boolean isCompatTintEnabled() {
        if (Build.VERSION.SDK_INT == 21) {
            Drawable drawable = this.mDrawable;
            return (drawable instanceof GradientDrawable) || (drawable instanceof DrawableContainer) || (drawable instanceof InsetDrawable) || (drawable instanceof RippleDrawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Method method;
        if (this.mDrawable != null && (method = sIsProjectedDrawableMethod) != null) {
            try {
                return ((Boolean) method.invoke(this.mDrawable, new Object[0])).booleanValue();
            } catch (Exception ex) {
                Log.w(TAG, "Error calling Drawable#isProjected() method", ex);
            }
        }
        return false;
    }

    @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14
    WrappedDrawableApi14.DrawableWrapperState mutateConstantState() {
        return new DrawableWrapperStateLollipop(this.mState, null);
    }

    /* renamed from: android.support.v4.graphics.drawable.WrappedDrawableApi21$DrawableWrapperStateLollipop */
    /* loaded from: classes.dex */
    private static class DrawableWrapperStateLollipop extends WrappedDrawableApi14.DrawableWrapperState {
        DrawableWrapperStateLollipop(WrappedDrawableApi14.DrawableWrapperState orig, Resources res) {
            super(orig, res);
        }

        @Override // android.support.p001v4.graphics.drawable.WrappedDrawableApi14.DrawableWrapperState, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources res) {
            return new WrappedDrawableApi21(this, res);
        }
    }

    private void findAndCacheIsProjectedDrawableMethod() {
        if (sIsProjectedDrawableMethod == null) {
            try {
                sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception ex) {
                Log.w(TAG, "Failed to retrieve Drawable#isProjected() method", ex);
            }
        }
    }
}
