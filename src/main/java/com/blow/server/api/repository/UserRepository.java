package com.blow.server.api.repository;

import com.blow.server.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(Long userId);
    boolean existsUserByNickname(String Nickname);
    boolean existsUserByEmail(String email);
    User findByEmail(String email);
}
