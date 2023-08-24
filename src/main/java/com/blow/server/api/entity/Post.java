package com.blow.server.api.entity;

import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"Post\"")
@Entity
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;

    @Column(name = "duedate")
    private LocalDateTime duedate;

    @Column(name = "borrow")
    private boolean borrow;

    @Column(name = "status")
    private boolean status;

    public boolean isOwner(Long userId){
        if(!users.getId().equals(userId)){
            return false;
        }
        return true;
    }
}
