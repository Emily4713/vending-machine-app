package com.techelevator;

public class Product {
    private String type;
    private String slotLocation;
    private String productName;
    private double price;
    private int stock;
    String productSound;
    // the stock when starting the application will ALWAYS be 5
    private final int STARTING_STOCK = 5;

    // We can instantiate this class to make a product

    public Product(String slotLocation, String productName, double price, String type ){
        this.type = type;
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.stock = STARTING_STOCK;
        setSound(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSound(String type){
        if (type.equalsIgnoreCase("gum")) this.productSound = "Chew Chew, Yum!";
        if (type.equalsIgnoreCase("candy")) this.productSound = "Munch Munch, Yum!";
        if (type.equalsIgnoreCase("chip")) this.productSound = "Crunch Crunch, Yum!";
        if (type.equalsIgnoreCase("drink")) this.productSound = "Glug Glug, Yum!";
    }
    public String getProductSound(){
        return productSound;
    }


    /* if stock is greater than 0 then it will remove 1 stock and return true
    otherwise it will print sold out and return false which will not remove 1 stock and will not
    charge the customer
     */
    public boolean subtractStock(){
        if (this.stock > 0){
            stock = stock - 1;
            return true;
        } else {
            System.out.println("SOLD OUT");
            return false;
        }
    }




}
