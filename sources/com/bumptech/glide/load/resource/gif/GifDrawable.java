package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.graphics.drawable.Animatable2Compat;
import android.view.Gravity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.gif.GifFrameLoader;
import com.bumptech.glide.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class GifDrawable extends Drawable implements GifFrameLoader.FrameCallback, Animatable, Animatable2Compat {
    private static final int GRAVITY = 119;
    public static final int LOOP_FOREVER = -1;
    public static final int LOOP_INTRINSIC = 0;
    private List<Animatable2Compat.AnimationCallback> animationCallbacks;
    private boolean applyGravity;
    private Rect destRect;
    private boolean isRecycled;
    private boolean isRunning;
    private boolean isStarted;
    private boolean isVisible;
    private int loopCount;
    private int maxLoopCount;
    private Paint paint;
    private final GifState state;

    @Deprecated
    public GifDrawable(Context context, GifDecoder gifDecoder, BitmapPool bitmapPool, Transformation<Bitmap> frameTransformation, int targetFrameWidth, int targetFrameHeight, Bitmap firstFrame) {
        this(context, gifDecoder, frameTransformation, targetFrameWidth, targetFrameHeight, firstFrame);
    }

    public GifDrawable(Context context, GifDecoder gifDecoder, Transformation<Bitmap> frameTransformation, int targetFrameWidth, int targetFrameHeight, Bitmap firstFrame) {
        this(new GifState(new GifFrameLoader(Glide.get(context), gifDecoder, targetFrameWidth, targetFrameHeight, frameTransformation, firstFrame)));
    }

    GifDrawable(GifState state2) {
        this.isVisible = true;
        this.maxLoopCount = -1;
        this.state = (GifState) Preconditions.checkNotNull(state2);
    }

    @VisibleForTesting
    GifDrawable(GifFrameLoader frameLoader, Paint paint2) {
        this(new GifState(frameLoader));
        this.paint = paint2;
    }

    public int getSize() {
        return this.state.frameLoader.getSize();
    }

    public Bitmap getFirstFrame() {
        return this.state.frameLoader.getFirstFrame();
    }

    public void setFrameTransformation(Transformation<Bitmap> frameTransformation, Bitmap firstFrame) {
        this.state.frameLoader.setFrameTransformation(frameTransformation, firstFrame);
    }

    public Transformation<Bitmap> getFrameTransformation() {
        return this.state.frameLoader.getFrameTransformation();
    }

    public ByteBuffer getBuffer() {
        return this.state.frameLoader.getBuffer();
    }

    public int getFrameCount() {
        return this.state.frameLoader.getFrameCount();
    }

    public int getFrameIndex() {
        return this.state.frameLoader.getCurrentIndex();
    }

    private void resetLoopCount() {
        this.loopCount = 0;
    }

    public void startFromFirstFrame() {
        Preconditions.checkArgument(!this.isRunning, "You cannot restart a currently running animation.");
        this.state.frameLoader.setNextStartFromFirstFrame();
        start();
    }

    public void start() {
        this.isStarted = true;
        resetLoopCount();
        if (this.isVisible) {
            startRunning();
        }
    }

    public void stop() {
        this.isStarted = false;
        stopRunning();
    }

    private void startRunning() {
        Preconditions.checkArgument(!this.isRecycled, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.state.frameLoader.getFrameCount() == 1) {
            invalidateSelf();
        } else if (!this.isRunning) {
            this.isRunning = true;
            this.state.frameLoader.subscribe(this);
            invalidateSelf();
        }
    }

    private void stopRunning() {
        this.isRunning = false;
        this.state.frameLoader.unsubscribe(this);
    }

    public boolean setVisible(boolean visible, boolean restart) {
        Preconditions.checkArgument(!this.isRecycled, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.isVisible = visible;
        if (!visible) {
            stopRunning();
        } else if (this.isStarted) {
            startRunning();
        }
        return super.setVisible(visible, restart);
    }

    public int getIntrinsicWidth() {
        return this.state.frameLoader.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.state.frameLoader.getHeight();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    /* access modifiers changed from: package-private */
    public void setIsRunning(boolean isRunning2) {
        this.isRunning = isRunning2;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.applyGravity = true;
    }

    public void draw(@NonNull Canvas canvas) {
        if (!this.isRecycled) {
            if (this.applyGravity) {
                Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), getDestRect());
                this.applyGravity = false;
            }
            canvas.drawBitmap(this.state.frameLoader.getCurrentFrame(), (Rect) null, getDestRect(), getPaint());
        }
    }

    public void setAlpha(int i) {
        getPaint().setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        getPaint().setColorFilter(colorFilter);
    }

    private Rect getDestRect() {
        if (this.destRect == null) {
            this.destRect = new Rect();
        }
        return this.destRect;
    }

    private Paint getPaint() {
        if (this.paint == null) {
            this.paint = new Paint(2);
        }
        return this.paint;
    }

    public int getOpacity() {
        return -2;
    }

    private Drawable.Callback findCallback() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        return callback;
    }

    public void onFrameReady() {
        if (findCallback() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (getFrameIndex() == getFrameCount() - 1) {
            this.loopCount++;
        }
        if (this.maxLoopCount != -1 && this.loopCount >= this.maxLoopCount) {
            notifyAnimationEndToListeners();
            stop();
        }
    }

    private void notifyAnimationEndToListeners() {
        if (this.animationCallbacks != null) {
            int size = this.animationCallbacks.size();
            for (int i = 0; i < size; i++) {
                this.animationCallbacks.get(i).onAnimationEnd(this);
            }
        }
    }

    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    public void recycle() {
        this.isRecycled = true;
        this.state.frameLoader.clear();
    }

    /* access modifiers changed from: package-private */
    public boolean isRecycled() {
        return this.isRecycled;
    }

    public void setLoopCount(int loopCount2) {
        int i = -1;
        if (loopCount2 <= 0 && loopCount2 != -1 && loopCount2 != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        } else if (loopCount2 == 0) {
            int intrinsicCount = this.state.frameLoader.getLoopCount();
            if (intrinsicCount != 0) {
                i = intrinsicCount;
            }
            this.maxLoopCount = i;
        } else {
            this.maxLoopCount = loopCount2;
        }
    }

    public void registerAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (animationCallback != null) {
            if (this.animationCallbacks == null) {
                this.animationCallbacks = new ArrayList();
            }
            this.animationCallbacks.add(animationCallback);
        }
    }

    public boolean unregisterAnimationCallback(@NonNull Animatable2Compat.AnimationCallback animationCallback) {
        if (this.animationCallbacks == null || animationCallback == null) {
            return false;
        }
        return this.animationCallbacks.remove(animationCallback);
    }

    public void clearAnimationCallbacks() {
        if (this.animationCallbacks != null) {
            this.animationCallbacks.clear();
        }
    }

    static final class GifState extends Drawable.ConstantState {
        @VisibleForTesting
        final GifFrameLoader frameLoader;

        GifState(GifFrameLoader frameLoader2) {
            this.frameLoader = frameLoader2;
        }

        @NonNull
        public Drawable newDrawable(Resources res) {
            return newDrawable();
        }

        @NonNull
        public Drawable newDrawable() {
            return new GifDrawable(this);
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }
}
