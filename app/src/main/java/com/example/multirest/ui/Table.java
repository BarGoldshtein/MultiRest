package com.example.multirest.ui;

import java.util.ArrayList;

public class Table {

    private String tableNume;
    private ArrayList <Order> orderList;
    private int bill;


    public  Table(String t){
        tableNume=t;
        orderList=new ArrayList<Order>();
        bill=0;
    }
    public double getBill() {

        return bill;
    }

    public int check(){
        for (Order o: orderList){
            bill +=o.getDish().getPrice();
        }
return bill;
    }







    public String getTableNume() {
        return tableNume;
    }

    public void setTableNume(String tableNume) {
        this.tableNume = tableNume;
    }

    public ArrayList<Order> getTable() {
        return orderList;
    }

    public void setTable(ArrayList<Order> table) {
        this.orderList = table;
    }
    public void add(Order o) {orderList.add(o);}
    }








