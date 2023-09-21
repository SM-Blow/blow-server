package com.blow.server.api.entity;

import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "\"PostScrap\"")
public class PostScrap extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Post_scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "status")
    private boolean status;


    @Builder
    public PostScrap(User user, Post post){
        setUser(user);
        this.status = true;
        this.post = post;
    }

    private void setUser(User user){
        if(Objects.nonNull(this.user)){
            this.user.getPostScraps().remove(this);
        }
        this.user = user;
        user.getPostScraps().add(this);
    }

    public Long getPostId(){
        return this.post.getId();
    }

    public PostScrap setPostStatus(boolean status){
        this.status = status;
        return this;
    }

    public Post getPost(){
        return this.post;
    }
}
