package main.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tag2post")

public class Tag2Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id", nullable=false, referencedColumnName = "id")
    private Post post;

    @OneToOne(targetEntity = Tag.class)
    @JoinColumn(name = "tag_id", nullable=false, referencedColumnName = "id")
    private Tag tag;
}
