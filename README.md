> [Live preview](https://hui-wang.info)

# Encore: Posts Recommendation Engine for Bloggers
Encore helps bloggers to promote their blogs by recommended posts with both internal and external links.
An internal link points to a post inside the same blog and an external one targets post of other bloggers.

By sharing links through recommended posts, we believe that we can
- create high-quality connections between bloggers
- help readers to discover more interesting posts

# How it works

## Submit posts
The engine collects posts from submissions. In order to be recommendable, a post must have
- a permalink
- a title
- some tags

## Recommendation Algorithm
Two posts are matched when they share at least one tag.

## Fetch recommended posts
One can fetch a list of recommended posts by providing post permalink. In addition, we can configure the number of internal and external links to be included in the list.

# Deployment
The service stack including REST server and mongodb is [dockerized](https://github.com/huiwang/encore/blob/master/bin/docker-compose.yml). This makes the application cloud native.

# Frontends
As mentioned above, the engine collects posts from submissions. Here is the list of supported frontend
- [hexo-recommended-posts](https://github.com/huiwang/hexo-recommended-posts)

# Open Source
To increase the visiblity and build trust among bloggers and readers, the engine is open sourced.
