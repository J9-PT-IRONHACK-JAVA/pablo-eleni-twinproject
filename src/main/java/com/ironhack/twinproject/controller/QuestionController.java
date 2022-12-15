package com.ironhack.twinproject.controller;

import com.ironhack.twinproject.dto.CategoryQuestionsList;
import com.ironhack.twinproject.proxy.QuestionProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionProxy questionProxy;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryQuestionsList getQuestionsList(int id){
            return questionProxy.getQuestionListByCategory(id);
        }
}
