package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OwnerOptions extends AppCompatActivity {
    Button updateDishs;
    Button openOrders;
    Button sendNot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_options);
        updateDishs=(Button) findViewById(R.id.button11);
        sendNot=(Button) findViewById(R.id.button);
        sendNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSend();


            }
        });
        updateDishs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openDialog();

                //moveToAdd();
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

    public void openDialog(){
MyDialog dialog=new MyDialog();
dialog.show(getSupportFragmentManager(),"MyDialog");
    }
    public void moveToAdd(){

        Intent intent=new Intent(this, AddDish.class);
        startActivity(intent);
    }

    public void moveToOpenAc(){
        Intent intent=new Intent(this, OpenOrders.class);
        startActivity(intent);
    }
    public void openSend(){
        Intent intent=new Intent(this, SendNot.class);
        startActivity(intent);
    }
}
