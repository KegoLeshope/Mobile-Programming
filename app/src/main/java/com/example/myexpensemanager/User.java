package com.example.myexpensemanager;

public class User {
    private String Item;
    private String Category;
    private String Amount;
    private String Date;

    public User (String item, String category, String amount, String date){
        Item = item;
        Category = category;
        Amount = amount;
        Date = date;
    }

    public String getItem() {
        return Item;
    }

    public String getCategory() {
        return Category;
    }

    public String getAmount() {
        return Amount;
    }

    public String getDate() {
        return Date;
    }
}
