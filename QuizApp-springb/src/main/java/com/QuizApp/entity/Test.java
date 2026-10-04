package com.QuizApp.entity;

import com.QuizApp.dto.TestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long time;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    public TestDto getDto() {
        TestDto dto = new TestDto();
        dto.setId(id);
        dto.setTitle(title);
        dto.setTime(time);
        dto.setDescription(description);

        return dto;
    }
}
