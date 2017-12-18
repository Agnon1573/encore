package com.truelaurel.encore.data;

import com.truelaurel.encore.post.Post;

import java.util.Arrays;
import java.util.HashSet;

public class PostData {
    public static Post POST1 = new Post("https://hui-wang.info/post1.html",
            "read book", "2017", new HashSet<>(Arrays.asList("book", "read")));
    public static Post POST2 = new Post("https://hui-wang.info/post2.html",
            "read blog", "2017", new HashSet<>(Arrays.asList("blog", "read")));
}
