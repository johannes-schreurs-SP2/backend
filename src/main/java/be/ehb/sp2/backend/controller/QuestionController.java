package be.ehb.sp2.backend.controller;


import be.ehb.sp2.backend.error.QuestionNotFoundException;
import be.ehb.sp2.backend.model.Question;
import be.ehb.sp2.backend.model.Survey;
import be.ehb.sp2.backend.repository.QuestionRepository;
import be.ehb.sp2.backend.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping({"", ""})
    public Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @GetMapping("/search/survey/{id}")
    public Iterable<Question> getAllQuestionsBySurveyId(@PathVariable Long id) {
        return questionRepository.findAllBySurveyId(id);
    }

    @PostMapping("survey/{id}")
    public Question createQuestion(@PathVariable Long id, @RequestBody Question question) {
        Survey survey = surveyRepository.findById(id).get();
        Question newQuestion = new Question();
        newQuestion.setQuestion(question.getQuestion());
        newQuestion.setSurvey(survey);
        return questionRepository.save(newQuestion);
    }

    @PutMapping("")
    public Question updateQuestion(@RequestBody Question question) {
        Question questionToUpdate;
        questionToUpdate = questionRepository.findById(question.getId()).get();
        questionToUpdate.setQuestion(question.getQuestion());
        return questionRepository.save(questionToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        if(! questionRepository.existsById(id)){
            throw new QuestionNotFoundException(id);
        } else {
            questionRepository.deleteById(id);
        }
    }
}
