package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.VideoView;

@BindingMethods({@BindingMethod(attribute = "android:onCompletion", method = "setOnCompletionListener", type = VideoView.class), @BindingMethod(attribute = "android:onError", method = "setOnErrorListener", type = VideoView.class), @BindingMethod(attribute = "android:onInfo", method = "setOnInfoListener", type = VideoView.class), @BindingMethod(attribute = "android:onPrepared", method = "setOnPreparedListener", type = VideoView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class VideoViewBindingAdapter {
}
