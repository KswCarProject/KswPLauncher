package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableChar extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableChar> CREATOR = new Parcelable.Creator<ObservableChar>() {
        public ObservableChar createFromParcel(Parcel source) {
            return new ObservableChar((char) source.readInt());
        }

        public ObservableChar[] newArray(int size) {
            return new ObservableChar[size];
        }
    };
    static final long serialVersionUID = 1;
    private char mValue;

    public ObservableChar(char value) {
        this.mValue = value;
    }

    public ObservableChar() {
    }

    public ObservableChar(Observable... dependencies) {
        super(dependencies);
    }

    public char get() {
        return this.mValue;
    }

    public void set(char value) {
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
