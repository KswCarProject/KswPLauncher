package android.databinding;

import java.io.Serializable;

public class ObservableField<T> extends BaseObservableField implements Serializable {
    static final long serialVersionUID = 1;
    private T mValue;

    public ObservableField(T value) {
        this.mValue = value;
    }

    public ObservableField() {
    }

    public ObservableField(Observable... dependencies) {
        super(dependencies);
    }

    public T get() {
        return this.mValue;
    }

    public void set(T value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }
}
