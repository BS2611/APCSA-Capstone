package com.backend;

import com.admin.Owner;

import java.util.ArrayList;

public class Shop {

    public static void addItems(ArrayList<Item> items)  {
        for(Item item : items){
            //TODO: Parse to Json file 
            JsonEditor.addItem(item);
        }
    }
       

    public static void newOrder(Customer customer, ArrayList<Item> inputItems, ArrayList<Integer> inputQuantity,
            int paymentType) {
       JsonEditor.storeOrder(customer,inputItems,inputQuantity,paymentType);

    }
    public static void getPendingOrders(){
        JsonEditor.showPendingOrders();

    }


    public static void removeCurrentOrder(int orderNo) {
        JsonEditor.removeOrderFromCurrent(orderNo);
    }


    public static void removeItem(int barcode) {
        JsonEditor.removeItem(barcode);
    }

}
