package be.ehb.sp2.backend.repository;

import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.model.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {

    Iterable<UserAnswer> findAllByUserId(Long id);
    Iterable<UserAnswer> findAllByAnswerId(Long id);
    Iterable<UserAnswer> findAllBySurveyId(Long id);
    int countUserAnswersByAnswerIdAndIsAnsweredIsTrue(Long id);

}
