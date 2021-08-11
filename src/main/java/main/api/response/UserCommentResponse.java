package main.api.response;

import lombok.Data;
import main.model.User;

@Data
public class UserCommentResponse {
    private int id;
    private String name;
    private final String photo;

    public UserCommentResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.photo = user.getPhoto();
    }
}
