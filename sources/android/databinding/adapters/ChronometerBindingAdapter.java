package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.Chronometer;

@BindingMethods({@BindingMethod(attribute = "android:onChronometerTick", method = "setOnChronometerTickListener", type = Chronometer.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ChronometerBindingAdapter {
}
