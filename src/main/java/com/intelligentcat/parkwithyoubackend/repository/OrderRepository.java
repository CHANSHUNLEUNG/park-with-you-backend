package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.exception.NoAvailablePlaceException;
import com.intelligentcat.parkwithyoubackend.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class OrderRepository {
    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public OrderResponse createNewOrder(String now, ParkingPlace availablePlace, OrderRequest orderRequest) {
        Integer customerId = orderRequest.getCustomerId();
        Integer parkingLotId = availablePlace.getParkingLotId();
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

    public Order getOrderById(Integer parkingPlaceId) {
        final String sql = "select * from `order` where id=?;";
        List<Order> orders = jdbcTemplate.query(
                sql,
                new Object[]{parkingPlaceId},
                (response, rowNumber) -> {
                    return new Order(
                            response.getString("order_time"),
                            response.getInt("id"),
                            response.getInt("customer_id"),
                            response.getInt("parking_place_id"),
                            response.getString("start_parking_time"),
                            response.getInt("parking_duration")
                    );
                });
        if (orders.size() == 0) {
            throw new NoAvailablePlaceException();
        }
        return orders.get(0);
    }

    public Order extendParkingBookingTime(Order order, Integer duration) {
        final String sql = "update `order` set parking_duration = parking_duration + ? where id= ?;";
        jdbcTemplate.update(sql, new Object[]{duration, order.getOrderId()});
        order.setDuration(order.getDuration() + duration);
        return order;
    }

    private class OrderDetailRowMapper implements RowMapper<OrderDetail> {
        @Override
        public OrderDetail mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderTime(resultSet.getString("order_time"));
            orderDetail.setOrderId(resultSet.getInt("order_id"));
            orderDetail.setCustomerId(resultSet.getInt("customer_id"));
            orderDetail.setParkingLotId(resultSet.getInt("parking_lot_id"));
            orderDetail.setParkingLotName(resultSet.getString("parking_lot_name"));
            orderDetail.setAddress(resultSet.getString("address"));
            orderDetail.setUnitPrice(resultSet.getDouble("unit_price"));
            orderDetail.setParkingPlaceId(resultSet.getInt("parking_place_id"));
            orderDetail.setParkingPlaceName(resultSet.getString("parking_place_name"));
            orderDetail.setStartParkingTime(resultSet.getString("start_parking_time"));
            orderDetail.setDuration(resultSet.getInt("parking_duration"));
            orderDetail.setDiscountAmount(resultSet.getDouble("discount_amount"));
            return orderDetail;
        }
    }

    public List<OrderDetail> findJointDetailByCustomerId(Integer customerId) {
        final String sql = "SELECT o.order_time `order_time` ,o.id `order_id` ,o.customer_id `customer_id` ,pl.id `parking_lot_id` ,pl.name `parking_lot_name` ,pl.address `address` ,pl.unit_price `unit_price` ,pp.id `parking_place_id` ,pp.name `parking_place_name` ,o.start_parking_time `start_parking_time` ,o.parking_duration `parking_duration` ,c.discount_amount `discount_amount` FROM `order` o INNER JOIN `parking_place` pp ON pp.id = o.parking_place_id INNER JOIN `parking_lot` pl ON pl.id = pp.parking_lot_id LEFT JOIN ( SELECT * FROM `coupon` WHERE customer_id = ? AND STATUS = 'used' ) c ON c.used_order_id = o.id WHERE o.customer_id = ? ORDER BY o.start_parking_time DESC ,o.order_time DESC;";
        return jdbcTemplate.query(sql, new Object[]{customerId, customerId}, new OrderDetailRowMapper());
    }
}
