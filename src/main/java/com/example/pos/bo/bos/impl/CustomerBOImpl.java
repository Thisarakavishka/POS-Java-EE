package com.example.pos.bo.bos.impl;

import com.example.pos.bo.bos.CustomerBO;
import com.example.pos.dao.DAOFactory;
import com.example.pos.dao.daos.CustomerDAO;
import com.example.pos.dto.CustomerDTO;
import com.example.pos.entity.Customer;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerBOImpl implements CustomerBO {
    ModelMapper modelMapper = new ModelMapper();
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean createCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.add(modelMapper.map(dto, Customer.class));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.update(modelMapper.map(dto, Customer.class));
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        return modelMapper.map(customerDAO.search(id), CustomerDTO.class);
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> customers = customerDAO.getAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }
}
