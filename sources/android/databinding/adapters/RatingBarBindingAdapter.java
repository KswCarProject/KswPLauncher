package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.RatingBar;

public class RatingBarBindingAdapter {
    public static void setRating(RatingBar view, float rating) {
        if (view.getRating() != rating) {
            view.setRating(rating);
        }
    }

    public static void setListeners(RatingBar view, final RatingBar.OnRatingBarChangeListener listener, final InverseBindingListener ratingChange) {
        if (ratingChange == null) {
            view.setOnRatingBarChangeListener(listener);
        } else {
            view.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    RatingBar.OnRatingBarChangeListener onRatingBarChangeListener = listener;
                    if (onRatingBarChangeListener != null) {
                        onRatingBarChangeListener.onRatingChanged(ratingBar, rating, fromUser);
                    }
                    ratingChange.onChange();
                }
            });
        }
    }
}
