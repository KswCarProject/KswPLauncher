package android.databinding.adapters;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

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

    public static void setAnimateLayoutChanges(ViewGroup view, boolean animate) {
        if (animate) {
            view.setLayoutTransition(new LayoutTransition());
        } else {
            view.setLayoutTransition((LayoutTransition) null);
        }
    }

    public static void setListener(ViewGroup view, final OnChildViewAdded added, final OnChildViewRemoved removed) {
        if (added == null && removed == null) {
            view.setOnHierarchyChangeListener((ViewGroup.OnHierarchyChangeListener) null);
        } else {
            view.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                public void onChildViewAdded(View parent, View child) {
                    OnChildViewAdded onChildViewAdded = added;
                    if (onChildViewAdded != null) {
                        onChildViewAdded.onChildViewAdded(parent, child);
                    }
                }

                public void onChildViewRemoved(View parent, View child) {
                    OnChildViewRemoved onChildViewRemoved = removed;
                    if (onChildViewRemoved != null) {
                        onChildViewRemoved.onChildViewRemoved(parent, child);
                    }
                }
            });
        }
    }

    public static void setListener(ViewGroup view, final OnAnimationStart start, final OnAnimationEnd end, final OnAnimationRepeat repeat) {
        if (start == null && end == null && repeat == null) {
            view.setLayoutAnimationListener((Animation.AnimationListener) null);
        } else {
            view.setLayoutAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    OnAnimationStart onAnimationStart = start;
                    if (onAnimationStart != null) {
                        onAnimationStart.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    OnAnimationEnd onAnimationEnd = end;
                    if (onAnimationEnd != null) {
                        onAnimationEnd.onAnimationEnd(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    OnAnimationRepeat onAnimationRepeat = repeat;
                    if (onAnimationRepeat != null) {
                        onAnimationRepeat.onAnimationRepeat(animation);
                    }
                }
            });
        }
    }
}
