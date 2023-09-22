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
@NoArgsConstructor
public class Event extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private boolean status;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "host")
    private String host;

    @Column(name = "content")
    private String content;

    @Column(name = "accept_count")
    private Long acceptCount;

    @Column(name = "current_apply_count")
    private Long currentApplyCount;

    @OneToMany(mappedBy = "event")
    private List<EventApply> eventApplyList = new ArrayList<>();


    public void currentApplyCountUp() {
        this.currentApplyCount+=1;
    }

    @Builder
    public Event(String title, LocalDateTime dueDate, String host, String content, Long acceptCount) {
        this.title = title;
        this.status = true;
        this.dueDate = dueDate;
        this.host = host;
        this.content = content;
        this.acceptCount = acceptCount;
        this.currentApplyCount = 0L;
    }

    public void setEventStatus(boolean status) {
        this.status = status;
    }

}
