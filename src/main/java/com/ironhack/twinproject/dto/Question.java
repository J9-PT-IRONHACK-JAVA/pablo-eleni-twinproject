package com.ironhack.twinproject.dto;

import lombok.Data;

@Data
public class Question {
    private int questionId;
    private String answer;
    private String question;
    private int value;
}
