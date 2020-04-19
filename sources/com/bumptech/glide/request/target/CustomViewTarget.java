package com.bumptech.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.bumptech.glide.R;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CustomViewTarget<T extends View, Z> implements Target<Z> {
    private static final String TAG = "CustomViewTarget";
    @IdRes
    private static final int VIEW_TAG_ID = R.id.glide_custom_view_target_tag;
    @Nullable
    private View.OnAttachStateChangeListener attachStateListener;
    private boolean isAttachStateListenerAdded;
    private boolean isClearedByUs;
    @IdRes
    private int overrideTag;
    private final SizeDeterminer sizeDeterminer;
    protected final T view;

    /* access modifiers changed from: protected */
    public abstract void onResourceCleared(@Nullable Drawable drawable);

    public CustomViewTarget(@NonNull T view2) {
        this.view = (View) Preconditions.checkNotNull(view2);
        this.sizeDeterminer = new SizeDeterminer(view2);
    }

    /* access modifiers changed from: protected */
    public void onResourceLoading(@Nullable Drawable placeholder) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    @NonNull
    public final CustomViewTarget<T, Z> waitForLayout() {
        this.sizeDeterminer.waitForLayout = true;
        return this;
    }

    @NonNull
    public final CustomViewTarget<T, Z> clearOnDetach() {
        if (this.attachStateListener != null) {
            return this;
        }
        this.attachStateListener = new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View v) {
                CustomViewTarget.this.resumeMyRequest();
            }

            public void onViewDetachedFromWindow(View v) {
                CustomViewTarget.this.pauseMyRequest();
            }
        };
        maybeAddAttachStateListener();
        return this;
    }

    public final CustomViewTarget<T, Z> useTagId(@IdRes int tagId) {
        if (this.overrideTag == 0) {
            this.overrideTag = tagId;
            return this;
        }
        throw new IllegalArgumentException("You cannot change the tag id once it has been set.");
    }

    @NonNull
    public final T getView() {
        return this.view;
    }

    public final void getSize(@NonNull SizeReadyCallback cb) {
        this.sizeDeterminer.getSize(cb);
    }

    public final void removeCallback(@NonNull SizeReadyCallback cb) {
        this.sizeDeterminer.removeCallback(cb);
    }

    public final void onLoadStarted(@Nullable Drawable placeholder) {
        maybeAddAttachStateListener();
        onResourceLoading(placeholder);
    }

    public final void onLoadCleared(@Nullable Drawable placeholder) {
        this.sizeDeterminer.clearCallbacksAndListener();
        onResourceCleared(placeholder);
        if (!this.isClearedByUs) {
            maybeRemoveAttachStateListener();
        }
    }

    public final void setRequest(@Nullable Request request) {
        setTag(request);
    }

    @Nullable
    public final Request getRequest() {
        Object tag = getTag();
        if (tag == null) {
            return null;
        }
        if (tag instanceof Request) {
            return (Request) tag;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    /* access modifiers changed from: package-private */
    public final void resumeMyRequest() {
        Request request = getRequest();
        if (request != null && request.isCleared()) {
            request.begin();
        }
    }

    /* access modifiers changed from: package-private */
    public final void pauseMyRequest() {
        Request request = getRequest();
        if (request != null) {
            this.isClearedByUs = true;
            request.clear();
            this.isClearedByUs = false;
        }
    }

    private void setTag(@Nullable Object tag) {
        this.view.setTag(this.overrideTag == 0 ? VIEW_TAG_ID : this.overrideTag, tag);
    }

    @Nullable
    private Object getTag() {
        return this.view.getTag(this.overrideTag == 0 ? VIEW_TAG_ID : this.overrideTag);
    }

    private void maybeAddAttachStateListener() {
        if (this.attachStateListener != null && !this.isAttachStateListenerAdded) {
            this.view.addOnAttachStateChangeListener(this.attachStateListener);
            this.isAttachStateListenerAdded = true;
        }
    }

    private void maybeRemoveAttachStateListener() {
        if (this.attachStateListener != null && this.isAttachStateListenerAdded) {
            this.view.removeOnAttachStateChangeListener(this.attachStateListener);
            this.isAttachStateListenerAdded = false;
        }
    }

    @VisibleForTesting
    static final class SizeDeterminer {
        private static final int PENDING_SIZE = 0;
        @Nullable
        @VisibleForTesting
        static Integer maxDisplayLength;
        private final List<SizeReadyCallback> cbs = new ArrayList();
        @Nullable
        private SizeDeterminerLayoutListener layoutListener;
        private final View view;
        boolean waitForLayout;

        SizeDeterminer(@NonNull View view2) {
            this.view = view2;
        }

        private static int getMaxDisplayLength(@NonNull Context context) {
            if (maxDisplayLength == null) {
                Display display = ((WindowManager) Preconditions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point displayDimensions = new Point();
                display.getSize(displayDimensions);
                maxDisplayLength = Integer.valueOf(Math.max(displayDimensions.x, displayDimensions.y));
            }
            return maxDisplayLength.intValue();
        }

        private void notifyCbs(int width, int height) {
            Iterator it = new ArrayList(this.cbs).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).onSizeReady(width, height);
            }
        }

        /* access modifiers changed from: package-private */
        public void checkCurrentDimens() {
            if (!this.cbs.isEmpty()) {
                int currentWidth = getTargetWidth();
                int currentHeight = getTargetHeight();
                if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                    notifyCbs(currentWidth, currentHeight);
                    clearCallbacksAndListener();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void getSize(@NonNull SizeReadyCallback cb) {
            int currentWidth = getTargetWidth();
            int currentHeight = getTargetHeight();
            if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                cb.onSizeReady(currentWidth, currentHeight);
                return;
            }
            if (!this.cbs.contains(cb)) {
                this.cbs.add(cb);
            }
            if (this.layoutListener == null) {
                ViewTreeObserver observer = this.view.getViewTreeObserver();
                this.layoutListener = new SizeDeterminerLayoutListener(this);
                observer.addOnPreDrawListener(this.layoutListener);
            }
        }

        /* access modifiers changed from: package-private */
        public void removeCallback(@NonNull SizeReadyCallback cb) {
            this.cbs.remove(cb);
        }

        /* access modifiers changed from: package-private */
        public void clearCallbacksAndListener() {
            ViewTreeObserver observer = this.view.getViewTreeObserver();
            if (observer.isAlive()) {
                observer.removeOnPreDrawListener(this.layoutListener);
            }
            this.layoutListener = null;
            this.cbs.clear();
        }

        private boolean isViewStateAndSizeValid(int width, int height) {
            return isDimensionValid(width) && isDimensionValid(height);
        }

        private int getTargetHeight() {
            int verticalPadding = this.view.getPaddingTop() + this.view.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getHeight(), layoutParams != null ? layoutParams.height : 0, verticalPadding);
        }

        private int getTargetWidth() {
            int horizontalPadding = this.view.getPaddingLeft() + this.view.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            return getTargetDimen(this.view.getWidth(), layoutParams != null ? layoutParams.width : 0, horizontalPadding);
        }

        private int getTargetDimen(int viewSize, int paramSize, int paddingSize) {
            int adjustedParamSize = paramSize - paddingSize;
            if (adjustedParamSize > 0) {
                return adjustedParamSize;
            }
            if (this.waitForLayout && this.view.isLayoutRequested()) {
                return 0;
            }
            int adjustedViewSize = viewSize - paddingSize;
            if (adjustedViewSize > 0) {
                return adjustedViewSize;
            }
            if (this.view.isLayoutRequested() || paramSize != -2) {
                return 0;
            }
            if (Log.isLoggable(CustomViewTarget.TAG, 4)) {
                Log.i(CustomViewTarget.TAG, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return getMaxDisplayLength(this.view.getContext());
        }

        private boolean isDimensionValid(int size) {
            return size > 0 || size == Integer.MIN_VALUE;
        }

        private static final class SizeDeterminerLayoutListener implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<SizeDeterminer> sizeDeterminerRef;

            SizeDeterminerLayoutListener(@NonNull SizeDeterminer sizeDeterminer) {
                this.sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
            }

            public boolean onPreDraw() {
                if (Log.isLoggable(CustomViewTarget.TAG, 2)) {
                    Log.v(CustomViewTarget.TAG, "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                SizeDeterminer sizeDeterminer = (SizeDeterminer) this.sizeDeterminerRef.get();
                if (sizeDeterminer == null) {
                    return true;
                }
                sizeDeterminer.checkCurrentDimens();
                return true;
            }
        }
    }
}
