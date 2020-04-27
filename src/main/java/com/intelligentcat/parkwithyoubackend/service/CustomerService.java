package com.intelligentcat.parkwithyoubackend.service;

import com.intelligentcat.parkwithyoubackend.exception.InvalidAccountException;
import com.intelligentcat.parkwithyoubackend.exception.IncorrectPasswordException;
import com.intelligentcat.parkwithyoubackend.model.Customer;
import com.intelligentcat.parkwithyoubackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void verifyUserNamePassword(String userName, String password) {
        List<Customer> customers = customerRepository.getCustomerByName(userName);
        if (customers.size() == 0) {
            throw new InvalidAccountException();
        }
        Customer targetCustomer = customers.get(0);
        if(!targetCustomer.getPassword().equals(password)){
            throw new IncorrectPasswordException();
        }
        return;
    }

    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.getAllCustomer();
        return customers;
    }

    public boolean createUserAccount(Customer customer) {
        String newUserName = customer.getName();
        String newPassword = customer.getPassword();
        String newBankAccount = customer.getBank_account();
        if(newUserName != null && newPassword != null && newBankAccount != null) {
            return customerRepository.createNewUser(newUserName, newPassword, newBankAccount);

        }
        return false;
    }
}
