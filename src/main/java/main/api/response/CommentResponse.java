package main.api.response;

import lombok.Data;
import main.model.PostComment;

@Data
public class CommentResponse {

    private final int id;
    private final long timestamp;
    private final String text;
    private final UserCommentResponse user;

    public CommentResponse(PostComment postComments) {
        this.id = postComments.getId();
        this.timestamp = postComments.getTime().getTime() / 1000;
        this.text = postComments.getText();
        this.user = new UserCommentResponse(postComments.getUser());
    }
}
