package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.error.AnswerNotFoundException;
import be.ehb.sp2.backend.model.Answer;
import be.ehb.sp2.backend.model.Question;
import be.ehb.sp2.backend.repository.AnswerRepository;
import be.ehb.sp2.backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

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

    @PostMapping("question/{id}")
    public Answer createAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        Question question = questionRepository.findById(id).get();
        Answer newAnswer = new Answer();
        newAnswer.setAnswer(answer.getAnswer());
        newAnswer.setQuestion(question);
        return answerRepository.save(newAnswer);
    }

    @PutMapping({"", "/"})
    public Answer updateAnswer(@RequestBody Answer answer) {
        Answer answerToUpdate;
        answerToUpdate = answerRepository.findById(answer.getId()).get();
        answerToUpdate.setAnswer(answer.getAnswer());
        return answerRepository.save(answerToUpdate);
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
