package com.blow.server.api.entity;

import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "\"Event\"")
@Entity
public class Event extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "host")
    private String host;

    @Column(name = "content")
    private String content;

    @Column(name = "accept_count")
    private Long acceptCount;

    @OneToMany(mappedBy = "event")
    private List<EventApply> eventApplyList = new ArrayList<>();
}
