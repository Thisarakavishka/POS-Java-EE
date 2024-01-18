package com.example.pos.dao.daos.impl;

import com.example.pos.dao.SQLUtil;
import com.example.pos.dao.daos.OrderDAO;
import com.example.pos.entity.Customer;
import com.example.pos.entity.Item;
import com.example.pos.entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order dto) throws Exception {
        boolean isOrderAdded = SQLUtil.execute("INSERT INTO orders (order_id, date, total, discount, balance, customer_id) VALUES (?,?,?,?,?,?)", dto.getOrderId(), dto.getDate(), dto.getTotal(), dto.getDiscount(), dto.getBalance(), dto.getCustomer().getCustomerId());
        if (isOrderAdded) {
            for (Item item : dto.getItems()) {
                addOrderItem(dto.getOrderId(), item.getItemCode());
            }
        }
        return isOrderAdded;
    }

    @Override
    public boolean update(Order dto) throws Exception {
        boolean isUpdated = SQLUtil.execute("UPDATE orders SET date=?, total=?, discount=?, balance=?, customer_id=? WHERE order_id = ?", dto.getDate(), dto.getTotal(), dto.getDiscount(), dto.getBalance(), dto.getCustomer().getCustomerId(), dto.getOrderId());
        if (isUpdated) {
            deleteOrderItems(dto.getOrderId());
            for (Item item : dto.getItems()) {
                addOrderItem(dto.getOrderId(), item.getItemCode());
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(String id) throws Exception {
        boolean isOrderDeleted = SQLUtil.execute("DELETE FROM orders WHERE order_id=?", id);
        if (isOrderDeleted){
            deleteOrderItems(id);
        }
        return isOrderDeleted;
    }

    @Override
    public Order search(String id) throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders WHERE order_id=?", id);
        if (resultSet.next()) {
            return extractOrderFromResultSet(resultSet);
        }
        return null;
    }

    @Override
    public List<Order> getAll() throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders");
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            orders.add(extractOrderFromResultSet(resultSet));
        }
        return orders;
    }

    private void addOrderItem(String orderId, String itemCode) throws Exception {
        SQLUtil.execute("INSERT INTO order_items (order_id, item_id) VALUES (?, ?)", orderId, itemCode);
    }

    private void deleteOrderItems(String orderId) throws Exception {
        SQLUtil.execute("DELETE FROM order_items WHERE order_id=?", orderId);
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setOrderId(resultSet.getString("order_id"));
        order.setDate(resultSet.getDate("date").toString());
        order.setTotal(resultSet.getDouble("total"));
        order.setDiscount(resultSet.getDouble("discount"));
        order.setBalance(resultSet.getDouble("balance"));

        Customer customer = fetchCustomerDetails(resultSet.getString("customer_Id"));
        order.setCustomer(customer);

        List<Item> items = fetchItemsForOrder(resultSet.getString("order_id"));
        order.setItems(items);

        return order;
    }

    private Customer fetchCustomerDetails(String customerId) throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customerId=?", customerId);
        if (resultSet.next()) {
            return new Customer(resultSet.getString("customerId"), resultSet.getString("customerName"), resultSet.getString("address"), resultSet.getDouble("salary"));
        }
        return null;
    }

    private List<Item> fetchItemsForOrder(String orderId) throws Exception {
        ResultSet itemResultSet = SQLUtil.execute("SELECT * FROM order_items WHERE order_id=?", orderId);
        List<Item> items = new ArrayList<>();
        while (itemResultSet.next()) {
            Item item = fetchItemDetails(itemResultSet.getString("item_id"));
            items.add(item);
        }
        return items;
    }

    private Item fetchItemDetails(String itemCode) throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE itemCode=?", itemCode);
        if (resultSet.next()) {
            return new Item(resultSet.getString("itemCode"), resultSet.getString("itemName"), resultSet.getInt("qty"), resultSet.getDouble("price"));
        }
        return null;
    }
}
