package com.blow.server.api.repository;

import com.blow.server.api.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findAllByUserIdOrderByDueDate(Long userId);
    Optional<Coupon> getCouponById(Long userId);
}
