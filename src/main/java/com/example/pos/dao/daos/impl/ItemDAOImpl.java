package com.example.pos.dao.daos.impl;

import com.example.pos.dao.SQLUtil;
import com.example.pos.dao.daos.ItemDAO;
import com.example.pos.entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item dto) throws Exception {
        return SQLUtil.execute("INSERT INTO item (itemCode, itemName, qty, price) VALUES (?,?,?,?)", dto.getItemCode(), dto.getItemName(), dto.getQty(), dto.getPrice());
    }

    @Override
    public boolean update(Item dto) throws Exception {
        return SQLUtil.execute("UPDATE item SET itemName=? ,qty=? ,price=? WHERE itemCode = ?", dto.getItemName(), dto.getQty(), dto.getPrice(), dto.getItemCode());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return SQLUtil.execute("DELETE FROM item WHERE itemCode=?", id);
    }

    @Override
    public Item search(String id) throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE itemCode=?", id);
        resultSet.next();
        return new Item(resultSet.getString("itemCode"), resultSet.getString("itemName"), resultSet.getInt("qty"), resultSet.getDouble("price"));
    }

    @Override
    public List<Item> getAll() throws Exception {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");
        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(new Item(resultSet.getString("itemCode"), resultSet.getString("itemName"), resultSet.getInt("qty"), resultSet.getDouble("price")));
        }
        return items;
    }
}
