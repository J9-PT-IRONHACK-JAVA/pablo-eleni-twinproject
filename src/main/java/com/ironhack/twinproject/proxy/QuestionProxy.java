package com.ironhack.twinproject.proxy;

import com.ironhack.twinproject.dto.CategoryQuestionsList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "questions", url = "https://jservice.io/api/category/")
public interface QuestionProxy {

    @GetMapping("{category}")
    CategoryQuestionsList getQuestionListByCategory(@PathVariable int category) ;
}
