package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.multirest.ui.Dish;
import com.example.multirest.ui.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class
OpenOrders extends AppCompatActivity  {
    private  ListView orders;
     LinkedList<Order>  myOrders=new LinkedList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ArrayAdapter<Order> adpt;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);


        orders = (ListView) findViewById(R.id.openO);
        adpt = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, myOrders);
        orders.setAdapter(adpt);
        adpt.notifyDataSetChanged();


        myRef.child("Order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myOrders.clear();
                adpt.notifyDataSetChanged();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();


                for (DataSnapshot child:children) {
                    Order o=new Order();
                    o=(Order)(child.getValue(Order.class));
                    if(o.isOpen())
                    myOrders.add(child.getValue(Order.class));
                    adpt.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


        orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order o=new Order();
                Order temp=adpt.getItem(position);
                o.setId(temp.getId());
                o.setOpen(false);
                o.setDish(new Dish(temp.getDish()));
                o.setTableNumber(temp.getTableNumber());
                myRef.child("Order").child(o.getId()).setValue(o);
               // myRef.child("Tables").child("Table "+o.getTableNumber()).child("orders").child(o.getId()).setValue(o);
                adpt.notifyDataSetChanged();
                Toast.makeText(parent.getContext(), "closed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
