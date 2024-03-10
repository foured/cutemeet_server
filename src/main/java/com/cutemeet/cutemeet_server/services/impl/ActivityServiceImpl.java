package com.cutemeet.cutemeet_server.services.impl;

import com.cutemeet.cutemeet_server.models.Activity;
import com.cutemeet.cutemeet_server.repository.ActivityRepository;
import com.cutemeet.cutemeet_server.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public Activity saveActivity(Activity activity, String username)
    {
        activity.setUsername(username);
        return repository.save(activity);
    }

    @Override
    public List<Activity> findActivitiesByTags(String tagsLine) {
        tagsLine = tagsLine.trim();
        tagsLine = tagsLine.toLowerCase();
        List<String> userTags = List.of(tagsLine.split("\\s*,\\s*"));


        return repository.findAll()
                .stream()
                .filter(activity -> {
                    List<String> tags = List.of(activity.getTags().split(", "));
                    return tags.containsAll(userTags);
                })
                .collect(Collectors.toList());
    }
}
