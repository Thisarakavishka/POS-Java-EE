package com.example.pos.dao;

import com.example.pos.dao.daos.impl.CustomerDAOImpl;
import com.example.pos.dao.daos.impl.ItemDAOImpl;
import com.example.pos.dao.daos.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {ITEM, CUSTOMER, ORDER}

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
