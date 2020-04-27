package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.OrderDetail;
import com.intelligentcat.parkwithyoubackend.model.OrderRequest;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.model.ParkingPlace;
import com.intelligentcat.parkwithyoubackend.repository.CustomerRepository;
import com.intelligentcat.parkwithyoubackend.repository.OrderRepository;
import com.intelligentcat.parkwithyoubackend.repository.ParkingLotRepository;
import com.intelligentcat.parkwithyoubackend.repository.ParkingPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private CustomerRepository customerRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingPlaceRepository parkingPlaceRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(CustomerRepository customerRepository,
                        ParkingLotRepository parkingLotRepository,
                        ParkingPlaceRepository parkingPlaceRepository,
                        OrderRepository orderRepository){
        this.customerRepository = customerRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.orderRepository = orderRepository;
    }

    public OrderResponse addNewBooking(Integer parkingLotId, OrderRequest orderRequest){
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        ParkingPlace nextAvailablePlace = parkingPlaceRepository.getNextAvailableParkingPlace(parkingLotId);

        parkingLotRepository.deductAvailableCountById(parkingLotId);

        parkingPlaceRepository.markParkingPlaceAsUnavailable(parkingLotId, nextAvailablePlace.getId());

        OrderResponse orderResponse =  orderRepository.createNewOrder(now, nextAvailablePlace, orderRequest);

        return orderResponse;
    }

	public List<OrderDetail> getOrderListByCustomer(Integer customerId) {
        return orderRepository.findJointDetailByCustomerId(customerId);
	}
}