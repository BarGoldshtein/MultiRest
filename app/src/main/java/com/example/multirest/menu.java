package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.multirest.ui.Dish;
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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
   ArrayAdapter<Dish> d;
     ListView MyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MyList =(ListView) findViewById(R.id.MyMenu);
        d=new ArrayAdapter<Dish>(this, android.R.layout.simple_list_item_1,dishes);
        MyList.setAdapter(d);

        myRef.child("DISH").addValueEventListener(new ValueEventListener(){
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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



