package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {
    private JdbcTemplate jdbcTemplate;

    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setPassword(resultSet.getString("password"));
            customer.setBank_account(resultSet.getString("bank_account"));
            return customer;
        }
    }

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> getAllCustomer() {
        final String sql = "select * from customer";
        return jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public List<Customer> getCustomerByName(String customerName) {
        final String sql = "select * from customer where name = ?;";
        return jdbcTemplate.query(sql, new Object[]{customerName}, new CustomerRowMapper());
    }
}
