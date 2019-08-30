package be.ehb.sp2.backend.controller;

import be.ehb.sp2.backend.model.LoginRequest;
import be.ehb.sp2.backend.model.LoginResponse;
import be.ehb.sp2.backend.model.User;
import be.ehb.sp2.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest loginRequest) {
        List<String> a = new ArrayList<>();
        a = loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
        String id = a.get(0);
        String authToken = a.get(1);

        if(authToken != null) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserId(id);
            loginResponse.setAuthToken(authToken);
            return loginResponse;
        } else {
            throw new Error("User not found");
        }
    }

    @GetMapping("/logout")
    public void logout (@RequestHeader String authToken) {
        if (! loginService.isLoggedIn(authToken))
            throw new Error("Not logged in");

        loginService.logout();
    }
}
