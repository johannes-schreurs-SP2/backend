package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.error.AnswerNotFoundException;
import be.ehb.sp2.backend.model.Answer;
import be.ehb.sp2.backend.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @GetMapping({"", "/"})
    public Iterable<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
    }

    @GetMapping("search/questions/{id}")
    public Iterable<Answer> getAllAnswersBasedOnQuestionId(@PathVariable Long id) {
        return answerRepository.findAnswersByQuestionId(id);
    }

    @PostMapping({"", "/"})
    public Answer createAnswer(@RequestBody Answer answer) {
        answer.setId(null);
        return answerRepository.save(answer);
    }

    @PutMapping({"", "/"})
    public Answer updateAnswer(@RequestBody Answer answer) {
        if(! answerRepository.existsById(answer.getId())) {
            throw new AnswerNotFoundException(answer.getId());
        } else {
            return answerRepository.save(answer);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        if(! answerRepository.existsById(id)) {
            throw new AnswerNotFoundException(id);
        } else {
            answerRepository.deleteById(id);
        }

    }
}
