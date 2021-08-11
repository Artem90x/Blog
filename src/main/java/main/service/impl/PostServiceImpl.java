package main.service.impl;

import lombok.AllArgsConstructor;
import main.api.response.PostResponseForList;
import main.api.response.PostsResponse;
import main.model.Post;
import main.repository.PostRepository;
import main.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;


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

    private PostsResponse createPostsResponse(Page<Post> pageOfTags, int size){

        List<PostResponseForList> postResponseList = new ArrayList<>();
        for (Post p : pageOfTags) {

            postResponseList.add(new PostResponseForList(p));
        }
        PostsResponse postsResponse = new PostsResponse();
        postsResponse.setPosts(postResponseList);
        postsResponse.setCount(size);

        return postsResponse;
    }
}
