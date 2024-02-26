package com.cutemeet.cutemeet_server.services.impl;

import com.cutemeet.cutemeet_server.models.Activity;
import com.cutemeet.cutemeet_server.repository.InMemoryActivityDAO;
import com.cutemeet.cutemeet_server.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryActivityServiceImpl implements ActivityService {
    private final InMemoryActivityDAO repository;

    @Override
    public List<Activity> getAllActivities() {
        return repository.getAllActivities();
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return repository.saveActivity(activity);
    }
}
