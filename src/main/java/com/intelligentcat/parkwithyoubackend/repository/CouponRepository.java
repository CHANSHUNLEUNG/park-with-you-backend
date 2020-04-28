package com.intelligentcat.parkwithyoubackend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CouponRepository {
	private JdbcTemplate jdbcTemplate;

	public CouponRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void activateCoupon(Integer couponid) {
		final String sql = "update coupon set status=\"active\" where id=?;";
		jdbcTemplate.update(sql, new Object[]{couponid});
	}

	public boolean createNewCoupon(Integer customerId, Integer genOrderId) {
		final String sql = "insert into `coupon` (customer_id, gen_order_id, status) values (?, ?, 'inactive')";

		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, customerId);
				preparedStatement.setInt(2, genOrderId);
			}
		}) > 0;

	}
}
