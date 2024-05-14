package com.backend;

import java.util.ArrayList;

public class Customer {
    final static public int CASH =1;
    final static public  int CARD =2;
    private String address;
    private int id;
    protected int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer(int id ) {
        this.id = id;

    }

    public static boolean verifyId(int id) {
        // TODO: Write the implemetation of this method

        return true;
    }

    public static int generateNewID(){
        // TODO: Write the implemetation of this method
        ArrayList<Integer> allUserIds = JsonEditor.getAllUserIds();
        int newId =0;
        while (!allUserIds.contains(newId)) {
            newId = (int)(Math.random()*(9999-1000+1))+1000;
        }
        return newId;
    }

    public void returnItem(int barcode, int quantity) {

    }

    public void returnItem(int barcode) {

    }

    public void buyItems(ArrayList<Item> inputItems, ArrayList<Integer> inputQuantities, int paymentType) {
        
        Shop.newOrder(this, inputItems,inputQuantities,paymentType);
        
    }

    private void registerNewOrder(Orders order) {
        
    }
    
}
