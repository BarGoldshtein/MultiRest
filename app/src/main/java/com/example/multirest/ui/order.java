package com.example.multirest.ui;

public class order {
    private String tableNumber;
    private Dish dish;
    private boolean isOpen;

    public order(){

    }


    public order(String t, Dish d){
        tableNumber=t;
        dish=new Dish(d);
        isOpen=true;
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
        return "order{" +
                "מספר שולחן='" + tableNumber + '\'' +
                ", מנה=" + dish.toString() +
                ", הזמנה פתוחה:=" + isOpen +
                '}';
    }

}
