package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableBoolean extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableBoolean> CREATOR = new Parcelable.Creator<ObservableBoolean>() {
        public ObservableBoolean createFromParcel(Parcel source) {
            boolean z = true;
            if (source.readInt() != 1) {
                z = false;
            }
            return new ObservableBoolean(z);
        }

        public ObservableBoolean[] newArray(int size) {
            return new ObservableBoolean[size];
        }
    };
    static final long serialVersionUID = 1;
    private boolean mValue;

    public ObservableBoolean(boolean value) {
        this.mValue = value;
    }

    public ObservableBoolean() {
    }

    public ObservableBoolean(Observable... dependencies) {
        super(dependencies);
    }

    public boolean get() {
        return this.mValue;
    }

    public void set(boolean value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mValue ? 1 : 0);
    }
}
