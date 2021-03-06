package com.intelligentcat.parkwithyoubackend.repository;

import com.intelligentcat.parkwithyoubackend.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
            customer.setBankAccount(resultSet.getString("bank_account"));
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


    public boolean createNewUser(String newUserName, String newPassword, String newBankAccount) {
        final String sql = "insert into customer (name,password,bank_account) values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {

            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, newUserName);
                            ps.setString(2, newPassword);
                            ps.setString(3, newBankAccount);
                            return ps;
                        }
                    },
                    keyHolder


            );

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean updateUserAccount(Integer id, String updatedUserName, String updatedPassword, String updatedBankAccount) {
        final String sql = "update customer set name = ?, password = ?, bank_account = ? where id = ?;";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {

            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            ps.setString(1, updatedUserName);
                            ps.setString(2, updatedPassword);
                            ps.setString(3, updatedBankAccount);
                            ps.setInt(4, id);
                            return ps;
                        }
                    },
                    keyHolder


            );

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
