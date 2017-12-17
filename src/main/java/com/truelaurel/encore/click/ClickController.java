package com.truelaurel.encore.click;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ClickController {


    private final ClickRepository clickRepository;

    public ClickController(ClickRepository clickRepository) {
        this.clickRepository = clickRepository;
    }

    @PostMapping("/clicks")
    public Mono<Click> trackClick(@RequestBody Click click) {
        return clickRepository.save(click);
    }
}
