package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.error.UserNotFoundException;
import be.ehb.sp2.backend.model.Survey;
import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping({"", "/"})
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/{id}/surveys")
    public Iterable<Survey> getUserSurveys(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)).getSurveys();
    }

    @GetMapping("/search/name/{keyword}")
    public Iterable<User> getUserByName(@PathVariable String keyword) {
        return userRepository.findUsersByNameContaining(keyword);
    }

    @GetMapping("/search/email/{keyword}")
    public User getUserByEmail(@PathVariable String keyword) {
        return userRepository.findUserByEmailContaining(keyword);
    }

    @PostMapping({"", "/"})
    public User createUser(@RequestBody User user){
        user.setId(null);
        return userRepository.save(user);
    }

    @PutMapping("")
    public User updateUser(@RequestBody User user) {
        if (! userRepository.existsById(user.getId()) )
            throw new UserNotFoundException(user.getId());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        if(! userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
