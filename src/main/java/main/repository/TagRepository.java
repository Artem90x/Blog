package main.repository;

import main.api.response.TagResponseAnswerQuery;
import main.model.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    @Query(value = "SELECT t.name as name, COUNT(t.id) as count FROM tags t " +
            "JOIN tag2post tp ON tp.tag_id = t.id " +
            "JOIN posts p on p.id = tp.post_id WHERE p.is_active = 1 " +
            "AND p.moderation_status = 'ACCEPTED' " +
            "AND p.time < NOW() GROUP BY tp.tag_id " +
            "ORDER BY count DESC", nativeQuery = true)
    List<TagResponseAnswerQuery> getRecentTags();

    @Query("SELECT t.id FROM Tag t WHERE t.name = :name")
    Integer getByName(@Param("name") String name);

    @Query(value = "INSERT IGNORE INTO (name) VALUES (:name)", nativeQuery = true)
    void insertTag(@Param("name") String name);

    @Query(value = "SELECT t.name FROM tags t " +
            "JOIN tag2post tp ON tp.tag_id = t.id WHERE tp.post_id = :post_id", nativeQuery = true)
    List<String> getTagsByPost(@Param("post_id") int post_id);
}
