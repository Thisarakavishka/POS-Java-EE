package com.example.pos.dao.daos.impl;

import com.example.pos.dao.SQLUtil;
import com.example.pos.dao.daos.CustomerDAO;
import com.example.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer dto) throws Exception {
        return SQLUtil.execute("INSERT INTO customer (customerId, customerName, address, salary) VALUES (?,?,?,?)", dto.getCustomerId(), dto.getCustomerName(), dto.getAddress(), dto.getSalary());
    }

    @Override
    public boolean update(Customer dto) throws Exception {
        return SQLUtil.execute("UPDATE customer SET customerName=?, address=?, salary=? WHERE customerId=?", dto.getCustomerName(), dto.getAddress(), dto.getSalary(), dto.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.execute("DELETE FROM customer WHERE customerId=?", id);
    }

    @Override
    public Customer search(String id) throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customerId=?", id);
        resultSet.next();
        return new Customer(resultSet.getString("customerId"), resultSet.getString("customerName"), resultSet.getString("address"), resultSet.getDouble("salary"));
    }

    @Override
    public List<Customer> getAll() throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(new Customer(resultSet.getString("customerId"), resultSet.getString("customerName"), resultSet.getString("address"), resultSet.getDouble("salary")));
        }
        return customers;
    }
}
