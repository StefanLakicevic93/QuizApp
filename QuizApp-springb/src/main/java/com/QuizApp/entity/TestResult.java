package com.QuizApp.entity;

import com.QuizApp.dto.TestDto;
import com.QuizApp.dto.TestResultDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "testresults")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int correctAnswers;

    private int totalQuestions;

    private double percentage;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TestResultDto getDto() {
        TestResultDto dto = new TestResultDto();

        dto.setId(id);
        dto.setTotalQuestions(totalQuestions);
        dto.setCorrectAnswers(correctAnswers);
        dto.setPercentage(percentage);
        dto.setTestName(test.getTitle());
        dto.setUserName(user.getName());

        return dto;
    }

}
