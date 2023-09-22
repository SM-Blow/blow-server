package com.blow.server.api.controller;


import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.coupon.request.CouponRegistRequestDTO;
import com.blow.server.api.dto.coupon.request.CouponUseRequestDTO;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("")
    private ResponseEntity<ApiResponse> createCoupon(
            @AuthenticationPrincipal BlowUserDetails userDetails,
            @Valid @RequestBody CouponRegistRequestDTO request)
    {
        couponService.createCoupon(userDetails.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_REGIST_COUPON.getMessage()));
    }

    @GetMapping("/list")
    private ResponseEntity<ApiResponse> getCoupons(
            @AuthenticationPrincipal BlowUserDetails userDetails)
    {
        val response = couponService.getCouponList(userDetails.getId());
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_COUPONS.getMessage(), response));
    }

    @PostMapping("/{couponId}")
    private ResponseEntity<ApiResponse> useCoupon(
            @AuthenticationPrincipal BlowUserDetails userDetails,
            @PathVariable Long couponId)
    {
        couponService.useCoupon(userDetails.getId(), couponId);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_USE_COUPON.getMessage()));
    }
}
