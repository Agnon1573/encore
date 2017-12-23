package com.truelaurel.encore.post;

import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.Set;

public class Post {

    @Id
    private String permalink;
    private String title;
    private Set<String> tags;

    public Post() {
    }

    public Post(String permalink, String title, Set<String> tags) {
        this.permalink = permalink;
        this.title = title;
        this.tags = tags;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
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
        return Objects.equals(permalink, post.permalink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permalink);
    }

    @Override
    public String toString() {
        return "Post{" +
                "permalink='" + permalink + '\'' +
                ", tags=" + tags +
                '}';
    }
}
