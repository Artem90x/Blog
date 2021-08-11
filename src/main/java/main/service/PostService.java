package main.service;

import main.api.response.PostsResponse;


public interface PostService {

    PostsResponse getAllPosts (int offset, int limit, String mode);
}
