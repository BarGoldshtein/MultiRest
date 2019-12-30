package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class myOrders extends AppCompatActivity {
    ArrayList<Order> orders = new ArrayList<Order>();
    ListView MyList;
    ArrayAdapter<Order> adpter;
    String table;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    TextView bill;
double sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        table=ClientOptions.getTable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        bill=(TextView)findViewById(R.id.textView8);

        MyList=(ListView) findViewById(R.id.myOrders);
        adpter=new ArrayAdapter<Order>(this,android.R.layout.simple_list_item_1,orders);
        MyList.setAdapter(adpter);

        String tn="Table"+" "+table;
        Table t=new Table(tn);
        myRef.child("Order").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();
                adpter.notifyDataSetChanged();
                table=ClientOptions.getTable();
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();

                for (DataSnapshot child:children) {

                    Order o=new Order();
                    o=(Order)(child.getValue(Order.class));
                    if(o.isOpen()&&o.getTableNumber().equals(table)){
                        orders.add(child.getValue(Order.class));
                        sum+=child.getValue(Order.class).getPrice();                    }
                    adpter.notifyDataSetChanged();
                }
                String ans=Double.toString(sum);
                bill.setText(ans+"לתשלום");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        //Toast.makeText(this,ans,Toast.LENGTH_LONG).show();

    }
}
