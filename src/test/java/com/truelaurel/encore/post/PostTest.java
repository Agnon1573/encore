package com.truelaurel.encore.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.truelaurel.encore.data.PostData.POST1;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void testCreatePost() {
        webClient.post().uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(POST1), Post.class)
                .exchange()
                .expectBody(Post.class).isEqualTo(POST1);
    }
}