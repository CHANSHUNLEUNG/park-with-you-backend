package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

	private CouponRepository couponRepository;

	@Autowired
	public CouponService(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}
	public void activateCoupon(Integer couponid) {
		couponRepository.activateCoupon(couponid);
	}
}
