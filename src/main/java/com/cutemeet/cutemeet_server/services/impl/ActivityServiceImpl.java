package com.cutemeet.cutemeet_server.services.impl;

import com.cutemeet.cutemeet_server.models.Activity;
import com.cutemeet.cutemeet_server.repository.ActivityRepository;
import com.cutemeet.cutemeet_server.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository repository;

    @Override
    public List<Activity> getAllActivities() {
        return repository.findAll();
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return repository.save(activity);
    }
}
