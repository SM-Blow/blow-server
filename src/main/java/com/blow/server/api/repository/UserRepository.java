package com.blow.server.api.repository;

import com.blow.server.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> getUserById(Long userId);

}
