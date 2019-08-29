package be.ehb.sp2.backend.repository;

import be.ehb.sp2.backend.model.Survey;
import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository<Survey, Long> {

    public Iterable<Survey> findSurveysByNameContaining(String keyword);

    public Iterable<Survey> findAllByOrderByIdAsc();

}
