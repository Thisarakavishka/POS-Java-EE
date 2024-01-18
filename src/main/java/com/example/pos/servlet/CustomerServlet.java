package com.example.pos.servlet;

import com.example.pos.bo.BOFactory;
import com.example.pos.bo.bos.CustomerBO;
import com.example.pos.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "customer", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    Logger logger = LoggerFactory.getLogger("com.example.pos.servlet.CustomerServlet");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Customer doGet-------------------------");

        resp.setContentType("application/json");
        if (req.getParameter("customerId") != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(customerBO.searchCustomer(req.getParameter("customerId")));
                resp.getWriter().write(json);
                logger.info("Customer Fetched");
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(customerBO.getAllCustomers());
                resp.getWriter().write(json);
                logger.info("Customers Fetched");
            } catch (Exception e) {
                logger.info(e.getMessage());
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

        try {
            String message = customerBO.createCustomer(customerDTO) ? "Customer Saved" : "Customer not Saved";
            resp.getWriter().write(message);
            logger.info(message);

        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
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
            String message = customerBO.updateCustomer(customerDTO) ? "Customer Updated" : "Customer not Updated";
            resp.getWriter().write(message);
            logger.info(message);
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
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
            if (customerBO.deleteCustomer(req.getParameter("customerId"))) {
                resp.getWriter().write("Customer Deleted");
                logger.info("Customer deleted");
            } else {
                resp.getWriter().write("Customer not Deleted");
                logger.info("Customer not deleted");
            }
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
        }
    }
}
