package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import com.wits.ksw.C0899R;

/* loaded from: classes11.dex */
public class AnimationUtils {
    private static final String TAG = AnimationUtils.class.getSimpleName();

    public static void playAnimation(Context context, View view, boolean right) {
        AnimationSet animationSet;
        try {
            view.clearAnimation();
            if (right) {
                animationSet = (AnimationSet) android.view.animation.AnimationUtils.loadAnimation(context, C0899R.anim.bmw_id8_right_tanslate_animotion);
            } else {
                animationSet = (AnimationSet) android.view.animation.AnimationUtils.loadAnimation(context, C0899R.anim.bmw_id8_left_tanslate_animotion);
            }
            animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.wits.ksw.launcher.utils.AnimationUtils.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    Log.i(AnimationUtils.TAG, "onAnimationStart: ");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    Log.i(AnimationUtils.TAG, "onAnimationEnd: ");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                    Log.i(AnimationUtils.TAG, "onAnimationRepeat: ");
                }
            });
            view.startAnimation(animationSet);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}