package main.service.impl;

import lombok.AllArgsConstructor;
import main.api.response.CommentResponse;
import main.api.response.PostResponse;
import main.api.response.PostResponseForList;
import main.api.response.PostsResponse;
import main.model.Post;
import main.model.PostComment;
import main.model.User;
import main.repository.CommentRepository;
import main.repository.PostRepository;
import main.repository.TagRepository;
import main.repository.UserRepository;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;


    @Override
    public PostsResponse getAllPosts(int offset, int limit, String mode) {
        Pageable pageable = PageRequest.of(offset / limit, limit);

        Page<Post> postsPage;

        switch (mode) {
            case "popular":
                postsPage = postRepository.findAllPostsByCommentsDesc(pageable);
                break;
            case "best":
                postsPage = postRepository.findAllPostsByVotesDesc(pageable);
                break;
            case "early":
                postsPage = postRepository.findAllPostsByTime(pageable);
                break;
            default:
                postsPage = postRepository.findAllPostsByTimeDesc(pageable);
                break;
        }

        return createPostsResponse(postsPage, postRepository.findAllPosts().size());
    }

    private PostsResponse createPostsResponse(Page<Post> pageOfTags, int size) {

        List<PostResponseForList> postResponseList = new ArrayList<>();
        for (Post p : pageOfTags) {

            postResponseList.add(new PostResponseForList(p));
        }
        PostsResponse postsResponse = new PostsResponse();
        postsResponse.setPosts(postResponseList);
        postsResponse.setCount(size);

        return postsResponse;
    }

    public PostsResponse getPostsSearch(int offset, int limit, String query) {
        Pageable pageable;
        pageable = PageRequest.of(offset / limit, limit);
        Page<Post> pageOfTags = postRepository.findAllPostsBySearch(query, pageable);

        return createPostsResponse(pageOfTags, (int) pageOfTags.getTotalElements());
    }

    public PostsResponse getPostsByDate(int offset, int limit, String date) {
        Pageable pageable;
        pageable = PageRequest.of(offset / limit, limit);
        Page<Post> pageByDate = postRepository.findAllPostsByDate(date, pageable);

        return createPostsResponse(pageByDate, (int) pageByDate.getTotalElements());
    }

    public PostsResponse getPostsByTag(int offset, int limit, String tag) {
        Pageable pageable;
        pageable = PageRequest.of(offset / limit, limit);
        Page<Post> pageByTag = postRepository.findAllPostsByTag(tag, pageable);

        return createPostsResponse(pageByTag, (int) pageByTag.getTotalElements());
    }

    public PostResponse getPostById(int id, Principal principal) {

        List<PostComment> commentsList = commentRepository.findComments(id);
        List<String> tagList = tagRepository.getTagsByPost(id);

        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (PostComment c : commentsList) {
            commentResponseList.add(new CommentResponse(c));
        }

        Post post;

        if (!(principal == null)) {

            post = postRepository.findPostById(id);

            if (post == null) {
                return null;
            }
        } else {
            post = postRepository.findPostAcceptedById(id);

            if (post == null) {
                return null;
            }

            post.setViewCount(post.getViewCount() + 1);
            postRepository.save(post);
        }
        return new PostResponse(commentResponseList, post, tagList);
    }
}
