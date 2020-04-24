package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.Spinner;

@BindingMethods({@BindingMethod(attribute = "android:popupBackground", method = "setPopupBackgroundDrawable", type = Spinner.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class SpinnerBindingAdapter {
}
