package com.blow.server.api.repository;

import com.blow.server.api.entity.Coupon;
import com.blow.server.api.entity.Report;
import com.blow.server.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportUser_IdAndTargetUser_Id(Long userId, Long targetUserId);

}
