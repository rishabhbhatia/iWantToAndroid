package com.statiate.iwantto.ui.activities;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.nineoldandroids.animation.IntEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import com.statiate.iwantto.R;
import com.statiate.iwantto.adapter.GoalSelectorAdapter;
import com.statiate.iwantto.animators.iWantAnimators;
import com.statiate.iwantto.models.GoalSelector;
import com.statiate.iwantto.utils.iWantConstants;
import com.statiate.iwantto.utils.iWantUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class GoalSelectorActivity extends AppCompatActivity {

    @BindView(R.id.tv_goal_selector_goal_count)
    TextView tvGoalSelectorGoalCount;
    @BindView(R.id.tv_goal_selector_goal_label)
    TextView tvGoalSelectorGoalLabel;
    @BindView(R.id.rl_goal_selector_goal_count_holder)
    RelativeLayout rlGoalSelectorGoalCountHolder;
    @BindView(R.id.rl_goal_selector_main)
    PercentRelativeLayout rlGoalSelectorMain;
    @BindView(R.id.iv_goal_selector)
    ImageView ivGoalSelector;
    @BindView(R.id.rl_goal_selector_bg)
    RelativeLayout rlGoalSelectorBg;
    @BindView(R.id.iv_goal_selector_header_settings)
    ImageView ivGoalSelectorHeaderSettings;
    @BindView(R.id.iv_goal_selector_header_add)
    ImageView ivGoalSelectorHeaderAdd;
    @BindView(R.id.rl_goal_selector_header)
    RelativeLayout rlGoalSelectorHeader;
    @BindView(R.id.srv_goal_selector_goals)
    SuperRecyclerView srvGoalSelectorGoals;
    @BindView(R.id.ll_goal_selector_recycler)
    LinearLayout llGoalSelectorRecycler;
    @BindView(R.id.ll_goal_selector_count_main)
    LinearLayout llGoalSelectorCountMain;
    @BindView(R.id.bt_goal_selector_do_it)
    Button btGoalSelectorDoIt;
    @BindView(R.id.ll_goal_selector_footer)
    LinearLayout llGoalSelectorFooter;
    @BindView(R.id.rvp_goal_selector_goals)
    RecyclerViewPager rvpGoalSelectorGoals;
    @BindView(R.id.civ_count)
    CircleImageView civCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_goal_selector);
        ButterKnife.bind(this);

//        animateGoalCounts();

        setupGoalSelectorRecyclerView();
    }

    private void hideStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void showStatusBar() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void setupGoalSelectorRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GoalSelectorActivity.this, LinearLayoutManager.HORIZONTAL, false);
        final GoalSelectorAdapter goalSelectorAdapter = new GoalSelectorAdapter(GoalSelectorActivity.this, iWantUtils.generateRandomGoalSelectors(40));


//        srvGoalSelectorGoals.setLayoutManager(linearLayoutManager);
//        srvGoalSelectorGoals.setAdapter(goalSelectorAdapter);

        rvpGoalSelectorGoals.setLayoutManager(linearLayoutManager);
        rvpGoalSelectorGoals.setAdapter(goalSelectorAdapter);

        rvpGoalSelectorGoals.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int currentPos) {
                GoalSelector goalSelector = goalSelectorAdapter.getGoal(currentPos);
                updateGoalSelectorUI(goalSelector);
                showPageChangeAnimation();
            }
        });

        updateGoalSelectorUI(goalSelectorAdapter.getGoal(0));
    }

    private void showPageChangeAnimation() {

        YoYo.with(Techniques.Pulse)
                .duration(300)
                .playOn(findViewById(R.id.rl_goal_selector_main));
    }

    private void animateGoalCounts(int endNumber) {
        iWantAnimators.animateCountOnTextView(tvGoalSelectorGoalCount, Integer.
                parseInt(tvGoalSelectorGoalCount.getText().toString()), endNumber, 1000);
    }

    public void updateGoalSelectorUI(GoalSelector goalSelector) {

        animateGoalCounts(goalSelector.getGoalCount());
        Glide.with(GoalSelectorActivity.this).load(goalSelector.getImageUrl()).asBitmap().fitCenter().into(ivGoalSelector);

//        BounceInterpolator bounceInterpolator = new BounceInterpolator();
      /*  ValueAnimator.ofObject(new WidthEvaluator(llGoalSelectorCountMain), llGoalSelectorCountMain.getWidth(),
                iWantUtils.generateRandomNumber(50,250)).start();*/

        ValueAnimator anim = ValueAnimator.ofInt(civCount.getMeasuredWidth(),
                iWantUtils.generateRandomNumber(250, 400));
        anim.setInterpolator(new AnticipateOvershootInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = civCount.getLayoutParams();
                layoutParams.width = val;
                layoutParams.height = val;
                civCount.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(1000);
        anim.start();
    }

    private class WidthEvaluator extends IntEvaluator {

        private View v;

        public WidthEvaluator(View v) {
            this.v = v;
        }

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            int num = (Integer) super.evaluate(fraction, startValue, endValue);
            ViewGroup.LayoutParams params = v.getLayoutParams();
            params.width = num;
            v.setLayoutParams(params);
            return num;
        }
    }

    @OnClick({R.id.rl_goal_selector_main, R.id.bt_goal_selector_do_it})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_goal_selector_main:
                break;
            case R.id.bt_goal_selector_do_it:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        hideStatusBar();
        super.onDestroy();
    }
}
