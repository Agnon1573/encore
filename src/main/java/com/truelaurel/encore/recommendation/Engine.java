package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.Link;
import com.truelaurel.encore.post.Post;
import com.truelaurel.encore.post.PostCreatedEvent;
import com.truelaurel.encore.post.PostRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.truelaurel.encore.recommendation.DomainNameExtractor.domain;

@Component
public class Engine implements ApplicationListener<PostCreatedEvent> {
    private final Map<String, Set<Link>> tagToLinks = new HashMap<>();
    private final Map<String, Post> urlToPost = new HashMap<>();

    public Engine(PostRepository postRepository) {
        postRepository.findAll().subscribe(this::updateTagIndex);
    }


    @Override
    public void onApplicationEvent(PostCreatedEvent postCreatedEvent) {
        Post post = postCreatedEvent.getPost();
        urlToPost.put(post.getPermalink(), post);
        updateTagIndex(post);
    }

    private void updateTagIndex(Post p) {
        p.getTags().forEach(
                tag -> {
                    Set<Link> links = tagToLinks.getOrDefault(tag, new HashSet<>());
                    links.add(new Link(p.getTitle(), p.getPermalink()));
                    tagToLinks.put(tag, links);
                }
        );
    }

    public Flux<Link> recommend(String url, int internal, int external) {
        Post post = urlToPost.get(url);
        String postDomain = domain(post.getPermalink());
        Map<Boolean, List<Link>> links = post.getTags()
                .stream()
                .flatMap(tag -> tagToLinks.getOrDefault(tag, new HashSet<>()).stream())
                .distinct()
                .collect(Collectors.partitioningBy(link -> postDomain.equals(domain(link.getPermalink()))));
        List<Link> internalLinks = links.get(true);
        List<Link> externalLinks = links.get(false);
        return Flux.fromStream(Stream.concat(
                internalLinks.stream()
                        .filter(link -> !link.getPermalink().equals(post.getPermalink()))
                        .limit(internal),
                externalLinks.stream().limit(external)
        ));
    }
}
