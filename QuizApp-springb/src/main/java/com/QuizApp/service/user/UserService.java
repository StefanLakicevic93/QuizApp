package com.QuizApp.service.user;

import com.QuizApp.entity.User;

public interface UserService {

    Boolean hasUserWithEmail(String email);

    User createUser(User user);

    User login(User user);

}
