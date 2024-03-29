package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.model.Answer;
import be.ehb.sp2.backend.model.Survey;
import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.model.UserAnswer;
import be.ehb.sp2.backend.repository.AnswerRepository;
import be.ehb.sp2.backend.repository.SurveyRepository;
import be.ehb.sp2.backend.repository.UserAnswerRepository;
import be.ehb.sp2.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/useranswers")
public class UserAnswerController {

    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    AnswerRepository answerRepository;


    @GetMapping({"", "/"})
    public Iterable<UserAnswer> getAllUserAnswers() {
        return userAnswerRepository.findAll();
    }

    @GetMapping("user/{id}")
    public Iterable<UserAnswer> getAnswersFromUser(@PathVariable Long id){
        return userAnswerRepository.findAllByUserId(id);
    }

    @GetMapping("survey/{id}")
    public Iterable<UserAnswer> getAnswersFromSurvey(@PathVariable Long id) {
        return userAnswerRepository.findAllBySurveyId(id);
    }

    @GetMapping("answer/{id}")
    public Iterable<UserAnswer> getAnswersFromAnswer(@PathVariable Long id) {
        return userAnswerRepository.findAllByAnswerId(id);
    }

    @GetMapping("count/answer/{id}")
    public int getCountFromAnswer(@PathVariable Long id) {
        return userAnswerRepository.countUserAnswersByAnswerIdAndIsAnsweredIsTrue(id);
    }


    @PostMapping({"", "/"})
    public void addAnswer(@RequestBody UserAnswer userAnswer) {

        userAnswer.setId(null);

        userAnswerRepository.save(userAnswer);
    }
}
