package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class RecommendationController {


    @Autowired
    private Engine engine;

    public RecommendationController() {
    }

    @GetMapping("/recommendation")
    public Flux<Link> recommend(
            @RequestParam String permalink,
            @RequestParam int internal,
            @RequestParam int external) {
        return engine.recommend(permalink, internal, external);
    }


}
