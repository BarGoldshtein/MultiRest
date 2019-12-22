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

public class OpenOrders extends AppCompatActivity  {
    private  ListView orders;
    static LinkedList<Order>  myOrders=new LinkedList<>();
    //ArrayAdapter<Order> adptr;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ArrayAdapter<Order> adpt;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

//        closeOrer=findViewById(R.id.button);
//        closeOrer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               int num=Integer.parseInt(text);
//               adptr.remove(adptr.getItem(num-1));
//              // DatabaseReference d=FirebaseDatabase.getInstance().getReference("order").child("");
//            }
//        });
       // Spinner spinner =findViewById(R.id.spinner2);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.orders, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);


        orders = (ListView) findViewById(R.id.openO);
        adpt = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, myOrders);
        orders.setAdapter(adpt);
        adpt.notifyDataSetChanged();


        myRef.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myOrders.clear();
                adpt.notifyDataSetChanged();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child:children) {
                    myOrders.add(child.getValue(Order.class));
                    adpt.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


//        orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                adptr.remove(  adptr.getItem( position )  );
//                adptr.notifyDataSetChanged();
//                Toast.makeText(parent.getContext(), "closed", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
