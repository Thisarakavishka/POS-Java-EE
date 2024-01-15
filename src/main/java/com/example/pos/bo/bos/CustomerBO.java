package com.example.pos.bo.bos;

import com.example.pos.bo.SuperBO;
import com.example.pos.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean createCustomer(CustomerDTO dtO) throws Exception;

    boolean updateCustomer(CustomerDTO dtO) throws Exception;

    CustomerDTO searchCustomer(String id) throws Exception;

    boolean deleteCustomer(String id) throws Exception;

    List<CustomerDTO> getAllCustomers() throws Exception;
}
