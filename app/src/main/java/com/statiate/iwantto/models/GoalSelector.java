package com.statiate.iwantto.models;

/**
 * Created by Rishabh Bhatia on 23/10/16.
 */

public class GoalSelector {

    private String name;
    private String imageUrl;
    private int goalCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGoalCount() {
        return goalCount;
    }

    public void setGoalCount(int goalCount) {
        this.goalCount = goalCount;
    }
}
