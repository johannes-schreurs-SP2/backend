package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DbController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/init/users")
    public Iterable<User> initDbUsers() {
        return userRepository.findAll();
    }


}
