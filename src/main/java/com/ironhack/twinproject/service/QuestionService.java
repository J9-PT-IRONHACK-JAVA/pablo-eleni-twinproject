package com.ironhack.twinproject.service;

import com.ironhack.twinproject.controller.QuestionController;
import com.ironhack.twinproject.dto.CategoryQuestionsList;
import com.ironhack.twinproject.dto.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionController questionController;

    public int getCluesCount(int categoryId) {
        return questionController.getQuestionsList(categoryId).getClues_count();
    }

    public int getRandomNumber(int numberOfClues) {
        return (int) (Math.random() * (numberOfClues) )+ 1;
    }

    //Devuelve una Question con los campos (questionId, answer,question,value)
    public List<Question> getQuestion(int categoryId) {
        int numberOfQuestions = getCluesCount(categoryId);
        return questionController.getQuestionsList(categoryId).getClues();
    }

}
