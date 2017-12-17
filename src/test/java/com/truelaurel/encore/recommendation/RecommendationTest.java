package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecommendationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testWelcome() {
        Post post = new Post("https://hui-wang.info/book.html", "读书", "2017", Collections.singleton("book"));
        RecommendationRequest request = new RecommendationRequest(1, 1, post);
        webClient.post().uri("/recommend").accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RecommendationRequest.class)
                .exchange()
                .expectBodyList(Link.class).isEqualTo(Collections.singletonList(new Link(post.getTitle(), post.getUrl())));
    }

}