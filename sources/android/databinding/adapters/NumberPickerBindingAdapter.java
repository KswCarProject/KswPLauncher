package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.NumberPicker;

public class NumberPickerBindingAdapter {
    public static void setValue(NumberPicker view, int value) {
        if (view.getValue() != value) {
            view.setValue(value);
        }
    }

    public static void setListeners(NumberPicker view, final NumberPicker.OnValueChangeListener listener, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnValueChangedListener(listener);
        } else {
            view.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    NumberPicker.OnValueChangeListener onValueChangeListener = listener;
                    if (onValueChangeListener != null) {
                        onValueChangeListener.onValueChange(picker, oldVal, newVal);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
