package com.cutemeet.cutemeet_server.controller;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.models.QuestionnaireData;
import com.cutemeet.cutemeet_server.services.MyUserService;
import com.cutemeet.cutemeet_server.services.QuestionnairesService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questionnaires")
@AllArgsConstructor
public class QuestionnairesController {
    private final QuestionnairesService questionnairesService;

    // age
    @GetMapping("/find_byAge")
    public List<QuestionnaireData> findPeopleByAge(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return questionnairesService.findByAge(authentication.getName(), 1);
    }

    @GetMapping("/find_byAgeInRange")
    public List<QuestionnaireData> findPeopleByAgeInRange(@RequestParam int range){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return questionnairesService.findByAge(authentication.getName(), range);
    }

    // education place
    @GetMapping("/find_byEducationPlace")
    public List<QuestionnaireData> findPeopleByEducationPlace(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return questionnairesService.findByEducationPlace(authentication.getName());
    }

    // tags
    @GetMapping("/find_byTagsJaccardSimilarity")
    public List<QuestionnaireData> findPeopleByTags(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return questionnairesService.findByTagsJaccardSimilarity(authentication.getName());
    }

    @GetMapping("/find_byTags")
    public List<QuestionnaireData> findPeopleByTags(@RequestParam String tagsLine){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return questionnairesService.findByTags(tagsLine, authentication.getName());
    }

    // tg link
    @GetMapping("/find_byTgLink")
    public List<QuestionnaireData> findPersonByTgLink(@RequestParam String tgLink){
        return List.of(questionnairesService.findByTgLink(tgLink));
    }

    // username
    @GetMapping("/find_byUsername")
    public List<QuestionnaireData> findPersonByUsername(@RequestParam String username){
        return List.of(questionnairesService.findByUsername(username));
    }
}
