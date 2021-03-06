package main.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "post_votes")
public class PostVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable=false)
    private Post post;

    @Column(name = "time", nullable = false)
    @NotNull
    private Date time;

    @Column(name = "value", columnDefinition = "BIT", nullable = false)
    private byte value;
}
