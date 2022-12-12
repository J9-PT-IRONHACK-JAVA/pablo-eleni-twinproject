package com.ironhack.twinproject.proxy;

import com.ironhack.twinproject.dto.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="stoicProxy", url = "https://jservice.io/api")
public interface TriviaProxy {
    @GetMapping
    Question getRandom();
}
