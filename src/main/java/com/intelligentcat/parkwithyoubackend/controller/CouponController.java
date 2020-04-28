package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.ShareLink;
import com.intelligentcat.parkwithyoubackend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coupons")
public class CouponController {
	private CouponService couponService;

	@Autowired
	public CouponController(CouponService couponService) {
		this.couponService = couponService;
	}

	@PostMapping
	public void activateCoupon(@RequestParam Integer couponid) {
		couponService.activateCoupon(couponid);
	}

	@GetMapping("/{customerId}/order/{orderId}")
	public ShareLink getShareLinkByCustomerIdAndOrderId(@PathVariable Integer customerId,
														@PathVariable Integer orderId){
		return couponService.getShareLink(customerId, orderId);
	}
}
