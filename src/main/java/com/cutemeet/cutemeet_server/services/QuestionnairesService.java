package com.cutemeet.cutemeet_server.services;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.models.QuestionnaireData;
import com.cutemeet.cutemeet_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionnairesService {
    private UserRepository repository;

    public List<QuestionnaireData> findByAge(String username, int range){
        Optional<MyUser> ouser = repository.findUserByUserName(username);

        if(ouser.isEmpty()) return null;
        MyUser user = ouser.get();
        int age = user.calculateAge();

        return repository.findAll()
                .stream()
                .filter(u -> Math.abs(u.calculateAge() - age) <= range && !u.getUserName().equals(username))
                .map(MyUser::getQuestionnaireData)
                .collect(Collectors.toList());
    }

    public List<QuestionnaireData> findByEducationPlace(String username){
        Optional<MyUser> ouser = repository.findUserByUserName(username);

        if(ouser.isEmpty()) return null;
        MyUser user = ouser.get();
        String epn = getDigitsFromString(user.getAccountData().getEducationPlace());

        if(epn.isEmpty()) return null;

        return repository.findAll()
                .stream()
                .filter(u -> getDigitsFromString(u.getAccountData().getEducationPlace()).equals(epn) && !u.getUserName().equals(username))
                .map(MyUser::getQuestionnaireData)
                .collect(Collectors.toList());
    }

    public QuestionnaireData findByTgLink(String tgLink){
        tgLink = tgLink.replace("@", "");
        String finalTgLink = tgLink;
        return repository.findAll()
                .stream()
                .filter(u -> {
                    String utgl = u.getAccountData().getTgLink().replace("@", "");
                    return utgl.equals(finalTgLink);
                })
                .findFirst()
                .map(MyUser::getQuestionnaireData)
                .orElse(null);
    }

    public QuestionnaireData findByUsername(String username){
        return repository.findUserByUserName(username)
                .map(MyUser::getQuestionnaireData)
                .orElse(null);
    }

    public List<QuestionnaireData> findByTags(String tagsLine, String username){
        tagsLine = tagsLine.trim();
        tagsLine = tagsLine.toLowerCase();
        List<String> userTags = List.of(tagsLine.split("\\s*,\\s*"));

        return repository.findAll()
                .stream()
                .filter(u -> {
                    List<String> tags = List.of(u.getAccountData().getTags().toLowerCase().split(", "));
                    return tags.containsAll(userTags) && !u.getUserName().equals(username);
                })
                .map(MyUser::getQuestionnaireData)
                .collect(Collectors.toList());
    }

    public List<QuestionnaireData> findByTagsJaccardSimilarity(String username){
        Optional<MyUser> ouser = repository.findUserByUserName(username);

        if(ouser.isEmpty()) return null;
        MyUser user = ouser.get();
        String tagsLine = user.getAccountData().getTags();
        tagsLine = tagsLine.trim();
        tagsLine = tagsLine.toLowerCase();
        List<String> userTags = List.of(tagsLine.split("\\s*,\\s*"));

        return repository.findAll()
                .stream()
                .filter(u -> {
                    List<String> tags = List.of(u.getAccountData().getTags().toLowerCase().split(", "));
                    return calculateJaccardSimilarity(userTags, tags) > 0.5 && !u.getUserName().equals(username);
                })
                .map(MyUser::getQuestionnaireData)
                .collect(Collectors.toList());
    }

    //
    // help methods
    //

    public static double calculateJaccardSimilarity(List<String> list1, List<String> list2) {
        int intersectionSize = 0;
        for (String tag : list1) {
            if (list2.contains(tag)) {
                intersectionSize++;
            }
        }

        int unionSize = list1.size() + list2.size() - intersectionSize;
        if (unionSize == 0) {
            return 0; // Защита от деления на ноль
        }

        return (double) intersectionSize / unionSize;
    }

    private String getDigitsFromString(String line){
        StringBuilder digits = new StringBuilder();
        for(char c : line.toCharArray()) {
            if(Character.isDigit(c))
                digits.append(c);
        }
        return digits.toString();
    }
}
