package com.QuizApp.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubmitTestDto {

    private Long testId;

    private Long userId;

    private List<QuestionResponse> response = new ArrayList<>();

}

