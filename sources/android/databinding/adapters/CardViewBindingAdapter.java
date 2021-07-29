package android.databinding.adapters;

import android.support.v7.widget.CardView;

public class CardViewBindingAdapter {
    public static void setContentPadding(CardView view, int padding) {
        view.setContentPadding(padding, padding, padding, padding);
    }

    public static void setContentPaddingLeft(CardView view, int left) {
        view.setContentPadding(left, view.getContentPaddingTop(), view.getContentPaddingRight(), view.getContentPaddingBottom());
    }

    public static void setContentPaddingTop(CardView view, int top) {
        view.setContentPadding(view.getContentPaddingLeft(), top, view.getContentPaddingRight(), view.getContentPaddingBottom());
    }

    public static void setContentPaddingRight(CardView view, int right) {
        view.setContentPadding(view.getContentPaddingLeft(), view.getContentPaddingTop(), right, view.getContentPaddingBottom());
    }

    public static void setContentPaddingBottom(CardView view, int bottom) {
        view.setContentPadding(view.getContentPaddingLeft(), view.getContentPaddingTop(), view.getContentPaddingRight(), bottom);
    }
}
