package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ClientOptions extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button signOut;
    private Button mButton;
    private Button OpenMenu;
    FirebaseAuth mAuth;
   private static String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);
        Spinner spinner =findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        signOut= (Button) findViewById(R.id.sign_out);
        mButton=(Button) findViewById(R.id.showMenu);
        mAuth = FirebaseAuth.getInstance();
        OpenMenu = (Button) findViewById(R.id.showMenu);
        OpenMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mAuth.signOut();//get signed out
                goToStart();
            }

        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });



    }

    private void goToStart() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);



    }

    public static String getTable(){
        return text;
    }

    public void openMenu(){
        Intent intent=new Intent(this,menu.class);
        startActivity(intent);    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
