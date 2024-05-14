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
    public static Item[] getItems(){
        //TODO: Change the logic
        return null;
    }

    public static boolean barcodeExsists(int inputBarcode) {
        // TODO Lookup for items
        return true;
    }
    

    public static void newOrder(Customer customer, ArrayList<Item> inputItems, ArrayList<Integer> inputQuantity,
            int paymentType) {
       JsonEditor.storeOrder(customer,inputItems,inputQuantity,paymentType);

    }
    public static void getPendingOrders(){

    }

}
