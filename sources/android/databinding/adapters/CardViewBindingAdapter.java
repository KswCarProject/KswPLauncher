package android.databinding.adapters;

import android.support.p004v7.widget.CardView;

/* loaded from: classes.dex */
public class CardViewBindingAdapter {
    public static void setContentPadding(CardView view, int padding) {
        view.setContentPadding(padding, padding, padding, padding);
    }

    public static void setContentPaddingLeft(CardView view, int left) {
        int top = view.getContentPaddingTop();
        int right = view.getContentPaddingRight();
        int bottom = view.getContentPaddingBottom();
        view.setContentPadding(left, top, right, bottom);
    }

    public static void setContentPaddingTop(CardView view, int top) {
        int left = view.getContentPaddingLeft();
        int right = view.getContentPaddingRight();
        int bottom = view.getContentPaddingBottom();
        view.setContentPadding(left, top, right, bottom);
    }

    public static void setContentPaddingRight(CardView view, int right) {
        int left = view.getContentPaddingLeft();
        int top = view.getContentPaddingTop();
        int bottom = view.getContentPaddingBottom();
        view.setContentPadding(left, top, right, bottom);
    }

    public static void setContentPaddingBottom(CardView view, int bottom) {
        int left = view.getContentPaddingLeft();
        int top = view.getContentPaddingTop();
        int right = view.getContentPaddingRight();
        view.setContentPadding(left, top, right, bottom);
    }
}
