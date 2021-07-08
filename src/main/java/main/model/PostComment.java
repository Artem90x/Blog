package main.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "post_comments")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Post parentId;

    @ManyToOne(optional=false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    @ManyToOne(optional=false)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String text;
}
