package com.example.pos.bo;

import com.example.pos.bo.bos.impl.ItemBOImpl;

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
                return null;  //need to change
            case ORDER:
                return null;  //need to change
            default:
                return null;
        }
    }
}
