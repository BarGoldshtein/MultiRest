package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.multirest.ui.Dish;
import com.example.multirest.ui.Order;
import com.example.multirest.ui.Table;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myOrders extends AppCompatActivity {
    ArrayList<Order> orders = new ArrayList<Order>();
    ListView MyList;
    ArrayAdapter<Order> adpter;
    String table;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        table=ClientOptions.getTable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        MyList=(ListView) findViewById(R.id.myOrders);
        adpter=new ArrayAdapter<Order>(this,android.R.layout.simple_list_item_1,orders);
        MyList.setAdapter(adpter);


        myRef.child("Tables").child("Table" + " "+table).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();//!!!!!
                adpter.notifyDataSetChanged();
                table=ClientOptions.getTable();
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    orders.add(child.getValue(Order.class));
                    //System.out.println("added!");
                    adpter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



    }
}
