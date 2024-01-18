package com.example.pos.dao.daos.impl;

import com.example.pos.dao.daos.OrderDAO;
import com.example.pos.entity.Order;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(Order dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Order search(String id) throws Exception {
        return null;
    }

    @Override
    public List<Order> getAll() throws Exception {
        return null;
    }
}
