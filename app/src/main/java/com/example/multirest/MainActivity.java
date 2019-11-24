package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

private Button buttonClient;
private Button buttonOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonClient = (Button) findViewById(R.id.bclient);


        buttonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
             openClient();

            }
        });


        buttonOwner = (Button) findViewById(R.id.bowner);
        buttonOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOwner();
            }
        });

    }
    public void openClient(){
            Intent intent=new Intent(this,Client.class);
            startActivity(intent);
    }
    public void openOwner(){
        Intent intent=new Intent(this,Owner.class);
        startActivity(intent);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.client_activity, menu);
//        return true;
//    }
}
