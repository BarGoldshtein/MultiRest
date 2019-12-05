package com.example.multirest.ui;

public class Dish {
    private String name;
    private String desc;
    private double price;


    public Dish(){
        name="";
        price=0;
        desc="";
    }
public Dish(Dish d){
   name=d.name;
   price=d.price;
   desc=d.desc;
}
    public Dish(String n,double p,String d)
    {
        name=n;
        price =p;
        desc=d;
    }

    public void setPrice(double p){
        price=p;

    }

    public String getDesc(){
        return desc;
    }

    public void setDesc(String d){
        desc=d;

    }

    public double getPrice(){
        return price;
    }


    public void setName(String n){
        name=n;

    }
public String toString(){
        String ans="name: "+name+", price: "+price+", description: "+desc;
        String ans1="שם המנה:"+" "+name+"."+" "+"מחיר:"+" "+price+"."+" "+"תיאור המנה:"+" "+desc+".";
        return ans1;
}
    public String getName(){
        return name;
    }




}
