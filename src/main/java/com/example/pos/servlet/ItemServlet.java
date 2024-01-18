package com.example.pos.servlet;

import com.example.pos.bo.BOFactory;
import com.example.pos.bo.bos.ItemBO;
import com.example.pos.dto.ItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "item", urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    Logger logger = LoggerFactory.getLogger("com.example.pos.servlet.ItemServlet");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("itemCode");
        resp.setContentType("application/json");
        if (itemCode != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(itemBO.searchItem(itemCode));
                resp.getWriter().write(json);
                logger.info("Item Fetched");
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(itemBO.getAllItems());
                resp.getWriter().write(json);
                logger.info("Items Fetched");

            } catch (Exception e) {
                logger.info(e.getMessage());
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
            String message = itemBO.createItem(itemDTO) ? "Item Saved" : "Item Not Saved";
            resp.getWriter().write(message);
            logger.info(message);
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
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
            String message = itemBO.updateItem(itemDTO) ? "Item Updated" : "Item Not Updated";
            resp.getWriter().write(message);
            logger.info(message);
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
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
                logger.info("Item Deleted");
            } else {
                resp.getWriter().write("Item not deleted");
                logger.info("Item not Deleted");
            }
        } catch (Exception e) {
            resp.getWriter().write("Something Wrong");
            logger.info(e.getMessage());
        }
    }
}
