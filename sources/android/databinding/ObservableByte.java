package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableByte extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableByte> CREATOR = new Parcelable.Creator<ObservableByte>() {
        public ObservableByte createFromParcel(Parcel source) {
            return new ObservableByte(source.readByte());
        }

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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.mValue);
    }
}
