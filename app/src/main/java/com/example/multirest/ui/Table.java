package com.example.multirest.ui;

import java.util.ArrayList;

public class Table {

    private String tableNume;
    private ArrayList <Order> table;

    public  Table(String t){
        tableNume=t;
        table=new ArrayList<Order>();
    }


    public String getTableNume() {
        return tableNume;
    }

    public void setTableNume(String tableNume) {
        this.tableNume = tableNume;
    }

    public ArrayList<Order> getTable() {
        return table;
    }

    public void setTable(ArrayList<Order> table) {
        this.table = table;
    }








}
