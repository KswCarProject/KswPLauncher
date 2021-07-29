package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.RadioGroup;

public class RadioGroupBindingAdapter {
    public static void setCheckedButton(RadioGroup view, int id) {
        if (id != view.getCheckedRadioButtonId()) {
            view.check(id);
        }
    }

    public static void setListeners(RadioGroup view, final RadioGroup.OnCheckedChangeListener listener, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnCheckedChangeListener(listener);
        } else {
            view.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = listener;
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(group, checkedId);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
