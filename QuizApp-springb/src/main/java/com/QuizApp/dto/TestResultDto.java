package com.QuizApp.dto;

import lombok.Data;

@Data
public class TestResultDto {

    private Long id;

    private int correctAnswers;

    private int totalQuestions;

    private double percentage;

    private String testName;

    private String userName;

}
