package com.example;
import java.util.*;

import com.backend.Customer;
import com.backend.Item;
import com.backend.OnlineCustomer;
import com.backend.Shop;;

public class CustomerInput {

    public static void run() {
        // TODO Auto-generated method stub
        int choice =0;
        Scanner sc = new Scanner(System.in);
        while (choice ==-1) {
            System.out.println("1. Use as a New Customer");
            System.out.println("2. Use as a Old Customer");
            choice =sc.nextInt();
            if(choice ==1){
                //TODO:Write the logic
            }
            else if (choice== 2 ){
                System.out.println("Enter your customerID");
                int inputID = sc.nextInt();
                if (!Customer.verifyId(inputID)) {
                    System.out.println("XXX Customer ID invalid XXX");
                    continue;
                } else {
                    System.out.println("Valid Customer ID!");
                }
                System.out.println("1. Online Customer");
                System.out.println("2. Offline Customer");
                if (choice == 1) {
                    System.out.println("1. Buy");
                    System.out.println("2. Return");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        ArrayList<Item> inputBarcodes = new ArrayList<Item>();
                        ArrayList<Integer> inputQuantities = new ArrayList<Integer>();
                        int inputBarcode =0,inputQuantity;
                        OnlineCustomer customer =null;;
                        while (inputBarcode>0) {
                            System.out.print("Enter the barcode of the item you want to buy. Enter -1 to exit ");
                            inputBarcode = sc.nextInt(); 
                            if(inputBarcode<=0){
                                break;
                            }
                            if(!Shop.barcodeExsists(inputBarcode)){
                                System.out.println("Not a valid barcode");
                                continue;
                            }

                            System.out.print("Enter the quantity of the item you want to buy ");
                            inputQuantity = sc.nextInt();
                            Item item = new Item(inputBarcode);
                            inputBarcodes.add(item);
                            inputQuantities.add(inputQuantity);
                            
                        }
                        System.out.print("Pickup/Delivery [1/2] ");
                        int inputMethod = sc.nextInt();
                        if (inputMethod == 1) {
                            String inputAddress = sc.nextLine();
                            customer = new OnlineCustomer(inputID, OnlineCustomer.pickup);

                        } else if (inputMethod == 2) {
                            customer = new OnlineCustomer(inputID, OnlineCustomer.delivery);
                            if (!customer.hasAddress()) {
                                System.out.println("Enter your address [Write in one line] ");
                                String address = sc.nextLine();
                                customer.setAddress(address);
                            }
                        }
                        customer.buyItems(inputBarcodes, inputQuantities);

                    }
                }
                if (choice == 2) {
                    System.out.println("1. Buy");
                    System.out.println("2. Return");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        System.out.println("Enter the barcode of the item you want to buy");
                        int inputBarcode = sc.nextInt();
                        System.out.println("Enter the quantity of the item you want to buy");
                        int inputQuantity = sc.nextInt();
                        System.out.print("Payment method Cash/Card[1/2] ");
                        int paymentType = sc.nextInt();
                        Customer customer = new Customer(inputID);
                        //TODO:Convert it into Array
                        //customer.buyItem(inputBarcode, inputQuantity, paymentType);

                    } else if (choice == 2) {
                        System.out.println("Enter the barcode of the item you want to return");
                        int inputBarcode = sc.nextInt();
                        System.out.println("Enter the quantity of the item you want to return [-1 for all]");
                        int inputQuantity = sc.nextInt();
                        Customer customer = new Customer(inputID);
                        if (inputQuantity == -1) {
                            customer.returnItem(inputBarcode);
                        } else if (inputQuantity > 0) {
                            customer.returnItem(inputBarcode, inputQuantity);
                        }

                    }
                }
            }
        }
    }

}
