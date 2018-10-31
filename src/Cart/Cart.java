package Cart;

import User.User;

public class Cart {
    private static String productname;
    private static int size;
    private static int quantity;
    private static int price;
    private static User user;
    
    public Cart(String productname, int size, int quantity, int price, User user){
        this.productname = productname;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.user = user;
    }
    public static String getProductname(){
        return productname;
    }public static int getSize(){
        return size;
    }public static int getQuantity(){
        return quantity;
    }public static int getPrice(){
        return price;
    }public static User getUser(){
        return user;
    }
}
