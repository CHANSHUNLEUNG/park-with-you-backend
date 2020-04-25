package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.ParkingPlace;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingPlaceRepository {
    private JdbcTemplate jdbcTemplate;

    public ParkingPlaceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ParkingPlace getNextAvailableParkingPlace(Integer parkingLotId){
        final String sql = "select * from parking_place where parking_lot_id=? and status=\"available\" limit 1;";
        ParkingPlace nextAvailablePlace = jdbcTemplate.queryForObject(
                sql,
                new Object[]{parkingLotId},
                (response, rowNumber) ->
                new ParkingPlace(
                        response.getInt("id"),
                        response.getString("name"),
                        response.getInt("parking_lot_id"),
                        response.getString("status")
                ));
        return nextAvailablePlace;
    }

    public boolean markParkingPlaceAsUnavailable(Integer parkingLotId, Integer parkingPlaceId){
        final String sql = "update parking_place set status='unavailable' where id=? and parking_lot_id=?;";
        try {
            jdbcTemplate.update(sql, new Object[] {parkingPlaceId, parkingLotId});
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
}
