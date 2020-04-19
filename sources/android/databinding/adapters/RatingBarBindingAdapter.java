package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.RatingBar;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:rating", type = RatingBar.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class RatingBarBindingAdapter {
    @BindingAdapter({"android:rating"})
    public static void setRating(RatingBar view, float rating) {
        if (view.getRating() != rating) {
            view.setRating(rating);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onRatingChanged", "android:ratingAttrChanged"})
    public static void setListeners(RatingBar view, final RatingBar.OnRatingBarChangeListener listener, final InverseBindingListener ratingChange) {
        if (ratingChange == null) {
            view.setOnRatingBarChangeListener(listener);
        } else {
            view.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (listener != null) {
                        listener.onRatingChanged(ratingBar, rating, fromUser);
                    }
                    ratingChange.onChange();
                }
            });
        }
    }
}
