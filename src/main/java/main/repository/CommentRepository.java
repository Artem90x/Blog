package main.repository;

import main.model.PostComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<PostComment, Integer> {

    @Query(value = "SELECT p.* FROM post_comments p WHERE p.post_id = :id", nativeQuery = true)
    List<PostComment> findComments(@Param("id") int id);
}
