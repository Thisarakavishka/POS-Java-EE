package com.example.pos.servlet;

import com.example.pos.bo.BOFactory;
import com.example.pos.bo.bos.ItemBO;
import com.example.pos.dto.ItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "item", urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("itemCode");
        resp.setContentType("application/json");
        if (itemCode != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(itemBO.searchItem(itemCode));
                resp.getWriter().write(json);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(itemBO.getAllItems());
                resp.getWriter().write(json);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Item doPost-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ItemDTO itemDTO = objectMapper.readValue(req.getInputStream(), ItemDTO.class);

        try {
            resp.getWriter().write(itemBO.createItem(itemDTO) ? "Item Saved" : "Item Not Saved");
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Item doPut-------------------------");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ItemDTO itemDTO = objectMapper.readValue(req.getInputStream(), ItemDTO.class);

        try {
            resp.getWriter().write(itemBO.updateItem(itemDTO) ? "Item Updated" : "Item Not Updated");
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Item doDelete-------------------------");

        if (req.getParameter("itemCode") == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        try {
            if (itemBO.deleteItem(req.getParameter("itemCode"))) {
                resp.getWriter().write("Item deleted");
            } else {
                resp.getWriter().write("Item not deleted");
            }
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
        }
    }
}
