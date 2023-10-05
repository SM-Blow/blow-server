package com.blow.server.api.repository;

import com.blow.server.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> getPostById(Long postId);

    @Query("SELECT p FROM Post p WHERE p.status != 3 ORDER BY p.createdAt DESC")
    List<Post> findAll();
    @Query("SELECT p FROM Post p WHERE LOWER(p.category) LIKE %:category% and p.status != 3 ORDER BY p.createdAt DESC")
    List<Post> findPostByCategoryOrderByCreatedAtDesc( @Param("category") String category);
    List<Post> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE %:postTitleKeyword% OR LOWER(p.content) LIKE %:postContentKeyword% ORDER BY p.createdAt DESC")
    List<Post> findAllByKeyword(
            @Param("postTitleKeyword") String postTitleKeyword,
            @Param("postContentKeyword") String postContentKeyword
    );
}
