package com.blow.server.api.dto.coupon.request;

import lombok.NonNull;

import java.time.LocalDateTime;

public record CouponUseRequestDTO(
        @NonNull
        Long CouponId) {
}
