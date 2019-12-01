package com.example.multirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ClientOptions extends AppCompatActivity {

    private Button signOut;
    private Button mButton;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_options);

        signOut= (Button) findViewById(R.id.sign_out);
        mButton=(Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();


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

    public void openMenu(){
        Intent intent=new Intent(this,menu.class);
        startActivity(intent);    }


}
