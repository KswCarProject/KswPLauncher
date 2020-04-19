package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableInt extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableInt> CREATOR = new Parcelable.Creator<ObservableInt>() {
        public ObservableInt createFromParcel(Parcel source) {
            return new ObservableInt(source.readInt());
        }

        public ObservableInt[] newArray(int size) {
            return new ObservableInt[size];
        }
    };
    static final long serialVersionUID = 1;
    private int mValue;

    public ObservableInt(int value) {
        this.mValue = value;
    }

    public ObservableInt() {
    }

    public ObservableInt(Observable... dependencies) {
        super(dependencies);
    }

    public int get() {
        return this.mValue;
    }

    public void set(int value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue);
    }
}
