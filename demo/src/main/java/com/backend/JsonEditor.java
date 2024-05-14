package com.backend;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.*;

public class JsonEditor {
    private static JsonObject object;
    final private static String path = ".\\info.json";
    private static Gson gson;

    public static void init() {
        FileReader reader;
        try {
            reader = new FileReader(path);
            gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            Object obj = jsonParser.parse(reader);
            object = (JsonObject) obj;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void getJsonVal(String Key) {
        System.out.println();
        if (object.has(Key)) {
            // return object.get(key);
        }
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            JsonElement obj = (entry.getValue());
            System.out.println(entry.getValue());
            if (obj.isJsonArray()) {
                System.out.println(obj.getAsJsonArray());
            }

        }
    }

    public static void getNestedJsonVal(String key, String nestedKey) {
        System.out.println();
        if (object.has(key)) {
            JsonElement jsonElement = object.get(key);
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject()) {
                    JsonElement jsonElement3 = element.getAsJsonObject().get(nestedKey);
                    System.out.println(jsonElement3.toString());
                }

            }

        }
    }

    protected static ArrayList<Integer> getAllUserIds() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (object.has("Users")) {
            JsonElement jsonElement = object.get("Users");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject()) {
                    JsonElement jsonElement3 = element.getAsJsonObject().get("ID");
                    arrayList.add(jsonElement3.getAsInt());
                }

            }

        }
        return arrayList;
    }

    protected static int getNumberOfOrders() {
        JsonArray pastOrders;
        JsonElement jsonElement = object.get("Orders");
        pastOrders = jsonElement.getAsJsonArray();
        JsonElement jsonElement3 = object.get("CurrentOrders");
        JsonArray currentOrders = jsonElement.getAsJsonArray();
        return pastOrders.size() + currentOrders.size();

    }

    public static void main(String[] args) {
        init();
        getNestedJsonVal("A", "a");
        Item item = new Item(1,"a",1,2);
        Item item2 = new Item(1,"a",1,2);
        ArrayList<Item> it = new ArrayList<>();
        it.add(item);
        it.add(item2);
        System.out.println(it.toString());
    }

    public static void storeOrder(Customer customer, ArrayList<Item> inputItems, ArrayList<Integer> inputQuantity,
            int paymentType) {
        if (object.has("CurrentOrders")) {
            JsonElement jsonElement = object.get("CurrentOrders");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", String.valueOf(customer.getId()));
            jsonObject.addProperty("items", inputItems.toString());
            jsonObject.addProperty("quantity", inputQuantity.toString());
            jsonObject.addProperty("quantity", String.valueOf(paymentType));
            jsonElement2.add(jsonObject);
        }

    }

    public static ArrayList<Item> getCurrentOrderItems() {
        ArrayList<Item> arrayList = new ArrayList<>();
        if (object.has("CurrentOrders")) {
            JsonElement jsonElement = object.get("CurrentOrders");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject()) {
                    String itemString = element.getAsJsonObject().get("item").getAsString();
                    Item.toItem(itemString);


                }

            }

        }
        return arrayList;

    }

    public static ArrayList<Item> getOrderItems() {
        ArrayList<Item> arrayList = new ArrayList<>();
        if (object.has("Orders")) {
            JsonElement jsonElement = object.get("Orders");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject()) {
                    String itemString = element.getAsJsonObject().get("item").getAsString();
                    arrayList.add(Item.toItem(itemString));

                }
            }

        }
        return arrayList;

 
    }
    public static ArrayList<Integer> getBarcodes() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (object.has("Item")) {
            JsonElement jsonElement = object.get("Item");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject()) {
                   if(element.getAsJsonObject().has("barcode")){
                   int num  = element.getAsJsonObject().get("barcode").getAsInt();
                   arrayList.add(num);
                   }

                }
            }

        }
        return arrayList;

 
    }
    public static void addItem( Item item){
        if (object.has("Item")) {
            JsonElement jsonElement = object.get("Item");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            JsonObject obj = new JsonObject();
            obj.addProperty("barcode",String.valueOf(item.getBarcode()));
            obj.addProperty("name",item.getItemName());
            obj.addProperty("cost-price",String.valueOf(item.getBarcode()));
            obj.addProperty("sale-price",String.valueOf(item.getBarcode()));
            jsonElement2.add(obj);
            obj.add("Item", jsonElement);

        }
    }
}
