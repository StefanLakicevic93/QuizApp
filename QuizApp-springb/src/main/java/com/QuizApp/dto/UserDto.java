package com.QuizApp.dto;

import com.QuizApp.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole role;

}
