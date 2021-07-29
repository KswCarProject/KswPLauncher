package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.CompoundButton;

public class CompoundButtonBindingAdapter {
    public static void setChecked(CompoundButton view, boolean checked) {
        if (view.isChecked() != checked) {
            view.setChecked(checked);
        }
    }

    public static void setListeners(CompoundButton view, final CompoundButton.OnCheckedChangeListener listener, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnCheckedChangeListener(listener);
        } else {
            view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = listener;
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
