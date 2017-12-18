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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecommendationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testRecommendation() {
        Post post1 = new Post("https://hui-wang.info/post1.html", "read books", "2017",
                new HashSet<>(Arrays.asList("book", "read")));

        webClient.post()
                .uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(post1), Post.class)
                .exchange();


        Post post2 = new Post("https://hui-wang.info/post2.html", "read blog", "2017",
                new HashSet<>(Arrays.asList("blog", "read")));
        webClient.post()
                .uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(post2), Post.class)
                .exchange();


        RecommendationRequest request = new RecommendationRequest(1, 1, post1);
        webClient.post().uri("/recommend").accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), RecommendationRequest.class)
                .exchange()
                .expectBodyList(Link.class).isEqualTo(Collections.singletonList(new Link(post2.getTitle(), post2.getUrl())));
    }

}