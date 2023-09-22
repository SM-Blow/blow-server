package com.blow.server.api.service.coupon;


import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.coupon.request.CouponRegistRequestDTO;
import com.blow.server.api.dto.coupon.request.CouponUseRequestDTO;
import com.blow.server.api.dto.coupon.response.CouponResponseDTO;
import com.blow.server.api.entity.Coupon;
import com.blow.server.api.repository.CouponRepository;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    @Override
    @Transactional
    public void registCoupon(Long userId, CouponRegistRequestDTO request){
        val user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        val coupon = Coupon.builder()
                .storeName(request.storeName())
                .content(request.content())
                .couponCode(request.coupon_code())
                .dueDate(request.dueDate())
                .user(user)
                .build();
        couponRepository.save(coupon);
    }

    @Override
    public CouponResponseDTO getCouponList(Long userId){
        val CouponList = couponRepository.findAllByUserIdOrderByDueDate(userId);
        return CouponResponseDTO.of(CouponList);
    }

    @Override
    public void useCoupon(Long userId, CouponUseRequestDTO request){
        val couponId = request.CouponId();
        val user = userRepository.getUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
        val coupon = couponRepository.getCouponById(couponId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_COUPON.getMessage()));

        if(!isOwner(coupon, user.getId())){
            throw new EntityNotFoundException((ExceptionMessage.NOT_COUPON_OWNER.getMessage()));
        }
    }

    private boolean isOwner(Coupon coupon, Long userId){
        if(!coupon.isOwner(userId)){
            return false;
        }
        return true;
    }
}
