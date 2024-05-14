package com.backend;

import java.util.ArrayList;

public class Customer {
    final static public int CASH =1;
    final static public  int CARD =2;
    private String address;
    private int id;
    private String firstName;
    public String getFirstName() {
        return firstName;
    }

    private String lastName;
    public String getLastName() {
        return lastName;
    }
    protected int getId() {
        return id;
    }
    public void printId(){
        System.out.println(this.id);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer(int id ) {
        this.id = id;
        if (verifyId(id)) {
            String[] customer = JsonEditor.getCustomer(id);
            this.firstName = customer[0];
            this.lastName = customer[1];
            this.address = customer[2];
        }


    }


    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.id = generateNewID();
        save();
        
    }

    private void save() {
     JsonEditor.saveCustomer(this)   ;
    }

    public static boolean verifyId(int id) {
         ArrayList<Integer> allUserIds = JsonEditor.getAllUserIds();
        if(allUserIds.contains(id)){
            return true;
        }
        return false;
    }

    public static int generateNewID(){
        int newId =1000;
        while (verifyId(newId)) {
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
