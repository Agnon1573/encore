package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.Link;
import com.truelaurel.encore.post.Post;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.truelaurel.encore.recommendation.DomainNameExtractor.domain;

@RestController
public class RecommendationController {

    private final Map<String, Set<Link>> tagToLinks = new HashMap<>();

    public RecommendationController() {
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
        updateTagIndex(post);
        String postDomain = domain(post.getUrl());
        Map<Boolean, List<Link>> links = post.getTags()
                .stream()
                .flatMap(tag -> tagToLinks.getOrDefault(tag, new HashSet<>()).stream())
                .collect(Collectors.partitioningBy(link -> postDomain.equals(domain(link.getUrl()))));
        List<Link> internalLinks = links.get(true);
        List<Link> externalLinks = links.get(false);
        return Flux.fromStream(Stream.concat(
                internalLinks.stream()
                        .filter(link -> !domain(link.getUrl()).equals(domain(post.getUrl())))
                        .limit(request.getInternalLinkCount()),
                externalLinks.stream().limit(request.getExternalLinkCount())
        ));
    }

}
