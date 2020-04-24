package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.ZoomControls;

@BindingMethods({@BindingMethod(attribute = "android:onZoomIn", method = "setOnZoomInClickListener", type = ZoomControls.class), @BindingMethod(attribute = "android:onZoomOut", method = "setOnZoomOutClickListener", type = ZoomControls.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ZoomControlsBindingAdapter {
}
