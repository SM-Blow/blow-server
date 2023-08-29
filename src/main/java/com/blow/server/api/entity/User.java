package com.blow.server.api.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name= "nickname")
    private String nickname;

    @Column(name= "seed")
    private Long seed;

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
