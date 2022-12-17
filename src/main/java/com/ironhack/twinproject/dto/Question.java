package com.ironhack.twinproject.dto;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String answer;
    private String question;
    private int value;
    private int category_id;
}
