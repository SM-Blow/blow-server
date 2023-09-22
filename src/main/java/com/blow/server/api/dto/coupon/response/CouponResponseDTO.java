package com.blow.server.api.dto.coupon.response;

import com.blow.server.api.entity.Coupon;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CouponResponseDTO(
        List<CouponResponseVO> couponList)
{
    public static CouponResponseDTO of(List<Coupon> couponList){
        return CouponResponseDTO.builder()
                .couponList(couponList.stream().map(CouponResponseVO::of).toList())
                .build();
    }
}

@Builder
record CouponResponseVO(
        Long couponId,
        String storeName,
        String content,
        LocalDateTime dueDate)
{
    public static CouponResponseVO of(Coupon coupon){
        return CouponResponseVO.builder()
                .couponId(coupon.getId())
                .storeName(coupon.getStoreName())
                .content(coupon.getContent())
                .dueDate(coupon.getDueDate())
                .build();
    }
}
