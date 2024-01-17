package com.example.pos.bo;

import com.example.pos.bo.bos.impl.CustomerBOImpl;
import com.example.pos.bo.bos.impl.ItemBOImpl;
import com.example.pos.bo.bos.impl.OrderBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {ITEM, CUSTOMER, ORDER}

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ITEM:
                return new ItemBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case ORDER:
                return new OrderBOImpl();
            default:
                return null;
        }
    }
}
