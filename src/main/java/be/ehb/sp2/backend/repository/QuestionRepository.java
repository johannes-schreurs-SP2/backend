package be.ehb.sp2.backend.repository;

import be.ehb.sp2.backend.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    public Iterable<Question> findAllBySurveyId(Long id);
}
