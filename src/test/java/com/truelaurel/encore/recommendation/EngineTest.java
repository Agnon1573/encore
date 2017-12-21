package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.post.PostRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.stream.Collectors;

import static com.truelaurel.encore.data.PostData.POST1;
import static org.junit.Assert.assertEquals;

public class EngineTest {

    private Engine engine = new Engine(Mockito.mock(PostRepository.class)) {
        @Override
        void initFromRepo(PostRepository postRepository) {
        }
    };

    @Test
    public void onlyOnePost() {
        engine.update(POST1);
        assertEquals(Collections.emptyList(),
                engine.recommend(POST1.getPermalink(), 1, 1).collect(Collectors.toList()).block());
    }
}