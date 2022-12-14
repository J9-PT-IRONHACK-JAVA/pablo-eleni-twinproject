package com.ironhack.twinproject.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CategoryQuestionsList {

    private int questionId;
    private String title;
    private int clues_count;
    private List<Question> questionList;
}

