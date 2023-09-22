package com.blow.server.api.repository;

import com.blow.server.api.entity.EventApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventApplyRepository extends JpaRepository<EventApply, Long> {
    Optional<EventApply> findByUser_idAndEvent_id(Long userId, Long eventId);
}
