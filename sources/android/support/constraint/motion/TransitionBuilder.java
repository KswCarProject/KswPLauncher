package android.support.constraint.motion;

import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.MotionScene;

public class TransitionBuilder {
    private static final String TAG = "TransitionBuilder";

    public static void validate(MotionLayout layout) {
        if (layout.mScene != null) {
            MotionScene scene = layout.mScene;
            if (!scene.validateLayout(layout)) {
                throw new RuntimeException("MotionLayout doesn't have the right motion scene.");
            } else if (scene.mCurrentTransition == null || scene.getDefinedTransitions().isEmpty()) {
                throw new RuntimeException("Invalid motion layout. Motion Scene doesn't have any transition.");
            }
        } else {
            throw new RuntimeException("Invalid motion layout. Layout missing Motion Scene.");
        }
    }

    public static MotionScene.Transition buildTransition(MotionScene scene, int transitionId, int startConstraintSetId, ConstraintSet startConstraintSet, int endConstraintSetId, ConstraintSet endConstraintSet) {
        MotionScene.Transition transition = new MotionScene.Transition(transitionId, scene, startConstraintSetId, endConstraintSetId);
        updateConstraintSetInMotionScene(scene, transition, startConstraintSet, endConstraintSet);
        return transition;
    }

    private static void updateConstraintSetInMotionScene(MotionScene scene, MotionScene.Transition transition, ConstraintSet startConstraintSet, ConstraintSet endConstraintSet) {
        int startId = transition.getStartConstraintSetId();
        int endId = transition.getEndConstraintSetId();
        scene.setConstraintSet(startId, startConstraintSet);
        scene.setConstraintSet(endId, endConstraintSet);
    }
}
