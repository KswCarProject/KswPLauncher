package android.databinding.adapters;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import com.android.databinding.library.baseAdapters.R;

public class ViewBindingAdapter {
    public static final int FADING_EDGE_HORIZONTAL = 1;
    public static final int FADING_EDGE_NONE = 0;
    public static final int FADING_EDGE_VERTICAL = 2;

    public interface OnViewAttachedToWindow {
        void onViewAttachedToWindow(View view);
    }

    public interface OnViewDetachedFromWindow {
        void onViewDetachedFromWindow(View view);
    }

    public static void setPadding(View view, float paddingFloat) {
        int padding = pixelsToDimensionPixelSize(paddingFloat);
        view.setPadding(padding, padding, padding, padding);
    }

    public static void setPaddingBottom(View view, float paddingFloat) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), pixelsToDimensionPixelSize(paddingFloat));
    }

    public static void setPaddingEnd(View view, float paddingFloat) {
        int padding = pixelsToDimensionPixelSize(paddingFloat);
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), padding, view.getPaddingBottom());
        } else {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), padding, view.getPaddingBottom());
        }
    }

    public static void setPaddingLeft(View view, float paddingFloat) {
        view.setPadding(pixelsToDimensionPixelSize(paddingFloat), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPaddingRight(View view, float paddingFloat) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), pixelsToDimensionPixelSize(paddingFloat), view.getPaddingBottom());
    }

    public static void setPaddingStart(View view, float paddingFloat) {
        int padding = pixelsToDimensionPixelSize(paddingFloat);
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(padding, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        } else {
            view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setPaddingTop(View view, float paddingFloat) {
        view.setPadding(view.getPaddingLeft(), pixelsToDimensionPixelSize(paddingFloat), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setRequiresFadingEdge(View view, int value) {
        boolean horizontal = false;
        boolean vertical = (value & 2) != 0;
        if ((value & 1) != 0) {
            horizontal = true;
        }
        view.setVerticalFadingEdgeEnabled(vertical);
        view.setHorizontalFadingEdgeEnabled(horizontal);
    }

    public static void setClickListener(View view, View.OnClickListener clickListener, boolean clickable) {
        view.setOnClickListener(clickListener);
        view.setClickable(clickable);
    }

    public static void setOnClick(View view, View.OnClickListener clickListener, boolean clickable) {
        view.setOnClickListener(clickListener);
        view.setClickable(clickable);
    }

    public static void setOnLongClickListener(View view, View.OnLongClickListener clickListener, boolean clickable) {
        view.setOnLongClickListener(clickListener);
        view.setLongClickable(clickable);
    }

    public static void setOnLongClick(View view, View.OnLongClickListener clickListener, boolean clickable) {
        view.setOnLongClickListener(clickListener);
        view.setLongClickable(clickable);
    }

    public static void setOnAttachStateChangeListener(View view, final OnViewDetachedFromWindow detach, final OnViewAttachedToWindow attach) {
        View.OnAttachStateChangeListener newListener;
        if (detach == null && attach == null) {
            newListener = null;
        } else {
            newListener = new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View v) {
                    OnViewAttachedToWindow onViewAttachedToWindow = attach;
                    if (onViewAttachedToWindow != null) {
                        onViewAttachedToWindow.onViewAttachedToWindow(v);
                    }
                }

                public void onViewDetachedFromWindow(View v) {
                    OnViewDetachedFromWindow onViewDetachedFromWindow = detach;
                    if (onViewDetachedFromWindow != null) {
                        onViewDetachedFromWindow.onViewDetachedFromWindow(v);
                    }
                }
            };
        }
        View.OnAttachStateChangeListener oldListener = (View.OnAttachStateChangeListener) ListenerUtil.trackListener(view, newListener, R.id.onAttachStateChangeListener);
        if (oldListener != null) {
            view.removeOnAttachStateChangeListener(oldListener);
        }
        if (newListener != null) {
            view.addOnAttachStateChangeListener(newListener);
        }
    }

    public static void setOnLayoutChangeListener(View view, View.OnLayoutChangeListener oldValue, View.OnLayoutChangeListener newValue) {
        if (oldValue != null) {
            view.removeOnLayoutChangeListener(oldValue);
        }
        if (newValue != null) {
            view.addOnLayoutChangeListener(newValue);
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    private static int pixelsToDimensionPixelSize(float pixels) {
        int result = (int) (0.5f + pixels);
        if (result != 0) {
            return result;
        }
        if (pixels == 0.0f) {
            return 0;
        }
        if (pixels > 0.0f) {
            return 1;
        }
        return -1;
    }
}
