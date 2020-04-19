package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.widget.ImageView;

@BindingMethods({@BindingMethod(attribute = "android:tint", method = "setImageTintList", type = ImageView.class), @BindingMethod(attribute = "android:tintMode", method = "setImageTintMode", type = ImageView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ImageViewBindingAdapter {
    @BindingAdapter({"android:src"})
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI((Uri) null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter({"android:src"})
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }
}
