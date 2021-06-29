package main.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tag2post")

public class Tag2Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", nullable=false, referencedColumnName = "id")
    private Integer post;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", nullable=false, referencedColumnName = "id")
    private Integer tag;
}
