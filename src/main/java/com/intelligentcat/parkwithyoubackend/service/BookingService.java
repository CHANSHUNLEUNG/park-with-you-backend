package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.model.ParkingPlace;
import com.intelligentcat.parkwithyoubackend.repository.CustomerRepository;
import com.intelligentcat.parkwithyoubackend.repository.ParkingPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private CustomerRepository customerRepository;
    private ParkingPlaceRepository parkingPlaceRepository;

    @Autowired
    public BookingService(CustomerRepository customerRepository,
                          ParkingPlaceRepository parkingPlaceRepository){
        this.customerRepository = customerRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
    }

    public OrderResponse addNewBooking(Integer parkingLotId, OrderRequest orderRequest){
        List<Customer> customerList = customerRepository.getAllCustomer();
        ParkingPlace nextAvailablePlace = parkingPlaceRepository.getNextAvailableParkingPlace(parkingLotId);
        System.out.println(nextAvailablePlace.toString());


        return new OrderResponse();
    }
}
