package com.example.pos.servlet;

import com.example.pos.bo.BOFactory;
import com.example.pos.bo.bos.CustomerBO;
import com.example.pos.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "customer",urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Customer doGet-------------------------");

        resp.setContentType("application/json");
        if(req.getParameter("customerId") != null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(customerBO.searchCustomer(req.getParameter("customerId")));
                resp.getWriter().write(json);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(customerBO.getAllCustomers());
                resp.getWriter().write(json);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Customer doPost-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO customerDTO = objectMapper.readValue(req.getInputStream(), CustomerDTO.class);

        System.out.println(customerDTO);

        try {
            resp.getWriter().write(customerBO.createCustomer(customerDTO) ? "Customer Saved":"Customer not Saved");
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Customer doPut-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO customerDTO = objectMapper.readValue(req.getInputStream(), CustomerDTO.class);

        try {
            resp.getWriter().write(customerBO.updateCustomer(customerDTO) ? "Customer Updated" : "Customer not Updated");
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Customer doDelete-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            if(customerBO.deleteCustomer(req.getParameter("customerId"))){
                resp.getWriter().write("Customer Deleted");
            }else {
                resp.getWriter().write("Customer not Deleted");
            }
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }
}
