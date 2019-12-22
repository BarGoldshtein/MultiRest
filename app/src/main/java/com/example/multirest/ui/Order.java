package com.example.multirest.ui;

public class Order {
    private String id;
    private String tableNumber;
    private Dish dish;
    private boolean isOpen;

    public Order(){

    }


    public Order(String id,String t, Dish d){
        this.id=id;
        tableNumber=t;
        dish=new Dish(d);
        isOpen=true;
    }
    public Order(Order t){
        id=t.id;
        tableNumber=t.tableNumber;
        dish=new Dish(t.dish);
        isOpen=t.isOpen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }


    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }


    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String toString() {
        return
                "מספר שולחן:" +" "+ tableNumber  +" "+
                        ", מנה:" +" "+ dish.toString() +" "

                ;
    }
}
