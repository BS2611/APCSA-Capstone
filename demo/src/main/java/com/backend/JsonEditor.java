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
    private static FileReader reader;
    private static FileWriter fileWriter;

    public static void init() {
        try {
            reader = new FileReader(path);
            // gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            Object obj = jsonParser.parse(reader);
            object = (JsonObject) obj;
            fileWriter = new FileWriter(path, true);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
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
                    JsonElement jsonElement3 = element.getAsJsonObject().get("id");
                    if (!jsonElement3.isJsonNull()) {
                        arrayList.add(jsonElement3.getAsInt());
                    }
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
        Item item = new Item(1, "a", 1, 2);
        Item item2 = new Item(1, "a", 1, 2);
        ArrayList<Item> it = new ArrayList<>();
        it.add(item);
        it.add(item2);
        System.out.println(it.toString());
        addItem(item2);
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
            jsonObject.addProperty("paymentType", String.valueOf(paymentType));
            jsonElement2.add(jsonObject);
            save();
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
                    String itemString = element.getAsJsonObject().get("items").getAsString();
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
                    if (element.getAsJsonObject().has("barcode")) {
                        int num = element.getAsJsonObject().get("barcode").getAsInt();
                        arrayList.add(num);
                    }

                }
            }

        }
        return arrayList;

    }

    public static Item getItems(int id) {
        ArrayList<Integer> arrayList = JsonEditor.getBarcodes();
        Item item = null;
        if (object.has("Item")) {
            JsonElement jsonElement = object.get("Item");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject() && !element.isJsonNull()) {
                    JsonObject nestedObj = new JsonObject();
                    System.out.println(element.toString());
                    if (element.getAsJsonObject().get("barcode").getAsInt() == id) {
                        item = new Item(id, element.getAsJsonObject().get("name").getAsString(),
                                element.getAsJsonObject().get("cost-price").getAsDouble(),
                                element.getAsJsonObject().get("sale-price").getAsDouble());
                    }

                }
            }

        }
        return item;

    }

    public static void addItem(Item item) {
        if (object.has("Item")) {
            JsonElement jsonElement = object.get("Item");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            System.out.println(jsonElement2.toString());
            JsonObject obj = new JsonObject();
            obj.addProperty("barcode", String.valueOf(item.getBarcode()));
            obj.addProperty("name", item.getItemName());
            obj.addProperty("cost-price", String.valueOf(item.getCostPrice()));
            obj.addProperty("sale-price", String.valueOf(item.getSalePrice()));
            jsonElement2.add(obj);
            System.out.println(jsonElement.toString());
            System.out.println(object.toString());
            save();

        }
    }

    public static String[] getCustomer(int id) {
        String res[] = new String[3];
        JsonElement jsonElement = object.get("Users");
        JsonArray array = jsonElement.getAsJsonArray();
        for (JsonElement element : array) {
            JsonObject obj = element.getAsJsonObject();
            if (obj.get("id").getAsInt() == id) {

                res[0] = obj.get("firstName").getAsString();
                res[1] = obj.get("lastName").getAsString();
                res[2] = obj.get("address").getAsString();

            }
        }
        return res;
    }

    private static void save() {

        try {
            new FileWriter(path);
            System.out.println(object.toString());
            fileWriter.write(object.toString());
            ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void saveCustomer(Customer customer) {
        if (object.has("Users")) {
            JsonElement jsonElement = object.get("Users");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            JsonObject obj = new JsonObject();
            ArrayList<Integer> allUserIds = JsonEditor.getAllUserIds();
            if (!allUserIds.contains(customer.getId())) {
                obj.addProperty("id", String.valueOf(customer.getId()));
                obj.addProperty("firstName", customer.getFirstName());
                obj.addProperty("lastName", customer.getLastName());
                obj.addProperty("address", customer.getAddress());
                jsonElement2.add(obj);
                System.out.println(jsonElement.toString());
                System.out.println(object.toString());
            } else {
                jsonElement2.remove(allUserIds.indexOf(customer.getId()));
                JsonObject newObj = new JsonObject();
                if (allUserIds.contains(customer.getId())) {
                    newObj.addProperty("id", String.valueOf(customer.getId()));
                    newObj.addProperty("firstName", customer.getFirstName());
                    newObj.addProperty("lastName", customer.getLastName());
                    newObj.addProperty("address", customer.getAddress());
                    jsonElement2.add(newObj);
                }

            }
            save();
        }
    }

    public static void showPendingOrders() {
        if (object.has("CurrentOrders")) {
            JsonElement jsonElement = object.get("CurrentOrders");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            System.out
                    .println("No" + "\t" + "Customer ID" + "\t" + "Items" + "\t" + "Quantity" + "\t" + "Payment Type");
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject() && !element.isJsonNull()) {
                    JsonObject nestedObj = element.getAsJsonObject();
                    System.out.print("" + i + 1 + "\t" + nestedObj.get("id").getAsString() + "\t");
                    String itemList = nestedObj.get("items").getAsString();
                    String quantityList = nestedObj.get("quantity").getAsString();
                    int paymentType = nestedObj.get("quantity").getAsInt();
                    String payment = "";
                    if (paymentType == Customer.CARD) {
                        payment = "card";
                    } else {
                        payment = "cash";
                    }

                    System.out.print(itemList + "\t" + quantityList + "\t" + payment);
                    System.out.println();
                }
            }
        }
    }

    public static void removeOrderFromCurrent(int orderIndex) {
        if (object.has("CurrentOrders")) {
            JsonElement jsonElement = object.get("CurrentOrders");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            System.out
                    .println("No" + "\t" + "Customer ID" + "\t" + "Items" + "\t" + "Quantity" + "\t" + "Payment Type");
            JsonObject obj = jsonElement2.get(orderIndex - 1).getAsJsonObject();
            JsonArray orders = object.get("Orders").getAsJsonArray();
            orders.add(obj);
            jsonElement2.remove(obj);
            save();
        }
    }

    public static void removeItem(int barcode) {
        Item item = null;
        if (object.has("Item")) {
            JsonElement jsonElement = object.get("Item");
            JsonArray jsonElement2 = jsonElement.getAsJsonArray();
            for (int i = 0; i < jsonElement2.size(); i++) {
                JsonElement element = jsonElement2.get(i);
                if (element.isJsonObject() && !element.isJsonNull()) {
                    JsonObject nestedObj = element.getAsJsonObject();
                    if (element.getAsJsonObject().get("barcode").getAsInt() == barcode) {
                        jsonElement2.remove(i);
                    }

                }
            }

        }
        save();
    }
}
