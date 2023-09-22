package com.blow.server.api.repository;

import com.blow.server.api.entity.Coupon;
import com.blow.server.api.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
