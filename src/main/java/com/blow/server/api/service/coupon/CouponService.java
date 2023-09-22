package com.blow.server.api.service.coupon;

import com.blow.server.api.dto.coupon.request.CouponRegistRequestDTO;
import com.blow.server.api.dto.coupon.request.CouponUseRequestDTO;
import com.blow.server.api.dto.coupon.response.CouponResponseDTO;

import java.util.List;

public interface CouponService {

    void registCoupon(Long userId, CouponRegistRequestDTO couponRegistRequestDTO);
    CouponResponseDTO getCouponList(Long userId);
    void useCoupon(Long userId, CouponUseRequestDTO couponUseRequestDTO);
}
