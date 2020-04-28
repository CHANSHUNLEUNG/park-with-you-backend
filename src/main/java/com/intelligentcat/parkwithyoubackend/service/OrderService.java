package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.*;
import com.intelligentcat.parkwithyoubackend.repository.*;
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
    private CouponRepository couponRepository;

    @Autowired
    public OrderService(CustomerRepository customerRepository,
                        ParkingLotRepository parkingLotRepository,
                        ParkingPlaceRepository parkingPlaceRepository,
                        OrderRepository orderRepository,
                        CouponRepository couponRepository){
        this.customerRepository = customerRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.orderRepository = orderRepository;
        this.couponRepository = couponRepository;
    }

    public OrderResponse addNewBooking(Integer parkingLotId, OrderRequest orderRequest){
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        ParkingPlace nextAvailablePlace = parkingPlaceRepository.getNextAvailableParkingPlace(parkingLotId);

        parkingLotRepository.deductAvailableCountById(parkingLotId);

        parkingPlaceRepository.markParkingPlaceAsUnavailable(parkingLotId, nextAvailablePlace.getId());

        OrderResponse orderResponse =  orderRepository.createNewOrder(now, nextAvailablePlace, orderRequest);

        couponRepository.createNewCoupon(orderRequest.getCustomerId(), orderResponse.getOrderId());

        return orderResponse;
    }


    public Order extendCurrentBooking(Integer orderId, ExtendOrderRequest order) {
        Order currentOrder = orderRepository.getOrderById(orderId);

        return orderRepository.extendParkingBookingTime(currentOrder, order.getDuration());
    }


	public List<OrderDetail> getOrderListByCustomer(Integer customerId) {
        return orderRepository.findJointDetailByCustomerId(customerId);
	}
}