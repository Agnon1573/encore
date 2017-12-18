package com.truelaurel.encore.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public PostController() {
    }

    @PostMapping("/posts")
    public Mono<Post> createPost(@RequestBody Post post) {
        return postRepository.save(post).doOnNext(p -> publisher.publishEvent(new PostCreatedEvent(this, post)));
    }

    @GetMapping
    public Mono<Post> getPost(@RequestParam String id) {
        return postRepository.findById(id);
    }
}
