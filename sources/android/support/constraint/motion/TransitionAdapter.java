package android.support.constraint.motion;

import android.support.constraint.motion.MotionLayout;

public abstract class TransitionAdapter implements MotionLayout.TransitionListener {
    public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
    }

    public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
    }

    public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
    }

    public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
    }
}
