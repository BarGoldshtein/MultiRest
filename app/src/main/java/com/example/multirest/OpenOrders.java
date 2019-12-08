package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.multirest.ui.Dish;
import com.example.multirest.ui.order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OpenOrders extends AppCompatActivity {
    private  ListView orders;
    static LinkedList<order>  myOrders=new LinkedList<>();
    private ArrayList[] openorders = new ArrayList[6];
    ArrayAdapter<order> adptr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);
        orders = (ListView) findViewById(R.id.openO);
        adptr = new ArrayAdapter<order>(this, android.R.layout.simple_list_item_1,myOrders);
        orders.setAdapter(adptr);
        updateopenorders();
        adptr.notifyDataSetChanged();


    }


    private void updateopenorders() {
     //   LinkedList<order> help=(LinkedList<order>) menu.getOrders();
//        for (int i=0;i<help.size();i++){
//            myOrders.add(help.get(i));
//            adptr.notifyDataSetChanged();
//        }
        Dish d=new Dish();
        order o=new order("6",d);
        myOrders.add(o);
        adptr.notifyDataSetChanged();
        myOrders.add(o);
        adptr.notifyDataSetChanged();

        Queue<order> orders = menu.getOrders();
        for (int i = 0; i < openorders.length; i++) {
            openorders[i] = new ArrayList();
        }

        for (order a : orders) {

            openorders[Integer.parseInt(a.getTableNumber())].add(a);
        }
    }

}
