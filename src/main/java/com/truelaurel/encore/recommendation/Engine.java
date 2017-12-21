package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.common.FunctionalReadWriteLock;
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
    private final Map<String, Post> permalinkToPost = new HashMap<>();
    private FunctionalReadWriteLock lock = new FunctionalReadWriteLock();

    public Engine(PostRepository postRepository) {
        initFromRepo(postRepository);
    }

    void initFromRepo(PostRepository postRepository) {
        postRepository.findAll().subscribe(this::update);
    }

    @Override
    public void onApplicationEvent(PostCreatedEvent postCreatedEvent) {
        doUpdate(postCreatedEvent.getPost());
    }

    void update(Post p) {
        lock.write(() -> doUpdate(p));
    }

    private void doUpdate(Post p) {
        permalinkToPost.put(p.getPermalink(), p);
        p.getTags().forEach(
                tag -> {
                    Set<Link> links = tagToLinks.getOrDefault(tag, new HashSet<>());
                    links.add(new Link(p.getTitle(), p.getPermalink()));
                    tagToLinks.put(tag, links);
                }
        );
    }

    public Flux<Link> recommend(String permalink, int internal, int external) {
        return lock.read(() -> doRecommend(permalink, internal, external));
    }

    private Flux<Link> doRecommend(String permalink, int internal, int external) {
        Post post = permalinkToPost.get(permalink);
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
