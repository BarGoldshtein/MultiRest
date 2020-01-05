package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.multirest.ui.Dish;
import com.example.multirest.ui.Order;
import com.example.multirest.ui.Table;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static android.app.PendingIntent.getActivity;
public class menu extends AppCompatActivity {
    ArrayList<Dish> dishes = new ArrayList<Dish>();
    private static Queue<Order> orders=new LinkedList<>();;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference myRef1 = database.getReference();
    DatabaseReference myRef2 = database.getReference();
    ArrayAdapter<Dish> d;
    ListView MyList;
    String table;
    Table t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        myRef1=FirebaseDatabase.getInstance().getReference().child("Order");
        myRef2=FirebaseDatabase.getInstance().getReference().child("Tables");
        t =new Table(table);
        t.setTableNume(table);
        MyList =(ListView) findViewById(R.id.MyMenu);
        d=new ArrayAdapter<Dish>(this, android.R.layout.simple_list_item_1,dishes);
        MyList.setAdapter(d);
        MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order o=new Order();
                o.setTableNumber(table);
                o.setOpen(true);
                o.setDish(d.getItem(position));
                String key=myRef1.push().getKey();
             o.setId(key);

                myRef1.child(key).setValue(o);
                orders.add(o);
                Toast.makeText(menu.this,"Order Accepted",Toast.LENGTH_LONG).show();
            }
        });
        myRef.child("DISH").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dishes.clear();//!!!!!
                d.notifyDataSetChanged();
                table = ClientOptions.getTable();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    dishes.add(child.getValue(Dish.class));
                    d.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public static Queue<Order> getOrders() {
        return orders;
    }

}