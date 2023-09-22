package com.blow.server.api.dto.coupon.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CouponRegistRequestDTO(
        @NotBlank
        @NotNull
        String storeName,
        @NotBlank
        @NotNull
        String content,
        @NotNull
        LocalDateTime dueDate,
        @NotBlank
        @NotNull
        String coupon_code
) {
}
