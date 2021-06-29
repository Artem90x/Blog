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

    @Column(name = "parent_id")
    private Integer parentId;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable = false)
    private Post postId;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String text;
}