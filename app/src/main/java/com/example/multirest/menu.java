
package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.multirest.ui.Dish;
import com.example.multirest.ui.order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Clock;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static android.app.PendingIntent.getActivity;

public class menu extends AppCompatActivity {
    ArrayList<Dish> dishes = new ArrayList<Dish>();
    private Button adDish1;
    private Button adDish2;
    private Button adDish3;
    private static Queue<order> orders=new LinkedList<>();;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();//get instance of firebase
    DatabaseReference myRef1 = database.getReference();
    ArrayAdapter<Dish> d;
    ListView MyList;
    String table;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        myRef1=FirebaseDatabase.getInstance().getReference().child("order");
        MyList =(ListView) findViewById(R.id.MyMenu);
        d=new ArrayAdapter<Dish>(this, android.R.layout.simple_list_item_1,dishes);
        MyList.setAdapter(d);
        MyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            //creating order and push it into firebase
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                order o=new order();
                o.setTableNumber(table);
                o.setOpen(true);
                String uid=myRef1.getKey();
                o.setId(uid);
                o.setDish(d.getItem(position));
                myRef1.push().setValue(o);
                orders.add(o);

                Toast.makeText(menu.this,"Order Accepted",Toast.LENGTH_LONG).show();
            }
        });


        myRef.child("DISH").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dishes.clear();//!!!!!
                d.notifyDataSetChanged();
                table=ClientOptions.getTable();
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    dishes.add(child.getValue(Dish.class));
                    d.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }
    public static Queue<order> getOrders() {
        return orders;
    }




}
