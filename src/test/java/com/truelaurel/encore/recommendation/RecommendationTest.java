package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.Link;
import com.truelaurel.encore.post.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.truelaurel.encore.data.PostData.POST1;
import static com.truelaurel.encore.data.PostData.POST2;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecommendationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testRecommendation() {
        webClient.post()
                .uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(POST1), Post.class)
                .exchange();


        webClient.post()
                .uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(POST2), Post.class)
                .exchange();

        webClient.get().uri(
                builder -> builder
                        .path("/recommendation")
                        .queryParam("url", POST1.getUrl())
                        .queryParam("internal", 1)
                        .queryParam("external", 1)
                        .build())
                .exchange()
                .expectBodyList(Link.class).isEqualTo(Collections.singletonList(new Link(POST2.getTitle(), POST2.getUrl())));
    }

}