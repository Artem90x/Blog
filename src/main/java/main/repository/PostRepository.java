package main.repository;


import main.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT COUNT(*) FROM posts p JOIN user u ON u.id = p.user_id WHERE u.email = :email " +
            "AND p.is_active = 1 " +
            "AND p.moderation_status = 'NEW'", nativeQuery = true)
    int findAllPostsIsModerate(@Param("email") String email);

    @Query(value = "SELECT p.* FROM posts p WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY p.time", nativeQuery = true)
    List<Post> findAllPosts();

    @Query(value = "SELECT * FROM posts p WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY (SELECT count(*) FROM post_comments c WHERE c.post_id = p.id) DESC", nativeQuery = true)
    Page<Post> findAllPostsByCommentsDesc(Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY (SELECT sum(value) FROM post_votes c WHERE c.post_id = p.id) DESC", nativeQuery = true)
    Page<Post> findAllPostsByVotesDesc(Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY p.time", nativeQuery = true)
    Page<Post> findAllPostsByTime(Pageable pageable);

    @Query(value = "SELECT * FROM posts WHERE is_active = 1 " +
            "AND moderation_status = 'ACCEPTED' " +
            "AND `time` < NOW() " +
            "ORDER BY time DESC", nativeQuery = true)
    Page<Post> findAllPostsByTimeDesc(Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE p.text LIKE %:query% " +
            "AND p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY p.time DESC", nativeQuery = true)
    Page<Post> findAllPostsBySearch(@Param("query") String query, Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE p.time LIKE :date% " +
            "AND p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY p.time", nativeQuery = true)
    Page<Post> findAllPostsByDate(@Param("date") String date, Pageable pageable);

    @Query(value = "SELECT * FROM posts p JOIN tag2post tp " +
            "ON tp.post_id = p.id JOIN tags t " +
            "ON t.id = tp.tag_id WHERE t.name = :tag " +
            "AND p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY p.time", nativeQuery = true)
    Page<Post> findAllPostsByTag(@Param("tag") String tag, Pageable pageable);

    @Query(value = "SELECT * FROM posts p WHERE p.id = :id", nativeQuery = true)
    Post findPostById(@Param("id") int id);

    @Query(value = "SELECT * FROM posts p WHERE p.id = :id " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.`time` < NOW() " +
            "ORDER BY p.time", nativeQuery = true)
    Post findPostAcceptedById(@Param("id") int id);
}
