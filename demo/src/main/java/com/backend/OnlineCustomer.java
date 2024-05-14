package com.backend;

import java.util.ArrayList;

public class OnlineCustomer extends Customer {

    final static public int delivery = 0;
    final static public int pickup = 1;

    public OnlineCustomer(int id, int orderType) {
        super(id);
    }

    public void buyItems(ArrayList<Item> inputItems, ArrayList<Integer> inputQuantities) {
        
        super.buyItems(inputItems, inputQuantities,Customer.CARD);

    }

    public void setAddress(String address) {
        
    }

    public boolean hasAddress() {
        return (super.getAddress().equals(null)|| super.getAddress().equals(""));
    }
}
