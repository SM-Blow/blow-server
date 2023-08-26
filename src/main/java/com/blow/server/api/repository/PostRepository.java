package com.blow.server.api.repository;

import com.blow.server.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> getPostById(Long postId);
    List<Post> findPostByCategoryOrderByCreatedAtDesc(String category);
}
