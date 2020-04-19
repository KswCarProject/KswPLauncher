package com.bumptech.glide.request.transition;

import android.view.View;
import com.bumptech.glide.request.transition.Transition;

public class ViewPropertyTransition<R> implements Transition<R> {
    private final Animator animator;

    public interface Animator {
        void animate(View view);
    }

    public ViewPropertyTransition(Animator animator2) {
        this.animator = animator2;
    }

    public boolean transition(R r, Transition.ViewAdapter adapter) {
        if (adapter.getView() == null) {
            return false;
        }
        this.animator.animate(adapter.getView());
        return false;
    }
}
