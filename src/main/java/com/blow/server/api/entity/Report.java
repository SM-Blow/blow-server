package com.blow.server.api.entity;


import com.blow.server.api.entity.superclass.TimeStamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Table(name = "\"Report\"")
@Entity
@NoArgsConstructor
public class Report extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id")
    private User targetUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User reportUser;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private boolean status;

    private void setReportUser(User user){
        if(Objects.nonNull(this.reportUser)){
            this.reportUser.getReportList().remove(this);
        }
        this.reportUser = user;
        user.getReportList().add(this);
    }
    @Builder
    public Report(User reportUser, User targetUser, String content, boolean status) {
        setReportUser(reportUser);
        this.targetUser = targetUser;
        this.content = content;
        this.status = true;
    }
}
