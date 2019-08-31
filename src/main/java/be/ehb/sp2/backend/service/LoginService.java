package be.ehb.sp2.backend.service;

import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService {


    private static String authToken = null;
    private static String userId = null;

    @Autowired
    UserRepository userRepository;

    public List<String> login (String email, String password) {

        User userToCheck = userRepository.findUserByEmailContaining(email);
        List<String> stuffToSend = new ArrayList<>();

        if (userToCheck != null) {
            if (email.equals(userToCheck.getEmail()) && password.equals(userToCheck.getPassword())) {
                this.authToken = UUID.randomUUID().toString();
                this.userId = userToCheck.getId().toString();
                stuffToSend.add(this.userId);
                stuffToSend.add(this.authToken);
                return stuffToSend;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public void logout () {
        this.authToken = null;
        this.userId = null;
    }

    public boolean isLoggedIn (String authToken) {
        return (this.authToken != null && authToken.equals(this.authToken))? true : false;
    }
}
