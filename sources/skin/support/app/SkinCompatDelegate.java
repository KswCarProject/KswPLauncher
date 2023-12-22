package skin.support.app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.p001v4.view.LayoutInflaterFactory;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes.dex */
public class SkinCompatDelegate implements LayoutInflaterFactory {
    private final Context mContext;
    private SkinCompatViewInflater mSkinCompatViewInflater;
    private List<WeakReference<SkinCompatSupportable>> mSkinHelpers = new ArrayList();

    private SkinCompatDelegate(Context context) {
        this.mContext = context;
    }

    @Override // android.support.p001v4.view.LayoutInflaterFactory
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        try {
            view = createView(parent, name, context, attrs);
        } catch (Exception e) {
        }
        if (view == null) {
            return null;
        }
        if (view instanceof SkinCompatSupportable) {
            this.mSkinHelpers.add(new WeakReference<>((SkinCompatSupportable) view));
        }
        return view;
    }

    public View createView(View parent, String name, Context context, AttributeSet attrs) {
        boolean isPre21 = Build.VERSION.SDK_INT < 21;
        if (this.mSkinCompatViewInflater == null) {
            this.mSkinCompatViewInflater = new SkinCompatViewInflater();
        }
        boolean inheritContext = isPre21 && shouldInheritContext((ViewParent) parent);
        return this.mSkinCompatViewInflater.createView(parent, name, context, attrs, inheritContext, isPre21, true, VectorEnabledTintResources.shouldBeUsed());
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            return false;
        }
        Context context = this.mContext;
        if (!(context instanceof Activity)) {
            return false;
        }
        View windowDecor = ((Activity) context).getWindow().getDecorView();
        while (parent != null) {
            if (parent == windowDecor || !(parent instanceof View) || ViewCompat.isAttachedToWindow((View) parent)) {
                return false;
            }
            parent = parent.getParent();
        }
        return true;
    }

    public static SkinCompatDelegate create(Context context) {
        return new SkinCompatDelegate(context);
    }

    public void applySkin() {
        List<WeakReference<SkinCompatSupportable>> list = this.mSkinHelpers;
        if (list != null && !list.isEmpty()) {
            for (WeakReference ref : this.mSkinHelpers) {
                if (ref != null && ref.get() != null) {
                    ref.get().applySkin();
                }
            }
        }
    }
}
