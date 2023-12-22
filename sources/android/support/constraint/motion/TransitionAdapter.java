package android.support.constraint.motion;

import android.support.constraint.motion.MotionLayout;

/* loaded from: classes.dex */
public abstract class TransitionAdapter implements MotionLayout.TransitionListener {
    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
    }

    @Override // android.support.constraint.motion.MotionLayout.TransitionListener
    public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
    }
}
