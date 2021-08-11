package main.api.response;

import lombok.Data;
import main.model.Post;
import main.model.PostVote;

import java.util.LinkedList;
import java.util.List;

@Data
public class PostResponseForList {
    private String text;
    private long id;
    private long timestamp;
    private UserPostResponse user;
    private String title;
    private String announce;
    private long likeCount;
    private long dislikeCount;
    private long commentCount;
    private long viewCount;

    private boolean active;
    private List<CommentResponse> comments;
    private List<String> tags;

    public PostResponseForList(Post post) {
        this.id = post.getId();
        this.timestamp = post.getTime().getTime() / 1000;
        this.user = new UserPostResponse(post.getUser());
        this.title = post.getTitle();
        this.announce = setAnnounce(post);
        this.likeCount = getLikeCount(post);
        this.dislikeCount = getDislikeCount(post);
        this.commentCount = setCommentCount(post);
        this.viewCount = post.getViewCount();
    }

    public String setAnnounce(Post post) {

        String announce = post.getText()
                .replaceAll("</div>", " ")
                .replaceAll("\\<.*?\\>", "")
                .replaceAll("&nbsp;", " ");

        if (announce.length() > 400) {
            return announce.substring(0, 400) + "...";
        }
        return announce;
    }

    public long getLikeCount(Post post) {
        likeCount = 0;

        if (!(post.getLike() == null)) {
            LinkedList<PostVote> like = new LinkedList<>(post.getLike());
            for (PostVote l : like
            ) {
                if (l.getValue() == 1) {
                    likeCount++;
                }
            }
        }
        return likeCount;
    }

    public long getDislikeCount(Post post) {
        dislikeCount = 0;

        if (!(post.getLike() == null)) {

            LinkedList<PostVote> like = new LinkedList<>(post.getLike());
            for (PostVote l : like
            ) {
                if (l.getValue() == 0) {
                    dislikeCount++;
                }
            }
        }
        return dislikeCount;
    }

    public long setCommentCount(Post post) {

        if (!(post.getComments() == null)) {
            commentCount = post.getComments().size();
        } else {
            commentCount = 0;
        }
        return commentCount;
    }
}
