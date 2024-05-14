package com.example;

import com.admin.Owner;
import com.backend.Item;
import com.backend.JsonEditor;
import com.backend.Shop;
import com.google.gson.Gson;
import java.util.Scanner;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int choice = 0;
    JsonEditor.init();
    while (choice >=0) {
      System.out.println("Welcome to the Grocery Store");
      System.out.println("Please select your chocie by selecting the corresponding number");
      System.out.println("YOU CAN STOP BY ENTERING -1");
      System.out.println("1. Use as a Customer");
      System.out.println("2. Use as the  Owner");
      choice = sc.nextInt();
      if (choice == 1) {
        CustomerInput.run();
      } else if (choice == 2) {
        System.out.println("1. Get order status");
        System.out.println("2. Add Item");
        System.out.println("3.Get Stats");
        System.out.println("4.Remove an Order from current status");
        System.out.println("5. Remove Item");
        choice = sc.nextInt();
        if (choice == 1) {
          Shop.getPendingOrders();
        } else if (choice == 2) {
          int userinput = 0;
          ArrayList<Item> items = new ArrayList<>();
          while (userinput != -1) {

            System.out.println("Enter barcode");
            int barcode = sc.nextInt();
            System.out.println("Enter item name");
            String itemName = sc.next();
            while (Item.doesItemExsist(barcode)) {
              System.out.println("Not a valid barcode try again");
              barcode = sc.nextInt();
            }
            System.out.println("Enter cost price");
            double cp = sc.nextInt();
            System.out.println("Enter sale price");
            double sp = sc.nextInt();
            Item item = new Item(barcode, itemName, cp, sp);
            items.add(item);
            System.out.println("Do you want to continue?Enter any number to continue and -1 to EXIT");
            userinput =sc.nextInt();
          }
          Shop.addItems(items);
          System.out.println("true");
        } else if (choice == 3) {
          double fututreProfit = 0, currentProfit = 0, currentSale = 0;
          for (Item item : JsonEditor.getCurrentOrderItems()) {
            fututreProfit += item.getSalePrice() - item.getCostPrice();
          }
          for (Item item : JsonEditor.getOrderItems()) {
            currentProfit += item.getSalePrice() - item.getCostPrice();
            currentSale += item.getCostPrice();
          }
          System.out.println("Your futue profit is " + fututreProfit);
          System.out.println("Your current profit is " + currentProfit);
          System.out.println("You sold good worth of " + currentSale);

        } else if (choice == 4) {
          System.out.println("Enter the order number which you want to remove from the current list");
          int orderNo = sc.nextInt();
          Shop.removeCurrentOrder(orderNo);
        } else if(choice ==5){
          System.out.println("Enter the barcode of the item you want to remove");
          int barcode = sc.nextInt();
          Shop.removeItem(barcode);
          System.out.println("done!");

        }
      }
      System.out.println();
    }

  }
}
