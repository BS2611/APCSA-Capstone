package com.backend;

public class Item {

    private int barcode;
    private String itemName;
    private double costPrice;
    private double salePrice;

    public double getSalePrice() {
        return salePrice;
    }
    public Item(int barcode, String itemName, double costPrice, double salePrice) {
        this.barcode = barcode;
        this.itemName = itemName;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        
    }
    public Item(int barcode){
        this.barcode = barcode;
        Item newItem = JsonEditor.getItems(barcode);
        this.itemName = newItem.itemName;
        this.costPrice = newItem.costPrice;
        this.salePrice = newItem.salePrice;
    }
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
    public static boolean doesItemExsist(int barcode){
        if(JsonEditor.getBarcodes().contains(barcode)){
            return true;
        }
        return false;
    }

    public String toString(){
        return barcode +"+"+itemName+"+"+costPrice+"+"+salePrice;
    }
    public static Item toItem(String str){
        str=str.substring(1,str.length()-1);
        String newStr[] = str.split("\\+");
        Item item = new Item(Integer.parseInt(newStr[0]), newStr[1], Double.parseDouble(newStr[2]), Double.parseDouble(newStr[3]));
        return item;
    }

}