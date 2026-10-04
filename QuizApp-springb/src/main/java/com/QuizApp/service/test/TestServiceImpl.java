package com.QuizApp.service.test;

import com.QuizApp.dto.*;
import com.QuizApp.entity.Question;
import com.QuizApp.entity.Test;
import com.QuizApp.entity.TestResult;
import com.QuizApp.entity.User;
import com.QuizApp.repository.QuestionRepository;
import com.QuizApp.repository.TestRepository;
import com.QuizApp.repository.TestResultRepository;
import com.QuizApp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserRepository userRepository;

    public TestDto createTest(TestDto dto) {
        Test test = new Test();

        test.setTitle(dto.getTitle());
        test.setTime(dto.getTime());
        test.setDescription(dto.getDescription());

        return testRepository.save(test).getDto();
    }

    public QuestionDto addQuestionInTest(QuestionDto dto) {
        Optional<Test> optionalTest = testRepository.findById(dto.getId());

        if(optionalTest.isPresent()) {
            Question question = new Question();

            question.setTest(optionalTest.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectOption(dto.getCorrectOption());

            return questionRepository.save(question).getDto();
        }
        throw new EntityNotFoundException("Test not found");
    }

    public List<TestDto> getAllTests() {
        return testRepository.findAll().stream().peek(
                test -> test.setTime(test.getQuestions().size() * test.getTime()))
                .toList().stream().map(Test::getDto)
                .collect(Collectors.toList());
    }

    public TestDetailsDto getAllQuestionsByTest(Long id) {
        Optional<Test> optionalTest = testRepository.findById(id);
        TestDetailsDto dto = new TestDetailsDto();

        if(optionalTest.isPresent()) {
            TestDto testDto = optionalTest.get().getDto();
            testDto.setTime(optionalTest.get().getTime() * optionalTest.get().getQuestions().size());

            dto.setTestDto(testDto);
            dto.setQuestions(optionalTest.get().getQuestions().stream().map(Question::getDto).toList());
            return dto;
        }
        return dto;
    }

    public TestResultDto submitTest(SubmitTestDto request) {
        if (request.getResponse() == null || request.getResponse().isEmpty()) {
            throw new IllegalArgumentException("No responses provided.");
        }

        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new EntityNotFoundException("Test not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        int correctAnswers = 0;
        for (QuestionResponse response : request.getResponse()) {
            Question question = questionRepository.findById(response.getQuestionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question not found"));

            if (question.getCorrectOption().equals(response.getSelectedOption())) {
                correctAnswers++;
            }
        }

        int totalQuestions = test.getQuestions().size();
        double percentage = ((double) correctAnswers / totalQuestions) * 100;

        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setUser(user);
        testResult.setTotalQuestions(totalQuestions);
        testResult.setCorrectAnswers(correctAnswers);
        testResult.setPercentage(percentage);

        return testResultRepository.save(testResult).getDto();
    }

    public List<TestResultDto> getAllTestResults() {
        return testResultRepository.findAll().stream().map(TestResult::getDto)
                .collect(Collectors.toList());
    }

    public List<TestResultDto> getAllTestResultsOfUser(Long userId) {
        return testResultRepository.findAllByUserId(userId).stream().map(TestResult::getDto)
                .collect(Collectors.toList());
    }


}
