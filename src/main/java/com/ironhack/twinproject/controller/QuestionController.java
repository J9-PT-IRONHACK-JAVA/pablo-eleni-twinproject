package com.ironhack.twinproject.controller;

import com.ironhack.twinproject.dto.CategoryQuestionsList;
import com.ironhack.twinproject.proxy.QuestionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionProxy questionProxy;

    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CategoryQuestionsList getQuestionsList(@PathVariable int category){
        return questionProxy.getQuestionListByCategory(category);
    }
}