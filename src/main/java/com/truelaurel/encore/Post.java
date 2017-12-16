package com.truelaurel.encore;

import java.util.Set;

public class Post {
    private String url;
    private String title;
    private String content;
    private Set<String> tags;

    public Post() {
    }

    public Post(String url, String title, String content, Set<String> tags) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "url='" + url + '\'' +
                ", tags=" + tags +
                '}';
    }
}
