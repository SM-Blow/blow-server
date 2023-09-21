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
    private int id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "content")
    private String content;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "coupon_code")
    private String couponCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}