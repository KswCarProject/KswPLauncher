package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.CardView;

@BindingMethods({@BindingMethod(attribute = "cardCornerRadius", method = "setRadius", type = CardView.class), @BindingMethod(attribute = "cardMaxElevation", method = "setMaxCardElevation", type = CardView.class), @BindingMethod(attribute = "cardPreventCornerOverlap", method = "setPreventCornerOverlap", type = CardView.class), @BindingMethod(attribute = "cardUseCompatPadding", method = "setUseCompatPadding", type = CardView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class CardViewBindingAdapter {
    @BindingAdapter({"contentPadding"})
    public static void setContentPadding(CardView view, int padding) {
        view.setContentPadding(padding, padding, padding, padding);
    }

    @BindingAdapter({"contentPaddingLeft"})
    public static void setContentPaddingLeft(CardView view, int left) {
        view.setContentPadding(left, view.getContentPaddingTop(), view.getContentPaddingRight(), view.getContentPaddingBottom());
    }

    @BindingAdapter({"contentPaddingTop"})
    public static void setContentPaddingTop(CardView view, int top) {
        view.setContentPadding(view.getContentPaddingLeft(), top, view.getContentPaddingRight(), view.getContentPaddingBottom());
    }

    @BindingAdapter({"contentPaddingRight"})
    public static void setContentPaddingRight(CardView view, int right) {
        view.setContentPadding(view.getContentPaddingLeft(), view.getContentPaddingTop(), right, view.getContentPaddingBottom());
    }

    @BindingAdapter({"contentPaddingBottom"})
    public static void setContentPaddingBottom(CardView view, int bottom) {
        view.setContentPadding(view.getContentPaddingLeft(), view.getContentPaddingTop(), view.getContentPaddingRight(), bottom);
    }
}
