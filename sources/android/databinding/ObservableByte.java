package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ObservableByte extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableByte> CREATOR = new Parcelable.Creator<ObservableByte>() { // from class: android.databinding.ObservableByte.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObservableByte createFromParcel(Parcel source) {
            return new ObservableByte(source.readByte());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObservableByte[] newArray(int size) {
            return new ObservableByte[size];
        }
    };
    static final long serialVersionUID = 1;
    private byte mValue;

    public ObservableByte(byte value) {
        this.mValue = value;
    }

    public ObservableByte() {
    }

    public ObservableByte(Observable... dependencies) {
        super(dependencies);
    }

    public byte get() {
        return this.mValue;
    }

    public void set(byte value) {
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
        dest.writeByte(this.mValue);
    }
}
