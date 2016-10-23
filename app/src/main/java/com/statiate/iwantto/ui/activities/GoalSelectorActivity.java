package com.statiate.iwantto.ui.activities;

import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.statiate.iwantto.R;
import com.statiate.iwantto.animators.iWantAnimators;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_selector);
        ButterKnife.bind(this);

        animateGoalCounts();
    }

    private void animateGoalCounts()
    {
        int endNumber = iWantUtils.generateRandomNumber(0, 100);
        Log.d(iWantConstants.TAG, "animating to "+endNumber);
        iWantAnimators.animateCountOnTextView(tvGoalSelectorGoalCount, Integer.
                parseInt(tvGoalSelectorGoalCount.getText().toString()), endNumber, 1000);
    }

    @OnClick({R.id.ll_goal_selector_goal_count_holder, R.id.rl_goal_selector_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_goal_selector_goal_count_holder:
                break;
            case R.id.rl_goal_selector_main:
                break;
        }
    }
}
