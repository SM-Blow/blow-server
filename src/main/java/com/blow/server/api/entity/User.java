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
    private int seed;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "report_status")
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user")
    List<PostScrap> postScraps = new ArrayList<>();

    @Where(clause = "status = true")
    @OneToMany(mappedBy = "user")
    List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "reportUser")
    private List<Report> reportList = new ArrayList<>();

    @OneToOne(mappedBy = "targetUser")
    private Report report;

    @OneToMany(mappedBy = "user")
    private List<EventApply> eventApplyList = new ArrayList<>();

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.seed = 0;
    }

    public void updateFCMToken(String fcmToken) { this.FCMToken = fcmToken; }
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }
}
