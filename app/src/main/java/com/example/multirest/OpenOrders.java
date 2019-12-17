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
import com.example.multirest.ui.order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OpenOrders extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private  ListView orders;
    private String text;
    private Button closeOrer;
    static LinkedList<order>  myOrders=new LinkedList<>();
    private ArrayList[] openorders = new ArrayList[6];
    ArrayAdapter<order> adptr;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_orders);

        closeOrer=findViewById(R.id.button);
        closeOrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int num=Integer.parseInt(text);
               adptr.remove(adptr.getItem(num-1));
              // DatabaseReference d=FirebaseDatabase.getInstance().getReference("order").child("");
            }
        });
        Spinner spinner =findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.orders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        orders = (ListView) findViewById(R.id.openO);
        adptr = new ArrayAdapter<order>(this, android.R.layout.simple_list_item_1, myOrders);
        orders.setAdapter(adptr);
        adptr.notifyDataSetChanged();


        myRef.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myOrders.clear(); adptr.notifyDataSetChanged();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    myOrders.add(child.getValue(order.class));
                    System.out.println("added!");
                    adptr.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
