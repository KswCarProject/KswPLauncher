package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;

public class ResolutionAnchor extends ResolutionNode {
    public static final int BARRIER_CONNECTION = 5;
    public static final int CENTER_CONNECTION = 2;
    public static final int CHAIN_CONNECTION = 4;
    public static final int DIRECT_CONNECTION = 1;
    public static final int MATCH_CONNECTION = 3;
    public static final int UNCONNECTED = 0;
    float computedValue;
    private ResolutionDimension dimension = null;
    private int dimensionMultiplier = 1;
    ConstraintAnchor myAnchor;
    float offset;
    private ResolutionAnchor opposite;
    private ResolutionDimension oppositeDimension = null;
    private int oppositeDimensionMultiplier = 1;
    private float oppositeOffset;
    float resolvedOffset;
    ResolutionAnchor resolvedTarget;
    ResolutionAnchor target;
    int type = 0;

    public ResolutionAnchor(ConstraintAnchor anchor) {
        this.myAnchor = anchor;
    }

    public void remove(ResolutionDimension resolutionDimension) {
        ResolutionDimension resolutionDimension2 = this.dimension;
        if (resolutionDimension2 == resolutionDimension) {
            this.dimension = null;
            this.offset = (float) this.dimensionMultiplier;
        } else if (resolutionDimension2 == this.oppositeDimension) {
            this.oppositeDimension = null;
            this.oppositeOffset = (float) this.oppositeDimensionMultiplier;
        }
        resolve();
    }

    public String toString() {
        if (this.state != 1) {
            return "{ " + this.myAnchor + " UNRESOLVED} type: " + sType(this.type);
        }
        if (this.resolvedTarget == this) {
            return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedOffset + "]  type: " + sType(this.type);
        }
        return "[" + this.myAnchor + ", RESOLVED: " + this.resolvedTarget + ":" + this.resolvedOffset + "] type: " + sType(this.type);
    }

    public void resolve(ResolutionAnchor target2, float offset2) {
        if (this.state == 0 || !(this.resolvedTarget == target2 || this.resolvedOffset == offset2)) {
            this.resolvedTarget = target2;
            this.resolvedOffset = offset2;
            if (this.state == 1) {
                invalidate();
            }
            didResolve();
        }
    }

    /* access modifiers changed from: package-private */
    public String sType(int type2) {
        if (type2 == 1) {
            return "DIRECT";
        }
        if (type2 == 2) {
            return "CENTER";
        }
        if (type2 == 3) {
            return "MATCH";
        }
        if (type2 == 4) {
            return "CHAIN";
        }
        if (type2 == 5) {
            return "BARRIER";
        }
        return "UNCONNECTED";
    }

    public void resolve() {
        ResolutionAnchor resolutionAnchor;
        ResolutionAnchor resolutionAnchor2;
        ResolutionAnchor resolutionAnchor3;
        ResolutionAnchor resolutionAnchor4;
        ResolutionAnchor resolutionAnchor5;
        ResolutionAnchor resolutionAnchor6;
        float distance;
        float distance2;
        float percent;
        ResolutionAnchor resolutionAnchor7;
        boolean isEndAnchor = true;
        if (this.state != 1 && this.type != 4) {
            ResolutionDimension resolutionDimension = this.dimension;
            if (resolutionDimension != null) {
                if (resolutionDimension.state == 1) {
                    this.offset = ((float) this.dimensionMultiplier) * this.dimension.value;
                } else {
                    return;
                }
            }
            ResolutionDimension resolutionDimension2 = this.oppositeDimension;
            if (resolutionDimension2 != null) {
                if (resolutionDimension2.state == 1) {
                    this.oppositeOffset = ((float) this.oppositeDimensionMultiplier) * this.oppositeDimension.value;
                } else {
                    return;
                }
            }
            if (this.type == 1 && ((resolutionAnchor7 = this.target) == null || resolutionAnchor7.state == 1)) {
                ResolutionAnchor resolutionAnchor8 = this.target;
                if (resolutionAnchor8 == null) {
                    this.resolvedTarget = this;
                    this.resolvedOffset = this.offset;
                } else {
                    this.resolvedTarget = resolutionAnchor8.resolvedTarget;
                    this.resolvedOffset = resolutionAnchor8.resolvedOffset + this.offset;
                }
                didResolve();
            } else if (this.type == 2 && (resolutionAnchor4 = this.target) != null && resolutionAnchor4.state == 1 && (resolutionAnchor5 = this.opposite) != null && (resolutionAnchor6 = resolutionAnchor5.target) != null && resolutionAnchor6.state == 1) {
                if (LinearSystem.getMetrics() != null) {
                    LinearSystem.getMetrics().centerConnectionResolved++;
                }
                this.resolvedTarget = this.target.resolvedTarget;
                ResolutionAnchor resolutionAnchor9 = this.opposite;
                resolutionAnchor9.resolvedTarget = resolutionAnchor9.target.resolvedTarget;
                if (!(this.myAnchor.mType == ConstraintAnchor.Type.RIGHT || this.myAnchor.mType == ConstraintAnchor.Type.BOTTOM)) {
                    isEndAnchor = false;
                }
                if (isEndAnchor) {
                    distance = this.target.resolvedOffset - this.opposite.target.resolvedOffset;
                } else {
                    distance = this.opposite.target.resolvedOffset - this.target.resolvedOffset;
                }
                if (this.myAnchor.mType == ConstraintAnchor.Type.LEFT || this.myAnchor.mType == ConstraintAnchor.Type.RIGHT) {
                    distance2 = distance - ((float) this.myAnchor.mOwner.getWidth());
                    percent = this.myAnchor.mOwner.mHorizontalBiasPercent;
                } else {
                    distance2 = distance - ((float) this.myAnchor.mOwner.getHeight());
                    percent = this.myAnchor.mOwner.mVerticalBiasPercent;
                }
                int margin = this.myAnchor.getMargin();
                int oppositeMargin = this.opposite.myAnchor.getMargin();
                if (this.myAnchor.getTarget() == this.opposite.myAnchor.getTarget()) {
                    percent = 0.5f;
                    margin = 0;
                    oppositeMargin = 0;
                }
                float distance3 = (distance2 - ((float) margin)) - ((float) oppositeMargin);
                if (isEndAnchor) {
                    ResolutionAnchor resolutionAnchor10 = this.opposite;
                    resolutionAnchor10.resolvedOffset = resolutionAnchor10.target.resolvedOffset + ((float) oppositeMargin) + (distance3 * percent);
                    this.resolvedOffset = (this.target.resolvedOffset - ((float) margin)) - ((1.0f - percent) * distance3);
                } else {
                    this.resolvedOffset = this.target.resolvedOffset + ((float) margin) + (distance3 * percent);
                    ResolutionAnchor resolutionAnchor11 = this.opposite;
                    resolutionAnchor11.resolvedOffset = (resolutionAnchor11.target.resolvedOffset - ((float) oppositeMargin)) - ((1.0f - percent) * distance3);
                }
                didResolve();
                this.opposite.didResolve();
            } else if (this.type == 3 && (resolutionAnchor = this.target) != null && resolutionAnchor.state == 1 && (resolutionAnchor2 = this.opposite) != null && (resolutionAnchor3 = resolutionAnchor2.target) != null && resolutionAnchor3.state == 1) {
                if (LinearSystem.getMetrics() != null) {
                    LinearSystem.getMetrics().matchConnectionResolved++;
                }
                ResolutionAnchor resolutionAnchor12 = this.target;
                this.resolvedTarget = resolutionAnchor12.resolvedTarget;
                ResolutionAnchor resolutionAnchor13 = this.opposite;
                ResolutionAnchor resolutionAnchor14 = resolutionAnchor13.target;
                resolutionAnchor13.resolvedTarget = resolutionAnchor14.resolvedTarget;
                this.resolvedOffset = resolutionAnchor12.resolvedOffset + this.offset;
                resolutionAnchor13.resolvedOffset = resolutionAnchor14.resolvedOffset + resolutionAnchor13.offset;
                didResolve();
                this.opposite.didResolve();
            } else if (this.type == 5) {
                this.myAnchor.mOwner.resolve();
            }
        }
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public void reset() {
        super.reset();
        this.target = null;
        this.offset = 0.0f;
        this.dimension = null;
        this.dimensionMultiplier = 1;
        this.oppositeDimension = null;
        this.oppositeDimensionMultiplier = 1;
        this.resolvedTarget = null;
        this.resolvedOffset = 0.0f;
        this.computedValue = 0.0f;
        this.opposite = null;
        this.oppositeOffset = 0.0f;
        this.type = 0;
    }

    public void update() {
        ConstraintAnchor targetAnchor = this.myAnchor.getTarget();
        if (targetAnchor != null) {
            if (targetAnchor.getTarget() == this.myAnchor) {
                this.type = 4;
                targetAnchor.getResolutionNode().type = 4;
            }
            int margin = this.myAnchor.getMargin();
            if (this.myAnchor.mType == ConstraintAnchor.Type.RIGHT || this.myAnchor.mType == ConstraintAnchor.Type.BOTTOM) {
                margin = -margin;
            }
            dependsOn(targetAnchor.getResolutionNode(), margin);
        }
    }

    public void dependsOn(int type2, ResolutionAnchor node, int offset2) {
        this.type = type2;
        this.target = node;
        this.offset = (float) offset2;
        node.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor node, int offset2) {
        this.target = node;
        this.offset = (float) offset2;
        node.addDependent(this);
    }

    public void dependsOn(ResolutionAnchor node, int multiplier, ResolutionDimension dimension2) {
        this.target = node;
        node.addDependent(this);
        this.dimension = dimension2;
        this.dimensionMultiplier = multiplier;
        dimension2.addDependent(this);
    }

    public void setOpposite(ResolutionAnchor opposite2, float oppositeOffset2) {
        this.opposite = opposite2;
        this.oppositeOffset = oppositeOffset2;
    }

    public void setOpposite(ResolutionAnchor opposite2, int multiplier, ResolutionDimension dimension2) {
        this.opposite = opposite2;
        this.oppositeDimension = dimension2;
        this.oppositeDimensionMultiplier = multiplier;
    }

    /* access modifiers changed from: package-private */
    public void addResolvedValue(LinearSystem system) {
        SolverVariable sv = this.myAnchor.getSolverVariable();
        ResolutionAnchor resolutionAnchor = this.resolvedTarget;
        if (resolutionAnchor == null) {
            system.addEquality(sv, (int) (this.resolvedOffset + 0.5f));
        } else {
            system.addEquality(sv, system.createObjectVariable(resolutionAnchor.myAnchor), (int) (this.resolvedOffset + 0.5f), 6);
        }
    }

    public float getResolvedValue() {
        return this.resolvedOffset;
    }
}
