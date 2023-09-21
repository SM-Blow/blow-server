package com.blow.server.api.entity;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "\"Coupon\"")
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

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
}