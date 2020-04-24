package android.databinding;

public interface Observable {

    public static abstract class OnPropertyChangedCallback {
        public abstract void onPropertyChanged(Observable observable, int i);
    }

    void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback);

    void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback);
}
