package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ConstraintAnchor {
    private static final boolean ALLOW_BINARY = false;
    private static final int UNSET_GONE_MARGIN = -1;
    private HashSet<ConstraintAnchor> mDependents = null;
    int mGoneMargin = -1;
    public int mMargin = 0;
    public final ConstraintWidget mOwner;
    SolverVariable mSolverVariable;
    public ConstraintAnchor mTarget;
    public final Type mType;

    public enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public boolean hasDependents() {
        HashSet<ConstraintAnchor> hashSet = this.mDependents;
        if (hashSet != null && hashSet.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasCenteredDependents() {
        HashSet<ConstraintAnchor> hashSet = this.mDependents;
        if (hashSet == null) {
            return false;
        }
        Iterator<ConstraintAnchor> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().getOpposite().isConnected()) {
                return true;
            }
        }
        return false;
    }

    public void copyFrom(ConstraintAnchor source, HashMap<ConstraintWidget, ConstraintWidget> map) {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.mTarget;
        if (!(constraintAnchor == null || (hashSet = constraintAnchor.mDependents) == null)) {
            hashSet.remove(this);
        }
        ConstraintAnchor constraintAnchor2 = source.mTarget;
        if (constraintAnchor2 != null) {
            this.mTarget = map.get(source.mTarget.mOwner).getAnchor(constraintAnchor2.getType());
        } else {
            this.mTarget = null;
        }
        ConstraintAnchor constraintAnchor3 = this.mTarget;
        if (constraintAnchor3 != null) {
            if (constraintAnchor3.mDependents == null) {
                constraintAnchor3.mDependents = new HashSet<>();
            }
            this.mTarget.mDependents.add(this);
        }
        this.mMargin = source.mMargin;
        this.mGoneMargin = source.mGoneMargin;
    }

    public ConstraintAnchor(ConstraintWidget owner, Type type) {
        this.mOwner = owner;
        this.mType = type;
    }

    public SolverVariable getSolverVariable() {
        return this.mSolverVariable;
    }

    public void resetSolverVariable(Cache cache) {
        SolverVariable solverVariable = this.mSolverVariable;
        if (solverVariable == null) {
            this.mSolverVariable = new SolverVariable(SolverVariable.Type.UNRESTRICTED, (String) null);
        } else {
            solverVariable.reset();
        }
    }

    public ConstraintWidget getOwner() {
        return this.mOwner;
    }

    public Type getType() {
        return this.mType;
    }

    public int getMargin() {
        ConstraintAnchor constraintAnchor;
        if (this.mOwner.getVisibility() == 8) {
            return 0;
        }
        if (this.mGoneMargin <= -1 || (constraintAnchor = this.mTarget) == null || constraintAnchor.mOwner.getVisibility() != 8) {
            return this.mMargin;
        }
        return this.mGoneMargin;
    }

    public ConstraintAnchor getTarget() {
        return this.mTarget;
    }

    public void reset() {
        HashSet<ConstraintAnchor> hashSet;
        ConstraintAnchor constraintAnchor = this.mTarget;
        if (!(constraintAnchor == null || (hashSet = constraintAnchor.mDependents) == null)) {
            hashSet.remove(this);
        }
        this.mTarget = null;
        this.mMargin = 0;
        this.mGoneMargin = -1;
    }

    public boolean connect(ConstraintAnchor toAnchor, int margin, int goneMargin, boolean forceConnection) {
        if (toAnchor == null) {
            reset();
            return true;
        } else if (!forceConnection && !isValidConnection(toAnchor)) {
            return false;
        } else {
            this.mTarget = toAnchor;
            if (toAnchor.mDependents == null) {
                toAnchor.mDependents = new HashSet<>();
            }
            this.mTarget.mDependents.add(this);
            if (margin > 0) {
                this.mMargin = margin;
            } else {
                this.mMargin = 0;
            }
            this.mGoneMargin = goneMargin;
            return true;
        }
    }

    public boolean connect(ConstraintAnchor toAnchor, int margin) {
        return connect(toAnchor, margin, -1, false);
    }

    public boolean isConnected() {
        return this.mTarget != null;
    }

    public boolean isValidConnection(ConstraintAnchor anchor) {
        boolean isCompatible = false;
        if (anchor == null) {
            return false;
        }
        Type target = anchor.getType();
        Type type = this.mType;
        if (target != type) {
            switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
                case 1:
                    if (target == Type.BASELINE || target == Type.CENTER_X || target == Type.CENTER_Y) {
                        return false;
                    }
                    return true;
                case 2:
                case 3:
                    boolean isCompatible2 = target == Type.LEFT || target == Type.RIGHT;
                    if (!(anchor.getOwner() instanceof Guideline)) {
                        return isCompatible2;
                    }
                    if (isCompatible2 || target == Type.CENTER_X) {
                        isCompatible = true;
                    }
                    return isCompatible;
                case 4:
                case 5:
                    boolean isCompatible3 = target == Type.TOP || target == Type.BOTTOM;
                    if (!(anchor.getOwner() instanceof Guideline)) {
                        return isCompatible3;
                    }
                    if (isCompatible3 || target == Type.CENTER_Y) {
                        isCompatible = true;
                    }
                    return isCompatible;
                case 6:
                case 7:
                case 8:
                case 9:
                    return false;
                default:
                    throw new AssertionError(this.mType.name());
            }
        } else if (type != Type.BASELINE || (anchor.getOwner().hasBaseline() && getOwner().hasBaseline())) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: android.support.constraint.solver.widgets.ConstraintAnchor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type = iArr;
            try {
                iArr[Type.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public boolean isSideAnchor() {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                throw new AssertionError(this.mType.name());
        }
    }

    public boolean isSimilarDimensionConnection(ConstraintAnchor anchor) {
        Type target = anchor.getType();
        if (target == this.mType) {
            return true;
        }
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
            case 1:
                if (target != Type.BASELINE) {
                    return true;
                }
                return false;
            case 2:
            case 3:
            case 7:
                if (target == Type.LEFT || target == Type.RIGHT || target == Type.CENTER_X) {
                    return true;
                }
                return false;
            case 4:
            case 5:
            case 6:
            case 8:
                if (target == Type.TOP || target == Type.BOTTOM || target == Type.CENTER_Y || target == Type.BASELINE) {
                    return true;
                }
                return false;
            case 9:
                return false;
            default:
                throw new AssertionError(this.mType.name());
        }
    }

    public void setMargin(int margin) {
        if (isConnected()) {
            this.mMargin = margin;
        }
    }

    public void setGoneMargin(int margin) {
        if (isConnected()) {
            this.mGoneMargin = margin;
        }
    }

    public boolean isVerticalAnchor() {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 7:
                return false;
            case 4:
            case 5:
            case 6:
            case 8:
            case 9:
                return true;
            default:
                throw new AssertionError(this.mType.name());
        }
    }

    public String toString() {
        return this.mOwner.getDebugName() + ":" + this.mType.toString();
    }

    public boolean isConnectionAllowed(ConstraintWidget target, ConstraintAnchor anchor) {
        return isConnectionAllowed(target);
    }

    public boolean isConnectionAllowed(ConstraintWidget target) {
        if (isConnectionToMe(target, new HashSet<>())) {
            return false;
        }
        ConstraintWidget parent = getOwner().getParent();
        if (parent == target || target.getParent() == parent) {
            return true;
        }
        return false;
    }

    private boolean isConnectionToMe(ConstraintWidget target, HashSet<ConstraintWidget> checked) {
        if (checked.contains(target)) {
            return false;
        }
        checked.add(target);
        if (target == getOwner()) {
            return true;
        }
        ArrayList<ConstraintAnchor> targetAnchors = target.getAnchors();
        int targetAnchorsSize = targetAnchors.size();
        for (int i = 0; i < targetAnchorsSize; i++) {
            ConstraintAnchor anchor = targetAnchors.get(i);
            if (anchor.isSimilarDimensionConnection(this) && anchor.isConnected() && isConnectionToMe(anchor.getTarget().getOwner(), checked)) {
                return true;
            }
        }
        return false;
    }

    public final ConstraintAnchor getOpposite() {
        switch (AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[this.mType.ordinal()]) {
            case 1:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            case 2:
                return this.mOwner.mRight;
            case 3:
                return this.mOwner.mLeft;
            case 4:
                return this.mOwner.mBottom;
            case 5:
                return this.mOwner.mTop;
            default:
                throw new AssertionError(this.mType.name());
        }
    }
}
