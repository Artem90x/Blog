package main.api.response;

import lombok.Data;
import main.model.Post;
import main.model.User;

@Data
public class UserPostResponse {

    private int id;
    private String name;
    private String photo;

    public UserPostResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public UserPostResponse(Post post) {
        this.id = post.getUser().getId();
        this.name = post.getUser().getName();
        this.photo = post.getUser().getPhoto();
    }
}
