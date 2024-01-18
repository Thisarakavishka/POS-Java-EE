package com.example.pos.servlet;

import com.example.pos.bo.BOFactory;
import com.example.pos.bo.bos.OrderBO;
import com.example.pos.dto.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    Logger logger = LoggerFactory.getLogger("com.example.pos.servlet.OrderServlet");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order doGet-------------------------");

        resp.setContentType("application/json");
        if (req.getParameter("orderId") != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(orderBO.searchOrder(req.getParameter("orderId")));
                resp.getWriter().write(json);
                logger.info("Order Fetched");
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(orderBO.getAllOrders());
                resp.getWriter().write(json);
                logger.info("Orders Fetched");
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order doPost-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(req.getInputStream(), OrderDTO.class);

        try {
            String message = orderBO.createOrder(orderDTO) ? "Order Saved" : "Order not Saved";
            resp.getWriter().write(message);
            logger.info(message);
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order doPut-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = objectMapper.readValue(req.getInputStream(), OrderDTO.class);

        try {
            String message = orderBO.updateOrder(orderDTO) ? "Order Updated" : "Order not Updated";
            resp.getWriter().write(message);
            logger.info(message);
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order doDelete-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            if (orderBO.deleteOrder(req.getParameter("orderId"))) {
                resp.getWriter().write("Order Deleted");
                logger.info("Order Deleted");
            } else {
                resp.getWriter().write("Order not Deleted");
                logger.info("Order not Deleted");
            }
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
        }
    }
}
