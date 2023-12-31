package com.blow.server.api.entity;

import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "duedate")
    private LocalDateTime duedate;

    @Column(name = "borrow")
    private boolean borrow;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy =  "post")
    private List<PostScrap> postScraps = new ArrayList<>();

    public boolean isOwner(Long userId){
        if(!user.getId().equals(userId)){
            return false;
        }
        return true;
    }
}
