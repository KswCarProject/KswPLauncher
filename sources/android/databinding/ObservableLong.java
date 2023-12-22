package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class ObservableLong extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableLong> CREATOR = new Parcelable.Creator<ObservableLong>() { // from class: android.databinding.ObservableLong.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObservableLong createFromParcel(Parcel source) {
            return new ObservableLong(source.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObservableLong[] newArray(int size) {
            return new ObservableLong[size];
        }
    };
    static final long serialVersionUID = 1;
    private long mValue;

    public ObservableLong(long value) {
        this.mValue = value;
    }

    public ObservableLong() {
    }

    public ObservableLong(Observable... dependencies) {
        super(dependencies);
    }

    public long get() {
        return this.mValue;
    }

    public void set(long value) {
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
        dest.writeLong(this.mValue);
    }
}
