package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.model.CustomerRequest;
import com.intelligentcat.parkwithyoubackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/{userName}/login")
    public Customer login(@PathVariable("userName") String userName,
                          @RequestBody CustomerRequest customerRequest) {
        return customerService.verifyUserNamePassword(userName, customerRequest.getPassword());
    }
}
