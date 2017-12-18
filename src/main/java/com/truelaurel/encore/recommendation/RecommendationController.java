package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class RecommendationController {


    @Autowired
    private Engine engine;

    public RecommendationController() {
    }

    @PostMapping("/recommend")
    public Flux<Link> recommend(@RequestBody RecommendationRequest request) {
        return engine.recommend(request);
    }


}
