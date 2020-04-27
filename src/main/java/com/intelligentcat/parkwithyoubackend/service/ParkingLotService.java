package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import com.intelligentcat.parkwithyoubackend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
	private ParkingLotRepository parkingLotRepository;

	@Autowired
	public ParkingLotService(ParkingLotRepository parkingLotRepository) {
		this.parkingLotRepository = parkingLotRepository;
	}


	public List<ParkingLot> getAllParkingLots() {
		return parkingLotRepository.findAll();
	}

	public List<ParkingLot> getParkingLotsByRegion(String region) {
		return parkingLotRepository.findByRegion(region);
	}
}
