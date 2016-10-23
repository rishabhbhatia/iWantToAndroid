package com.statiate.iwantto.ui.activities;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.statiate.iwantto.R;
import com.statiate.iwantto.adapter.GoalSelectorAdapter;
import com.statiate.iwantto.animators.iWantAnimators;
import com.statiate.iwantto.models.GoalSelector;
import com.statiate.iwantto.utils.iWantConstants;
import com.statiate.iwantto.utils.iWantUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoalSelectorActivity extends AppCompatActivity {

    @BindView(R.id.tv_goal_selector_goal_count)
    TextView tvGoalSelectorGoalCount;
    @BindView(R.id.tv_goal_selector_goal_label)
    TextView tvGoalSelectorGoalLabel;
    @BindView(R.id.ll_goal_selector_goal_count_holder)
    LinearLayout llGoalSelectorGoalCountHolder;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_selector);
        ButterKnife.bind(this);

//        animateGoalCounts();

        setupGoalSelectorRecyclerView();
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
            }
        });

        updateGoalSelectorUI(goalSelectorAdapter.getGoal(0));
    }

    private void animateGoalCounts() {
        int endNumber = iWantUtils.generateRandomNumber(0, 100);
        Log.d(iWantConstants.TAG, "animating to " + endNumber);
        iWantAnimators.animateCountOnTextView(tvGoalSelectorGoalCount, Integer.
                parseInt(tvGoalSelectorGoalCount.getText().toString()), endNumber, 1000);
    }

    public void updateGoalSelectorUI(GoalSelector goalSelector)
    {
        Glide.with(GoalSelectorActivity.this).load(goalSelector.getImageUrl()).asBitmap().fitCenter().into(ivGoalSelector);
        tvGoalSelectorGoalCount.setText(goalSelector.getGoalCount()+"");
        Log.d(iWantConstants.TAG, "hello hello beauti "+goalSelector.getGoalCount()+ " & image is "+goalSelector.getImageUrl());

    }

    @OnClick({R.id.ll_goal_selector_goal_count_holder, R.id.rl_goal_selector_main, R.id.bt_goal_selector_do_it})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_goal_selector_goal_count_holder:
                break;
            case R.id.rl_goal_selector_main:
                break;
            case R.id.bt_goal_selector_do_it:
                break;
        }
    }

}
