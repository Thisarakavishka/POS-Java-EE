package com.example.pos.bo.bos;

import com.example.pos.bo.SuperBO;
import com.example.pos.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    boolean createItem(ItemDTO dto) throws Exception;

    boolean updateItem(ItemDTO dto) throws Exception;

    ItemDTO searchItem(String id) throws Exception;

    boolean deleteItem(String id) throws Exception;

    List<ItemDTO> getAllItems() throws Exception;
}
