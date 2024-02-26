package com.cutemeet.cutemeet_server.controller;

import com.cutemeet.cutemeet_server.models.Activity;
import com.cutemeet.cutemeet_server.services.ActivityService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/save")
    public String saveActivity(@RequestBody Activity activity){
        service.saveActivity(activity);
        return "Activity saved!";
    }
}
