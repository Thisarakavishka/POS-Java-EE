package com.example.pos.servlet;

import com.example.pos.bo.BOFactory;
import com.example.pos.bo.bos.OrderBO;
import com.example.pos.dto.OrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Order doGet-------------------------");

        resp.setContentType("application/json");
        if (req.getParameter("orderId") != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(orderBO.searchOrder(req.getParameter("orderId")));
                resp.getWriter().write(json);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(orderBO.getAllOrders());
                resp.getWriter().write(json);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
            resp.getWriter().write(orderBO.createOrder(orderDTO) ? "Order Saved" : "Order not Saved");
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
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
            resp.getWriter().write(orderBO.updateOrder(orderDTO) ? "Order Updated" : "Order not Updated");
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
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
            if (orderBO.deleteOrder(req.getParameter("orderId"))){
                resp.getWriter().write("Order Deleted");
            }else {
                resp.getWriter().write("Order not Deleted");
            }
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }
}
