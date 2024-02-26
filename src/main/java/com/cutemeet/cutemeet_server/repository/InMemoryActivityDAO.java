package com.cutemeet.cutemeet_server.repository;

import com.cutemeet.cutemeet_server.models.Activity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryActivityDAO {
    private final List<Activity> activities = new ArrayList<Activity>();

    public List<Activity> getAllActivities() {
        return activities;
    }

    public Activity saveActivity(Activity activity) {
        activities.add(activity);
        return activity;
    }
}

