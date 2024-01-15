package com.example.pos.bo.bos;

import com.example.pos.bo.SuperBO;
import com.example.pos.dto.OrderDTO;

import java.util.List;

public interface OrderBO extends SuperBO {
    boolean createOrder(OrderDTO dtO) throws Exception;

    boolean updateOrder(OrderDTO dtO) throws Exception;

    OrderDTO searchOrder(String id) throws Exception;

    boolean deleteOrder(String id) throws Exception;

    List<OrderDTO> getAllOrders() throws Exception;
}
