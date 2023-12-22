package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ObservableShort extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableShort> CREATOR = new Parcelable.Creator<ObservableShort>() { // from class: android.databinding.ObservableShort.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObservableShort createFromParcel(Parcel source) {
            return new ObservableShort((short) source.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObservableShort[] newArray(int size) {
            return new ObservableShort[size];
        }
    };
    static final long serialVersionUID = 1;
    private short mValue;

    public ObservableShort(short value) {
        this.mValue = value;
    }

    public ObservableShort() {
    }

    public ObservableShort(Observable... dependencies) {
        super(dependencies);
    }

    public short get() {
        return this.mValue;
    }

    public void set(short value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue);
    }
}
