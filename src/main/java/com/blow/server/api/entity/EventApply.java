package com.blow.server.api.entity;

import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Table(name = "\"EventApply\"")
@Entity
public class EventApply extends TimeStamped {

    @Id
    @GeneratedValue
    @Column(name = "event_apply_id")
    private Long id;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    private void setUser(User user){
        if(Objects.nonNull(this.user)){
            this.user.getEventApplyList().remove(this);
        }
        this.user = user;
        user.getEventApplyList().add(this);
    }

    private void setEvent(Event event) {
        if(Objects.nonNull(this.event)){
            this.event.getEventApplyList().remove(this);
        }
        this.event = event;
        event.getEventApplyList().add(this);
    }
}
