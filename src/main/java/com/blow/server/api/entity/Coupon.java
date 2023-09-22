package com.blow.server.api.entity;


import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "\"Coupon\"")
@Entity
public class Coupon extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "content")
    private String content;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "coupon_code")
    private String couponCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Coupon(String storeName, String content, LocalDateTime dueDate, String couponCode, User user){
        this.storeName = storeName;
        this.content = content;
        this.dueDate = dueDate;
        this.couponCode = couponCode;
        this.user = user;
    }

    public boolean isOwner(Long userId){
        if(!user.getId().equals(userId)){
            return false;
        }
        return true;
    }
}