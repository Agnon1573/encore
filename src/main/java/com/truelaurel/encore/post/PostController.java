package com.truelaurel.encore.post;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/posts")
    public Mono<Post> createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping
    public Mono<Post> getPost(@RequestParam String id) {
        return postRepository.findById(id);
    }
}
