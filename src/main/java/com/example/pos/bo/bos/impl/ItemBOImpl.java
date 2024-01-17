package com.example.pos.bo.bos.impl;

import com.example.pos.bo.bos.ItemBO;
import com.example.pos.dao.DAOFactory;
import com.example.pos.dao.daos.ItemDAO;
import com.example.pos.dto.ItemDTO;
import com.example.pos.entity.Item;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {

    ModelMapper modelMapper = new ModelMapper();
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean createItem(ItemDTO dto) throws Exception {
        return itemDAO.add(modelMapper.map(dto, Item.class));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws Exception {
        return itemDAO.update(modelMapper.map(dto, Item.class));
    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        return modelMapper.map(itemDAO.search(id), ItemDTO.class);
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
        List<Item> items = itemDAO.getAll();
        return items.stream().map(item -> modelMapper.map(item, ItemDTO.class)).collect(Collectors.toList());
    }
}
