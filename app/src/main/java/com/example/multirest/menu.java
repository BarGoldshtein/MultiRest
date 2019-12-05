package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class menu extends AppCompatActivity {
    ArrayList<Dish> dishes = new ArrayList<Dish>();
   private Button adDish1;
   private Button adDish2;
   private Button adDish3;
   private ArrayList<order> orders;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference();

    ArrayAdapter<Dish> d;
    ListView MyList;
    String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        orders=new ArrayList<order>();
        MyList =(ListView) findViewById(R.id.MyMenu);
        adDish1=(Button)findViewById(R.id.button5);
        adDish2=(Button)findViewById(R.id.button4);
        adDish3=(Button)findViewById(R.id.button7);
        d=new ArrayAdapter<Dish>(this, android.R.layout.simple_list_item_1,dishes);
        MyList.setAdapter(d);
        adDish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Dish di=d.getItem(0);
               order o=new order(table,di);
                myRef=FirebaseDatabase.getInstance().getReference();

              //  myRef.child("order").setValue(o);

            }
        });


        myRef.child("DISH").addValueEventListener(new ValueEventListener(){
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        table=ClientOptions.getTable();
        Iterable<DataSnapshot> children=dataSnapshot.getChildren();
        for (DataSnapshot child:children) {
           dishes.add(child.getValue(Dish.class));
          System.out.println("added!");
           d.notifyDataSetChanged();

       }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

  });

//        ArrayAdapter<Dish> d=new ArrayAdapter<Dish>(getActivity(), android.R.layout.simple_list_item_1,dishes);
//        setListAdapter(d);
    }




}



