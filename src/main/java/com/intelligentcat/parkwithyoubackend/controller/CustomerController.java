package com.intelligentcat.parkwithyoubackend.controller;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.model.CustomerRequest;
import com.intelligentcat.parkwithyoubackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public Customer login(@RequestBody CustomerRequest customerRequest) {
        return customerService.login(customerRequest.getUserName(), customerRequest.getPassword());
    }

    @GetMapping
    public List<String> returnAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAccount(@RequestBody Customer customer) {
        boolean isCreateSuccess = customerService.createUserAccount(customer);
        if (!isCreateSuccess) {
            return new ResponseEntity<>("Error, cannot create account.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInfo(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        boolean isUpdatedSuccess = customerService.updateUserAccountInfo(id, customer);
        if (!isUpdatedSuccess) {
            return new ResponseEntity<>("Error, cannot update user information.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
