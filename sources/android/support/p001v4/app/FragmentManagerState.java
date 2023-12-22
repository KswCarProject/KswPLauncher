package android.support.p001v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: FragmentManager.java */
/* renamed from: android.support.v4.app.FragmentManagerState */
/* loaded from: classes.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new Parcelable.Creator<FragmentManagerState>() { // from class: android.support.v4.app.FragmentManagerState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState createFromParcel(Parcel in) {
            return new FragmentManagerState(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState[] newArray(int size) {
            return new FragmentManagerState[size];
        }
    };
    FragmentState[] mActive;
    int[] mAdded;
    BackStackState[] mBackStack;
    int mNextFragmentIndex;
    int mPrimaryNavActiveIndex;

    public FragmentManagerState() {
        this.mPrimaryNavActiveIndex = -1;
    }

    public FragmentManagerState(Parcel in) {
        this.mPrimaryNavActiveIndex = -1;
        this.mActive = (FragmentState[]) in.createTypedArray(FragmentState.CREATOR);
        this.mAdded = in.createIntArray();
        this.mBackStack = (BackStackState[]) in.createTypedArray(BackStackState.CREATOR);
        this.mPrimaryNavActiveIndex = in.readInt();
        this.mNextFragmentIndex = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.mActive, flags);
        dest.writeIntArray(this.mAdded);
        dest.writeTypedArray(this.mBackStack, flags);
        dest.writeInt(this.mPrimaryNavActiveIndex);
        dest.writeInt(this.mNextFragmentIndex);
    }
}
