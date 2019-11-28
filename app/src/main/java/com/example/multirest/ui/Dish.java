package com.example.multirest.ui;

public class Dish {
    private String name;
    private double price;

    public Dish(){

    }

    public Dish(String n,double p)
    {
        name=n;
        price =p;
    }

    public void setPrice(double p){
        price=p;

    }

    public double getPrice(){
        return price;
    }


    public void setName(String n){
        name=n;

    }

    public String getName(){
        return name;
    }




}
