package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerOptions extends AppCompatActivity {
    Button updateDishs;
    Button openOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_options);
        updateDishs=(Button) findViewById(R.id.button11);
        updateDishs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToAdd();
            }
        });
        openOrders=(Button) findViewById(R.id.button13);
        openOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToOpenAc();
            }
        });
    }
    public void moveToAdd(){
        Intent intent=new Intent(this, AddDish.class);
        startActivity(intent);
    }

    public void moveToOpenAc(){
        Intent intent=new Intent(this, OpenOrders.class);
        startActivity(intent);
    }
}
