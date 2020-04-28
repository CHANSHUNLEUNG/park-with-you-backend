package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.exception.InvalidAccountException;
import com.intelligentcat.parkwithyoubackend.exception.IncorrectPasswordException;
import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.repository.CouponRepository;
import com.intelligentcat.parkwithyoubackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final String HIDDEN_PASSWORD = "******";
    private final Double DISCOUNT_AMOUNT = new Double(10);
    private CustomerRepository customerRepository;
    private CouponRepository couponRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CouponRepository couponRepository) {
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }

    public Customer login(String userName, String password) {
        List<Customer> customers = customerRepository.getCustomerByName(userName);
        if (customers.size() == 0) {
            throw new InvalidAccountException();
        }
        Customer targetCustomer = customers.get(0);
        if(!targetCustomer.getPassword().equals(password)){
            throw new IncorrectPasswordException();
        }
        targetCustomer.setPassword(HIDDEN_PASSWORD);

        Integer availableCouponCount = couponRepository.getAvailableCouponCountById(targetCustomer.getId());
        targetCustomer.setAvailableCouponCount(availableCouponCount);
        targetCustomer.setDiscount(DISCOUNT_AMOUNT);
        return targetCustomer;
    }

    public List<String> getAll() {
        List<Customer> customers = customerRepository.getAllCustomer();
        List<String> customerNames = customers.stream().map(Customer::getName).collect(Collectors.toList());
        return customerNames;
    }

    public boolean createUserAccount(Customer customer) {
        String newUserName = customer.getName();
        String newPassword = customer.getPassword();
        String newBankAccount = customer.getBankAccount();
        if(newUserName != null && newPassword != null && newBankAccount != null) {
            return customerRepository.createNewUser(newUserName, newPassword, newBankAccount);

        }
        return false;
    }

    public boolean updateUserAccountInfo(Integer id, Customer customer) {
        String updatedUserName = customer.getName();
        String updatedPassword = customer.getPassword();
        String updatedBankAccount = customer.getBankAccount();
        if(updatedUserName != null && updatedPassword != null && updatedBankAccount != null) {
            return customerRepository.updateUserAccount(id, updatedUserName, updatedPassword, updatedBankAccount);

        }
        return false;
    }

}
