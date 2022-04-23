package skin.support.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinCompatVersionUtils;

public class SkinCompatProgressBarHelper extends SkinCompatHelper {
    private int mIndeterminateDrawableResId = 0;
    private int mIndeterminateTintResId = 0;
    private int mProgressDrawableResId = 0;
    private Bitmap mSampleTile;
    private final ProgressBar mView;

    SkinCompatProgressBarHelper(ProgressBar view) {
        this.mView = view;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = this.mView.getContext().obtainStyledAttributes(attrs, R.styleable.SkinCompatProgressBar, defStyleAttr, 0);
        this.mIndeterminateDrawableResId = a.getResourceId(R.styleable.SkinCompatProgressBar_android_indeterminateDrawable, 0);
        this.mProgressDrawableResId = a.getResourceId(R.styleable.SkinCompatProgressBar_android_progressDrawable, 0);
        a.recycle();
        if (Build.VERSION.SDK_INT > 21) {
            TypedArray a2 = this.mView.getContext().obtainStyledAttributes(attrs, new int[]{16843881}, defStyleAttr, 0);
            this.mIndeterminateTintResId = a2.getResourceId(0, 0);
            a2.recycle();
        }
        applySkin();
    }

    private Drawable tileify(Drawable drawable, boolean clip) {
        if (SkinCompatVersionUtils.isV4WrappedDrawable(drawable)) {
            Drawable inner = SkinCompatVersionUtils.getV4WrappedDrawableWrappedDrawable(drawable);
            if (inner != null) {
                SkinCompatVersionUtils.setV4WrappedDrawableWrappedDrawable(drawable, tileify(inner, clip));
            }
        } else if (SkinCompatVersionUtils.isV4DrawableWrapper(drawable)) {
            Drawable inner2 = SkinCompatVersionUtils.getV4DrawableWrapperWrappedDrawable(drawable);
            if (inner2 != null) {
                SkinCompatVersionUtils.setV4DrawableWrapperWrappedDrawable(drawable, tileify(inner2, clip));
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable background = (LayerDrawable) drawable;
            int N = background.getNumberOfLayers();
            Drawable[] outDrawables = new Drawable[N];
            for (int i = 0; i < N; i++) {
                int id = background.getId(i);
                outDrawables[i] = tileify(background.getDrawable(i), id == 16908301 || id == 16908303);
            }
            LayerDrawable newBg = new LayerDrawable(outDrawables);
            for (int i2 = 0; i2 < N; i2++) {
                newBg.setId(i2, background.getId(i2));
            }
            return newBg;
        } else if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap tileBitmap = bitmapDrawable.getBitmap();
            if (this.mSampleTile == null) {
                this.mSampleTile = tileBitmap;
            }
            ShapeDrawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            shapeDrawable.getPaint().setShader(new BitmapShader(tileBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
            shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return clip ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }

    private Drawable tileifyIndeterminate(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable background = (AnimationDrawable) drawable;
        int N = background.getNumberOfFrames();
        AnimationDrawable newBg = new AnimationDrawable();
        newBg.setOneShot(background.isOneShot());
        for (int i = 0; i < N; i++) {
            Drawable frame = tileify(background.getFrame(i), true);
            frame.setLevel(10000);
            newBg.addFrame(frame, background.getDuration(i));
        }
        newBg.setLevel(10000);
        return newBg;
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, (RectF) null, (float[]) null);
    }

    public void applySkin() {
        int checkResourceId = checkResourceId(this.mIndeterminateDrawableResId);
        this.mIndeterminateDrawableResId = checkResourceId;
        if (checkResourceId != 0) {
            Drawable drawable = SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mIndeterminateDrawableResId);
            drawable.setBounds(this.mView.getIndeterminateDrawable().getBounds());
            this.mView.setIndeterminateDrawable(tileifyIndeterminate(drawable));
        }
        int checkProgressDrawableResId = checkProgressDrawableResId(this.mProgressDrawableResId);
        this.mProgressDrawableResId = checkProgressDrawableResId;
        if (checkProgressDrawableResId != 0) {
            this.mView.setProgressDrawable(tileify(SkinCompatResources.getDrawableCompat(this.mView.getContext(), this.mProgressDrawableResId), false));
        }
        if (Build.VERSION.SDK_INT > 21) {
            int checkResourceId2 = checkResourceId(this.mIndeterminateTintResId);
            this.mIndeterminateTintResId = checkResourceId2;
            if (checkResourceId2 != 0) {
                ProgressBar progressBar = this.mView;
                progressBar.setIndeterminateTintList(SkinCompatResources.getColorStateList(progressBar.getContext(), this.mIndeterminateTintResId));
            }
        }
    }

    private int checkProgressDrawableResId(int mProgressDrawableResId2) {
        if (mProgressDrawableResId2 == R.drawable.abc_ratingbar_material) {
            return 0;
        }
        return checkResourceId(mProgressDrawableResId2);
    }
}
