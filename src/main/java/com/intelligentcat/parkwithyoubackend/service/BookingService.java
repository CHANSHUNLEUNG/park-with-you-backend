package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.model.OrderResponse;
import com.intelligentcat.parkwithyoubackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private CustomerRepository customerRepository;

    @Autowired
    public BookingService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Autowired
    public OrderResponse addNewBooking(){
        List<Customer> customerList = customerRepository.getAllCustomer();
        System.out.println(customerList.size());
        return new OrderResponse();
    }
}
