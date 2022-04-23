package android.databinding.adapters;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;

public class SwitchCompatBindingAdapter {
    public static void setSwitchTextAppearance(SwitchCompat view, int value) {
        view.setSwitchTextAppearance((Context) null, value);
    }
}
