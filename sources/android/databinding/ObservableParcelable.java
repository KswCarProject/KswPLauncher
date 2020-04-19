package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableParcelable<T extends Parcelable> extends ObservableField<T> implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableParcelable> CREATOR = new Parcelable.Creator<ObservableParcelable>() {
        public ObservableParcelable createFromParcel(Parcel source) {
            return new ObservableParcelable(source.readParcelable(getClass().getClassLoader()));
        }

        public ObservableParcelable[] newArray(int size) {
            return new ObservableParcelable[size];
        }
    };
    static final long serialVersionUID = 1;

    public ObservableParcelable(T value) {
        super(value);
    }

    public ObservableParcelable() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) get(), 0);
    }
}
