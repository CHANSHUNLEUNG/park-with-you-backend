package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
	@Query(
					value = "select * from parking_lot where region=?",
					nativeQuery = true)
	List<ParkingLot> findByRegion(String region);

	@Modifying
	@Transactional
	@Query(
					value = "update parking_lot set available_count=available_count-1 where id=?",
					nativeQuery = true)
	void deductAvailableCountById(Integer parkingLotId);
}