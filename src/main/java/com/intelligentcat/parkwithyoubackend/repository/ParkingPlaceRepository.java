package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.exception.NoAvailablePlaceException;
import com.intelligentcat.parkwithyoubackend.model.ParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Integer> {
    @Query(
            value = "select * from parking_place where parking_lot_id=? and status=\"available\" limit 1",
            nativeQuery = true)
    ParkingPlace getNextAvailableParkingPlace(Integer parkingLotId);

    @Modifying
    @Transactional
    @Query(
            value = "update parking_place set status='unavailable' where id=? and parking_lot_id=?",
            nativeQuery = true)
    void markParkingPlaceAsUnavailable(Integer parkingPlaceId, Integer parkingLotId);
}