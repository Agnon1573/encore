package com.truelaurel.encore.common;

import java.util.Objects;

public class Link {
    private String title;
    private String permalink;

    public Link() {
    }

    public Link(String title, String permalink) {
        this.title = title;
        this.permalink = permalink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getTitle() {
        return title;
    }

    public String getPermalink() {
        return permalink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(permalink, link.permalink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permalink);
    }

    @Override
    public String toString() {
        return "Link{" +
                "title='" + title + '\'' +
                ", permalink='" + permalink + '\'' +
                '}';
    }
}
