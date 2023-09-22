package com.blow.server.api.repository;

import com.blow.server.api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByStatus(boolean status);
}
