package com.truelaurel.encore.recommendation;

import com.truelaurel.encore.post.Post;

public class RecommendationRequest {
    private int internalLinkCount;
    private int externalLinkCount;
    private Post post;

    public RecommendationRequest() {
    }

    public RecommendationRequest(int externalLinkCount, int internalLinkCount, Post post) {
        this.externalLinkCount = externalLinkCount;
        this.internalLinkCount = internalLinkCount;
        this.post = post;
    }

    public int getExternalLinkCount() {
        return externalLinkCount;
    }

    public void setExternalLinkCount(int externalLinkCount) {
        this.externalLinkCount = externalLinkCount;
    }

    public int getInternalLinkCount() {
        return internalLinkCount;
    }

    public void setInternalLinkCount(int internalLinkCount) {
        this.internalLinkCount = internalLinkCount;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "RecommendationRequest{" +
                "externalLinkCount=" + externalLinkCount +
                ", internalLinkCount=" + internalLinkCount +
                '}';
    }
}
