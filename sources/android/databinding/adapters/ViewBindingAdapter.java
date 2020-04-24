package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.view.View;
import com.android.databinding.library.baseAdapters.R;

@BindingMethods({@BindingMethod(attribute = "android:backgroundTint", method = "setBackgroundTintList", type = View.class), @BindingMethod(attribute = "android:fadeScrollbars", method = "setScrollbarFadingEnabled", type = View.class), @BindingMethod(attribute = "android:getOutline", method = "setOutlineProvider", type = View.class), @BindingMethod(attribute = "android:nextFocusForward", method = "setNextFocusForwardId", type = View.class), @BindingMethod(attribute = "android:nextFocusLeft", method = "setNextFocusLeftId", type = View.class), @BindingMethod(attribute = "android:nextFocusRight", method = "setNextFocusRightId", type = View.class), @BindingMethod(attribute = "android:nextFocusUp", method = "setNextFocusUpId", type = View.class), @BindingMethod(attribute = "android:nextFocusDown", method = "setNextFocusDownId", type = View.class), @BindingMethod(attribute = "android:requiresFadingEdge", method = "setVerticalFadingEdgeEnabled", type = View.class), @BindingMethod(attribute = "android:scrollbarDefaultDelayBeforeFade", method = "setScrollBarDefaultDelayBeforeFade", type = View.class), @BindingMethod(attribute = "android:scrollbarFadeDuration", method = "setScrollBarFadeDuration", type = View.class), @BindingMethod(attribute = "android:scrollbarSize", method = "setScrollBarSize", type = View.class), @BindingMethod(attribute = "android:scrollbarStyle", method = "setScrollBarStyle", type = View.class), @BindingMethod(attribute = "android:transformPivotX", method = "setPivotX", type = View.class), @BindingMethod(attribute = "android:transformPivotY", method = "setPivotY", type = View.class), @BindingMethod(attribute = "android:onDrag", method = "setOnDragListener", type = View.class), @BindingMethod(attribute = "android:onClick", method = "setOnClickListener", type = View.class), @BindingMethod(attribute = "android:onApplyWindowInsets", method = "setOnApplyWindowInsetsListener", type = View.class), @BindingMethod(attribute = "android:onCreateContextMenu", method = "setOnCreateContextMenuListener", type = View.class), @BindingMethod(attribute = "android:onFocusChange", method = "setOnFocusChangeListener", type = View.class), @BindingMethod(attribute = "android:onGenericMotion", method = "setOnGenericMotionListener", type = View.class), @BindingMethod(attribute = "android:onHover", method = "setOnHoverListener", type = View.class), @BindingMethod(attribute = "android:onKey", method = "setOnKeyListener", type = View.class), @BindingMethod(attribute = "android:onLongClick", method = "setOnLongClickListener", type = View.class), @BindingMethod(attribute = "android:onSystemUiVisibilityChange", method = "setOnSystemUiVisibilityChangeListener", type = View.class), @BindingMethod(attribute = "android:onTouch", method = "setOnTouchListener", type = View.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ViewBindingAdapter {
    public static final int FADING_EDGE_HORIZONTAL = 1;
    public static final int FADING_EDGE_NONE = 0;
    public static final int FADING_EDGE_VERTICAL = 2;

    @TargetApi(12)
    public interface OnViewAttachedToWindow {
        void onViewAttachedToWindow(View view);
    }

    @TargetApi(12)
    public interface OnViewDetachedFromWindow {
        void onViewDetachedFromWindow(View view);
    }

    @BindingAdapter({"android:padding"})
    public static void setPadding(View view, float paddingFloat) {
        int padding = pixelsToDimensionPixelSize(paddingFloat);
        view.setPadding(padding, padding, padding, padding);
    }

    @BindingAdapter({"android:paddingBottom"})
    public static void setPaddingBottom(View view, float paddingFloat) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), pixelsToDimensionPixelSize(paddingFloat));
    }

    @BindingAdapter({"android:paddingEnd"})
    public static void setPaddingEnd(View view, float paddingFloat) {
        int padding = pixelsToDimensionPixelSize(paddingFloat);
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), padding, view.getPaddingBottom());
        } else {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), padding, view.getPaddingBottom());
        }
    }

    @BindingAdapter({"android:paddingLeft"})
    public static void setPaddingLeft(View view, float paddingFloat) {
        view.setPadding(pixelsToDimensionPixelSize(paddingFloat), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    @BindingAdapter({"android:paddingRight"})
    public static void setPaddingRight(View view, float paddingFloat) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), pixelsToDimensionPixelSize(paddingFloat), view.getPaddingBottom());
    }

    @BindingAdapter({"android:paddingStart"})
    public static void setPaddingStart(View view, float paddingFloat) {
        int padding = pixelsToDimensionPixelSize(paddingFloat);
        if (Build.VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(padding, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        } else {
            view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    @BindingAdapter({"android:paddingTop"})
    public static void setPaddingTop(View view, float paddingFloat) {
        view.setPadding(view.getPaddingLeft(), pixelsToDimensionPixelSize(paddingFloat), view.getPaddingRight(), view.getPaddingBottom());
    }

    @BindingAdapter({"android:requiresFadingEdge"})
    public static void setRequiresFadingEdge(View view, int value) {
        boolean horizontal = false;
        boolean vertical = (value & 2) != 0;
        if ((value & 1) != 0) {
            horizontal = true;
        }
        view.setVerticalFadingEdgeEnabled(vertical);
        view.setHorizontalFadingEdgeEnabled(horizontal);
    }

    @BindingAdapter({"android:onClickListener", "android:clickable"})
    public static void setClickListener(View view, View.OnClickListener clickListener, boolean clickable) {
        view.setOnClickListener(clickListener);
        view.setClickable(clickable);
    }

    @BindingAdapter({"android:onClick", "android:clickable"})
    public static void setOnClick(View view, View.OnClickListener clickListener, boolean clickable) {
        view.setOnClickListener(clickListener);
        view.setClickable(clickable);
    }

    @BindingAdapter({"android:onLongClickListener", "android:longClickable"})
    public static void setOnLongClickListener(View view, View.OnLongClickListener clickListener, boolean clickable) {
        view.setOnLongClickListener(clickListener);
        view.setLongClickable(clickable);
    }

    @BindingAdapter({"android:onLongClick", "android:longClickable"})
    public static void setOnLongClick(View view, View.OnLongClickListener clickListener, boolean clickable) {
        view.setOnLongClickListener(clickListener);
        view.setLongClickable(clickable);
    }

    @BindingAdapter(requireAll = false, value = {"android:onViewDetachedFromWindow", "android:onViewAttachedToWindow"})
    public static void setOnAttachStateChangeListener(View view, final OnViewDetachedFromWindow detach, final OnViewAttachedToWindow attach) {
        View.OnAttachStateChangeListener newListener;
        if (detach == null && attach == null) {
            newListener = null;
        } else {
            newListener = new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View v) {
                    if (attach != null) {
                        attach.onViewAttachedToWindow(v);
                    }
                }

                public void onViewDetachedFromWindow(View v) {
                    if (detach != null) {
                        detach.onViewDetachedFromWindow(v);
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

    @BindingAdapter({"android:onLayoutChange"})
    public static void setOnLayoutChangeListener(View view, View.OnLayoutChangeListener oldValue, View.OnLayoutChangeListener newValue) {
        if (oldValue != null) {
            view.removeOnLayoutChangeListener(oldValue);
        }
        if (newValue != null) {
            view.addOnLayoutChangeListener(newValue);
        }
    }

    @BindingAdapter({"android:background"})
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
