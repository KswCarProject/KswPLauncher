package android.databinding.adapters;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

@BindingMethods({@BindingMethod(attribute = "android:alwaysDrawnWithCache", method = "setAlwaysDrawnWithCacheEnabled", type = ViewGroup.class), @BindingMethod(attribute = "android:animationCache", method = "setAnimationCacheEnabled", type = ViewGroup.class), @BindingMethod(attribute = "android:splitMotionEvents", method = "setMotionEventSplittingEnabled", type = ViewGroup.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ViewGroupBindingAdapter {

    public interface OnAnimationEnd {
        void onAnimationEnd(Animation animation);
    }

    public interface OnAnimationRepeat {
        void onAnimationRepeat(Animation animation);
    }

    public interface OnAnimationStart {
        void onAnimationStart(Animation animation);
    }

    public interface OnChildViewAdded {
        void onChildViewAdded(View view, View view2);
    }

    public interface OnChildViewRemoved {
        void onChildViewRemoved(View view, View view2);
    }

    @BindingAdapter({"android:animateLayoutChanges"})
    @TargetApi(11)
    public static void setAnimateLayoutChanges(ViewGroup view, boolean animate) {
        if (animate) {
            view.setLayoutTransition(new LayoutTransition());
        } else {
            view.setLayoutTransition((LayoutTransition) null);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onChildViewAdded", "android:onChildViewRemoved"})
    public static void setListener(ViewGroup view, final OnChildViewAdded added, final OnChildViewRemoved removed) {
        if (added == null && removed == null) {
            view.setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
        } else {
            view.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                public void onChildViewAdded(View parent, View child) {
                    if (added != null) {
                        added.onChildViewAdded(parent, child);
                    }
                }

                public void onChildViewRemoved(View parent, View child) {
                    if (removed != null) {
                        removed.onChildViewRemoved(parent, child);
                    }
                }
            });
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onAnimationStart", "android:onAnimationEnd", "android:onAnimationRepeat"})
    public static void setListener(ViewGroup view, final OnAnimationStart start, final OnAnimationEnd end, final OnAnimationRepeat repeat) {
        if (start == null && end == null && repeat == null) {
            view.setLayoutAnimationListener((Animation.AnimationListener) null);
        } else {
            view.setLayoutAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    if (start != null) {
                        start.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    if (end != null) {
                        end.onAnimationEnd(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    if (repeat != null) {
                        repeat.onAnimationRepeat(animation);
                    }
                }
            });
        }
    }
}
