package com.cutemeet.cutemeet_server.controller;

import com.cutemeet.cutemeet_server.models.Activity;
import com.cutemeet.cutemeet_server.services.ActivityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
@AllArgsConstructor
public class ActivitiesController {
    private final ActivityService service;

    @GetMapping("/all")
    public List<Activity> getAllActivities(){
        return service.getAllActivities();
    }

    @GetMapping("/find_byTag")
    public List<Activity> findByTag(@RequestParam String tagsLine){
        return service.findActivitiesByTags(tagsLine);
    }

    @PostMapping("/save")
    public String saveActivity(@RequestBody Activity activity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String tagsLine = activity.getTags().trim();
        tagsLine = tagsLine.toLowerCase();
        String[] tags = tagsLine.split("\\s*,\\s*");
        activity.setTags(String.join(", ", tags));

        service.saveActivity(activity, username);
        return "Activity saved!";
    }
}
