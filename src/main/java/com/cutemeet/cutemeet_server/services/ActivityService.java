package com.cutemeet.cutemeet_server.services;

import com.cutemeet.cutemeet_server.models.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ActivityService {
    List<Activity> getAllActivities();
    Activity saveActivity(Activity activity);
}
