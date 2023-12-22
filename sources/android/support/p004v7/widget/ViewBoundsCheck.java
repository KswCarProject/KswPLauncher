package android.support.p004v7.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v7.widget.ViewBoundsCheck */
/* loaded from: classes.dex */
class ViewBoundsCheck {
    static final int CVE_PVE_POS = 12;
    static final int CVE_PVS_POS = 8;
    static final int CVS_PVE_POS = 4;
    static final int CVS_PVS_POS = 0;

    /* renamed from: EQ */
    static final int f60EQ = 2;
    static final int FLAG_CVE_EQ_PVE = 8192;
    static final int FLAG_CVE_EQ_PVS = 512;
    static final int FLAG_CVE_GT_PVE = 4096;
    static final int FLAG_CVE_GT_PVS = 256;
    static final int FLAG_CVE_LT_PVE = 16384;
    static final int FLAG_CVE_LT_PVS = 1024;
    static final int FLAG_CVS_EQ_PVE = 32;
    static final int FLAG_CVS_EQ_PVS = 2;
    static final int FLAG_CVS_GT_PVE = 16;
    static final int FLAG_CVS_GT_PVS = 1;
    static final int FLAG_CVS_LT_PVE = 64;
    static final int FLAG_CVS_LT_PVS = 4;

    /* renamed from: GT */
    static final int f61GT = 1;

    /* renamed from: LT */
    static final int f62LT = 4;
    static final int MASK = 7;
    BoundFlags mBoundFlags = new BoundFlags();
    final Callback mCallback;

    /* renamed from: android.support.v7.widget.ViewBoundsCheck$Callback */
    /* loaded from: classes.dex */
    interface Callback {
        View getChildAt(int i);

        int getChildCount();

        int getChildEnd(View view);

        int getChildStart(View view);

        View getParent();

        int getParentEnd();

        int getParentStart();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.widget.ViewBoundsCheck$ViewBounds */
    /* loaded from: classes.dex */
    public @interface ViewBounds {
    }

    ViewBoundsCheck(Callback callback) {
        this.mCallback = callback;
    }

    /* renamed from: android.support.v7.widget.ViewBoundsCheck$BoundFlags */
    /* loaded from: classes.dex */
    static class BoundFlags {
        int mBoundFlags = 0;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        BoundFlags() {
        }

        void setBounds(int rvStart, int rvEnd, int childStart, int childEnd) {
            this.mRvStart = rvStart;
            this.mRvEnd = rvEnd;
            this.mChildStart = childStart;
            this.mChildEnd = childEnd;
        }

        void setFlags(int flags, int mask) {
            this.mBoundFlags = (this.mBoundFlags & (~mask)) | (flags & mask);
        }

        void addFlags(int flags) {
            this.mBoundFlags |= flags;
        }

        void resetFlags() {
            this.mBoundFlags = 0;
        }

        int compare(int x, int y) {
            if (x > y) {
                return 1;
            }
            if (x == y) {
                return 2;
            }
            return 4;
        }

        boolean boundsMatch() {
            int i = this.mBoundFlags;
            if ((i & 7) == 0 || (i & (compare(this.mChildStart, this.mRvStart) << 0)) != 0) {
                int i2 = this.mBoundFlags;
                if ((i2 & 112) == 0 || (i2 & (compare(this.mChildStart, this.mRvEnd) << 4)) != 0) {
                    int i3 = this.mBoundFlags;
                    if ((i3 & 1792) == 0 || (i3 & (compare(this.mChildEnd, this.mRvStart) << 8)) != 0) {
                        int i4 = this.mBoundFlags;
                        return (i4 & 28672) == 0 || (i4 & (compare(this.mChildEnd, this.mRvEnd) << 12)) != 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
    }

    View findOneViewWithinBoundFlags(int fromIndex, int toIndex, int preferredBoundFlags, int acceptableBoundFlags) {
        int start = this.mCallback.getParentStart();
        int end = this.mCallback.getParentEnd();
        int next = toIndex > fromIndex ? 1 : -1;
        View acceptableMatch = null;
        for (int i = fromIndex; i != toIndex; i += next) {
            View child = this.mCallback.getChildAt(i);
            int childStart = this.mCallback.getChildStart(child);
            int childEnd = this.mCallback.getChildEnd(child);
            this.mBoundFlags.setBounds(start, end, childStart, childEnd);
            if (preferredBoundFlags != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(preferredBoundFlags);
                if (this.mBoundFlags.boundsMatch()) {
                    return child;
                }
            }
            if (acceptableBoundFlags != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(acceptableBoundFlags);
                if (this.mBoundFlags.boundsMatch()) {
                    acceptableMatch = child;
                }
            }
        }
        return acceptableMatch;
    }

    boolean isViewWithinBoundFlags(View child, int boundsFlags) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(child), this.mCallback.getChildEnd(child));
        if (boundsFlags != 0) {
            this.mBoundFlags.resetFlags();
            this.mBoundFlags.addFlags(boundsFlags);
            return this.mBoundFlags.boundsMatch();
        }
        return false;
    }
}
