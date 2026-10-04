package com.QuizApp.controller;

import com.QuizApp.entity.User;
import com.QuizApp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody User user) {
        if(userService.hasUserWithEmail(user.getEmail())) {
            return new ResponseEntity<>("Email already in use", HttpStatus.NOT_ACCEPTABLE);
        }
        User createdUser = userService.createUser(user);

        if(createdUser == null) {
            return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User dbUser = userService.login(user);

        if(dbUser == null)
            return new ResponseEntity<>("Wrong credentials", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

}
