package com.example.pos.bo.bos.impl;

import com.example.pos.bo.bos.OrderBO;
import com.example.pos.dao.DAOFactory;
import com.example.pos.dao.daos.OrderDAO;
import com.example.pos.dto.OrderDTO;
import com.example.pos.entity.Order;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class OrderBOImpl implements OrderBO {
    ModelMapper modelMapper = new ModelMapper();
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean createOrder(OrderDTO dto) throws Exception {
        return orderDAO.add(modelMapper.map(dto, Order.class));
    }

    @Override
    public boolean updateOrder(OrderDTO dto) throws Exception {
        return orderDAO.update(modelMapper.map(dto, Order.class));
    }

    @Override
    public OrderDTO searchOrder(String id) throws Exception {
        return modelMapper.map(orderDAO.search(id), OrderDTO.class);
    }

    @Override
    public boolean deleteOrder(String id) throws Exception {
        return orderDAO.delete(id);
    }

    @Override
    public List<OrderDTO> getAllOrders() throws Exception {
        List<Order> orders = orderDAO.getAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }
}
