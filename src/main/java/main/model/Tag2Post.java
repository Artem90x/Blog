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

    @OneToOne(targetEntity = Post.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable=false, referencedColumnName = "id")
    private Integer post;

    @OneToOne(targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", nullable=false, referencedColumnName = "id")
    private Integer tag;
}
