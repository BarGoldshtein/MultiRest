package com.example.multirest.ui;

public class order {

    private String tableNumber;
    private Dish dish;
    private boolean isOpen;


    public order(String t, Dish d){
        tableNumber=t;
        dish=new Dish(d);
        isOpen=true;
    }
}
