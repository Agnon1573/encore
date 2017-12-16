package com.truelaurel.encore;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class RecommendationController {

    private final Map<String, Set<Link>> tagToLinks = new HashMap<>();

    private PostRepository postRepository;

    public RecommendationController(final PostRepository postRepository) {
        this.postRepository = postRepository;
        Flux<Post> posts = postRepository.findAll();
        posts.subscribe(this::updateTagIndex);
    }

    private void updateTagIndex(Post p) {
        p.getTags().forEach(
                tag -> {
                    Set<Link> links = tagToLinks.getOrDefault(tag, new HashSet<>());
                    links.add(new Link(p.getTitle(), p.getUrl()));
                    tagToLinks.put(tag, links);
                }
        );
    }

    @PostMapping("/recommend")
    public Flux<Link> recommend(@RequestBody RecommendationRequest request) {
        Post post = request.getPost();
        postRepository.save(post);
        updateTagIndex(post);
        String postDomain = DomainNameExtractor.domain(post.getUrl());
        Map<Boolean, List<Link>> links = post.getTags()
                .stream()
                .flatMap(tag -> tagToLinks.getOrDefault(tag, new HashSet<>()).stream())
                .collect(Collectors.partitioningBy(link -> postDomain.equals(DomainNameExtractor.domain(link.getUrl()))));
        List<Link> internalLinks = links.get(true);
        List<Link> externalLinks = links.get(false);
        return Flux.fromStream(Stream.concat(
                internalLinks.stream().limit(request.getInternalLinkCount()),
                externalLinks.stream().limit(request.getExternalLinkCount())
        ));
    }

}
