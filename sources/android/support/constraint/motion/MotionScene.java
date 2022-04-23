package android.support.constraint.motion;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.R;
import android.support.constraint.StateSet;
import android.support.constraint.motion.MotionLayout;
import android.support.constraint.motion.utils.Easing;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

public class MotionScene {
    static final int ANTICIPATE = 4;
    static final int BOUNCE = 5;
    private static final boolean DEBUG = false;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final int INTERPOLATOR_REFRENCE_ID = -2;
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    static final int LINEAR = 3;
    private static final int SPLINE_STRING = -1;
    public static final String TAG = "MotionScene";
    static final int TRANSITION_BACKWARD = 0;
    static final int TRANSITION_FORWARD = 1;
    public static final int UNSET = -1;
    private boolean DEBUG_DESKTOP = false;
    private ArrayList<Transition> mAbstractTransitionList = new ArrayList<>();
    private HashMap<String, Integer> mConstraintSetIdMap = new HashMap<>();
    /* access modifiers changed from: private */
    public SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    Transition mCurrentTransition = null;
    /* access modifiers changed from: private */
    public int mDefaultDuration = 400;
    private Transition mDefaultTransition = null;
    private SparseIntArray mDeriveMap = new SparseIntArray();
    private boolean mDisableAutoTransition = false;
    private MotionEvent mLastTouchDown;
    float mLastTouchX;
    float mLastTouchY;
    /* access modifiers changed from: private */
    public int mLayoutDuringTransition = 0;
    /* access modifiers changed from: private */
    public final MotionLayout mMotionLayout;
    private boolean mMotionOutsideRegion = false;
    private boolean mRtl;
    StateSet mStateSet = null;
    private ArrayList<Transition> mTransitionList = new ArrayList<>();
    private MotionLayout.MotionTracker mVelocityTracker;

    /* access modifiers changed from: package-private */
    public void setTransition(int beginId, int endId) {
        int start = beginId;
        int end = endId;
        StateSet stateSet = this.mStateSet;
        if (stateSet != null) {
            int tmp = stateSet.stateGetConstraintID(beginId, -1, -1);
            if (tmp != -1) {
                start = tmp;
            }
            int tmp2 = this.mStateSet.stateGetConstraintID(endId, -1, -1);
            if (tmp2 != -1) {
                end = tmp2;
            }
        }
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = it.next();
            if ((transition.mConstraintSetEnd == end && transition.mConstraintSetStart == start) || (transition.mConstraintSetEnd == endId && transition.mConstraintSetStart == beginId)) {
                this.mCurrentTransition = transition;
                if (transition != null && transition.mTouchResponse != null) {
                    this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
                    return;
                }
                return;
            }
        }
        Transition matchTransiton = this.mDefaultTransition;
        Iterator<Transition> it2 = this.mAbstractTransitionList.iterator();
        while (it2.hasNext()) {
            Transition transition2 = it2.next();
            if (transition2.mConstraintSetEnd == endId) {
                matchTransiton = transition2;
            }
        }
        Transition t = new Transition(this, matchTransiton);
        int unused = t.mConstraintSetStart = start;
        int unused2 = t.mConstraintSetEnd = end;
        if (start != -1) {
            this.mTransitionList.add(t);
        }
        this.mCurrentTransition = t;
    }

    public void addTransition(Transition transition) {
        int index = getIndex(transition);
        if (index == -1) {
            this.mTransitionList.add(transition);
        } else {
            this.mTransitionList.set(index, transition);
        }
    }

    public void removeTransition(Transition transition) {
        int index = getIndex(transition);
        if (index != -1) {
            this.mTransitionList.remove(index);
        }
    }

    private int getIndex(Transition transition) {
        int id = transition.mId;
        if (id != -1) {
            for (int index = 0; index < this.mTransitionList.size(); index++) {
                if (this.mTransitionList.get(index).mId == id) {
                    return index;
                }
            }
            return -1;
        }
        throw new IllegalArgumentException("The transition must have an id");
    }

    public boolean validateLayout(MotionLayout layout) {
        return layout == this.mMotionLayout && layout.mScene == this;
    }

    public void setTransition(Transition transition) {
        this.mCurrentTransition = transition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
        }
    }

    private int getRealID(int stateid) {
        int tmp;
        StateSet stateSet = this.mStateSet;
        if (stateSet == null || (tmp = stateSet.stateGetConstraintID(stateid, -1, -1)) == -1) {
            return stateid;
        }
        return tmp;
    }

    public List<Transition> getTransitionsWithState(int stateid) {
        int stateid2 = getRealID(stateid);
        ArrayList<Transition> ret = new ArrayList<>();
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = it.next();
            if (transition.mConstraintSetStart == stateid2 || transition.mConstraintSetEnd == stateid2) {
                ret.add(transition);
            }
        }
        return ret;
    }

    public void addOnClickListeners(MotionLayout motionLayout, int currentState) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = it.next();
            if (transition.mOnClicks.size() > 0) {
                Iterator it2 = transition.mOnClicks.iterator();
                while (it2.hasNext()) {
                    ((Transition.TransitionOnClick) it2.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it3 = this.mAbstractTransitionList.iterator();
        while (it3.hasNext()) {
            Transition transition2 = it3.next();
            if (transition2.mOnClicks.size() > 0) {
                Iterator it4 = transition2.mOnClicks.iterator();
                while (it4.hasNext()) {
                    ((Transition.TransitionOnClick) it4.next()).removeOnClickListeners(motionLayout);
                }
            }
        }
        Iterator<Transition> it5 = this.mTransitionList.iterator();
        while (it5.hasNext()) {
            Transition transition3 = it5.next();
            if (transition3.mOnClicks.size() > 0) {
                Iterator it6 = transition3.mOnClicks.iterator();
                while (it6.hasNext()) {
                    ((Transition.TransitionOnClick) it6.next()).addOnClickListeners(motionLayout, currentState, transition3);
                }
            }
        }
        Iterator<Transition> it7 = this.mAbstractTransitionList.iterator();
        while (it7.hasNext()) {
            Transition transition4 = it7.next();
            if (transition4.mOnClicks.size() > 0) {
                Iterator it8 = transition4.mOnClicks.iterator();
                while (it8.hasNext()) {
                    ((Transition.TransitionOnClick) it8.next()).addOnClickListeners(motionLayout, currentState, transition4);
                }
            }
        }
    }

    public Transition bestTransitionFor(int currentState, float dx, float dy, MotionEvent mLastTouchDown2) {
        RectF region;
        float val;
        if (currentState == -1) {
            return this.mCurrentTransition;
        }
        List<Transition> candidates = getTransitionsWithState(currentState);
        float max = 0.0f;
        Transition best = null;
        RectF cache = new RectF();
        for (Transition transition : candidates) {
            if (!transition.mDisable && transition.mTouchResponse != null) {
                transition.mTouchResponse.setRTL(this.mRtl);
                RectF region2 = transition.mTouchResponse.getTouchRegion(this.mMotionLayout, cache);
                if ((region2 == null || mLastTouchDown2 == null || region2.contains(mLastTouchDown2.getX(), mLastTouchDown2.getY())) && ((region = transition.mTouchResponse.getTouchRegion(this.mMotionLayout, cache)) == null || mLastTouchDown2 == null || region.contains(mLastTouchDown2.getX(), mLastTouchDown2.getY()))) {
                    float val2 = transition.mTouchResponse.dot(dx, dy);
                    if (transition.mConstraintSetEnd == currentState) {
                        val = val2 * -1.0f;
                    } else {
                        val = val2 * 1.1f;
                    }
                    if (val > max) {
                        max = val;
                        best = transition;
                    }
                }
            }
        }
        return best;
    }

    public ArrayList<Transition> getDefinedTransitions() {
        return this.mTransitionList;
    }

    public Transition getTransitionById(int id) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = it.next();
            if (transition.mId == id) {
                return transition;
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int[] ids = new int[this.mConstraintSetMap.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = this.mConstraintSetMap.keyAt(i);
        }
        return ids;
    }

    /* access modifiers changed from: package-private */
    public boolean autoTransition(MotionLayout motionLayout, int currentState) {
        if (isProcessingTouch() || this.mDisableAutoTransition) {
            return false;
        }
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            Transition transition = it.next();
            if (transition.mAutoTransition != 0) {
                if (currentState == transition.mConstraintSetStart && (transition.mAutoTransition == 4 || transition.mAutoTransition == 2)) {
                    motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    motionLayout.setTransition(transition);
                    if (transition.mAutoTransition == 4) {
                        motionLayout.transitionToEnd();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.evaluate(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    }
                    return true;
                } else if (currentState == transition.mConstraintSetEnd && (transition.mAutoTransition == 3 || transition.mAutoTransition == 1)) {
                    motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    motionLayout.setTransition(transition);
                    if (transition.mAutoTransition == 3) {
                        motionLayout.transitionToStart();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.evaluate(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(MotionLayout.TransitionState.FINISHED);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isProcessingTouch() {
        return this.mVelocityTracker != null;
    }

    public void setRtl(boolean rtl) {
        this.mRtl = rtl;
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
        }
    }

    public static class Transition {
        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        static final int TRANSITION_FLAG_FIRST_DRAW = 1;
        /* access modifiers changed from: private */
        public int mAutoTransition = 0;
        /* access modifiers changed from: private */
        public int mConstraintSetEnd = -1;
        /* access modifiers changed from: private */
        public int mConstraintSetStart = -1;
        /* access modifiers changed from: private */
        public int mDefaultInterpolator = 0;
        /* access modifiers changed from: private */
        public int mDefaultInterpolatorID = -1;
        /* access modifiers changed from: private */
        public String mDefaultInterpolatorString = null;
        /* access modifiers changed from: private */
        public boolean mDisable = false;
        /* access modifiers changed from: private */
        public int mDuration = 400;
        /* access modifiers changed from: private */
        public int mId = -1;
        /* access modifiers changed from: private */
        public boolean mIsAbstract = false;
        /* access modifiers changed from: private */
        public ArrayList<KeyFrames> mKeyFramesList = new ArrayList<>();
        private int mLayoutDuringTransition = 0;
        /* access modifiers changed from: private */
        public final MotionScene mMotionScene;
        /* access modifiers changed from: private */
        public ArrayList<TransitionOnClick> mOnClicks = new ArrayList<>();
        /* access modifiers changed from: private */
        public int mPathMotionArc = -1;
        /* access modifiers changed from: private */
        public float mStagger = 0.0f;
        /* access modifiers changed from: private */
        public TouchResponse mTouchResponse = null;
        private int mTransitionFlags = 0;

        public int getLayoutDuringTransition() {
            return this.mLayoutDuringTransition;
        }

        public void addOnClick(Context context, XmlPullParser parser) {
            this.mOnClicks.add(new TransitionOnClick(context, this, parser));
        }

        public int getId() {
            return this.mId;
        }

        public int getEndConstraintSetId() {
            return this.mConstraintSetEnd;
        }

        public int getStartConstraintSetId() {
            return this.mConstraintSetStart;
        }

        public void setDuration(int duration) {
            this.mDuration = duration;
        }

        public int getDuration() {
            return this.mDuration;
        }

        public float getStagger() {
            return this.mStagger;
        }

        public List<KeyFrames> getKeyFrameList() {
            return this.mKeyFramesList;
        }

        public List<TransitionOnClick> getOnClickList() {
            return this.mOnClicks;
        }

        public TouchResponse getTouchResponse() {
            return this.mTouchResponse;
        }

        public void setStagger(float stagger) {
            this.mStagger = stagger;
        }

        public void setPathMotionArc(int arcMode) {
            this.mPathMotionArc = arcMode;
        }

        public int getPathMotionArc() {
            return this.mPathMotionArc;
        }

        public boolean isEnabled() {
            return !this.mDisable;
        }

        public void setEnable(boolean enable) {
            this.mDisable = !enable;
        }

        public String debugString(Context context) {
            String ret;
            if (this.mConstraintSetStart == -1) {
                ret = "null";
            } else {
                ret = context.getResources().getResourceEntryName(this.mConstraintSetStart);
            }
            if (this.mConstraintSetEnd == -1) {
                return ret + " -> null";
            }
            return ret + " -> " + context.getResources().getResourceEntryName(this.mConstraintSetEnd);
        }

        public boolean isTransitionFlag(int flag) {
            return (this.mTransitionFlags & flag) != 0;
        }

        static class TransitionOnClick implements View.OnClickListener {
            public static final int ANIM_TOGGLE = 17;
            public static final int ANIM_TO_END = 1;
            public static final int ANIM_TO_START = 16;
            public static final int JUMP_TO_END = 256;
            public static final int JUMP_TO_START = 4096;
            int mMode = 17;
            int mTargetId = -1;
            private final Transition mTransition;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser parser) {
                this.mTransition = transition;
                TypedArray a = context.obtainStyledAttributes(Xml.asAttributeSet(parser), R.styleable.OnClick);
                int N = a.getIndexCount();
                for (int i = 0; i < N; i++) {
                    int attr = a.getIndex(i);
                    if (attr == R.styleable.OnClick_targetId) {
                        this.mTargetId = a.getResourceId(attr, this.mTargetId);
                    } else if (attr == R.styleable.OnClick_clickAction) {
                        this.mMode = a.getInt(attr, this.mMode);
                    }
                }
                a.recycle();
            }

            public void addOnClickListeners(MotionLayout motionLayout, int currentState, Transition transition) {
                int i = this.mTargetId;
                View v = i == -1 ? motionLayout : motionLayout.findViewById(i);
                if (v == null) {
                    Log.e(MotionScene.TAG, "OnClick could not find id " + this.mTargetId);
                    return;
                }
                int start = transition.mConstraintSetStart;
                int end = transition.mConstraintSetEnd;
                if (start == -1) {
                    v.setOnClickListener(this);
                    return;
                }
                int i2 = this.mMode;
                boolean z = false;
                boolean listen = ((i2 & 1) != 0 && currentState == start) | ((i2 & 256) != 0 && currentState == start) | ((i2 & 1) != 0 && currentState == start) | ((i2 & 16) != 0 && currentState == end);
                if ((i2 & 4096) != 0 && currentState == end) {
                    z = true;
                }
                if (listen || z) {
                    v.setOnClickListener(this);
                }
            }

            public void removeOnClickListeners(MotionLayout motionLayout) {
                int i = this.mTargetId;
                if (i != -1) {
                    View v = motionLayout.findViewById(i);
                    if (v == null) {
                        Log.e(MotionScene.TAG, " (*)  could not find id " + this.mTargetId);
                    } else {
                        v.setOnClickListener((View.OnClickListener) null);
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public boolean isTransitionViable(Transition current, MotionLayout tl) {
                Transition transition = this.mTransition;
                if (transition == current) {
                    return true;
                }
                int dest = transition.mConstraintSetEnd;
                int from = this.mTransition.mConstraintSetStart;
                if (from == -1) {
                    if (tl.mCurrentState != dest) {
                        return true;
                    }
                    return false;
                } else if (tl.mCurrentState == from || tl.mCurrentState == dest) {
                    return true;
                } else {
                    return false;
                }
            }

            public void onClick(View view) {
                MotionLayout tl = this.mTransition.mMotionScene.mMotionLayout;
                if (tl.isInteractionEnabled()) {
                    if (this.mTransition.mConstraintSetStart == -1) {
                        int currentState = tl.getCurrentState();
                        if (currentState == -1) {
                            tl.transitionToState(this.mTransition.mConstraintSetEnd);
                            return;
                        }
                        Transition t = new Transition(this.mTransition.mMotionScene, this.mTransition);
                        int unused = t.mConstraintSetStart = currentState;
                        int unused2 = t.mConstraintSetEnd = this.mTransition.mConstraintSetEnd;
                        tl.setTransition(t);
                        tl.transitionToEnd();
                        return;
                    }
                    Transition current = this.mTransition.mMotionScene.mCurrentTransition;
                    int i = this.mMode;
                    boolean bidirectional = false;
                    boolean forward = ((i & 1) == 0 && (i & 256) == 0) ? false : true;
                    boolean backward = ((i & 16) == 0 && (i & 4096) == 0) ? false : true;
                    if (forward && backward) {
                        bidirectional = true;
                    }
                    if (bidirectional) {
                        Transition transition = this.mTransition.mMotionScene.mCurrentTransition;
                        Transition transition2 = this.mTransition;
                        if (transition != transition2) {
                            tl.setTransition(transition2);
                        }
                        if (tl.getCurrentState() == tl.getEndState() || tl.getProgress() > 0.5f) {
                            forward = false;
                        } else {
                            backward = false;
                        }
                    }
                    if (!isTransitionViable(current, tl)) {
                        return;
                    }
                    if (forward && (1 & this.mMode) != 0) {
                        tl.setTransition(this.mTransition);
                        tl.transitionToEnd();
                    } else if (backward && (this.mMode & 16) != 0) {
                        tl.setTransition(this.mTransition);
                        tl.transitionToStart();
                    } else if (forward && (this.mMode & 256) != 0) {
                        tl.setTransition(this.mTransition);
                        tl.setProgress(1.0f);
                    } else if (backward && (this.mMode & 4096) != 0) {
                        tl.setTransition(this.mTransition);
                        tl.setProgress(0.0f);
                    }
                }
            }
        }

        Transition(MotionScene motionScene, Transition global) {
            this.mMotionScene = motionScene;
            if (global != null) {
                this.mPathMotionArc = global.mPathMotionArc;
                this.mDefaultInterpolator = global.mDefaultInterpolator;
                this.mDefaultInterpolatorString = global.mDefaultInterpolatorString;
                this.mDefaultInterpolatorID = global.mDefaultInterpolatorID;
                this.mDuration = global.mDuration;
                this.mKeyFramesList = global.mKeyFramesList;
                this.mStagger = global.mStagger;
                this.mLayoutDuringTransition = global.mLayoutDuringTransition;
            }
        }

        public Transition(int id, MotionScene motionScene, int constraintSetStartId, int constraintSetEndId) {
            this.mId = id;
            this.mMotionScene = motionScene;
            this.mConstraintSetStart = constraintSetStartId;
            this.mConstraintSetEnd = constraintSetEndId;
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
        }

        Transition(MotionScene motionScene, Context context, XmlPullParser parser) {
            this.mDuration = motionScene.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene.mLayoutDuringTransition;
            this.mMotionScene = motionScene;
            fillFromAttributeList(motionScene, context, Xml.asAttributeSet(parser));
        }

        private void fillFromAttributeList(MotionScene motionScene, Context context, AttributeSet attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Transition);
            fill(motionScene, context, a);
            a.recycle();
        }

        private void fill(MotionScene motionScene, Context context, TypedArray a) {
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.Transition_constraintSetEnd) {
                    this.mConstraintSetEnd = a.getResourceId(attr, this.mConstraintSetEnd);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.mConstraintSetEnd))) {
                        ConstraintSet cSet = new ConstraintSet();
                        cSet.load(context, this.mConstraintSetEnd);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetEnd, cSet);
                    }
                } else if (attr == R.styleable.Transition_constraintSetStart) {
                    this.mConstraintSetStart = a.getResourceId(attr, this.mConstraintSetStart);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.mConstraintSetStart))) {
                        ConstraintSet cSet2 = new ConstraintSet();
                        cSet2.load(context, this.mConstraintSetStart);
                        motionScene.mConstraintSetMap.append(this.mConstraintSetStart, cSet2);
                    }
                } else if (attr == R.styleable.Transition_motionInterpolator) {
                    TypedValue type = a.peekValue(attr);
                    if (type.type == 1) {
                        int resourceId = a.getResourceId(attr, -1);
                        this.mDefaultInterpolatorID = resourceId;
                        if (resourceId != -1) {
                            this.mDefaultInterpolator = -2;
                        }
                    } else if (type.type == 3) {
                        String string = a.getString(attr);
                        this.mDefaultInterpolatorString = string;
                        if (string.indexOf("/") > 0) {
                            this.mDefaultInterpolatorID = a.getResourceId(attr, -1);
                            this.mDefaultInterpolator = -2;
                        } else {
                            this.mDefaultInterpolator = -1;
                        }
                    } else {
                        this.mDefaultInterpolator = a.getInteger(attr, this.mDefaultInterpolator);
                    }
                } else if (attr == R.styleable.Transition_duration) {
                    this.mDuration = a.getInt(attr, this.mDuration);
                } else if (attr == R.styleable.Transition_staggered) {
                    this.mStagger = a.getFloat(attr, this.mStagger);
                } else if (attr == R.styleable.Transition_autoTransition) {
                    this.mAutoTransition = a.getInteger(attr, this.mAutoTransition);
                } else if (attr == R.styleable.Transition_android_id) {
                    this.mId = a.getResourceId(attr, this.mId);
                } else if (attr == R.styleable.Transition_transitionDisable) {
                    this.mDisable = a.getBoolean(attr, this.mDisable);
                } else if (attr == R.styleable.Transition_pathMotionArc) {
                    this.mPathMotionArc = a.getInteger(attr, -1);
                } else if (attr == R.styleable.Transition_layoutDuringTransition) {
                    this.mLayoutDuringTransition = a.getInteger(attr, 0);
                } else if (attr == R.styleable.Transition_transitionFlags) {
                    this.mTransitionFlags = a.getInteger(attr, 0);
                }
            }
            if (this.mConstraintSetStart == -1) {
                this.mIsAbstract = true;
            }
        }
    }

    public MotionScene(MotionLayout layout) {
        this.mMotionLayout = layout;
    }

    MotionScene(Context context, MotionLayout layout, int resourceID) {
        this.mMotionLayout = layout;
        load(context, resourceID);
        this.mConstraintSetMap.put(R.id.motion_base, new ConstraintSet());
        this.mConstraintSetIdMap.put("motion_base", Integer.valueOf(R.id.motion_base));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x008a, code lost:
        switch(r6) {
            case 0: goto L_0x0131;
            case 1: goto L_0x00ef;
            case 2: goto L_0x00b0;
            case 3: goto L_0x00ab;
            case 4: goto L_0x00a2;
            case 5: goto L_0x009d;
            case 6: goto L_0x008f;
            default: goto L_0x008d;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008f, code lost:
        android.support.constraint.motion.MotionScene.Transition.access$1300(r4).add(new android.support.constraint.motion.KeyFrames(r12, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009d, code lost:
        parseConstraintSet(r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a2, code lost:
        r11.mStateSet = new android.support.constraint.StateSet(r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ab, code lost:
        r4.addOnClick(r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b0, code lost:
        if (r4 != null) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b2, code lost:
        android.util.Log.v(TAG, " OnSwipe (" + r12.getResources().getResourceEntryName(r13) + ".xml:" + r1.getLineNumber() + ")");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e4, code lost:
        android.support.constraint.motion.MotionScene.Transition.access$202(r4, new android.support.constraint.motion.TouchResponse(r12, r11.mMotionLayout, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ef, code lost:
        r6 = r11.mTransitionList;
        r7 = new android.support.constraint.motion.MotionScene.Transition(r11, r12, r1);
        r4 = r7;
        r6.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00fc, code lost:
        if (r11.mCurrentTransition != null) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0102, code lost:
        if (android.support.constraint.motion.MotionScene.Transition.access$1200(r4) != false) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0104, code lost:
        r11.mCurrentTransition = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010a, code lost:
        if (android.support.constraint.motion.MotionScene.Transition.access$200(r4) == null) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010c, code lost:
        android.support.constraint.motion.MotionScene.Transition.access$200(r11.mCurrentTransition).setRTL(r11.mRtl);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011b, code lost:
        if (android.support.constraint.motion.MotionScene.Transition.access$1200(r4) == false) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0121, code lost:
        if (android.support.constraint.motion.MotionScene.Transition.access$000(r4) != -1) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0123, code lost:
        r11.mDefaultTransition = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0126, code lost:
        r11.mAbstractTransitionList.add(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x012b, code lost:
        r11.mTransitionList.remove(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0131, code lost:
        parseMotionSceneTags(r12, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0135, code lost:
        android.util.Log.v(TAG, "WARNING UNKNOWN ATTRIBUTE " + r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load(android.content.Context r12, int r13) {
        /*
            r11 = this;
            android.content.res.Resources r0 = r12.getResources()
            android.content.res.XmlResourceParser r1 = r0.getXml(r13)
            r2 = 0
            r3 = 0
            r4 = 0
            int r5 = r1.getEventType()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x000f:
            r6 = 1
            if (r5 == r6) goto L_0x0159
            switch(r5) {
                case 0: goto L_0x014c;
                case 1: goto L_0x0015;
                case 2: goto L_0x001a;
                case 3: goto L_0x0017;
                default: goto L_0x0015;
            }     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x0015:
            goto L_0x0152
        L_0x0017:
            r3 = 0
            goto L_0x0152
        L_0x001a:
            java.lang.String r7 = r1.getName()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r3 = r7
            boolean r7 = r11.DEBUG_DESKTOP     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r7 == 0) goto L_0x003b
            java.io.PrintStream r7 = java.lang.System.out     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r9 = "parsing = "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.StringBuilder r8 = r8.append(r3)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r7.println(r8)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x003b:
            int r7 = r3.hashCode()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r8 = -1
            java.lang.String r9 = "MotionScene"
            switch(r7) {
                case -1349929691: goto L_0x007f;
                case -1239391468: goto L_0x0075;
                case 269306229: goto L_0x006c;
                case 312750793: goto L_0x0062;
                case 327855227: goto L_0x0058;
                case 793277014: goto L_0x0050;
                case 1382829617: goto L_0x0046;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x0089
        L_0x0046:
            java.lang.String r6 = "StateSet"
            boolean r6 = r3.equals(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0045
            r6 = 4
            goto L_0x008a
        L_0x0050:
            boolean r6 = r3.equals(r9)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0045
            r6 = 0
            goto L_0x008a
        L_0x0058:
            java.lang.String r6 = "OnSwipe"
            boolean r6 = r3.equals(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0045
            r6 = 2
            goto L_0x008a
        L_0x0062:
            java.lang.String r6 = "OnClick"
            boolean r6 = r3.equals(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0045
            r6 = 3
            goto L_0x008a
        L_0x006c:
            java.lang.String r7 = "Transition"
            boolean r7 = r3.equals(r7)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r7 == 0) goto L_0x0045
            goto L_0x008a
        L_0x0075:
            java.lang.String r6 = "KeyFrameSet"
            boolean r6 = r3.equals(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0045
            r6 = 6
            goto L_0x008a
        L_0x007f:
            java.lang.String r6 = "ConstraintSet"
            boolean r6 = r3.equals(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0045
            r6 = 5
            goto L_0x008a
        L_0x0089:
            r6 = r8
        L_0x008a:
            switch(r6) {
                case 0: goto L_0x0131;
                case 1: goto L_0x00ef;
                case 2: goto L_0x00b0;
                case 3: goto L_0x00ab;
                case 4: goto L_0x00a2;
                case 5: goto L_0x009d;
                case 6: goto L_0x008f;
                default: goto L_0x008d;
            }     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x008d:
            goto L_0x0135
        L_0x008f:
            android.support.constraint.motion.KeyFrames r6 = new android.support.constraint.motion.KeyFrames     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.<init>(r12, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.util.ArrayList r7 = r4.mKeyFramesList     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r7.add(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x009d:
            r11.parseConstraintSet(r12, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x00a2:
            android.support.constraint.StateSet r6 = new android.support.constraint.StateSet     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.<init>(r12, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r11.mStateSet = r6     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x00ab:
            r4.addOnClick(r12, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x00b0:
            if (r4 != 0) goto L_0x00e4
            android.content.res.Resources r6 = r12.getResources()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r6 = r6.getResourceEntryName(r13)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            int r7 = r1.getLineNumber()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r10 = " OnSwipe ("
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.StringBuilder r8 = r8.append(r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r10 = ".xml:"
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.StringBuilder r8 = r8.append(r7)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r10 = ")"
            java.lang.StringBuilder r8 = r8.append(r10)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r8 = r8.toString()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.util.Log.v(r9, r8)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x00e4:
            android.support.constraint.motion.TouchResponse r6 = new android.support.constraint.motion.TouchResponse     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.support.constraint.motion.MotionLayout r7 = r11.mMotionLayout     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.<init>(r12, r7, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.support.constraint.motion.TouchResponse unused = r4.mTouchResponse = r6     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x00ef:
            java.util.ArrayList<android.support.constraint.motion.MotionScene$Transition> r6 = r11.mTransitionList     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.support.constraint.motion.MotionScene$Transition r7 = new android.support.constraint.motion.MotionScene$Transition     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r7.<init>(r11, r12, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r4 = r7
            r6.add(r7)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.support.constraint.motion.MotionScene$Transition r6 = r11.mCurrentTransition     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 != 0) goto L_0x0117
            boolean r6 = r4.mIsAbstract     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 != 0) goto L_0x0117
            r11.mCurrentTransition = r4     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.support.constraint.motion.TouchResponse r6 = r4.mTouchResponse     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x0117
            android.support.constraint.motion.MotionScene$Transition r6 = r11.mCurrentTransition     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.support.constraint.motion.TouchResponse r6 = r6.mTouchResponse     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            boolean r7 = r11.mRtl     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.setRTL(r7)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x0117:
            boolean r6 = r4.mIsAbstract     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 == 0) goto L_0x014b
            int r6 = r4.mConstraintSetEnd     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            if (r6 != r8) goto L_0x0126
            r11.mDefaultTransition = r4     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x012b
        L_0x0126:
            java.util.ArrayList<android.support.constraint.motion.MotionScene$Transition> r6 = r11.mAbstractTransitionList     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.add(r4)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x012b:
            java.util.ArrayList<android.support.constraint.motion.MotionScene$Transition> r6 = r11.mTransitionList     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.remove(r4)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x0131:
            r11.parseMotionSceneTags(r12, r1)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            goto L_0x014b
        L_0x0135:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r6.<init>()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r7 = "WARNING UNKNOWN ATTRIBUTE "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.StringBuilder r6 = r6.append(r3)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            java.lang.String r6 = r6.toString()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            android.util.Log.v(r9, r6)     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
        L_0x014b:
            goto L_0x0152
        L_0x014c:
            java.lang.String r6 = r1.getName()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r2 = r6
        L_0x0152:
            int r6 = r1.next()     // Catch:{ XmlPullParserException -> 0x015f, IOException -> 0x015a }
            r5 = r6
            goto L_0x000f
        L_0x0159:
            goto L_0x0163
        L_0x015a:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0164
        L_0x015f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0163:
        L_0x0164:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.MotionScene.load(android.content.Context, int):void");
    }

    private void parseMotionSceneTags(Context context, XmlPullParser parser) {
        TypedArray a = context.obtainStyledAttributes(Xml.asAttributeSet(parser), R.styleable.MotionScene);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.MotionScene_defaultDuration) {
                this.mDefaultDuration = a.getInt(attr, this.mDefaultDuration);
            } else if (attr == R.styleable.MotionScene_layoutDuringTransition) {
                this.mLayoutDuringTransition = a.getInteger(attr, 0);
            }
        }
        a.recycle();
    }

    private int getId(Context context, String idString) {
        int id = -1;
        if (idString.contains("/")) {
            id = context.getResources().getIdentifier(idString.substring(idString.indexOf(47) + 1), "id", context.getPackageName());
            if (this.DEBUG_DESKTOP) {
                System.out.println("id getMap res = " + id);
            }
        }
        if (id != -1) {
            return id;
        }
        if (idString != null && idString.length() > 1) {
            return Integer.parseInt(idString.substring(1));
        }
        Log.e(TAG, "error in parsing id");
        return id;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0050, code lost:
        if (r8.equals("deriveConstraintsFrom") != false) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseConstraintSet(android.content.Context r14, org.xmlpull.v1.XmlPullParser r15) {
        /*
            r13 = this;
            android.support.constraint.ConstraintSet r0 = new android.support.constraint.ConstraintSet
            r0.<init>()
            r1 = 0
            r0.setForceId(r1)
            int r2 = r15.getAttributeCount()
            r3 = -1
            r4 = -1
            r5 = 0
        L_0x0010:
            r6 = 1
            r7 = -1
            if (r5 >= r2) goto L_0x0072
            java.lang.String r8 = r15.getAttributeName(r5)
            java.lang.String r9 = r15.getAttributeValue(r5)
            boolean r10 = r13.DEBUG_DESKTOP
            if (r10 == 0) goto L_0x0038
            java.io.PrintStream r10 = java.lang.System.out
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "id string = "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r9)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
        L_0x0038:
            int r10 = r8.hashCode()
            switch(r10) {
                case -1496482599: goto L_0x004a;
                case 3355: goto L_0x0040;
                default: goto L_0x003f;
            }
        L_0x003f:
            goto L_0x0053
        L_0x0040:
            java.lang.String r6 = "id"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x003f
            r6 = r1
            goto L_0x0054
        L_0x004a:
            java.lang.String r10 = "deriveConstraintsFrom"
            boolean r10 = r8.equals(r10)
            if (r10 == 0) goto L_0x003f
            goto L_0x0054
        L_0x0053:
            r6 = r7
        L_0x0054:
            switch(r6) {
                case 0: goto L_0x005d;
                case 1: goto L_0x0058;
                default: goto L_0x0057;
            }
        L_0x0057:
            goto L_0x006f
        L_0x0058:
            int r4 = r13.getId(r14, r9)
            goto L_0x006f
        L_0x005d:
            int r3 = r13.getId(r14, r9)
            java.util.HashMap<java.lang.String, java.lang.Integer> r6 = r13.mConstraintSetIdMap
            java.lang.String r7 = stripID(r9)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)
            r6.put(r7, r10)
        L_0x006f:
            int r5 = r5 + 1
            goto L_0x0010
        L_0x0072:
            if (r3 == r7) goto L_0x008c
            android.support.constraint.motion.MotionLayout r1 = r13.mMotionLayout
            int r1 = r1.mDebugPath
            if (r1 == 0) goto L_0x007d
            r0.setValidateOnParse(r6)
        L_0x007d:
            r0.load((android.content.Context) r14, (org.xmlpull.v1.XmlPullParser) r15)
            if (r4 == r7) goto L_0x0087
            android.util.SparseIntArray r1 = r13.mDeriveMap
            r1.put(r3, r4)
        L_0x0087:
            android.util.SparseArray<android.support.constraint.ConstraintSet> r1 = r13.mConstraintSetMap
            r1.put(r3, r0)
        L_0x008c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.motion.MotionScene.parseConstraintSet(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    public ConstraintSet getConstraintSet(Context context, String id) {
        if (this.DEBUG_DESKTOP) {
            System.out.println("id " + id);
            System.out.println("size " + this.mConstraintSetMap.size());
        }
        for (int i = 0; i < this.mConstraintSetMap.size(); i++) {
            int key = this.mConstraintSetMap.keyAt(i);
            String IdAsString = context.getResources().getResourceName(key);
            if (this.DEBUG_DESKTOP) {
                System.out.println("Id for <" + i + "> is <" + IdAsString + "> looking for <" + id + ">");
            }
            if (id.equals(IdAsString)) {
                return this.mConstraintSetMap.get(key);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ConstraintSet getConstraintSet(int id) {
        return getConstraintSet(id, -1, -1);
    }

    /* access modifiers changed from: package-private */
    public ConstraintSet getConstraintSet(int id, int width, int height) {
        int cid;
        if (this.DEBUG_DESKTOP) {
            System.out.println("id " + id);
            System.out.println("size " + this.mConstraintSetMap.size());
        }
        StateSet stateSet = this.mStateSet;
        if (!(stateSet == null || (cid = stateSet.stateGetConstraintID(id, width, height)) == -1)) {
            id = cid;
        }
        if (this.mConstraintSetMap.get(id) != null) {
            return this.mConstraintSetMap.get(id);
        }
        Log.e(TAG, "Warning could not find ConstraintSet id/" + Debug.getName(this.mMotionLayout.getContext(), id) + " In MotionScene");
        SparseArray<ConstraintSet> sparseArray = this.mConstraintSetMap;
        return sparseArray.get(sparseArray.keyAt(0));
    }

    public void setConstraintSet(int id, ConstraintSet set) {
        this.mConstraintSetMap.put(id, set);
    }

    public void getKeyFrames(MotionController motionController) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            Transition transition2 = this.mDefaultTransition;
            if (transition2 != null) {
                Iterator it = transition2.mKeyFramesList.iterator();
                while (it.hasNext()) {
                    ((KeyFrames) it.next()).addFrames(motionController);
                }
                return;
            }
            return;
        }
        Iterator it2 = transition.mKeyFramesList.iterator();
        while (it2.hasNext()) {
            ((KeyFrames) it2.next()).addFrames(motionController);
        }
    }

    /* access modifiers changed from: package-private */
    public Key getKeyFrame(Context context, int type, int target, int position) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return null;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            KeyFrames keyFrames = (KeyFrames) it.next();
            Iterator<Integer> it2 = keyFrames.getKeys().iterator();
            while (true) {
                if (it2.hasNext()) {
                    Integer integer = it2.next();
                    if (target == integer.intValue()) {
                        Iterator<Key> it3 = keyFrames.getKeyFramesForView(integer.intValue()).iterator();
                        while (it3.hasNext()) {
                            Key key = it3.next();
                            if (key.mFramePosition == position && key.mType == type) {
                                return key;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getTransitionDirection(int stateId) {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (it.next().mConstraintSetStart == stateId) {
                return 0;
            }
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public boolean hasKeyFramePosition(View view, int position) {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return false;
        }
        Iterator it = transition.mKeyFramesList.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().mFramePosition == position) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setKeyframe(View view, int position, String name, Object value) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            Iterator it = transition.mKeyFramesList.iterator();
            while (it.hasNext()) {
                Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
                while (it2.hasNext()) {
                    if (it2.next().mFramePosition == position) {
                        float v = 0.0f;
                        if (value != null) {
                            v = ((Float) value).floatValue();
                        }
                        if (v == 0.0f) {
                        }
                        name.equalsIgnoreCase("app:PerpendicularPath_percent");
                    }
                }
            }
        }
    }

    public float getPathPercent(View view, int position) {
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public boolean supportTouch() {
        Iterator<Transition> it = this.mTransitionList.iterator();
        while (it.hasNext()) {
            if (it.next().mTouchResponse != null) {
                return true;
            }
        }
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void processTouchEvent(MotionEvent event, int currentState, MotionLayout motionLayout) {
        MotionLayout.MotionTracker motionTracker;
        MotionEvent motionEvent;
        RectF cache = new RectF();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = this.mMotionLayout.obtainVelocityTracker();
        }
        this.mVelocityTracker.addMovement(event);
        if (currentState != -1) {
            boolean z = false;
            switch (event.getAction()) {
                case 0:
                    this.mLastTouchX = event.getRawX();
                    this.mLastTouchY = event.getRawY();
                    this.mLastTouchDown = event;
                    if (this.mCurrentTransition.mTouchResponse != null) {
                        RectF region = this.mCurrentTransition.mTouchResponse.getLimitBoundsTo(this.mMotionLayout, cache);
                        if (region == null || region.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                            RectF region2 = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, cache);
                            if (region2 == null || region2.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                                this.mMotionOutsideRegion = false;
                            } else {
                                this.mMotionOutsideRegion = true;
                            }
                            this.mCurrentTransition.mTouchResponse.setDown(this.mLastTouchX, this.mLastTouchY);
                            return;
                        }
                        this.mLastTouchDown = null;
                        return;
                    }
                    return;
                case 2:
                    float dy = event.getRawY() - this.mLastTouchY;
                    float dx = event.getRawX() - this.mLastTouchX;
                    if ((((double) dx) != 0.0d || ((double) dy) != 0.0d) && (motionEvent = this.mLastTouchDown) != null) {
                        Transition transition = bestTransitionFor(currentState, dx, dy, motionEvent);
                        if (transition != null) {
                            motionLayout.setTransition(transition);
                            RectF region3 = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, cache);
                            if (region3 != null && !region3.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                                z = true;
                            }
                            this.mMotionOutsideRegion = z;
                            this.mCurrentTransition.mTouchResponse.setUpTouchEvent(this.mLastTouchX, this.mLastTouchY);
                            break;
                        }
                    } else {
                        return;
                    }
                    break;
            }
        }
        Transition transition2 = this.mCurrentTransition;
        if (!(transition2 == null || transition2.mTouchResponse == null || this.mMotionOutsideRegion)) {
            this.mCurrentTransition.mTouchResponse.processTouchEvent(event, this.mVelocityTracker, currentState, this);
        }
        this.mLastTouchX = event.getRawX();
        this.mLastTouchY = event.getRawY();
        if (event.getAction() == 1 && (motionTracker = this.mVelocityTracker) != null) {
            motionTracker.recycle();
            this.mVelocityTracker = null;
            if (motionLayout.mCurrentState != -1) {
                autoTransition(motionLayout, motionLayout.mCurrentState);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void processScrollMove(float dx, float dy) {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollMove(dx, dy);
        }
    }

    /* access modifiers changed from: package-private */
    public void processScrollUp(float dx, float dy) {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollUp(dx, dy);
        }
    }

    /* access modifiers changed from: package-private */
    public float getProgressDirection(float dx, float dy) {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getProgressDirection(dx, dy);
    }

    /* access modifiers changed from: package-private */
    public int getStartId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetStart;
    }

    /* access modifiers changed from: package-private */
    public int getEndId() {
        Transition transition = this.mCurrentTransition;
        if (transition == null) {
            return -1;
        }
        return transition.mConstraintSetEnd;
    }

    public Interpolator getInterpolator() {
        switch (this.mCurrentTransition.mDefaultInterpolator) {
            case -2:
                return AnimationUtils.loadInterpolator(this.mMotionLayout.getContext(), this.mCurrentTransition.mDefaultInterpolatorID);
            case -1:
                final Easing easing = Easing.getInterpolator(this.mCurrentTransition.mDefaultInterpolatorString);
                return new Interpolator() {
                    public float getInterpolation(float v) {
                        return (float) easing.get((double) v);
                    }
                };
            case 0:
                return new AccelerateDecelerateInterpolator();
            case 1:
                return new AccelerateInterpolator();
            case 2:
                return new DecelerateInterpolator();
            case 3:
                return null;
            case 4:
                return new AnticipateInterpolator();
            case 5:
                return new BounceInterpolator();
            default:
                return null;
        }
    }

    public int getDuration() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mDuration;
        }
        return this.mDefaultDuration;
    }

    public void setDuration(int duration) {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            transition.setDuration(duration);
        } else {
            this.mDefaultDuration = duration;
        }
    }

    public int gatPathMotionArc() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mPathMotionArc;
        }
        return -1;
    }

    public float getStaggered() {
        Transition transition = this.mCurrentTransition;
        if (transition != null) {
            return transition.mStagger;
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public float getMaxAcceleration() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getMaxAcceleration();
    }

    /* access modifiers changed from: package-private */
    public float getMaxVelocity() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return 0.0f;
        }
        return this.mCurrentTransition.mTouchResponse.getMaxVelocity();
    }

    /* access modifiers changed from: package-private */
    public void setupTouch() {
        Transition transition = this.mCurrentTransition;
        if (transition != null && transition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setupTouch();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getMoveWhenScrollAtTop() {
        Transition transition = this.mCurrentTransition;
        if (transition == null || transition.mTouchResponse == null) {
            return false;
        }
        return this.mCurrentTransition.mTouchResponse.getMoveWhenScrollAtTop();
    }

    /* access modifiers changed from: package-private */
    public void readFallback(MotionLayout motionLayout) {
        int i = 0;
        while (i < this.mConstraintSetMap.size()) {
            int key = this.mConstraintSetMap.keyAt(i);
            if (hasCycleDependency(key)) {
                Log.e(TAG, "Cannot be derived from yourself");
                return;
            } else {
                readConstraintChain(key);
                i++;
            }
        }
        for (int i2 = 0; i2 < this.mConstraintSetMap.size(); i2++) {
            this.mConstraintSetMap.valueAt(i2).readFallback((ConstraintLayout) motionLayout);
        }
    }

    private boolean hasCycleDependency(int key) {
        int derived = this.mDeriveMap.get(key);
        int len = this.mDeriveMap.size();
        while (derived > 0) {
            if (derived == key) {
                return true;
            }
            int len2 = len - 1;
            if (len < 0) {
                return true;
            }
            derived = this.mDeriveMap.get(derived);
            len = len2;
        }
        return false;
    }

    private void readConstraintChain(int key) {
        int derivedFromId = this.mDeriveMap.get(key);
        if (derivedFromId > 0) {
            readConstraintChain(this.mDeriveMap.get(key));
            ConstraintSet cs = this.mConstraintSetMap.get(key);
            ConstraintSet derivedFrom = this.mConstraintSetMap.get(derivedFromId);
            if (derivedFrom == null) {
                Log.e(TAG, "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.getName(this.mMotionLayout.getContext(), derivedFromId));
                return;
            }
            cs.readFallback(derivedFrom);
            this.mDeriveMap.put(key, -1);
        }
    }

    public static String stripID(String id) {
        if (id == null) {
            return "";
        }
        int index = id.indexOf(47);
        if (index < 0) {
            return id;
        }
        return id.substring(index + 1);
    }

    public int lookUpConstraintId(String id) {
        return this.mConstraintSetIdMap.get(id).intValue();
    }

    public String lookUpConstraintName(int id) {
        for (Map.Entry<String, Integer> entry : this.mConstraintSetIdMap.entrySet()) {
            if (entry.getValue().intValue() == id) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void disableAutoTransition(boolean disable) {
        this.mDisableAutoTransition = disable;
    }
}
