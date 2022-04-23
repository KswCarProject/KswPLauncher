package android.databinding.adapters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

public class ImageViewBindingAdapter {
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI((Uri) null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }
}
