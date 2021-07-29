package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    ArrayList<Animatable2Compat.AnimationCallback> mAnimationCallbacks;
    private Animator.AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final Drawable.Callback mCallback;
    private Context mContext;

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect x0) {
        return super.getPadding(x0);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int x0) {
        super.setChangingConfigurations(x0);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int x0, PorterDuff.Mode x1) {
        super.setColorFilter(x0, x1);
    }

    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean x0) {
        super.setFilterBitmap(x0);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float x0, float x1) {
        super.setHotspot(x0, x1);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int x0, int x1, int x2, int x3) {
        super.setHotspotBounds(x0, x1, x2, x3);
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] x0) {
        return super.setState(x0);
    }

    AnimatedVectorDrawableCompat() {
        this((Context) null, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(Context context) {
        this(context, (AnimatedVectorDrawableCompatState) null, (Resources) null);
    }

    private AnimatedVectorDrawableCompat(Context context, AnimatedVectorDrawableCompatState state, Resources res) {
        this.mArgbEvaluator = null;
        this.mAnimatorListener = null;
        this.mAnimationCallbacks = null;
        AnonymousClass1 r0 = new Drawable.Callback() {
            public void invalidateDrawable(Drawable who) {
                AnimatedVectorDrawableCompat.this.invalidateSelf();
            }

            public void scheduleDrawable(Drawable who, Runnable what, long when) {
                AnimatedVectorDrawableCompat.this.scheduleSelf(what, when);
            }

            public void unscheduleDrawable(Drawable who, Runnable what) {
                AnimatedVectorDrawableCompat.this.unscheduleSelf(what);
            }
        };
        this.mCallback = r0;
        this.mContext = context;
        if (state != null) {
            this.mAnimatedVectorState = state;
        } else {
            this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(context, state, r0, res);
        }
    }

    public Drawable mutate() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
        }
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004c A[Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059 A[Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.graphics.drawable.AnimatedVectorDrawableCompat create(android.content.Context r8, int r9) {
        /*
            java.lang.String r0 = "parser error"
            java.lang.String r1 = "AnimatedVDCompat"
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 24
            if (r2 < r3) goto L_0x0032
            android.support.graphics.drawable.AnimatedVectorDrawableCompat r0 = new android.support.graphics.drawable.AnimatedVectorDrawableCompat
            r0.<init>(r8)
            android.content.res.Resources r1 = r8.getResources()
            android.content.res.Resources$Theme r2 = r8.getTheme()
            android.graphics.drawable.Drawable r1 = android.support.v4.content.res.ResourcesCompat.getDrawable(r1, r9, r2)
            r0.mDelegateDrawable = r1
            android.graphics.drawable.Drawable r1 = r0.mDelegateDrawable
            android.graphics.drawable.Drawable$Callback r2 = r0.mCallback
            r1.setCallback(r2)
            android.support.graphics.drawable.AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState r1 = new android.support.graphics.drawable.AnimatedVectorDrawableCompat$AnimatedVectorDrawableDelegateState
            android.graphics.drawable.Drawable r2 = r0.mDelegateDrawable
            android.graphics.drawable.Drawable$ConstantState r2 = r2.getConstantState()
            r1.<init>(r2)
            r0.mCachedConstantStateDelegate = r1
            return r0
        L_0x0032:
            android.content.res.Resources r2 = r8.getResources()
            android.content.res.XmlResourceParser r3 = r2.getXml(r9)     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            android.util.AttributeSet r4 = android.util.Xml.asAttributeSet(r3)     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
        L_0x003e:
            int r5 = r3.next()     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            r6 = r5
            r7 = 2
            if (r5 == r7) goto L_0x004a
            r5 = 1
            if (r6 == r5) goto L_0x004a
            goto L_0x003e
        L_0x004a:
            if (r6 != r7) goto L_0x0059
            android.content.res.Resources r5 = r8.getResources()     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            android.content.res.Resources$Theme r7 = r8.getTheme()     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            android.support.graphics.drawable.AnimatedVectorDrawableCompat r0 = createFromXmlInner(r8, r5, r3, r4, r7)     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            return r0
        L_0x0059:
            org.xmlpull.v1.XmlPullParserException r5 = new org.xmlpull.v1.XmlPullParserException     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            java.lang.String r7 = "No start tag found"
            r5.<init>(r7)     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
            throw r5     // Catch:{ XmlPullParserException -> 0x0066, IOException -> 0x0061 }
        L_0x0061:
            r3 = move-exception
            android.util.Log.e(r1, r0, r3)
            goto L_0x006b
        L_0x0066:
            r3 = move-exception
            android.util.Log.e(r1, r0, r3)
        L_0x006b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatedVectorDrawableCompat.create(android.content.Context, int):android.support.graphics.drawable.AnimatedVectorDrawableCompat");
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        AnimatedVectorDrawableCompat drawable = new AnimatedVectorDrawableCompat(context);
        drawable.inflate(r, parser, attrs, theme);
        return drawable;
    }

    public Drawable.ConstantState getConstantState() {
        if (this.mDelegateDrawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
    }

    public int getChangingConfigurations() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations;
    }

    public void draw(Canvas canvas) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.draw(canvas);
        if (this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            invalidateSelf();
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(bounds);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setBounds(bounds);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] state) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(state);
        }
        return this.mAnimatedVectorState.mVectorDrawable.setState(state);
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int level) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setLevel(level);
        }
        return this.mAnimatedVectorState.mVectorDrawable.setLevel(level);
    }

    public int getAlpha() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        return this.mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public void setAlpha(int alpha) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(alpha);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setAlpha(alpha);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter);
        }
    }

    public void setTint(int tint) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, tint);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTint(tint);
        }
    }

    public void setTintList(ColorStateList tint) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, tint);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTintList(tint);
        }
    }

    public void setTintMode(PorterDuff.Mode tintMode) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setTintMode(tintMode);
        }
    }

    public boolean setVisible(boolean visible, boolean restart) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(visible, restart);
        }
        this.mAnimatedVectorState.mVectorDrawable.setVisible(visible, restart);
        return super.setVisible(visible, restart);
    }

    public boolean isStateful() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }
        return this.mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public int getOpacity() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    public int getIntrinsicWidth() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }
        return this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public boolean isAutoMirrored() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
        }
        return this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored();
    }

    public void setAutoMirrored(boolean mirrored) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, mirrored);
        } else {
            this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(mirrored);
        }
    }

    public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, res, parser, attrs, theme);
            return;
        }
        int eventType = parser.getEventType();
        int innerDepth = parser.getDepth() + 1;
        while (eventType != 1 && (parser.getDepth() >= innerDepth || eventType != 3)) {
            if (eventType == 2) {
                String tagName = parser.getName();
                if (ANIMATED_VECTOR.equals(tagName)) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
                    int drawableRes = a.getResourceId(0, 0);
                    if (drawableRes != 0) {
                        VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(res, drawableRes, theme);
                        vectorDrawable.setAllowCaching(false);
                        vectorDrawable.setCallback(this.mCallback);
                        if (this.mAnimatedVectorState.mVectorDrawable != null) {
                            this.mAnimatedVectorState.mVectorDrawable.setCallback((Drawable.Callback) null);
                        }
                        this.mAnimatedVectorState.mVectorDrawable = vectorDrawable;
                    }
                    a.recycle();
                } else if (TARGET.equals(tagName)) {
                    TypedArray a2 = res.obtainAttributes(attrs, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
                    String target = a2.getString(0);
                    int id = a2.getResourceId(1, 0);
                    if (id != 0) {
                        Context context = this.mContext;
                        if (context != null) {
                            setupAnimatorsForTarget(target, AnimatorInflaterCompat.loadAnimator(context, id));
                        } else {
                            a2.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    a2.recycle();
                } else {
                    continue;
                }
            }
            eventType = parser.next();
        }
        this.mAnimatedVectorState.setupAnimatorSet();
    }

    public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        inflate(res, parser, attrs, (Resources.Theme) null);
    }

    public void applyTheme(Resources.Theme t) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, t);
        }
    }

    public boolean canApplyTheme() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }
        return false;
    }

    private static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState state) {
            this.mDelegateState = state;
        }

        public Drawable newDrawable() {
            AnimatedVectorDrawableCompat drawableCompat = new AnimatedVectorDrawableCompat();
            drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable();
            drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
            return drawableCompat;
        }

        public Drawable newDrawable(Resources res) {
            AnimatedVectorDrawableCompat drawableCompat = new AnimatedVectorDrawableCompat();
            drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(res);
            drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
            return drawableCompat;
        }

        public Drawable newDrawable(Resources res, Resources.Theme theme) {
            AnimatedVectorDrawableCompat drawableCompat = new AnimatedVectorDrawableCompat();
            drawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(res, theme);
            drawableCompat.mDelegateDrawable.setCallback(drawableCompat.mCallback);
            return drawableCompat;
        }

        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }
    }

    private static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
        AnimatorSet mAnimatorSet;
        ArrayList<Animator> mAnimators;
        int mChangingConfigurations;
        ArrayMap<Animator, String> mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState copy, Drawable.Callback owner, Resources res) {
            if (copy != null) {
                this.mChangingConfigurations = copy.mChangingConfigurations;
                VectorDrawableCompat vectorDrawableCompat = copy.mVectorDrawable;
                if (vectorDrawableCompat != null) {
                    Drawable.ConstantState cs = vectorDrawableCompat.getConstantState();
                    if (res != null) {
                        this.mVectorDrawable = (VectorDrawableCompat) cs.newDrawable(res);
                    } else {
                        this.mVectorDrawable = (VectorDrawableCompat) cs.newDrawable();
                    }
                    VectorDrawableCompat vectorDrawableCompat2 = (VectorDrawableCompat) this.mVectorDrawable.mutate();
                    this.mVectorDrawable = vectorDrawableCompat2;
                    vectorDrawableCompat2.setCallback(owner);
                    this.mVectorDrawable.setBounds(copy.mVectorDrawable.getBounds());
                    this.mVectorDrawable.setAllowCaching(false);
                }
                ArrayList<Animator> arrayList = copy.mAnimators;
                if (arrayList != null) {
                    int numAnimators = arrayList.size();
                    this.mAnimators = new ArrayList<>(numAnimators);
                    this.mTargetNameMap = new ArrayMap<>(numAnimators);
                    for (int i = 0; i < numAnimators; i++) {
                        Animator anim = copy.mAnimators.get(i);
                        Animator animClone = anim.clone();
                        String targetName = copy.mTargetNameMap.get(anim);
                        animClone.setTarget(this.mVectorDrawable.getTargetByName(targetName));
                        this.mAnimators.add(animClone);
                        this.mTargetNameMap.put(animClone, targetName);
                    }
                    setupAnimatorSet();
                }
            }
        }

        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public Drawable newDrawable(Resources res) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public void setupAnimatorSet() {
            if (this.mAnimatorSet == null) {
                this.mAnimatorSet = new AnimatorSet();
            }
            this.mAnimatorSet.playTogether(this.mAnimators);
        }
    }

    private void setupColorAnimator(Animator animator) {
        List<Animator> childAnimators;
        if ((animator instanceof AnimatorSet) && (childAnimators = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimators.size(); i++) {
                setupColorAnimator(childAnimators.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnim = (ObjectAnimator) animator;
            String propertyName = objectAnim.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.mArgbEvaluator == null) {
                    this.mArgbEvaluator = new ArgbEvaluator();
                }
                objectAnim.setEvaluator(this.mArgbEvaluator);
            }
        }
    }

    private void setupAnimatorsForTarget(String name, Animator animator) {
        animator.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(name));
        if (Build.VERSION.SDK_INT < 21) {
            setupColorAnimator(animator);
        }
        if (this.mAnimatedVectorState.mAnimators == null) {
            this.mAnimatedVectorState.mAnimators = new ArrayList<>();
            this.mAnimatedVectorState.mTargetNameMap = new ArrayMap<>();
        }
        this.mAnimatedVectorState.mAnimators.add(animator);
        this.mAnimatedVectorState.mTargetNameMap.put(animator, name);
    }

    public boolean isRunning() {
        if (this.mDelegateDrawable != null) {
            return ((AnimatedVectorDrawable) this.mDelegateDrawable).isRunning();
        }
        return this.mAnimatedVectorState.mAnimatorSet.isRunning();
    }

    public void start() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable) this.mDelegateDrawable).start();
        } else if (!this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            this.mAnimatedVectorState.mAnimatorSet.start();
            invalidateSelf();
        }
    }

    public void stop() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable) this.mDelegateDrawable).stop();
        } else {
            this.mAnimatedVectorState.mAnimatorSet.end();
        }
    }

    private static boolean unregisterPlatformCallback(AnimatedVectorDrawable dr, Animatable2Compat.AnimationCallback callback) {
        return dr.unregisterAnimationCallback(callback.getPlatformCallback());
    }

    public void registerAnimationCallback(Animatable2Compat.AnimationCallback callback) {
        if (this.mDelegateDrawable != null) {
            registerPlatformCallback((AnimatedVectorDrawable) this.mDelegateDrawable, callback);
        } else if (callback != null) {
            if (this.mAnimationCallbacks == null) {
                this.mAnimationCallbacks = new ArrayList<>();
            }
            if (!this.mAnimationCallbacks.contains(callback)) {
                this.mAnimationCallbacks.add(callback);
                if (this.mAnimatorListener == null) {
                    this.mAnimatorListener = new AnimatorListenerAdapter() {
                        public void onAnimationStart(Animator animation) {
                            ArrayList<Animatable2Compat.AnimationCallback> tmpCallbacks = new ArrayList<>(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                            int size = tmpCallbacks.size();
                            for (int i = 0; i < size; i++) {
                                tmpCallbacks.get(i).onAnimationStart(AnimatedVectorDrawableCompat.this);
                            }
                        }

                        public void onAnimationEnd(Animator animation) {
                            ArrayList<Animatable2Compat.AnimationCallback> tmpCallbacks = new ArrayList<>(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                            int size = tmpCallbacks.size();
                            for (int i = 0; i < size; i++) {
                                tmpCallbacks.get(i).onAnimationEnd(AnimatedVectorDrawableCompat.this);
                            }
                        }
                    };
                }
                this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
            }
        }
    }

    private static void registerPlatformCallback(AnimatedVectorDrawable avd, Animatable2Compat.AnimationCallback callback) {
        avd.registerAnimationCallback(callback.getPlatformCallback());
    }

    private void removeAnimatorSetListener() {
        if (this.mAnimatorListener != null) {
            this.mAnimatedVectorState.mAnimatorSet.removeListener(this.mAnimatorListener);
            this.mAnimatorListener = null;
        }
    }

    public boolean unregisterAnimationCallback(Animatable2Compat.AnimationCallback callback) {
        if (this.mDelegateDrawable != null) {
            unregisterPlatformCallback((AnimatedVectorDrawable) this.mDelegateDrawable, callback);
        }
        ArrayList<Animatable2Compat.AnimationCallback> arrayList = this.mAnimationCallbacks;
        if (arrayList == null || callback == null) {
            return false;
        }
        boolean removed = arrayList.remove(callback);
        if (this.mAnimationCallbacks.size() == 0) {
            removeAnimatorSetListener();
        }
        return removed;
    }

    public void clearAnimationCallbacks() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable) this.mDelegateDrawable).clearAnimationCallbacks();
            return;
        }
        removeAnimatorSetListener();
        ArrayList<Animatable2Compat.AnimationCallback> arrayList = this.mAnimationCallbacks;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public static void registerAnimationCallback(Drawable dr, Animatable2Compat.AnimationCallback callback) {
        if (dr != null && callback != null && (dr instanceof Animatable)) {
            if (Build.VERSION.SDK_INT >= 24) {
                registerPlatformCallback((AnimatedVectorDrawable) dr, callback);
            } else {
                ((AnimatedVectorDrawableCompat) dr).registerAnimationCallback(callback);
            }
        }
    }

    public static boolean unregisterAnimationCallback(Drawable dr, Animatable2Compat.AnimationCallback callback) {
        if (dr == null || callback == null || !(dr instanceof Animatable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return unregisterPlatformCallback((AnimatedVectorDrawable) dr, callback);
        }
        return ((AnimatedVectorDrawableCompat) dr).unregisterAnimationCallback(callback);
    }

    public static void clearAnimationCallbacks(Drawable dr) {
        if (dr != null && (dr instanceof Animatable)) {
            if (Build.VERSION.SDK_INT >= 24) {
                ((AnimatedVectorDrawable) dr).clearAnimationCallbacks();
            } else {
                ((AnimatedVectorDrawableCompat) dr).clearAnimationCallbacks();
            }
        }
    }
}
