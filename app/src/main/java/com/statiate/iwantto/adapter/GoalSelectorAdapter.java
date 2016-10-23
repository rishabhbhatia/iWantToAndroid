package com.statiate.iwantto.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.statiate.iwantto.R;
import com.statiate.iwantto.models.GoalSelector;
import com.statiate.iwantto.utils.iWantConstants;

import java.util.ArrayList;

/**
 * Created by Rishabh Bhatia on 23/10/16.
 */

public class GoalSelectorAdapter extends RecyclerView.Adapter<GoalSelectorAdapter.GoalSelectorHolder> {

    private Context context;
    private ArrayList<GoalSelector> goalSelectors;

    public GoalSelectorAdapter(Context context, ArrayList<GoalSelector> goalSelectors) {
        this.context = context;
        this.goalSelectors = goalSelectors;
    }

    @Override
    public GoalSelectorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new GoalSelectorHolder(inflater.inflate(R.layout.row_goal_selector, parent, false));
    }

    @Override
    public void onBindViewHolder(GoalSelectorHolder holder, int position) {

        holder.clear();

        GoalSelector goalSelector = goalSelectors.get(position);
        holder.tvGoalSelectorLabel.setText(goalSelector.getName());
        Log.d(iWantConstants.TAG, "Pos: "+position+" & goal is: "+goalSelector.getName());
    }

    @Override
    public int getItemCount() {
        return goalSelectors.size();
    }

    class GoalSelectorHolder extends RecyclerView.ViewHolder {

        private TextView tvGoalSelectorLabel;

        public GoalSelectorHolder(View itemView) {
            super(itemView);
            tvGoalSelectorLabel = (TextView) itemView.findViewById(R.id.tv_row_goal_selector);
        }

        public void clear() {
            tvGoalSelectorLabel.setText("");
        }
    }
}
