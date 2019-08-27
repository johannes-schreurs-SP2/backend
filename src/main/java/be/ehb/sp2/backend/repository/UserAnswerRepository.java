package be.ehb.sp2.backend.repository;

import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.model.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {

    public Iterable<UserAnswer> findAllByUserId(Long id);

    public Iterable<UserAnswer> findAllBySurveyId(Long id);
}
