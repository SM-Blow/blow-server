package com.blow.server.api.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "\"User\"")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(name= "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="refresh_token")
    private String refreshToken;

    @Column(name="fcm_token")
    private String FCMToken;

    @Column(name= "nickname")
    private String nickname;

    @Column(name= "seed")
    private Long seed;

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user")
    List<PostScrap> postScraps = new ArrayList<>();


    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateFCMToken(String fcmToken) { this.FCMToken = fcmToken; }
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
