package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import org.springframework.jdbc.core.JdbcTemplate;

public class OrderRepository {
    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrderResponse createNewOrder(Integer parking_lot_id, Integer parking_place_id){

        return new OrderResponse();
    }
}
