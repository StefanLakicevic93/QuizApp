package com.QuizApp.service.test;

import com.QuizApp.dto.*;
import com.QuizApp.entity.Test;

import java.util.List;

public interface TestService {

    TestDto createTest(TestDto dto);

    public QuestionDto addQuestionInTest(QuestionDto dto);

    List<TestDto> getAllTests();

    TestDetailsDto getAllQuestionsByTest(Long id);

    TestResultDto submitTest(SubmitTestDto request);

    List<TestResultDto> getAllTestResults();

    List<TestResultDto> getAllTestResultsOfUser(Long userId);

}
