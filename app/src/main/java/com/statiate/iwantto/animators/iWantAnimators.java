package com.statiate.iwantto.animators;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by Rishabh Bhatia on 23/10/16.
 */

public class iWantAnimators {

    public static void animateCountOnTextView(final TextView view, int startCount, int endCount, int duration)
    {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(startCount, endCount);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setText(String.valueOf(animation.getAnimatedValue()));
//                animation.setRepeatCount(5);  //test animation
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(duration);
        animator.start();
    }

    public static void scaleViewAnimation(View v, float startScale, float endScale)
    {
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        v.startAnimation(anim);
    }
}
