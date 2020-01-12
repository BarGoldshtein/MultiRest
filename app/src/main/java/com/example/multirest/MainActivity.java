package com.example.multirest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.about_us){
            Toast.makeText(this, "about us" , Toast.LENGTH_LONG).show();

            Intent intent=new Intent(this,AboutUs.class);
            startActivity(intent);

        }


        if(item.getItemId() == R.id.terms){
            Toast.makeText(this, "terms" , Toast.LENGTH_LONG).show();

            Intent intent=new Intent(this,terms.class);
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
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
