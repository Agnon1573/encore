package com.truelaurel.encore.post;

import java.util.Objects;
import java.util.Set;

public class Post {
    private String url;
    private String title;
    private Set<String> tags;

    public Post() {
    }

    public Post(String url, String title, Set<String> tags) {
        this.url = url;
        this.title = title;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(url, post.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "Post{" +
                "url='" + url + '\'' +
                ", tags=" + tags +
                '}';
    }
}
