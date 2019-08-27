package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.model.Survey;
import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.repository.SurveyRepository;
import be.ehb.sp2.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DbController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SurveyRepository surveyRepository;


    @GetMapping("/init/users")
    public Iterable<User> initDbUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/init/surveys")
    public Iterable<Survey> initSurveys() {
        return surveyRepository.findAll();
    }


}
