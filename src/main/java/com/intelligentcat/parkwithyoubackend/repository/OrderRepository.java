package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.model.ParkingPlace;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class OrderRepository {
    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrderResponse createNewOrder(String now, ParkingPlace availablePlace, OrderRequest orderRequest) {
        Integer customerId = orderRequest.getCustomerId();
        Integer parkingLotId = availablePlace.getParking_lot_id();
        Integer parkingPlaceId = availablePlace.getId();
        String parkingPlaceName = availablePlace.getName();
        String orderTime = now;
        String startParkingTime = orderRequest.getStartTime();
        Integer parkingDuration = orderRequest.getDuration();

        final String sql = "insert into `order` (customer_id, parking_place_id, order_time, start_parking_time, parking_duration) values (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, customerId);
                        ps.setInt(2, parkingPlaceId);
                        ps.setString(3, orderTime);
                        ps.setString(4, startParkingTime);
                        ps.setInt(5, parkingDuration);
                        return ps;
                    }
                },
                keyHolder
        );
        Integer orderId = keyHolder.getKey().intValue();
        return new OrderResponse(orderTime,
                orderId,
                customerId,
                parkingLotId,
                parkingPlaceId,
                parkingPlaceName,
                startParkingTime,
                parkingDuration);
    }
}
