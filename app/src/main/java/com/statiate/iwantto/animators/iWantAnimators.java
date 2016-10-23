package com.statiate.iwantto.animators;

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
}
