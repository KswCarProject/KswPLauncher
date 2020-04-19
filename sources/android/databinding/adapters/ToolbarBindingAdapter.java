package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.Toolbar;

@BindingMethods({@BindingMethod(attribute = "android:onMenuItemClick", method = "setOnMenuItemClickListener", type = Toolbar.class), @BindingMethod(attribute = "android:onNavigationClick", method = "setNavigationOnClickListener", type = Toolbar.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ToolbarBindingAdapter {
}
