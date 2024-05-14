package com.backend;

import java.util.ArrayList;

public class Orders {

    private ArrayList<Item> inputItems;
    private ArrayList<Integer> inputQuantities;
    private int paymentType;
    private int orderNo;
    public Orders(Customer customer, ArrayList<Item> inputItems, ArrayList<Integer> inputQuantities, int paymentType) {
        this.inputItems = inputItems;
        this.inputQuantities = inputQuantities;
        this.paymentType = paymentType;
        orderNo =JsonEditor.getNumberOfOrders() +1;
        
    }

    

}
