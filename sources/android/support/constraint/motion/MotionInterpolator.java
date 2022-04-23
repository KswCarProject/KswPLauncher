package android.support.constraint.motion;

import android.view.animation.Interpolator;

public abstract class MotionInterpolator implements Interpolator {
    public abstract float getInterpolation(float f);

    public abstract float getVelocity();
}
