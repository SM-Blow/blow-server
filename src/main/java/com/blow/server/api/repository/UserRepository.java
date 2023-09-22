package com.blow.server.api.repository;

import com.blow.server.api.entity.EventApply;
import com.blow.server.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(Long userId);
    boolean existsUserByNickname(String Nickname);
    boolean existsUserByEmail(String email);
    User findByEmail(String email);

    @Query("select distinct u from User u join fetch u.eventApplyList where u.id = :id")
    Optional<User> findWithEventApplyListById(@Param("id") Long userId);
}
