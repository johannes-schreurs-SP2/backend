package be.ehb.sp2.backend.repository;

import be.ehb.sp2.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

    Iterable<User> findUsersByNameContaining(String keyword);
    Iterable<User> findUsersBySurveysIsNotNull();

}
