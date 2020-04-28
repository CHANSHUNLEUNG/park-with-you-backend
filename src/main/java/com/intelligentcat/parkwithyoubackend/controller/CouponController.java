package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.ShareLink;
import com.intelligentcat.parkwithyoubackend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


@RestController
@RequestMapping("/coupons")
public class CouponController {
	private CouponService couponService;

	@Autowired
	public CouponController(CouponService couponService) {
		this.couponService = couponService;
	}

	@GetMapping
	public void activateCoupon(@RequestParam String coupon) {
		couponService.activateCoupon(coupon);
	}

	@GetMapping("/share-link")
	public ShareLink getShareLinkByCustomerIdAndOrderId(@RequestParam Integer customerId,
														@RequestParam Integer orderId){
		return couponService.getShareLink(customerId, orderId);
	}
}
