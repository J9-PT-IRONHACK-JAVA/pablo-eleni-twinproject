package com.ironhack.twinproject.service;

import com.ironhack.twinproject.controller.QuestionController;
import com.ironhack.twinproject.dto.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionController questionController;

    public int getCluesCount (int categoryId) {
        return questionController.getQuestionsList(categoryId).getClues_count();
    }

    public int getRandomNumber (int numberOfClues) {
        return (int) (Math.random() * (numberOfClues) )+ 1;
    }

    //Devuelve una Question con los campos (questionId, answer,question,value)
    public Question getQuestion (int categoryId) {
        int numberOfQuestions = getCluesCount(categoryId);
        int randomNumber = getRandomNumber(numberOfQuestions);
        return questionController.getQuestionsList(categoryId).getQuestionList().get(randomNumber);
    }

}
