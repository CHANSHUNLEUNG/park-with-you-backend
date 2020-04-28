package com.intelligentcat.parkwithyoubackend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository {
    private JdbcTemplate jdbcTemplate;
}
