package main.service;

import main.api.response.PostResponse;
import main.api.response.PostsResponse;

import java.security.Principal;


public interface PostService {

    PostsResponse getAllPosts (int offset, int limit, String mode);
    PostsResponse getPostsSearch(int offset, int limit, String query);
    PostsResponse getPostsByDate(int offset, int limit, String date);
    PostsResponse getPostsByTag(int offset, int limit, String tag);
    PostResponse getPostById(int id, Principal principal);
}
