package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.ShareLink;
import com.intelligentcat.parkwithyoubackend.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CouponService {
	final private String ACTIVATE_COUPON_URL = "/coupons?coupon=%s";
	private CouponRepository couponRepository;

	@Autowired
	public CouponService(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}
	public void activateCoupon(Integer couponid) {
		couponRepository.activateCoupon(couponid);
	}

    public ShareLink getShareLink(Integer customerId, Integer orderId) {
		String couponId =  couponRepository.getCouponId(customerId, orderId);
		String encodedCouponId = Base64.getEncoder().encodeToString(couponId.getBytes());
		return new ShareLink(String.format(ACTIVATE_COUPON_URL, encodedCouponId));
    }
}
