package be.ehb.sp2.backend.controller;


import be.ehb.sp2.backend.error.QuestionNotFoundException;
import be.ehb.sp2.backend.model.Question;
import be.ehb.sp2.backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

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

    @PostMapping({"", "/"})
    public Question createQuestion(@RequestBody Question question) {
        question.setId(null);
        return questionRepository.save(question);
    }

    @PutMapping("")
    public Question updateQuestion(@RequestBody Question question) {
        if(! questionRepository.existsById(question.getId())) {
            throw new QuestionNotFoundException(question.getId());
        } else {
            return questionRepository.save(question);
        }
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
