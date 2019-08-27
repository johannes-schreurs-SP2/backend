package be.ehb.sp2.backend.repository;

import be.ehb.sp2.backend.model.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    public Iterable<Answer> findAnswersByQuestionId(Long id);
}
