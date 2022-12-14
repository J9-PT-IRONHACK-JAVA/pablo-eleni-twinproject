package com.ironhack.twinproject.proxy;


import com.ironhack.twinproject.dto.CategoryQuestionsList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="questions", url = "https://jservice.io/api/category/")
//@Headers()
public interface QuestionProxy {
    @GetMapping
    CategoryQuestionsList getOne();
    @GetMapping("{category}")
    CategoryQuestionsList getQuestionListByCategory(@PathVariable int category) ;
}

