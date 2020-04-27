package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import com.intelligentcat.parkwithyoubackend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
	private ParkingLotRepository parkingLotRepository;
	private OrderService orderService;

	@Autowired
	public ParkingLotService(ParkingLotRepository parkingLotRepository, OrderService orderService) {
		this.parkingLotRepository = parkingLotRepository;
		this.orderService = orderService;
	}


	public List<ParkingLot> getAllParkingLots() {
		return parkingLotRepository.findAll();
	}

	public List<ParkingLot> getParkingLotsByRegion(String region) {
		return parkingLotRepository.findByRegion(region);
	}

	public OrderResponse addNewBooking(Integer parkingLotId, OrderRequest orderRequest) {
		return orderService.addNewBooking(parkingLotId, orderRequest);
	}
}
