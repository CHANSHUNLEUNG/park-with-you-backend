package com.intelligentcat.parkwithyoubackend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository {
	private JdbcTemplate jdbcTemplate;

	public CouponRepository(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public void activateCoupon(Integer couponid) {
		final String sql = "update coupon set status=\"active\" where id=?;";
		jdbcTemplate.update(sql, new Object[]{couponid});
	}
}
