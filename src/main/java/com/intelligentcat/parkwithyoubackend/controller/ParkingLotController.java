package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.ParkingLot;
import com.intelligentcat.parkwithyoubackend.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {
	private ParkingLotService parkingLotService;

	@Autowired
	public ParkingLotController(ParkingLotService parkingLotService) {
		this.parkingLotService = parkingLotService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ParkingLot> getAllParkingLots(@RequestParam(required = false) String region) {
		if (region != null) {
			return parkingLotService.getParkingLotsByRegion(region);
		}
		return parkingLotService.getAllParkingLots();
	}
}
