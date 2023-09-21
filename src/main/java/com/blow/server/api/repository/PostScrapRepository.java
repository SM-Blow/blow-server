package com.blow.server.api.repository;

import com.blow.server.api.entity.PostScrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostScrapRepository extends JpaRepository<PostScrap, Long> {
    PostScrap findByUser_IdAndPost_Id(Long userId, Long postId);
}
